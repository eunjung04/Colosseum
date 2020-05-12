package com.example.colosseum.utils

import android.content.Context
import android.util.Log
import android.widget.Toast
import okhttp3.*
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import org.json.JSONObject
import java.io.IOException

class ServerUtils {

    interface JsonResponseHandler {
        fun onResponse(json: JSONObject)
    }

    companion object {

        val BASE_URL = "http://ec2-15-165-177-142.ap-northeast-2.compute.amazonaws.com/"

        fun postRequestLogin(
            context: Context,
            id: String,
            pw: String,
            handler: JsonResponseHandler?
        ) {

            val client = OkHttpClient()
            //어떤 기능을 수행하러 가는지 주소 완성.
            //http://192.168.0.243:5000/auth
            val urlStr = "${BASE_URL}/user"

            //서버에 들고갈 데이터를 첨부. =>POST메쏘드의 예제
            val formBody = FormBody.Builder()
                .add("email", id)
                .add("password", pw)
                .build()


            //화면이동으로 따지면 Intent  객체를 만드는 행위.
            val request = Request.Builder()
                .url(urlStr)
                .post(formBody)
                //.header()=>API가 헤더를 요구하면 추가해야함.
                .build()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {

                    e.printStackTrace()
                }

                override fun onResponse(call: Call, response: Response) {


                    val body = response.body!!.string()
                    val json = JSONObject(body)

                    handler?.onResponse(json)


                }


            })

        }

        fun getRequestMyInfo(context: Context, handler: JsonResponseHandler?) {

            val client = OkHttpClient()
            val urlBuilder = "${BASE_URL}/my_info".toHttpUrlOrNull()!!.newBuilder()
            urlBuilder.addEncodedQueryParameter("device_token", "임시기기토큰")
            urlBuilder.addEncodedQueryParameter("os", "Android")

            val urlStr = urlBuilder.build().toString()

            //  Log.d("완성된 주소", urlStr)

            val request = Request.Builder()
                .url(urlStr)
                //.header("X-Http-Token", ContextUtil.getUserToken(context))
                .build()

            client.newCall(request).enqueue((object :Callback{
                override fun onFailure(call: Call, e: IOException) {
                    e.printStackTrace()
                }
                override fun onResponse(call: Call, response: Response) {
                    val body = response.body!!.string()
                    val json = JSONObject(body)
                    handler?.onResponse(json)

            }

        })


        }
    }
}