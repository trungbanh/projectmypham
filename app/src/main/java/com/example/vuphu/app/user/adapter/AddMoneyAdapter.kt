package com.example.vuphu.app.user.adapter

import android.content.Context
import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast

import com.example.vuphu.app.R
import com.example.vuphu.app.`object`.HistoryDeposit
import com.example.vuphu.app.`object`.addMoney
import com.example.vuphu.app.`object`.order
import com.example.vuphu.app.user.adapter.AddMoneyAdapter.addViewHolder

import java.util.ArrayList

/**
 * Created by vuphu on 4/1/2018.
 */

class AddMoneyAdapter {


    class addViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var date: TextView
        internal var number: TextView
        internal var status: TextView

        init {
            date = itemView.findViewById(R.id.tv_user_add_date)
            number = itemView.findViewById(R.id.tv_user_add_price)
            status = itemView.findViewById(R.id.tv_user_add_status)
        }
    }

    class orderAdap(internal var list: List<HistoryDeposit>, internal var context: Context) : RecyclerView.Adapter<addViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): addViewHolder {
            val v = LayoutInflater.from(context).inflate(R.layout.item_user_add_money, parent, false)



            return addViewHolder(v)
        }

        override fun onBindViewHolder(holder: addViewHolder, position: Int) {
            holder.status.text = list[position].id
            holder.number.text = list[position].numberDeposit!!.toString()
            holder.date.text = list[position].createAt

        }

        override fun getItemCount(): Int {
            return list.size
        }


    }
}
