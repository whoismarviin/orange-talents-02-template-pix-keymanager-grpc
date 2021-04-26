package br.com.zup.edu.carrega

import br.com.zup.edu.*
import br.com.zup.edu.clientes.dtos.bcb.ClientesNoBcb
import br.com.zup.edu.repositorios.ChavePixRepository
import com.google.protobuf.Timestamp
import io.grpc.stub.StreamObserver
import java.time.ZoneId
import javax.inject.Inject
import javax.inject.Singleton
import javax.xml.validation.Validator


@Singleton
class CarregaChavePixEndpoint(
    @Inject private val repository: ChavePixRepository,
    @Inject private val bcbClient: ClientesNoBcb,
    @Inject private val validator: Validator
) : CarregaChavePixServiceGrpc.CarregaChavePixServiceImplBase() {

    override fun consulta(
        request: CarregaChavePixRequest?,
        responseObserver: StreamObserver<CarregaChavePixResponse>?
    ) {


        val filtro = request!!.toModel(validator)

        val chavePixInfo: ChavePixInfo = filtro.filtra(repository, bcbClient)


        val resposta = CarregaChavePixResponse.newBuilder()
            .setClienteId(chavePixInfo.clienteId?.toString())
            .setPixId(chavePixInfo.pixId?.toString() ?: "")
            .setChave(CarregaChavePixResponse.ChavePix.newBuilder()
                .setTipo(TipoChave.valueOf(chavePixInfo.tipo.name))
                .setCriadaEm(
                    chavePixInfo.registradaEm.let {
                        val criadaEm = it.atZone(ZoneId.of("UTC")).toInstant()
                        Timestamp.newBuilder()
                            .setNanos(criadaEm.nano)
                            .setSeconds(criadaEm.epochSecond)
                            .build()
                    }
                ).setConta(
                    CarregaChavePixResponse.ChavePix.ContaInfo.newBuilder()
                        .setTipo(TipoConta.valueOf(chavePixInfo.tipoDeConta.name))
                        .setInstituicao(chavePixInfo.conta.instituicao.toString())
                        .setAgencia(chavePixInfo.conta.agencia)
                        .setNomeDoTitular(chavePixInfo.conta.nome)
                        .setCpfDoTitular(chavePixInfo.conta.documento)
                        .setNumeroDaConta(chavePixInfo.conta.numero)
                        .build()
                )

                .build())
            .build()

        responseObserver!!.onNext(resposta)
        responseObserver?.onCompleted()

    }
}