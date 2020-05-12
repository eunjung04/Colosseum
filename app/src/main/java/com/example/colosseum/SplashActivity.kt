package com.example.colosseum

import android.widget.Toast
import com.example.colosseum.utils.ContextUtil

class SplashActivity : BaseActivity() {
    override fun setupEvents() {

        if(ContextUtil.getUserToken(mContext)!=""&& ContextUtil.isAutoLogin(mContext)){
            Toast.makeText(mContext,"메인화면으로 이동해야함", Toast.LENGTH_SHORT).show()
        }

    }

    override fun setValue() {

    }


}

