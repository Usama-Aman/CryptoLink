package com.elementary.cryptolink.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.elementary.cryptolink.R
import com.elementary.cryptolink.databinding.FragmentWebviewBinding

class WebViewFragment : Fragment() {

    private lateinit var v: View
    private lateinit var mBinding: FragmentWebviewBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_webview, container, false)

        mBinding.executePendingBindings()
        mBinding.lifecycleOwner = this

        initViews()

        return mBinding.root
    }

    private fun initViews() {
        mBinding.ivBack.setOnClickListener {
            activity!!.supportFragmentManager.popBackStack()
        }


    }


}