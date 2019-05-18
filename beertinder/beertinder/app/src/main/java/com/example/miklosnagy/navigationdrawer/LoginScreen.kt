package com.example.miklosnagy.navigationdrawer

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApi
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class LoginScreen : AppCompatActivity(),GoogleApiClient.OnConnectionFailedListener{
    override fun onConnectionFailed(p0: ConnectionResult) {

    }


   private var mAuth: FirebaseAuth?=null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)
        mAuth=FirebaseAuth.getInstance()
        val btn:com.google.android.gms.common.SignInButton=findViewById(R.id.signInButton)
        val userin:EditText=findViewById(R.id.emailtext)
        val pwIn:EditText=findViewById(R.id.passwordtext)
        val resetBtn:TextView=findViewById(R.id.pwreset)
        btn.setOnClickListener {
            signIn(userin.text.toString(),pwIn.text.toString())

        }

        resetBtn.setOnClickListener() {
            sendResetMail(userin.text.toString())

        }






    }

    private fun createAcc(username:String,pw:String){
        this.mAuth?.createUserWithEmailAndPassword(username,pw)!!.addOnCompleteListener {


            if(it.isSuccessful){
                val currUser: FirebaseUser? = mAuth!!.currentUser
                Toast.makeText(this,"Successful create",Toast.LENGTH_SHORT).show()

            }else{

                Toast.makeText(this,"Error create",Toast.LENGTH_SHORT).show()


            }
        }
    }


    private  fun sendResetMail(email:String){
        this.mAuth?.sendPasswordResetEmail(email)?.addOnCompleteListener {
            if(it.isSuccessful){
                Toast.makeText(this,"Reset email was send.",Toast.LENGTH_SHORT).show()

            }else{
                Toast.makeText(this,"Reset Error.",Toast.LENGTH_SHORT).show()


            }

        }
    }

    private fun signIn(username:String,pw:String){


        this.mAuth?.signInWithEmailAndPassword(username, pw)?.addOnCompleteListener {
            if (it.isSuccessful) {

                val currUser: FirebaseUser? = mAuth!!.currentUser
                val displayName: String = currUser?.email!!
                val uid:String=currUser?.uid



                Toast.makeText(this, "Successful login", Toast.LENGTH_SHORT).show()
                val intent: Intent = Intent(this, NavigationActivity::class.java)
                intent.putExtra("name", displayName)
                intent.putExtra("uid", uid)

                startActivity(intent)


            } else {
                Toast.makeText(this, "Error login", Toast.LENGTH_SHORT).show()


            }


        }
    }


    override fun onStart() {
        super.onStart()
        val currUser:FirebaseUser?= mAuth!!.currentUser
    }


    private fun updateUI(curr:FirebaseUser){

    }











}
