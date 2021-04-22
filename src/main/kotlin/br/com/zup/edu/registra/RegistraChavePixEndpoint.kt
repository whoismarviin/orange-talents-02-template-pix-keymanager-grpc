package br.com.zup.edu.registra

import br.com.zup.edu.CriaChavePixServiceGrpc
import br.com.zup.edu.RegistraChavePixRequest
import br.com.zup.edu.RegistraChavePixResponse
import br.com.zup.edu.registra.extension.toModel
import io.grpc.stub.StreamObserver
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RegistraChavePixEndpoint(@Inject private val service: NovaChavePixService) :
    CriaChavePixServiceGrpc.CriaChavePixServiceImplBase() {
    override fun registra(
        request: RegistraChavePixRequest?,
        responseObserver: StreamObserver<RegistraChavePixResponse>?
    ) {
        val chaveASerRegistrada = request?.toModel();
        val chaveCriada = service.registra(chaveASerRegistrada!!)

        responseObserver?.onNext(
            RegistraChavePixResponse.newBuilder()
                .setClienteId(chaveCriada.clienteId.toString()).build()
        )

    }
}