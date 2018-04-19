package com.example.vuphu.app.user.adapter

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.example.vuphu.app.R

/**
 * Created by vuphu on 3/10/2018.
 */

class ViewPagerAdapter(private val context: Context) : PagerAdapter() {
    private var layoutInflater: LayoutInflater? = null
    private val text = arrayOf("Bước 1", "Bước 2", "Bước 3")

    override fun getCount(): Int {

        return text.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater!!.inflate(R.layout.item_walkthrough, null)
        //val img = view.findViewById<ImageView>(R.id.img_viewpager)
        val tv = view.findViewById<TextView>(R.id.text_viewwpager)
        tv.text = text[position]
        val viewPager = container as ViewPager
        viewPager.addView(view, 0)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val viewPager = container as ViewPager

        val view = `object` as View
        viewPager.removeView(view)
    }
}
