package br.com.zup.edu.clientes.dtos.bcb

import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.http.client.annotation.Client
import java.net.http.HttpResponse


@Client("http://localhost:8082/api/v1")
interface ClientesNoBcb {
    @Post("/keys")
    fun criaChaveBcb(@Body request: CriaPixKeyRequest): HttpResponse<CriaPixKeyResponse>

    @Delete("/keys/{key}")
    fun deletaChaveBcb(@Body request: DeletaChavePixRequest) : HttpResponse<DeletaChavePixResponse>


    @Get("/keys/{key}")
    fun buscaChaveBcb(chave: String): HttpResponse<DetalhesPixResponse>



}