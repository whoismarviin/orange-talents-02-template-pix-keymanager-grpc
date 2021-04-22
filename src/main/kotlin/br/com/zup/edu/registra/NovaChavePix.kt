package br.com.zup.edu.registra

import br.com.zup.edu.TipoChave
import br.com.zup.edu.TipoConta
import br.com.zup.edu.clientes.dtos.ContaAssociada
import br.com.zup.edu.registra.entidades.ChavePix
import java.util.*

class NovaChavePix(
    val clienteId: UUID,
    val tipoChave: TipoChave,
    val chave: String,
    val tipoConta: TipoConta
) {
    fun toModel(conta: ContaAssociada): ChavePix {
        return ChavePix(
            clienteId = this.clienteId,
            tipoChave = this.tipoChave,
            chave = this.chave,
            tipoConta = this.tipoConta,
            conta
        )


    }


}
