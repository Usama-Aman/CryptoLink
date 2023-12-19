package com.elementary.cryptolink.fragments.change_password

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.elementary.cryptolink.R
import com.elementary.cryptolink.databinding.FragmentChangePasswordBinding
import com.elementary.cryptolink.databinding.FragmentHomeBinding
import com.elementary.cryptolink.fragments.home.HomeFragmentViewModel

class ChangePasswordFragment: Fragment() {

    private lateinit var mBinding: FragmentChangePasswordBinding
    private lateinit var mViewModel: ChangePasswordFragmentViewModel
    private lateinit var mContext: Context

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mContext = context!!
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_change_password, container, false)

        mViewModel = ViewModelProviders.of(this).get(ChangePasswordFragmentViewModel::class.java)
        mViewModel.init(this)
        mBinding.viewModel = mViewModel

        mBinding.executePendingBindings()
        mBinding.lifecycleOwner = this

        return mBinding.root
    }


}