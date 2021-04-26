package br.com.zup.edu.carrega

import br.com.zup.edu.clientes.dtos.bcb.ClientesNoBcb
import br.com.zup.edu.repositorios.ChavePixRepository
import io.micronaut.core.annotation.Introspected
import java.lang.IllegalArgumentException
import java.util.*

sealed class Filtro {

    abstract fun filtra(repository: ChavePixRepository, bcbClient: ClientesNoBcb): ChavePixInfo

    @Introspected
    data class PorPixID(
        val clienteId: String,
        val pixId: String
    ) : Filtro() {
        private val clienteUUID = UUID.fromString(clienteId)
        private val pixUUID = UUID.fromString(pixId)


        override fun filtra(repository: ChavePixRepository, bcbClient: ClientesNoBcb): ChavePixInfo {
            return repository.findById(pixUUID)
                .filter { it.pertence(clienteUUID) }
                .map(ChavePixInfo::of).orElseThrow { Exception("Chave pix não foi encontrada no nosso sistema") }


        }

    }


    @Introspected
    data class PorChave(
        val chave: String
    ) : Filtro() {

        override fun filtra(repository: ChavePixRepository, bcbClient: ClientesNoBcb): ChavePixInfo {
            return repository.findByChave(chave).map(ChavePixInfo::of).orElseGet {
                val response = bcbClient.buscaChaveBcb(chave)

                when (response.statusCode()) {
                    200 -> response!!.body().toModel()
                    else -> throw Exception("A chave pix não foi encontrada no nosso sistema")
                }
            }
        }
    }


    @Introspected
    class Invalido:Filtro(){
        override fun filtra(repository: ChavePixRepository, bcbClient: ClientesNoBcb): ChavePixInfo {
            throw IllegalArgumentException("Chave pix invalida ou não encontrada")
        }
    }


}
