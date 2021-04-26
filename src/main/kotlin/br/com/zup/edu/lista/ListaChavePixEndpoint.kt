package br.com.zup.edu.lista

import br.com.zup.edu.*
import br.com.zup.edu.repositorios.ChavePixRepository
import com.google.protobuf.Timestamp
import io.grpc.stub.StreamObserver
import java.time.ZoneId
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class ListaChavePixEndpoint(@Inject val chaveRepository: ChavePixRepository) :
    PixListaChavesPorClienteGrpc.PixListaChavesPorClienteImplBase() {

    override fun lista(request: ListaChavePixRequest, responseObserver: StreamObserver<ListaChavesResponse>) {

        if (request.clienteId.isNotBlank()) {
            throw IllegalArgumentException("É necessario informar o clienteId que deseja buscar")

        }

        val clienteId = UUID.fromString(request.clienteId)


        val chaves = chaveRepository.findAllByClienteId(clienteId).map {
            ListaChavesResponse.ChavePix.newBuilder()
                .setChave(it.chave)
                .setPixID(it.id.toString())
                .setTipo(TipoChave.valueOf(it.tipoChave.name))
                .setTipoDeConta(TipoConta.valueOf(it.tipoConta.name))
                .setCriadaEm(it.criadaEm.let {
                    val criadaEm = it.atZone(ZoneId.of("UTC")).toInstant()
                    Timestamp.newBuilder()
                        .setSeconds(criadaEm.epochSecond)
                        .setNanos(criadaEm.nano)
                        .build()
                })

                .build()
        }

        val resposta: ListaChavesResponse = ListaChavesResponse.newBuilder()
            .addAllChaves(chaves)
            .setClienteId(clienteId.toString())
            .build()


        responseObserver.onNext(resposta)

    }
}