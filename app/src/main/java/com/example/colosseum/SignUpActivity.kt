package com.example.colosseum

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import com.example.colosseum.utils.ServerUtils
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.json.JSONObject

class SignUpActivity : BaseActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        setupEvents()
        setValues()
}

    override fun setupEvents() {

        emailCheckBtn.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                isEmailCheckOk=false

                emailCheckResultTxt.setText(R.string.id_check_message)
                emailCheckResultTxt.setTextColor(resources.getColor(R.color.darkGray))
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })

        emailCheckBtn.setOnClickListener {
            val inputEmail=emailEdt.text.toString()

            ServerUtils.getRequestMainInfo(mContext, inputEmail, object:ServerUtils.JsonResponseHandler{
                override fun onResponse(json: JSONObject) {
                    Log.d("이메일중복응답", json.toString())

                    val code=json.getInt("code")


                    if(code==200){
                        runOnUiThread {
                    emailCheckBtn.setTextColor(resources.getColor(R.color.azul))
                    emailCheckBtn.setText(R.string.id_check_success_message)
                }

            }
            else{
                runOnUiThread {
                    emailCheckBtn.setTextColor(resources.getColor(R.color.grapefruit))
                    emailCheckBtn.setText(R.string.id_check_fail_message)

                }

            }
        }
    })
}

    }

    override fun setValue() {


    }
    }