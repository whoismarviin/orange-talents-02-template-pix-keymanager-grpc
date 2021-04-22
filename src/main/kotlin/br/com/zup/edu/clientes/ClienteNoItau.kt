package br.com.zup.edu.clientes

import br.com.zup.edu.clientes.dtos.ClienteResponse
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.QueryValue
import io.micronaut.http.client.annotation.Client

@Client("http://localhost:9091/api/v1")
interface ClienteNoItau {
    @Get("clientes/{id}/contas?=tipo={tipo}")
    fun registraCliente(@PathVariable clienteId :String, @QueryValue tipo: String):HttpResponse<ClienteResponse>

}
