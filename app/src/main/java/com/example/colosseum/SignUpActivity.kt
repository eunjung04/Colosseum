package com.example.colosseum

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.example.colosseum.utils.ServerUtils
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : BaseActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        setupEvents()
        setValues()
}

    override fun setupEvents() {

        emailEdt.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                isEmailChecked=false

                emailChecked.setText(R.string.id_check_message)
                emailChecked.setTextColor(resources.getColor())
            }
        })

        emailChecked.setOnClickListener {
            val inputEmail=emailEdt.text.toString()

            ServerUtils.getRequestEmailDupleCheck(mContext, inputEmail, object:ServerUtils)

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

    }

    override fun setValue() {


    }
    }