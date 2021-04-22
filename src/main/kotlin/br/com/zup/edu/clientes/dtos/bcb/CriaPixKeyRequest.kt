package br.com.zup.edu.clientes.dtos.bcb

import br.com.zup.edu.TipoChave
import br.com.zup.edu.TipoConta

class CriaPixKeyRequest(
    val keyType: TipoChave,
    val key: String,
    val bankAcount: BankAccount,
    val owner: OwnerRequest
) {



    data class BankAccount(
        val participant : String,
        val branch: String,
        val accountNumber: String,
        val accountType: String
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


    data class OwnerRequest(
        val type:EnumType,
        val name :String,
        val taxIdNumber : String
    ){
        enum class EnumType {
            NATURAL_PERSON,LEGAL_PERSON

        }
    }

}