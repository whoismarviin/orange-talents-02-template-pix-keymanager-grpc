package br.com.zup.edu.registra.entidades

import br.com.zup.edu.TipoChave
import br.com.zup.edu.TipoConta
import br.com.zup.edu.clientes.dtos.ContaAssociada
import java.time.LocalDateTime
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class ChavePix(
    val pixId:UUID,
    val clienteId: UUID,
    val tipoChave: TipoChave,
    val chave: String,
    val tipoConta: TipoConta,
    val conta: ContaAssociada,
    val criadaEm: LocalDateTime
) {

    @Id
    var id: Long = TODO("initialize me")


    fun pertence(clienteId: UUID)= this.clienteId.equals(clienteId)
}
