package br.com.zup.edu.carrega

import br.com.zup.edu.CarregaChavePixRequest
import javax.xml.validation.Validator

fun CarregaChavePixRequest.toModel(validator: Validator): Filtro {

    val filtro = when (filtroCase!!) {
        CarregaChavePixRequest.FiltroCase.PIXID -> pixId.let {
            Filtro.PorPixID(clienteId = it.clientId, pixId = it.clientId)
        }
        CarregaChavePixRequest.FiltroCase.CHAVE -> Filtro.PorChave(chave)
        CarregaChavePixRequest.FiltroCase.FILTRO_NOT_SET -> Filtro.Invalido()

    }

    return filtro

}