package br.com.exemplo.caronasapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.exemplo.caronasapp.databinding.RowItemBinding
import br.com.exemplo.caronasapp.network.Carona

class MyAdapter: RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    private var myList = emptyList<Carona>()

    inner class MyViewHolder(val binding: RowItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder( RowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false) )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.txtCidOrigem.text = myList[position].nmcidorigem
        holder.binding.txtCidDestino.text = myList[position].nmciddestino
        holder.binding.txtHrSaida.text = myList[position].hrsaida.substring(0, 5)
        holder.binding.txtHrRetorno.text = myList[position].hrretorno.substring(0, 5)
        holder.binding.txtTelefone.text = myList[position].telefone
    }

    override fun getItemCount(): Int {
        return myList.size
    }

    fun setData( newList: List<Carona> ) {
        myList = newList
        notifyDataSetChanged()
    }
}