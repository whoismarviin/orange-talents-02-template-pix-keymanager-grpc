package br.com.zup.edu.registra

import br.com.zup.edu.clientes.ClienteNoItau
import br.com.zup.edu.registra.entidades.ChavePix
import br.com.zup.edu.repositorios.ChavePixRepository
import java.lang.IllegalStateException
import javax.inject.Inject

class NovaChavePixService(@Inject private val itauCliente: ClienteNoItau,
                          @Inject private val repository: ChavePixRepository
) {

    fun registra(novaChave : NovaChavePix): ChavePix{

        //1. Verificar se existe dentro do nosso repositorio.
        if (repository.findByChave(novaChave.chave).isPresent){
            throw Exception("Ja temos essa chave dentro do nosso repositorio")
        }

        //Colocar dentro do meu cliente no itau
        val response = itauCliente.registraCliente(novaChave.chave.toString(),novaChave.tipoChave.toString())
        val conta = response.body().toModel() ?: throw IllegalStateException("Cliente não possui conta no itau")

        //3. Grava dentro do banco de dados
        val chave = novaChave.toModel(conta)
        repository.save(chave)

        return chave


    }
}
