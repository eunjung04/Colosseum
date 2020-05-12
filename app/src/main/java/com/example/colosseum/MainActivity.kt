package com.example.colosseum

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.colosseum.utils.ServerUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupEvents()
        setValue()

    }

    override fun setValue() {

        loginBtn.setOnClickListener {

            val email=emailEdt.text.toString()
            val pw=pwEdt.text.toString()

            ServerUtils.postRequestLogin(mContext, email, pw, object : ServerUtils.JsonResponseHandler)


            val code=json.getInt("code")

            if(code==200){



            }else{
                val message=json.getstring("message")

                runOnUiThread{
                    Toast.makeText(mContext,message, Toast.LENGTH_SHORT).show()
                }

            }


        }

    }

    override fun setupEvents() {

    }
}
