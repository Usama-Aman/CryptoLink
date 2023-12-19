package com.elementary.cryptolink.fragments.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import com.elementary.cryptolink.R
import com.elementary.cryptolink.fragments.get_link.GetLinkFragment
import com.elementary.cryptolink.utils.Constants

class HomeFragmentViewModel : ViewModel() {

    private lateinit var homeFragment: HomeFragment

    fun init(homeFragment: HomeFragment) {
        this.homeFragment = homeFragment
    }

    fun onBtnClicked(view: View) {

        when (view.id) {

            R.id.btnGetLink -> {
                val fragment = GetLinkFragment()
                val bundle = Bundle()
                bundle.putBoolean("isAddLink", false)
                fragment.arguments = bundle
                homeFragment.activity?.supportFragmentManager
                    ?.beginTransaction()
                    ?.replace(R.id.fragmentContainer, fragment, Constants.GetLinkFragment)
                    ?.addToBackStack(Constants.GetLinkFragment)
                    ?.commit()
            }
            R.id.btnAddLink -> {
                val fragment = GetLinkFragment()
                val bundle = Bundle()
                bundle.putBoolean("isAddLink", true)
                fragment.arguments = bundle
                homeFragment.activity?.supportFragmentManager
                    ?.beginTransaction()
                    ?.replace(R.id.fragmentContainer, fragment, Constants.GetLinkFragment)
                    ?.addToBackStack(Constants.GetLinkFragment)
                    ?.commit()
            }
        }

    }

}