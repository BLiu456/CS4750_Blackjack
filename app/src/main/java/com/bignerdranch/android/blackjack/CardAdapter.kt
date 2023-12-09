package com.bignerdranch.android.blackjack

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import java.lang.reflect.Type

class PlayerAdapter(private val cardList: List<Card>): RecyclerView.Adapter<PlayerAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        lateinit var card_front: ImageView

        init {
            card_front = view.findViewById(R.id.cardView)
        }
    }

    override fun getItemCount(): Int {
        return cardList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.card_front.setImageResource(cardList[position].front)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_item, parent, false)
        return ViewHolder(view)
    }
}

class DealerAdapter(private val cardList: List<Card>): RecyclerView.Adapter<DealerAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        lateinit var card_front: ImageView

        init {
            card_front = view.findViewById(R.id.cardView)
        }
    }

    override fun getItemCount(): Int {
        return cardList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        if (position == 0 && !cardList[0].flipped) {
            holder.card_front.setImageResource(R.drawable.card_back_red)
            return
        }
        holder.card_front.setImageResource(cardList[position].front)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_item, parent, false)
        return ViewHolder(view)
    }
}