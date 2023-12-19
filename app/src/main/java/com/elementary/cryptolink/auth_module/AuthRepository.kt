package com.elementary.cryptolink.auth_module

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.elementary.cryptolink.network.Api
import com.elementary.cryptolink.network.RetrofitClient

class AuthRepository(_context: Context) {

    private var context = _context
    private var api: Api = RetrofitClient.getClient(context).create(Api::class.java)
    private var isLoading: MutableLiveData<Boolean> = MutableLiveData()

    fun getIsLoading(): LiveData<Boolean> = isLoading

    fun loginUser() {

    }

}