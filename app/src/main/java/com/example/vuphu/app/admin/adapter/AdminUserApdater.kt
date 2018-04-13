package com.example.vuphu.app.admin.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.example.vuphu.app.R
import com.example.vuphu.app.`object`.AcountId

import java.util.ArrayList

import de.hdodenhof.circleimageview.CircleImageView

/**
 * Created by vuphu on 3/31/2018.
 */

class AdminUserApdater {


    class userViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val img_user: CircleImageView
        val tv_name: TextView

        init {
            img_user = itemView.findViewById(R.id.img_admin_user)
            tv_name = itemView.findViewById(R.id.tv_admin_user_name)


        }
    }

    class userAdap(internal var list: ArrayList<AcountId>, internal var context: Context) : RecyclerView.Adapter<userViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): userViewHolder {
            val v = LayoutInflater.from(context).inflate(R.layout.admin_item_user, null)
            return userViewHolder(v)
        }

        override fun onBindViewHolder(holder: userViewHolder, position: Int) {
            holder.tv_name.text = list[position].name

            holder.itemView.setOnClickListener {

            }
        }

        override fun getItemCount(): Int {
            return list.size
        }
    }


}
