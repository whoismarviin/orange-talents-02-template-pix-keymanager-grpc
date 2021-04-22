package br.com.zup.edu.repositorios

import br.com.zup.edu.registra.entidades.ChavePix
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository
import java.util.*

@Repository
interface ChavePixRepository :JpaRepository<ChavePix,UUID> {
    fun findByChave(chave:String):Optional<ChavePix>
    fun findByClienteIdAndChave(chave: UUID,clienteId :UUID): Optional<ChavePix>
}
