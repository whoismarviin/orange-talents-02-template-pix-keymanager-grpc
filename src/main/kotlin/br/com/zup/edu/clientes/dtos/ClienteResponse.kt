package br.com.zup.edu.clientes.dtos

class ClienteResponse(
    val nome: String,
    val instituicao: ContaAssociada.Instituicao,
    val titular: ContaAssociada.Titular,
    val documento: String,
    val agencia: String,
    val numero: String,
) {

    fun toModel(): ContaAssociada {
        return ContaAssociada(
            nome= this.nome,
            instituicao= this.instituicao,
            titular= this.titular,
            documento= this.documento,
            agencia= this.agencia,
            numero= this.numero
        )

    }


}
