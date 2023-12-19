package com.elementary.cryptolink.fragments.get_link

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.elementary.cryptolink.R

class GetLinksAdapter(_isAddLink: Boolean, _getLinkFragment: GetLinkFragment) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var context: Context
    private var isAddLink = _isAddLink
    private var getLinkFragment = _getLinkFragment

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent.context
        return Item(LayoutInflater.from(parent.context).inflate(R.layout.item_get_link, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is Item) {
            holder.bind(position)
        }
    }

    override fun getItemCount(): Int = 16

    inner class Item(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var tvCoinName: TextView = itemView.findViewById(R.id.tvCoinName)
        private var btnGetLink: TextView = itemView.findViewById(R.id.btnGetLink)
        private var tvLink: TextView = itemView.findViewById(R.id.tvLink)
        private var ivCoinImage: ImageView = itemView.findViewById(R.id.ivCoinImage)
        private var btnDeleteLink: ImageView = itemView.findViewById(R.id.btnDeleteLink)
        private var layoutLink: ConstraintLayout = itemView.findViewById(R.id.layoutLink)

        fun bind(position: Int) {

            if (isAddLink) {
                btnDeleteLink.visibility = View.GONE
                btnGetLink.visibility = View.GONE
                tvLink.visibility = View.GONE

                layoutLink.setOnClickListener {
                    getLinkFragment.showDialog()
                }
            } else {
                btnDeleteLink.visibility = View.GONE
                btnGetLink.visibility = View.VISIBLE
                tvLink.visibility = View.GONE

                btnGetLink.setOnClickListener {
                    btnGetLink.visibility = View.GONE
                    tvLink.visibility = View.VISIBLE
                }

            }


        }

    }
}