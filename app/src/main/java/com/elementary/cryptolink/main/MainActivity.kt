package com.elementary.cryptolink.main

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.elementary.cryptolink.R
import com.elementary.cryptolink.databinding.ActivityMainBinding
import com.elementary.cryptolink.fragments.home.HomeFragment
import com.elementary.cryptolink.fragments.menu.MenuFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mContext: Context
    private lateinit var currentFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        initTabs()
        changeTab(0)
    }

    private fun initTabs() {

        val view = LayoutInflater.from(mContext).inflate(R.layout.item_tab_layout, null)
        view.findViewById<ImageView>(R.id.tabIcon).setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_home))
        view?.findViewById<ImageView>(R.id.tabIcon)
            ?.setColorFilter(ContextCompat.getColor(mContext, R.color.appColor))

        val view1 = LayoutInflater.from(mContext).inflate(R.layout.item_tab_layout, null)
        view1.findViewById<ImageView>(R.id.tabIcon).setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_home))
        view1.findViewById<ImageView>(R.id.tabIcon).visibility = View.INVISIBLE
        view1.findViewById<ImageView>(R.id.tabIcon).isEnabled = false
        view1.findViewById<ImageView>(R.id.tabIcon).isClickable = false
        view1?.findViewById<ImageView>(R.id.tabIcon)
            ?.setColorFilter(ContextCompat.getColor(mContext, R.color.textGrey))

        val view3 = LayoutInflater.from(mContext).inflate(R.layout.item_tab_layout, null)
        view3.findViewById<ImageView>(R.id.tabIcon).setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_home))
        view3.findViewById<ImageView>(R.id.tabIcon).visibility = View.INVISIBLE
        view3.findViewById<ImageView>(R.id.tabIcon).isEnabled = false
        view3.findViewById<ImageView>(R.id.tabIcon).isClickable = false
        view3?.findViewById<ImageView>(R.id.tabIcon)
            ?.setColorFilter(ContextCompat.getColor(mContext, R.color.textGrey))

        val view2 = LayoutInflater.from(mContext).inflate(R.layout.item_tab_layout, null)
        view2.findViewById<ImageView>(R.id.tabIcon).setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_edit_profile))
        view2?.findViewById<ImageView>(R.id.tabIcon)
            ?.setColorFilter(ContextCompat.getColor(mContext, R.color.textGrey))

        val tab = mBinding.tabLayout.newTab().setCustomView(view)
        val tab1 = mBinding.tabLayout.newTab().setCustomView(view1)
        val tab3 = mBinding.tabLayout.newTab().setCustomView(view3)
        val tab2 = mBinding.tabLayout.newTab().setCustomView(view2)


        mBinding.tabLayout.addTab(tab)
        mBinding.tabLayout.addTab(tab1)
        mBinding.tabLayout.addTab(tab3)
        mBinding.tabLayout.addTab(tab2)

        mBinding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {

                var position = tab?.position

                if (position == 0 || position == 1 || position == 2) {
                    mBinding.tabLayout.getTabAt(0)?.customView?.findViewById<ImageView>(R.id.tabIcon)
                        ?.setColorFilter(ContextCompat.getColor(mContext, R.color.appColor))
                    position = 0
                } else {

                    val v = tab?.customView
                    v?.findViewById<ImageView>(R.id.tabIcon)
                        ?.setColorFilter(ContextCompat.getColor(mContext, R.color.appColor))
                }

                changeTab(position!!)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                var position = tab?.position

                if (position == 0 || position == 1 || position == 2) {
                    mBinding.tabLayout.getTabAt(0)?.customView?.findViewById<ImageView>(R.id.tabIcon)
                        ?.setColorFilter(ContextCompat.getColor(mContext, R.color.textGrey))
                } else {
                    val v = tab?.customView
                    v?.findViewById<ImageView>(R.id.tabIcon)
                        ?.setColorFilter(ContextCompat.getColor(mContext, R.color.textGrey))
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
    }

    private fun changeTab(position: Int) {
        when (position) {
            0 -> {
                supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)

                if (::currentFragment.isInitialized) {
                    if (currentFragment !is HomeFragment) {
                        launchFragment(HomeFragment())
                    }
                } else
                    launchFragment(HomeFragment())

                currentFragment = HomeFragment()
            }
            3 -> {
                supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)

                if (::currentFragment.isInitialized) {
                    if (currentFragment !is MenuFragment) {
                        launchFragment(MenuFragment())
                    }
                } else
                    launchFragment(MenuFragment())

                currentFragment = MenuFragment()
            }
        }
    }


    private fun launchFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment, "").commit()
    }

}