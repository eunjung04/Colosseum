package com.example.colosseum

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.provider.MediaStore
import android.util.Log
import com.bumptech.glide.Glide
import com.example.colosseum.utils.ServerUtils
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.main2.*
import org.json.JSONObject

class MainActivity : BaseActivity() {



    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main2)
        setupEvents()
        setValues()
    }

    override val REQ_FOR_PICK_IMAGE_FROM_GALLERY: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.


    override fun setupEvents() {
        changeProfileBtn.setOnClickListener {
            val myIntent=Intent(Intent.ACTION_PICK)
            myIntent.setType("image/*")
            myIntent.setType(MediaStore.Images.Media.CONTENT_TYPE)
            startActivityForResult(myIntent, REQ_FOR_PICK_IMAGE_FROM_GALLERY)
        }

    }

    override fun setValues() {

        override fun onResume() {
            super.onResume()

            SeverUtils.getRequestMainInfo(mContext, object : ServerUtils.JsonResponseHandler{
                override fun onResponse(json: JSONObject) {
                    Log.d("메인화면응답", json.toString())

                    val code=json.getInt("code")

                    if(code==200) {
                        val data=json.getJSONObject("data")
                        val user=data.getJSONObject("user")
                        val topic=data.getJSONObject("topic")

                        runOnUiThread {
                            myNickNameTxt.text=user.getString("nick_name")
                            thisWeekBattleTopicTxt.text=topic.getString("title")

                            Glide.with(mContext).load(topic.getString("img_url")).into(topicImg)


                }
            }




                }
            })
        }




            }
            override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
                super.onActivityResult(requestCode, resultCode, data)

                if(requestCode==REQ_FOR_PICK_IMAGE_FROM_GALLERY)
                    if(resultCode== Activity.RESULT_OK){
                        //  Log.d("이미지가져온결과", data?.data.toString())
                        Glide.with(mContext).load(data?.data).into(myProfileImg)
        }

    }
}