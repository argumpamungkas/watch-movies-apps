package com.argumpamungkas.moviesapps.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.argumpamungkas.moviesapps.ui.detail.overview.OverviewFragment
import com.argumpamungkas.moviesapps.ui.detail.videos.VideosFragment

class SectionPagerAdapter(activity:AppCompatActivity): FragmentStateAdapter(activity) {
    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position){
            0 -> fragment = OverviewFragment()
            1 -> fragment = VideosFragment()
        }
        return fragment as Fragment
    }
}