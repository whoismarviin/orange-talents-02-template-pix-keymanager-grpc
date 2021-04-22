package br.com.zup.edu.clientes.dtos

import java.util.*
import javax.persistence.Embeddable
import javax.persistence.Embedded

@Embeddable
class ContaAssociada(
    val nome: String,
    @Embedded
    val instituicao: Instituicao,
    @Embedded
    val titular: Titular,
    val documento: String,
    val agencia: String,
    val numero: String
){


    @Embeddable
    class Instituicao(
        var nome: String,
        var ispb: String
    ){

    }

    @Embeddable
     class Titular(
        var id: UUID,
        var nome: String,
        var cpf: String
    ){

     }

}