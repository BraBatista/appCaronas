package br.com.exemplo.caronasapp.network

data class Carona(
    val idcarona: Int?,
    val idcidorigem: Int,
    val nmcidorigem: String?,
    val idciddestino: Int,
    val nmciddestino: String?,
    val vagas: Int,
    val telefone: String,
    val hrsaida: String,
    val hrretorno: String
)