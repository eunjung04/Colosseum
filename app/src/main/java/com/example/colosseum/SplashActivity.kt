package com.example.colosseum

import android.content.Intent
import android.widget.Toast
import android.os.Bundle
import android.os.Handler
import androidx.core.content.ContextCompat.startActivity
import com.example.colosseum.utils.ContextUtil
import java.util.logging.Handler

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setupEvents()
        setValues()



        }

    override fun setupEvents() {

    }

    override fun setValues() {

        android.os.Handler().postDelayed({

            if(ContextUtil.getUserToken(mContext)!=""&& ContextUtil.isAutoLogin(mContext)) {
                Toast.makeText(mContext, "메인화면으로 이동해야함", Toast.LENGTH_SHORT).show()

                val myIntent=Intent(mContext, MainActivity ::class.java)
                startActivity((myIntent)

                    finish()

            }



            else{
                val myIntent=Intent(mContext, LoginActivity::class.java)
                startActivity(myIntent)

               finish()


        }, 2000)




    }


}

