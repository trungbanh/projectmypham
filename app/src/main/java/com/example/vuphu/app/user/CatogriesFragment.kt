package com.example.vuphu.app.user

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.vuphu.app.AcsynHttp.AsyncHttpApi
import com.example.vuphu.app.ItemOffsetDecoration
import com.example.vuphu.app.R
import com.example.vuphu.app.`object`.Product
import com.example.vuphu.app.user.adapter.ProductApdater
import com.example.vuphu.app.user.adapter.ViewPagerAdapter
import com.google.gson.Gson
import com.loopj.android.http.JsonHttpResponseHandler

import org.json.JSONArray
import org.json.JSONException

import java.util.ArrayList

import cz.msebera.android.httpclient.Header


class CatogriesFragment : Fragment() {


    private var viewPager: ViewPager? = null
    private var list_product: RecyclerView? = null
    private var product: ArrayList<Product>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_catogries, container, false)

        list_product = v.findViewById(R.id.list_product)
        list_product!!.setHasFixedSize(true)
        val gridLayoutManager = GridLayoutManager(context, 2)
        list_product!!.layoutManager = gridLayoutManager

        product = ArrayList()
        viewPager = v.findViewById(R.id.viewPager)

        loafProduct()

        list_product!!.isNestedScrollingEnabled = false
        val itemDecoration = ItemOffsetDecoration(context!!, R.dimen.item_offset)
        list_product!!.addItemDecoration(itemDecoration)


        return v
    }


    fun loafProduct() {
        AsyncHttpApi.get(activity!!.getSharedPreferences("data", Context.MODE_PRIVATE).getString("token", ""), "/products/", null, object : JsonHttpResponseHandler() {

            override fun onSuccess(statusCode: Int, headers: Array<Header>?, response: JSONArray?) {

                val gson = Gson()
                val jArray = response
                if (response != null) {
                    for (i in 0 until response.length()) {
                        try {
                            product!!.add(gson.fromJson(response.get(i).toString(), Product::class.java!!))
                            Log.i("product", response.get(i).toString())
                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }
                    }
                }
                val adap = ProductApdater.productAdap(product, context)
                list_product!!.adapter = adap
                val adapter = context?.let { ViewPagerAdapter(it) }
                viewPager!!.adapter = adapter
            }
        })
    }

    companion object {
        fun newInstance(): CatogriesFragment {
            val fragment = CatogriesFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }


}


