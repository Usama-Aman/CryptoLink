package com.elementary.cryptolink.fragments.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.elementary.cryptolink.R
import com.elementary.cryptolink.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var mBinding: FragmentHomeBinding
    private lateinit var mViewModel: HomeFragmentViewModel
    private lateinit var mContext: Context

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mContext = context!!
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        mViewModel = ViewModelProviders.of(this).get(HomeFragmentViewModel::class.java)
        mViewModel.init(this)
        mBinding.viewModel = mViewModel

        mBinding.executePendingBindings()
        mBinding.lifecycleOwner = this

        return mBinding.root
    }


}