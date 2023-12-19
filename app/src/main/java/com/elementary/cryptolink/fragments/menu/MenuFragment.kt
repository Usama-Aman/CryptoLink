package com.elementary.cryptolink.fragments.menu

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.elementary.cryptolink.R
import com.elementary.cryptolink.databinding.FragmentHomeBinding
import com.elementary.cryptolink.databinding.FragmentMenuBinding
import com.elementary.cryptolink.fragments.home.HomeFragmentViewModel

class MenuFragment : Fragment() {

    private lateinit var mBinding: FragmentMenuBinding
    private lateinit var mViewModel: MenuFragmentViewModel
    private lateinit var mContext: Context

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mContext = context!!
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_menu, container, false)

        mViewModel = ViewModelProviders.of(this).get(MenuFragmentViewModel::class.java)
        mViewModel.init(this)
        mBinding.viewModel = mViewModel

        mBinding.executePendingBindings()
        mBinding.lifecycleOwner = this

        initViews()

        return mBinding.root
    }

    private fun initViews() {
        Glide.with(mContext).load(R.drawable.test).into(mBinding.ivProfileImage)
    }


}