package com.elementary.cryptolink.fragments.my_links

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.elementary.cryptolink.R

class MyLinksAdapter(_mViewModel: MyLinkFragmentViewModel) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var context: Context
    private  var mViewModel = _mViewModel

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent.context
        return Item(LayoutInflater.from(parent.context).inflate(R.layout.item_get_link, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is Item) {
            holder.bind(position)
        }
    }

    override fun getItemCount(): Int = 6

    inner class Item(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var tvCoinName: TextView = itemView.findViewById(R.id.tvCoinName)
        private var btnGetLink: TextView = itemView.findViewById(R.id.btnGetLink)
        private var tvLink: TextView = itemView.findViewById(R.id.tvLink)
        private var ivCoinImage: ImageView = itemView.findViewById(R.id.ivCoinImage)
        private var btnDeleteLink: ImageView = itemView.findViewById(R.id.btnDeleteLink)

        fun bind(position: Int) {

            btnGetLink.visibility = View.GONE
            tvLink.visibility = View.GONE
            btnDeleteLink.visibility = View.VISIBLE


            btnDeleteLink.setOnClickListener {
                mViewModel.deleteLink(position)
            }

        }

    }
}