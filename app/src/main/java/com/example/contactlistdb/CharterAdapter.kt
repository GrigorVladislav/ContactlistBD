package com.example.contactlistdb

import android.content.Context
import android.location.GpsStatus
import android.net.sip.SipAudioCall
import android.net.sip.SipSession
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.charter_layout.view.*
import okhttp3.internal.http2.Http2Connection

class CharterAdapter(val items : ArrayList<Charters>, val context: Context):
    RecyclerView.Adapter<CharterAdapter.ViewHolder>() {

    private lateinit var listener : Listener
    interface Listener {
        fun onClick(position: Int)
    }
    fun setListener( listener:Listener){
        this.listener = listener
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.charter_layout,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)  {
        holder.charterName.text = items.get(position).Name
        holder.charterRule.text = items.get(position).Role
        holder.charterIcon.setImageResource(items.get(position).ImageId ?: R.drawable.hollow)

        holder.itemView.setOnClickListener {
        listener.onClick(position)
        }
        holder.itemView.setOnCreateContextMenuListener { menu, v, menuInfo ->
            menu.add(0,position,0,"Details")
            menu.add(1,position,0,"Edit")
            menu.add(2,position,0,"Delete")
        }


    }
    override fun getItemCount(): Int {
        return items.size
    }


    class ViewHolder(view:View):RecyclerView.ViewHolder(view){

        val charterName = itemView.charter_name
        val charterRule = itemView.charter_rule
        val charterIcon = itemView.charter_icon

    }
}