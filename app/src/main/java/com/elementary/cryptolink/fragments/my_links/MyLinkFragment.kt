package com.elementary.cryptolink.fragments.my_links

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.elementary.cryptolink.R
import com.elementary.cryptolink.databinding.FragmentMyLinksBinding

class MyLinkFragment : Fragment() {

    private lateinit var mBinding: FragmentMyLinksBinding
    private lateinit var mViewModel: MyLinkFragmentViewModel
    private lateinit var mContext: Context
    private lateinit var myLinksAdapter: MyLinksAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mContext = context!!
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_links, container, false)

        mViewModel = ViewModelProviders.of(this).get(MyLinkFragmentViewModel::class.java)
        mViewModel.init(this)
        mBinding.viewModel = mViewModel

        mBinding.executePendingBindings()
        mBinding.lifecycleOwner = this

        setAdapter()

        return mBinding.root
    }

    private fun setAdapter() {
        myLinksAdapter = MyLinksAdapter(mViewModel)
        mBinding.getLinkRecyclerView.layoutManager = LinearLayoutManager(mContext)
        mBinding.getLinkRecyclerView.adapter = myLinksAdapter
    }


}