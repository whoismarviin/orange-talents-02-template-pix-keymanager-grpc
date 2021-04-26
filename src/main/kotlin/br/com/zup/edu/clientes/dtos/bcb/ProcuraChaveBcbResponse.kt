package br.com.zup.edu.clientes.dtos.bcb

import br.com.zup.edu.TipoChave
import br.com.zup.edu.TipoConta
import br.com.zup.edu.carrega.ChavePixInfo
import br.com.zup.edu.clientes.dtos.ContaAssociada
import java.time.LocalDateTime
import java.util.*

class ProcuraChaveBcbResponse(
    val keyType: TipoChave,
    val key: String,
    val bankAccount: CriaPixKeyRequest.BankAccount,
    val owner: CriaPixKeyRequest.OwnerRequest,
    val createdAt: LocalDateTime
) {

    fun toModel(): ChavePixInfo {
        return ChavePixInfo(
            chave = this.key, tipo = keyType, tipoDeConta = when (this.bankAccount.accountType) {
                CriaPixKeyRequest.AccountType.CAAC -> TipoConta.CONTA_CORRENTE
                CriaPixKeyRequest.AccountType.SVGS -> TipoConta.CONTA_POUPANCA
                else -> TipoConta.UNRECOGNIZED
            }, conta = ContaAssociada(
                instituicao = ContaAssociada.Instituicao(nome = bankAccount.participant, ispb = bankAccount.branch),
                titular = ContaAssociada.Titular(
                    id = UUID.fromString(owner.taxIdNumber),
                    nome = owner.name,
                    cpf = owner.taxIdNumber
                ),
                documento = owner.taxIdNumber,
                nome = owner.name,
                agencia = bankAccount.branch,
                numero = bankAccount.accountNumber
            )
        )
    }

}
