package com.elementary.cryptolink.auth_module

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProviders
import com.elementary.cryptolink.R
import com.elementary.cryptolink.databinding.ActivityAuthBinding
import com.elementary.cryptolink.utils.AppUtils


class AuthActivity : AppCompatActivity(), Auth {


    private lateinit var mContext: Context
    lateinit var mBinding: ActivityAuthBinding
    private lateinit var mViewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
//        window.setFlags(
//            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
//            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
//        )

        mBinding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mViewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)
        mViewModel.init(this)
        mBinding.viewModel = mViewModel
        mBinding.authModules = this

        mBinding.executePendingBindings()
        mBinding.lifecycleOwner = this

        observeViewModels()
        initViews()
    }

    private fun setWindowFlag(bits: Int, on: Boolean) {
        val win = window
        val winParams = win.attributes
        if (on) {
            winParams.flags = winParams.flags or bits
        } else {
            winParams.flags = winParams.flags and bits.inv()
        }
        win.attributes = winParams
    }

    private fun observeViewModels() {
        mViewModel.isLoading().observe(this, {

        })
    }

    private fun initViews() {

    }

    override fun onAuthClick(view: View) {
        AppUtils.hideKeyboard(this)

        when (view.id) {

            R.id.authLogin -> {
                mBinding.authLogin.setTextColor(ContextCompat.getColor(mContext, R.color.white))
                mBinding.authLogin.setBackgroundColor(ContextCompat.getColor(mContext, R.color.appColor))
                mBinding.authSignUp.setTextColor(ContextCompat.getColor(mContext, R.color.textGrey))
                mBinding.authSignUp.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white))

                mBinding.layoutLogin.visibility = View.VISIBLE
                mBinding.layoutSignUp.visibility = View.GONE
                mBinding.layoutForgotPassword.visibility = View.GONE
            }
            R.id.authSignUp -> {
                mBinding.authSignUp.setTextColor(ContextCompat.getColor(mContext, R.color.white))
                mBinding.authSignUp.setBackgroundColor(ContextCompat.getColor(mContext, R.color.appColor))
                mBinding.authLogin.setTextColor(ContextCompat.getColor(mContext, R.color.textGrey))
                mBinding.authLogin.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white))

                mBinding.layoutSignUp.visibility = View.VISIBLE
                mBinding.layoutLogin.visibility = View.GONE
                mBinding.layoutForgotPassword.visibility = View.GONE
            }
            R.id.btnForgotPassword -> {
                mBinding.authSignUp.setTextColor(ContextCompat.getColor(mContext, R.color.textGrey))
                mBinding.authSignUp.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white))
                mBinding.authLogin.setTextColor(ContextCompat.getColor(mContext, R.color.textGrey))
                mBinding.authLogin.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white))

                mBinding.layoutForgotPassword.visibility = View.VISIBLE
                mBinding.layoutSignUp.visibility = View.GONE
                mBinding.layoutLogin.visibility = View.GONE
            }
            R.id.btnBackToLogin -> {
                mBinding.authLogin.callOnClick()
            }

        }
    }


}
