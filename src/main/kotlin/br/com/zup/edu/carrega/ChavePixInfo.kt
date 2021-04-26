package br.com.zup.edu.carrega

import br.com.zup.edu.TipoChave
import br.com.zup.edu.TipoConta
import br.com.zup.edu.clientes.dtos.ContaAssociada
import br.com.zup.edu.registra.entidades.ChavePix
import java.time.LocalDateTime
import java.util.*

data class ChavePixInfo(
    val pixId: UUID? = null,
    val clienteId: UUID? = null,
    val tipo: TipoChave,
    val chave: String,
    val tipoDeConta: TipoConta,
    val conta: ContaAssociada,
    val registradaEm: LocalDateTime = LocalDateTime.now()
) {

    companion object {

        fun of(chave: ChavePix): ChavePixInfo {

            return ChavePixInfo(
                pixId = chave.pixId,
                clienteId = chave.clienteId,
                tipo = chave.tipoChave,
                chave = chave.chave,
                conta = chave.conta,
                tipoDeConta = chave.tipoConta,
                registradaEm = chave.criadaEm
            )

        }
    }

}