package br.com.zup.edu.remove

import br.com.zup.edu.RemoveChavePixRequest
import br.com.zup.edu.RemoveChavePixResponse
import br.com.zup.edu.RemoveChavePixServiceGrpc
import io.grpc.stub.StreamObserver
import javax.inject.Inject

class RemoveChavePixEndpoint(@Inject val service: RemoveChavePixService) :
    RemoveChavePixServiceGrpc.RemoveChavePixServiceImplBase() {
    override fun remove(
        request: RemoveChavePixRequest?,
        responseObserver: StreamObserver<RemoveChavePixResponse>
    ) {

        service.remove(request!!.clientId, request.pixId)

        responseObserver.onNext(
            RemoveChavePixResponse.newBuilder()
                .setClientId(request.clientId)
                .setPixId(request.pixId)
                .build()
        )

        responseObserver.onCompleted()

    }
}