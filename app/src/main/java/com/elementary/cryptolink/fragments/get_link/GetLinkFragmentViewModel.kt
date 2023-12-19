package com.elementary.cryptolink.fragments.get_link

import android.view.View
import androidx.lifecycle.ViewModel
import com.elementary.cryptolink.R

class GetLinkFragmentViewModel : ViewModel() {

    private lateinit var getLinkFragment: GetLinkFragment

    fun init(getLinkFragment: GetLinkFragment) {
        this.getLinkFragment = getLinkFragment
    }

    fun onBtnClicked(view: View) {

        when (view.id) {

            R.id.btnGetLink -> {

            }
            R.id.btnAddLink -> {

            }
            R.id.ivBack -> {
                getLinkFragment.activity?.supportFragmentManager?.popBackStack()
            }

        }

    }

}