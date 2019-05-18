@file:Suppress("DEPRECATION")

package com.example.miklosnagy.navigationdrawer

import API.ApiInterface
import API.Message
import API.Place
import android.annotation.SuppressLint
import android.app.Activity
import android.app.Fragment
import android.content.Context
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.github.amlcurran.showcaseview.OnShowcaseEventListener
import com.github.amlcurran.showcaseview.ShowcaseView
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.*

import com.google.android.gms.maps.GoogleMap.MAP_TYPE_NORMAL
import com.google.android.gms.maps.model.*
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception


class Fragment3_Settings() : Fragment(),OnShowcaseEventListener {


    companion object {
        fun newInstance(): Fragment3_Settings {
            return Fragment3_Settings()
        }
    }


    private lateinit var mapFragment: MapFragment;
    private var mContext: Context? = null
    private var supportMapFragment: SupportMapFragment? = null
    private lateinit var mMap: GoogleMap
    private  lateinit var vie:View
    private  lateinit var activitiIns:Activity

    private  lateinit var markers:Array<Marker>



    @SuppressLint("MissingPermission")
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater?.inflate(R.layout.setting_layout, container, false)
        vie=v!!
        markers= emptyArray()


        mapFragment = MapFragment.newInstance(GoogleMapOptions()
                .mapType(MAP_TYPE_NORMAL)
                .compassEnabled(true)
                .rotateGesturesEnabled(true)
                .tiltGesturesEnabled(true)
                .scrollGesturesEnabled(true)
                .zoomControlsEnabled(true)
                .zoomGesturesEnabled(true))
        mapFragment.getMapAsync {
            mMap = it

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(47.510346, 19.030973), 14f
));
            mMap.animateCamera(CameraUpdateFactory.zoomIn());
            mMap.animateCamera(CameraUpdateFactory.zoomTo(10f), 2000, null);

            val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                    .baseUrl("https://androidapi.azurewebsites.net").build()

            val placesApi = retrofit.create(ApiInterface::class.java)
            var call = placesApi.getPlaces()
            try {
                call.enqueue(object : Callback<List<Place>> {
                    override fun onResponse(call: Call<List<Place>>, response: Response<List<Place>>) {
                        val list = response.body()

                        val share=activitiIns?.getPreferences(Context.MODE_PRIVATE)
                        if(!share?.contains("third")!!){
                            ShowcaseView.Builder(activitiIns)
                                    .setContentTitle("Tipp")
                                    .withMaterialShowcase().useDecorViewAsParent()

                                    .setContentText(R.string.Shocase3Content)
                                    .setStyle(R.style.CustomShowcaseTheme3)
                                    .hideOnTouchOutside()
                                    .setShowcaseEventListener(this@Fragment3_Settings)
                                    .build()

                            share.edit().putString("third","third").commit()

                        }


                        if(response.isSuccessful) {



                            for (i in list!!.indices) {

                                val sydney = LatLng(list!![i].latitude.toDouble(), list!![i].longitude.toDouble())

                                val bi = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)
                                val mar: Marker = mMap.addMarker(MarkerOptions().position(sydney).title(list!![i].name).icon(bi))
                                markers += mar
                                mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))

                            }
                        }


                    }

                    override fun onFailure(call: Call<List<Place>>, t: Throwable) {

                    }
                })
            }catch (e:Exception){
                Toast.makeText(v?.context,"Network problem occurred",Toast.LENGTH_SHORT).show()
            }

            mMap!!.setOnMapLongClickListener {
                for(item in markers){

                    if(Math.abs(item.getPosition().latitude - it.latitude) < 0.005 && Math.abs(item.getPosition().longitude - it.longitude) < 0.005) {
                        Toast.makeText(v?.context,"Notification has been sent",Toast.LENGTH_SHORT).show()
                        val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                                .baseUrl("https://androidapi.azurewebsites.net").build()

                        val placesApi = retrofit.create(ApiInterface::class.java)

                        var call = placesApi.postNoti(Message(item.title))
                        try {
                            call.enqueue(object : Callback<String> {
                                override fun onFailure(call: Call<String>, t: Throwable) {

                                }

                                override fun onResponse(call: Call<String>, response: Response<String>) {

                                }

                            })
                        }catch (e:Exception){
                            Toast.makeText(v?.context,"Network problem occurred.",Toast.LENGTH_SHORT).show()

                        }


                        break
                    }
                }
            }


        }




        return v
    }

    @SuppressLint("MissingPermission")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)



        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, mapFragment)
                .commit()


    }



    override fun onShowcaseViewShow(showcaseView: ShowcaseView?) {
        vie.alpha=0.007f
    }

    override fun onShowcaseViewHide(showcaseView: ShowcaseView?) {
        vie.alpha=1f
    }

    override fun onShowcaseViewDidHide(showcaseView: ShowcaseView?) {
        vie.alpha=1f

    }

    override fun onShowcaseViewTouchBlocked(motionEvent: MotionEvent?) {

    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        activitiIns=activity!!
    }



    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

}

