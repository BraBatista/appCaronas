package br.com.exemplo.caronasapp.network

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*
import java.sql.Time

interface ApiService {
    //@Headers("Authorization: Bearer 83a491b463e98197b7bfb7dc3694f3")
    @GET( "api/caronas" )
    suspend fun getAll() : Response<List<Carona>>

    @GET( "api/caronas/cidades/{idCidadeOrigem}/cidades/{idCidadeDestino}")
    suspend fun getCaronasByCidades( @Path("idCidadeOrigem") idOrigem : Int, @Path("idCidadeDestino") idDestino : Int ) : Response<List<Carona>>

    /*@POST( "api/caronas" )
    suspend fun salvarCarona( @Body minhaCarona : Carona ) : Response<String>
    */

    @FormUrlEncoded
    @POST("api/caronas")
    suspend fun salvarCarona(
        @Field("idusuario") myIdUsuario: Int,
        @Field("idcidorigem") myIdcidorigem: Int,
        @Field("idciddestino") myIdciddestino: Int,
        @Field("vagas") vagas : Int,
        @Field("telefone") myTelefone : String,
        @Field("hrsaida") myHrSaida : Time,
        @Field("hrretorno") myHrRetorno : Time
    ) : Response<String>
}