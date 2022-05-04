package br.com.exemplo.caronasapp.viewModel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.exemplo.caronasapp.network.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.HttpException
import java.io.IOException
import java.sql.Time
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class RetrofitViewModel: ViewModel() {

    private val _response = MutableLiveData<List<Carona>?>()
    val response: LiveData<List<Carona>?>
        get() = _response

    private val _responsePost = MutableLiveData<String?>()
    val responsePost: LiveData<String?>
        get() = _responsePost

    private val _cidOrigem = MutableLiveData<Int>()
    val cidOrigem: LiveData<Int>
        get() = _cidOrigem

    private val _cidDestino = MutableLiveData<Int>()
    val cidDestino: LiveData<Int>
        get() = _cidDestino
        //set(value) {}

    init {
        //getAllCaronas()
        _response.value = null
        _cidOrigem.value = 0
        _cidDestino.value = 0
        _responsePost.value = null
    }

    private fun getAllCaronas(): Job = viewModelScope.launch {
        try {
            _response.value = RetrofitInstance.retrofit.getAll().body()
        }
        catch( e: IOException ) {
            Log.i("MYTAG", "IOException - Sem internet, URL incorreto, etc")
            _response.value = null
        }
        catch( e: HttpException ) {
            Log.i("MYTAG", "HttpException - Status Codes não iniciados com 2xx ")
            _response.value = null
        }
        catch( e: Exception ) {
            Log.i("MYTAG", "Exception Genérica: "+e.message)
            _response.value = null
        }
    }

    private fun getCaronas(idOrigem: Int, idDestino: Int): Job = viewModelScope.launch {
        try {
            _response.value = RetrofitInstance.retrofit.getCaronasByCidades( idOrigem, idDestino ).body()        }
        catch( e: IOException) {
            Log.i("MYTAG4", "IOException - Sem internet, URL incorreto, etc")
            _response.value = null
        }
        catch( e: HttpException) {
            Log.i("MYTAG4", "HttpException - Status Codes não iniciados com 2xx ")
            _response.value = null
        }
        catch( e: Exception ) {
            Log.i("MYTAG4", "Exception Genérica: "+e.message)
            _response.value = null
        }
    }

    //public fun postCarona(novaCarona: Carona): Job = viewModelScope.launch {
    public fun postCarona(idcidorigem: Int, idciddestino: Int, vagas: Int, telefone: String, hrSaida: Time, hrRetorno: Time): Job = viewModelScope.launch {
        try {
            //val h1 = LocalTime.parse(novaCarona.hrsaida)
            //val formatter = DateTimeFormatter.ISO_TIME;
            //val h1 = LocalDate.parse(novaCarona.hrsaida, DateTimeFormatter.ISO_TIME);
            //val h2 = LocalDate.parse(novaCarona.hrretorno, DateTimeFormatter.ISO_TIME);

           // _responsePost.value = RetrofitInstance.retrofit.salvarCarona( 2,novaCarona.idcidorigem, novaCarona.idciddestino, novaCarona.vagas, novaCarona.telefone, h1, h1 ).body()
           // _responsePost.value = RetrofitInstance.retrofit.salvarCarona( 2, idcidorigem, idciddestino, vagas, telefone, hrSaida, hrRetorno ).body()
        }
        catch( e: IOException ) {
            Log.i("MYTAG", "IOException - Sem internet, URL incorreto, etc")
            _response.value = null
        }
        catch( e: HttpException ) {
            Log.i("MYTAG", "HttpException - Status Codes não iniciados com 2xx ")
            _response.value = null
        }
        catch( e: Exception ) {
            Log.i("MYTAG", "Exception Genérica: "+e.message)
            _response.value = null
        }
    }

    public fun setCidades( c1: String, c2: String ) {
        _cidOrigem.value = c1.toInt()
        _cidDestino.value = c2.toInt()
        getCaronas(c1.toInt(), c2.toInt())
    }

    /*
    public fun newCarona( newCarona: Carona ) {
        postCarona( newCarona )
    }
     */

    override fun onCleared() {
        super.onCleared()
    }

}