package com.elementary.cryptolink

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.elementary.cryptolink.fragments.get_link.GetLinkFragment

class AddLinkDialog(_context: Context, getLinkFragment: GetLinkFragment) : Dialog(_context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_add_link)


    }

}