package br.com.zup.edu.registra

import br.com.zup.edu.clientes.ClienteNoItau
import br.com.zup.edu.clientes.dtos.bcb.ClientesNoBcb
import br.com.zup.edu.clientes.dtos.bcb.CriaPixKeyRequest
import br.com.zup.edu.registra.entidades.ChavePix
import br.com.zup.edu.repositorios.ChavePixRepository
import javax.inject.Inject

class NovaChavePixService(
    @Inject private val itauCliente: ClienteNoItau,
    @Inject private val bcbClientesNoBcb: ClientesNoBcb,
    @Inject private val repository: ChavePixRepository
) {

    fun registra(novaChave: NovaChavePix): ChavePix {

        //1. Verificar se existe dentro do nosso repositorio.
        if (repository.findByChave(novaChave.chave).isPresent) {
            throw Exception("Ja temos essa chave dentro do nosso repositorio")
        }

        //2.Colocar dentro do meu cliente no itau
        val response = itauCliente.registraCliente(novaChave.chave.toString(), novaChave.tipoChave.toString())
        val conta = response.body().toModel() ?: throw IllegalStateException("Cliente não possui conta no itau")


        //3. Grava dentro do banco de dados
        val chave = novaChave.toModel(conta)


        val clienteNoItau =
            itauCliente.registraCliente(chave.clienteId.toString(), tipo = chave.tipoChave.toString())!!.body()


        //4. Coloca dentro do bc
        val bcbRequest = bcbClientesNoBcb.criaChaveBcb(
            CriaPixKeyRequest(
                keyType = chave.tipoChave,
                key = chave.chave,
                bankAcount = CriaPixKeyRequest.BankAccount(
                    participant = clienteNoItau.nome,
                    branch = clienteNoItau.agencia,
                    accountNumber = chave.conta.numero,
                    accountType = CriaPixKeyRequest.AccountType.by(chave.tipoConta),
                ),
                owner = CriaPixKeyRequest.OwnerRequest(
                    type = CriaPixKeyRequest.OwnerRequest.EnumType.LEGAL_PERSON,
                    name = clienteNoItau.nome,
                    taxIdNumber = novaChave.chave.random().toString()
                )

            )
        )

        repository.save(chave)





        return chave


    }
}
