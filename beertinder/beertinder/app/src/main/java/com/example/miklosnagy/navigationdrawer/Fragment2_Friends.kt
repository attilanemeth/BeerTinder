@file:Suppress("DEPRECATION")

package com.example.miklosnagy.navigationdrawer

import API.ApiInterface
import API.Place
import android.app.Activity
import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.text.TextPaint
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.request.transition.Transition
import com.github.amlcurran.showcaseview.OnShowcaseEventListener
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception
import com.github.amlcurran.showcaseview.ShowcaseView




class Fragment2_Friends() : Fragment(),OnShowcaseEventListener {


    companion object {
        fun newInstance(): Fragment2_Friends {
            return Fragment2_Friends()
        }
    }

    private lateinit var places: Array<Place>
    private  lateinit var vie:View


    private lateinit var viewAdapter: RecyclerView.Adapter<*>



    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        places= emptyArray()

        val rootView=inflater?.inflate(R.layout.friends_layout, container, false)
        vie=rootView!!
        val recyclerView = rootView?.findViewById(R.id.recycler_view_test) as RecyclerView?
        recyclerView?.layoutManager = LinearLayoutManager(activity)



        viewAdapter = PlacesAdapterAdapter(places)

        recyclerView?.adapter = viewAdapter
        val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .baseUrl("https://androidapi.azurewebsites.net").build()

        val placesApi = retrofit.create(ApiInterface::class.java)
        try {
            var call = placesApi.getInProgress()

            call.enqueue(object : Callback<List<Place>> {
                override fun onResponse(call: Call<List<Place>>, response: Response<List<Place>>) {
                    val list = response.body()

                    val share=activity?.getPreferences(Context.MODE_PRIVATE)
                    if(!share?.contains("second")!!){
                        ShowcaseView.Builder(activity)
                                .setContentTitle("Tipp")
                                .withMaterialShowcase().useDecorViewAsParent()

                                .setContentText(R.string.Shocase2Content)
                                .setStyle(R.style.CustomShowcaseTheme3)
                                .hideOnTouchOutside()
                                .setShowcaseEventListener(this@Fragment2_Friends)
                                .build()

                        share.edit().putString("second","second").commit()

                    }
                    if(response.isSuccessful){

                    (viewAdapter as PlacesAdapterAdapter).addNewStatutes(list!!)

                    }

                }


                override fun onFailure(call: Call<List<Place>>, t: Throwable) {

                }
            })
        }catch (e:Exception){
            Toast.makeText(rootView?.context,"Network problem occurred", Toast.LENGTH_SHORT).show()

        }







        return  rootView
    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)



    }

    override fun onShowcaseViewHide(showcaseView: ShowcaseView?) {
        vie.alpha=1f
    }

    override fun onShowcaseViewDidHide(showcaseView: ShowcaseView?) {
        vie.alpha=1f
    }

    override fun onShowcaseViewTouchBlocked(motionEvent: MotionEvent?) {

    }

    override fun onShowcaseViewShow(showcaseView: ShowcaseView?) {
        vie.alpha=0.007f
    }









}




