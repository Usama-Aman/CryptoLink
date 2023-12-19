package com.elementary.cryptolink.fragments.menu

import android.content.Intent
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModel
import com.elementary.cryptolink.R
import com.elementary.cryptolink.fragments.WebViewFragment
import com.elementary.cryptolink.fragments.change_password.ChangePasswordFragment
import com.elementary.cryptolink.fragments.my_links.MyLinkFragment
import com.elementary.cryptolink.fragments.profile.ProfileFragment
import com.elementary.cryptolink.utils.AppUtils
import com.elementary.cryptolink.utils.Constants
import com.google.android.material.bottomsheet.BottomSheetDialog

class MenuFragmentViewModel : ViewModel() {


    private lateinit var menuFragment: MenuFragment

    fun init(menuFragment: MenuFragment) {
        this.menuFragment = menuFragment
    }

    fun onBtnClicked(view: View) {
        AppUtils.preventDoubleClick(view)

        when (view.id) {
            R.id.tvEditProfile -> {
                menuFragment.activity?.supportFragmentManager
                    ?.beginTransaction()
                    ?.replace(R.id.fragmentContainer, ProfileFragment(), Constants.ProfileFragment)
                    ?.addToBackStack(Constants.ProfileFragment)
                    ?.commit()
            }
            R.id.tvMyLinks -> {
                menuFragment.activity?.supportFragmentManager
                    ?.beginTransaction()
                    ?.replace(R.id.fragmentContainer, MyLinkFragment(), Constants.MyLinkFragment)
                    ?.addToBackStack(Constants.MyLinkFragment)
                    ?.commit()
            }
            R.id.tvSocialMedia -> {

                val shareIntent = Intent()
                shareIntent.action = Intent.ACTION_SEND
                shareIntent.type = "text/plain"
                shareIntent.putExtra(Intent.EXTRA_TEXT, "Testing");
                menuFragment.startActivity(Intent.createChooser(shareIntent, "Select an option"))


            }
            R.id.tvChangePassword -> {
                menuFragment.activity?.supportFragmentManager
                    ?.beginTransaction()
                    ?.replace(R.id.fragmentContainer, ChangePasswordFragment(), Constants.ChangePasswordFragment)
                    ?.addToBackStack(Constants.ChangePasswordFragment)
                    ?.commit()
            }
            R.id.tvAboutApp -> {
                menuFragment.activity?.supportFragmentManager
                    ?.beginTransaction()
                    ?.replace(R.id.fragmentContainer, WebViewFragment(), Constants.AboutApp)
                    ?.addToBackStack(Constants.AboutApp)
                    ?.commit()
            }
            R.id.tvSignOut -> {
                showBottomSheet()
            }
        }

    }

    private fun showBottomSheet() {
        val mBottomSheetDialog = BottomSheetDialog(menuFragment.context!!)
        val sheetView: View = menuFragment.activity!!.layoutInflater.inflate(R.layout.logout_bottom_sheet, null)
        mBottomSheetDialog.setContentView(sheetView)
        mBottomSheetDialog.setCancelable(true)
        mBottomSheetDialog.setCanceledOnTouchOutside(true)
        (sheetView.parent as View).setBackgroundColor(menuFragment.context!!.resources.getColor(android.R.color.transparent))
        val tvTitle = sheetView.findViewById<View>(R.id.tvTitle) as TextView
        tvTitle.text = "Are you sure you want to logout?"

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