package br.com.zup.edu.remove

import br.com.zup.edu.repositorios.ChavePixRepository
import io.micronaut.validation.Validated
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton
import javax.validation.constraints.NotBlank


@Singleton
@Validated
class RemoveChavePixService(@Inject val repositorio: ChavePixRepository) {

    fun remove(@NotBlank clienteId: String, @NotBlank pixId: String) {

        val uuidCliente = UUID.fromString(clienteId)
        val uuidPixID = UUID.fromString(pixId)


        repositorio.findByClienteIdAndChave(uuidCliente, uuidPixID)
            .orElseThrow({ Exception("Não foi encontrado nenhum cliente para essas informações") })

        repositorio.deleteById(uuidPixID)

    }

}
