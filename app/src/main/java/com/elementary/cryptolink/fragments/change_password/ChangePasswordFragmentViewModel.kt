package com.elementary.cryptolink.fragments.change_password

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.lifecycle.ViewModel
import com.elementary.cryptolink.R
import com.elementary.cryptolink.utils.AppUtils

class ChangePasswordFragmentViewModel : ViewModel() {

    private lateinit var changePasswordFragment: ChangePasswordFragment
    private var oldPassword = ""
    private var newPassword = ""
    private var confirmNewPassword = ""

    fun init(changePasswordFragment: ChangePasswordFragment) {
        this.changePasswordFragment = changePasswordFragment
    }

    val oldPasswordWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            oldPassword = s.toString()
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }
    }

    val newPasswordWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            newPassword = s.toString()
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }
    }

    val confirmNewPasswordWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            confirmNewPassword = s.toString()
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }
    }

    fun onBtnClicked(view: View) {
        AppUtils.preventDoubleClick(view)
        when (view.id) {

            R.id.ivBack -> {
                changePasswordFragment.activity?.supportFragmentManager?.popBackStack()
            }


        }
    }


}