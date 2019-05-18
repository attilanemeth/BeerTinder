@file:Suppress("DEPRECATION")

package com.example.miklosnagy.navigationdrawer

import android.app.Fragment
import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.github.amlcurran.showcaseview.OnShowcaseEventListener
import com.github.amlcurran.showcaseview.ShowcaseView
import com.microsoft.windowsazure.notifications.NotificationsManager
import kotlinx.android.synthetic.*
import notifications.MyHandler
import notifications.NotificationSettings
import notifications.RegistrationIntentService
import org.w3c.dom.Text


class Fragment1_LetsDrink() : Fragment(),OnShowcaseEventListener {

    private lateinit var vie:View
    companion object {
        fun newInstance(): Fragment1_LetsDrink {
            return Fragment1_LetsDrink()
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val myView =  inflater?.inflate(R.layout.letsdrink_layout, container, false)
        // val myText : TextView = view.findViewById(R.id.tv_accessibility)
        val likeButton = myView?.findViewById<Button>(R.id.likeButton)
        val dislikeButton = myView?.findViewById<Button>(R.id.dislikeButton)
        val intent =  Intent(activity, RegistrationIntentService::class.java)

        vie=myView!!

        NotificationsManager.handleNotifications(myView?.context, NotificationSettings.SenderId, MyHandler::class.java)


        likeButton?.setOnClickListener{
            Toast.makeText(myView.context, getString(R.string.get_notification), Toast.LENGTH_LONG).show()
            MyHandler.receiveNoti=true
            activity.startService(intent)
        }

        dislikeButton?.setOnClickListener{
            Toast.makeText(myView.context, getString(R.string.not_get_notification), Toast.LENGTH_LONG).show()
            MyHandler.receiveNoti=false
            activity.stopService(intent);

        }
        val share=activity?.getPreferences(Context.MODE_PRIVATE)
            if(!share?.contains("fourth")!!){
            ShowcaseView.Builder(activity)
                    .setContentTitle("Tipp")
                    .withMaterialShowcase().useDecorViewAsParent()

                    .setContentText(R.string.Shocase4Content)
                    .setStyle(R.style.CustomShowcaseTheme3)
                    .hideOnTouchOutside()
                    .setShowcaseEventListener(this@Fragment1_LetsDrink)
                    .build()

            share.edit().putString("fourth","fourth").commit()

        }

        return myView
    }


    override fun onShowcaseViewShow(showcaseView: ShowcaseView?) {

    }

    override fun onShowcaseViewHide(showcaseView: ShowcaseView?) {

    }

    override fun onShowcaseViewDidHide(showcaseView: ShowcaseView?) {

    }

    override fun onShowcaseViewTouchBlocked(motionEvent: MotionEvent?) {

    }


}