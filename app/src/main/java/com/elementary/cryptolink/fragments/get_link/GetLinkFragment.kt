package com.elementary.cryptolink.fragments.get_link

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.elementary.cryptolink.AddLinkDialog
import com.elementary.cryptolink.R
import com.elementary.cryptolink.databinding.FragmentGetLinksBinding

class GetLinkFragment : Fragment() {

    private lateinit var mBinding: FragmentGetLinksBinding
    private lateinit var mViewModel: GetLinkFragmentViewModel
    private lateinit var mContext: Context
    private lateinit var getLinksAdapter: GetLinksAdapter

    private var isAddLink = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mContext = context!!
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_get_links, container, false)

        isAddLink = arguments?.getBoolean("isAddLink", false)!!

        mViewModel = ViewModelProviders.of(this).get(GetLinkFragmentViewModel::class.java)
        mViewModel.init(this)
        mBinding.viewModel = mViewModel

        mBinding.executePendingBindings()
        mBinding.lifecycleOwner = this

        setAdapter()

        return mBinding.root
    }

    private fun setAdapter() {
        getLinksAdapter = GetLinksAdapter(isAddLink, this)
        mBinding.getLinkRecyclerView.layoutManager = LinearLayoutManager(mContext)
        mBinding.getLinkRecyclerView.adapter = getLinksAdapter
    }

    fun showDialog() {
        val addLinkDialog = AddLinkDialog(mContext, this)
        addLinkDialog.setOwnerActivity(activity!!)
        addLinkDialog.show()
    }


}