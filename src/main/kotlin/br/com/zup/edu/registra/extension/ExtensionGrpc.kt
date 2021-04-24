package br.com.zup.edu.registra.extension;

import br.com.zup.edu.RegistraChavePixRequest
import br.com.zup.edu.TipoChave
import br.com.zup.edu.TipoConta
import br.com.zup.edu.clientes.dtos.bcb.CriaPixKeyRequest
import br.com.zup.edu.registra.NovaChavePix
import br.com.zup.edu.registra.entidades.ChavePix
import java.util.*

fun RegistraChavePixRequest.toModel(): NovaChavePix {
    return NovaChavePix(
        clienteId = UUID.fromString(clienteId),
        tipoChave = TipoChave.valueOf(tipoChave.name),
        chave = chave,
        tipoConta = TipoConta.valueOf(tipoConta.name)

    )
}


fun ChavePix.toBcb(
    bankAccount: CriaPixKeyRequest.BankAccount,
    owner: CriaPixKeyRequest.OwnerRequest
): CriaPixKeyRequest {
    return CriaPixKeyRequest(
        keyType = tipoChave,
        key = chave,
        bankAcount = bankAccount,
        owner = owner

    )
}