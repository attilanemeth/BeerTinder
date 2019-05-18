package com.example.miklosnagy.navigationdrawer

import notifications.MyHandler
import notifications.NotificationSettings
import notifications.RegistrationIntentService
import android.content.Context
import android.location.LocationManager
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast

import com.google.firebase.database.FirebaseDatabase

import kotlinx.android.synthetic.main.activity_navigation.*
import kotlinx.android.synthetic.main.app_bar_navigation.*

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.microsoft.windowsazure.notifications.NotificationsManager;
import android.content.Intent;
import android.content.SharedPreferences
import com.github.amlcurran.showcaseview.ShowcaseView
import com.github.amlcurran.showcaseview.targets.ActionItemTarget
import java.lang.Exception
import com.github.amlcurran.showcaseview.targets.ActionViewTarget




class NavigationActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    lateinit var LocationManager:LocationManager




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)
        setSupportActionBar(toolbar)



        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)


        LocationManager= getSystemService(Context.LOCATION_SERVICE) as
                LocationManager

        if(intent.extras["startmap"]=="tru"){
            intent.putExtra("startmap","false")

            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame, Fragment3_Settings.newInstance())
                    .commit()
        }

    }


    private fun checkPlayServices(): Boolean {
        val apiAvailability = GoogleApiAvailability.getInstance()
        val resultCode = apiAvailability.isGooglePlayServicesAvailable(this)
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
            } else {
                ToastNotify("This device is not supported by Google Play Services.")
                finish()
            }
            return false
        }
        return true
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.navigation, menu)
       // val image:ImageView=findViewById(R.id.imageView)
        //Glide.with(this).load(intent.extras["img"]).into(image)
        val name:TextView=findViewById(R.id.UserName)
        //val email:TextView=findViewById(R.id.UserMail)
        try {
            name.text=intent.extras["name"].toString()
        }catch (e:Exception){

        }

        //email.text=intent.extras["email"].toString()

        return true
    }



    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        @Suppress("DEPRECATION")
        val fragmentManager   = getFragmentManager()

        when (item.itemId) {
            R.id.letsdrink_layout -> {
                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame, Fragment1_LetsDrink.newInstance())
                        .commit()
            }
            R.id.friends_layout -> {
                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame, Fragment2_Friends.newInstance())
                        .commit()
            }

            R.id.settings_layout -> {
                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame, Fragment3_Settings.newInstance())
                        .commit()
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    fun ToastNotify(notificationMessage: String) {
        runOnUiThread {
            Toast.makeText(this, notificationMessage, Toast.LENGTH_LONG).show()
        }
    }



}
