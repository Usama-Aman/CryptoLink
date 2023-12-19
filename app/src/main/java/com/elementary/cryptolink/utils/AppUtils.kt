package com.elementary.cryptolink.utils

import android.app.Activity
import android.content.Context
import android.graphics.Typeface
import android.net.ConnectivityManager
import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import com.elementary.cryptolink.R
import com.tapadoo.alerter.Alerter

object AppUtils {

    fun preventDoubleClick(v: View) {
        v.isEnabled = false
        Handler(Looper.getMainLooper()).postDelayed({
            v.isEnabled = true
        }, 100)
    }

    fun showToast(activity: Activity, message: String, isSuccess: Boolean) {
        try {
            val regularTypeFace = Typeface.createFromAsset(activity.assets, "fonts/regular_questrial.otf")
            val regularTypeFaceTitle = Typeface.createFromAsset(activity.assets, "fonts/regular_questrial.otf")

            if (isSuccess) {
                Alerter.create(activity)
                    .setTextTypeface(regularTypeFace)
                    .setTitleTypeface(regularTypeFaceTitle)
                    .setTitle("Success")
                    .setText(message)
                    .setBackgroundColorInt(ContextCompat.getColor(activity, R.color.green))
                    .setIcon(R.drawable.ic_success)
                    .setIconColorFilter(0)
                    .setDuration(1800)
                    .show()

            } else {

                Alerter.create(activity)
                    .setTextTypeface(regularTypeFace)
                    .setTitleTypeface(regularTypeFaceTitle)
                    .setTitle("Error")
                    .setText(message)
                    .setBackgroundColorInt(ContextCompat.getColor(activity, R.color.red))
                    .setIcon(R.drawable.ic_error)
                    .setIconColorFilter(0)
                    .setDuration(1800)
                    .show()

            }


        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }


    }

    fun isNetworkAvailable(context: Context): Boolean {
        val cm = context
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return (netInfo != null && netInfo.isConnectedOrConnecting
                && cm.activeNetworkInfo!!.isAvailable
                && cm.activeNetworkInfo!!.isConnected)
    }

    fun String.isEmailValid(): Boolean {
        return !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
    }

    fun hideKeyboard(activity: Activity) {
        val imm: InputMethodManager = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }


}