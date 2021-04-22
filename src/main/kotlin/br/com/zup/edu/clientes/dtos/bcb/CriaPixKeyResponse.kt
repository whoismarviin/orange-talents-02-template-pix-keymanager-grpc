package br.com.zup.edu.clientes.dtos.bcb

import br.com.zup.edu.TipoChave
import br.com.zup.edu.TipoConta
import java.time.LocalDateTime
import javax.persistence.GeneratedValue


class CriaPixKeyResponse(
    val keyType: TipoChave,
    val key: String,
    val bankAccount: BankAccountResponse,
    val owner: OwnerResponse,

) {

    @GeneratedValue
    val createdEvent = LocalDateTime.now();



    data class BankAccountResponse(
        val participant : String,
        val branch: String,
        val accountNumber: String,
        val accountType: AccountType
    )

    enum class AccountType {
        NOT_FOUND,SVGS,CAAC;

        companion object {
            fun by(domainType: TipoConta): AccountType {
                return when (domainType) {
                    TipoConta.CONTA_CORRENTE -> CAAC
                    TipoConta.CONTA_POUPANCA -> SVGS
                    else -> NOT_FOUND
                }
            }
        }
    }

    data class OwnerResponse(
        val type:EnumType,
        val name :String,
        val taxIdNumber : String
    ){
        enum class EnumType {
            NATURAL_PERSON,LEGAL_PERSON

        }
    }
}
