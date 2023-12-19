package com.elementary.cryptolink.fragments.my_links

import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModel
import com.elementary.cryptolink.R
import com.elementary.cryptolink.fragments.get_link.GetLinkFragment
import com.google.android.material.bottomsheet.BottomSheetDialog

class MyLinkFragmentViewModel : ViewModel() {

    private lateinit var myLinkFragment: MyLinkFragment

    fun init(myLinkFragment: MyLinkFragment) {
        this.myLinkFragment = myLinkFragment
    }

    fun onBtnClicked(view: View) {

        when (view.id) {
            R.id.ivBack -> {
                myLinkFragment.activity?.supportFragmentManager?.popBackStack()
            }
        }

    }

    fun deleteLink(position: Int) {
        showBottomSheet()
    }

    private fun showBottomSheet() {
        val mBottomSheetDialog = BottomSheetDialog(myLinkFragment.context!!)
        val sheetView: View = myLinkFragment.activity!!.layoutInflater.inflate(R.layout.logout_bottom_sheet, null)
        mBottomSheetDialog.setContentView(sheetView)
        mBottomSheetDialog.setCancelable(true)
        mBottomSheetDialog.setCanceledOnTouchOutside(true)
        (sheetView.parent as View).setBackgroundColor(myLinkFragment.context!!.resources.getColor(android.R.color.transparent))
        val tvTitle = sheetView.findViewById<View>(R.id.tvTitle) as TextView
        tvTitle.text = "Are you sure you want to delete?"

        val tvLogOut = sheetView.findViewById<View>(R.id.tvLogOut) as TextView
        val btnCancel = sheetView.findViewById<View>(R.id.tvCancel) as TextView
        tvLogOut.setOnClickListener(View.OnClickListener {
            mBottomSheetDialog.dismiss()
            /*TODO HIT API*/
        })

        btnCancel.setOnClickListener { mBottomSheetDialog.dismiss() }
        mBottomSheetDialog.show()
    }


}