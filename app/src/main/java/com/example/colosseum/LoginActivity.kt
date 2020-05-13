package com.example.colosseum

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.colosseum.utils.ContextUtil
import com.example.colosseum.utils.ServerUtils
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class LoginActivity(override val LoginActivity: Unit) : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setupEvents()
        setValue()

    }

    override fun setupEvents() {



        autoLoginCheckBox.setOnCheckedChangeListener { buttonView, isChecked ->
            ContextUtil.setAutoLogin(mContext, isChecked)
        }

        loginBtn.setOnClickListener {

            val email=emailEdt.text.toString()
            val pw=pwEdt.text.toString()

            ServerUtils.postRequestLogin(mContext, email, pw, object : ServerUtils.JsonResponseHandler{
                override fun onResponse(json: JSONObject) {
                    Log.d("로그인 응답",json.toString())
                    val code=json.getInt("code")





            if(code==200){

                val data=json.getJSONObject("data")
                val token=data.getString("token")

                ContextUtil.setUserToken(mContext, token)


                runOnUiThread {
                    Toast.makeText(mContext, resources.getString(R.string.login_success_message),Toast.LENGTH_SHORT).show()
                }







            }else{
                val message=json.getString("message")

                runOnUiThread{
                    Toast.makeText(mContext,message, Toast.LENGTH_SHORT).show()
                }

            }
        }
    })

        }

    }

    override fun setValue() {
        autoLoginCheckBox.isChecked=ContextUtil.isAutoLogin(mContext)

    }
}
