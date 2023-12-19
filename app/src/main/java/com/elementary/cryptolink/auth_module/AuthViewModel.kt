package com.elementary.cryptolink.auth_module

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.elementary.cryptolink.main.MainActivity
import com.elementary.cryptolink.utils.AppUtils
import com.elementary.cryptolink.utils.AppUtils.isEmailValid

class AuthViewModel : ViewModel() {

    var userEmail: String = ""
    var userPassword: String = ""

    @SuppressLint("StaticFieldLeak")
    lateinit var activity: AuthActivity
    private lateinit var mRepository: AuthRepository

    private var signUpName: String = ""
    private var signUpEmail: String = ""
    private var signUpPassword: String = ""
    private var signUpConfirmPassword: String = ""

    private var loginEmail: String = ""
    private var loginPassword: String = ""

    private var forgotPasswordEmail: String = ""


    fun init(activity: AuthActivity) {
        this.activity = activity
        mRepository = AuthRepository(activity)
    }

    fun isLoading() = mRepository.getIsLoading()

    val signUpNameWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            signUpName = s.toString()
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }
    }
    val signUpEmailWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            signUpEmail = s.toString()
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }
    }
    val signUpPasswordWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            signUpPassword = s.toString()
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }
    }
    val signUpConfirmPasswordWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            signUpConfirmPassword = s.toString()
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }
    }

    val loginEmailWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            loginEmail = s.toString()
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }
    }
    val loginPasswordWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            loginPassword = s.toString()
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }
    }

    val forgotPasswordWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            forgotPasswordEmail = s.toString()
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }
    }


    fun hitLoginApi(view: View) {
        AppUtils.preventDoubleClick(view)
        AppUtils.hideKeyboard(activity)

//        if (loginEmail.isBlank()) {
//                AppUtils.showToast(activity, "Email is required", false)
//            return
//        }
//        if (!loginEmail.isEmailValid()) {
//            AppUtils.showToast(activity, "Email is invalid", false)
//            return
//        }
//
//        if (loginPassword.isBlank()) {
//            AppUtils.showToast(activity, "Password is required", false)
//            return
//        }
//        Toast.makeText(activity, "User login successfully", Toast.LENGTH_SHORT).show()

        activity.startActivity(Intent(activity, MainActivity::class.java))
        activity.finish()
    }

    fun hitSignUpApi(view: View) {
        AppUtils.preventDoubleClick(view)
        AppUtils.hideKeyboard(activity)

        if (signUpName.isBlank()) {
            AppUtils.showToast(activity, "Name is required", false)
            return
        }
        if (signUpEmail.isBlank()) {
            AppUtils.showToast(activity, "Email is required", false)
            return
        }
        if (!signUpEmail.isEmailValid()) {
            AppUtils.showToast(activity, "Email is invalid", false)
            return
        }

        if (signUpPassword.isBlank()) {
            AppUtils.showToast(activity, "Password is required", false)
            return
        }

        if (signUpConfirmPassword.isBlank()) {
            AppUtils.showToast(activity, "Confirm Password is required", false)
            return
        }

        if (signUpPassword != signUpConfirmPassword) {
            AppUtils.showToast(activity, "Password & Confirm Password does not match", false)
            return
        }

    }

    fun hitForgotPassword(view: View) {
        AppUtils.preventDoubleClick(view)
        AppUtils.hideKeyboard(activity)

        if (forgotPasswordEmail.isBlank()) {
            AppUtils.showToast(activity, "Email is required", false)
            return
        }
        if (!forgotPasswordEmail.isEmailValid()) {
            AppUtils.showToast(activity, "Email is invalid", false)
            return
        }

        Toast.makeText(activity, "Password changed successfully", Toast.LENGTH_SHORT).show()
        activity.mBinding.authLogin.callOnClick()
    }


}