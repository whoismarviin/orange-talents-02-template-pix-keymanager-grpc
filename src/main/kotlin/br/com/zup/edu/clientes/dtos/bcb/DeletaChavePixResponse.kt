package br.com.zup.edu.clientes.dtos.bcb

import java.time.LocalDateTime

class DeletaChavePixResponse(
    val key: String,
    val participant:String,
    createdAt: LocalDateTime
) {

     val createdAt =LocalDateTime.now()


}
