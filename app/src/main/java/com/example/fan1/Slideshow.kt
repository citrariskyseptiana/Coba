package com.example.fan1

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_identitas.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.btnKembali
import kotlinx.android.synthetic.main.activity_main.txt1
import kotlinx.android.synthetic.main.activity_main.txt2
import kotlinx.android.synthetic.main.activity_slideshow.*
import org.json.JSONArray
import org.json.JSONObject

class Slideshow : AppCompatActivity() {

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slideshow)

        val recyclerView = findViewById(R.id.recyclerView) as RecyclerView
        recyclerView.layoutManager= LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        val users=ArrayList<User>()


        AndroidNetworking.get("http://192.168.43.191/cobarepo-master/contoh_slideshow_json.php")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    Log.e("_kotlinResponse", response.toString())

                    val jsonArray = response.getJSONArray("result")
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)
                        Log.e("_kotlinTitle", jsonObject.optString("judul_slideshow"))

                        // txt1.setText(jsonObject.optString("shubuh"))
                        var isi1=jsonObject.optString("judul_slideshow").toString()
                        var isi2=jsonObject.optString("url_slideshow").toString()

                        users.add(User("$isi1", "$isi2"))


                    }

                    val adapter=CustomAdapter(users)
                    recyclerView.adapter=adapter


                }

                override fun onError(anError: ANError) {
                    Log.i("_err", anError.toString())
                }
            })

        val context = this

        btnKembali.setOnClickListener{
            val intent = Intent(context,MenuUtama::class.java)
            startActivity(intent)
        }
//
//        btnGanti.setOnClickListener{
//            var data_judulslideshow = edits1.text.toString()
//            var data_urlslideshow = edits2.text.toString()
//
//            postkeserver(data_judulslideshow, data_urlslideshow)
//
//            val intent2 = Intent(context,MenuUtama::class.java)
//            startActivity(intent2)
//        }
//
//        getdariserver()
    }

//    fun getdariserver(){
//        AndroidNetworking.get("https://tugas1citra.000webhostapp.com/contoh_slideshow_json.php")
//            .setPriority(Priority.MEDIUM)
//            .build()
//            .getAsJSONObject(object : JSONObjectRequestListener {
//                override fun onResponse(response: JSONObject) {
//                    Log.e("_kotlinResponse", response.toString())
//
//                    val jsonArray = response.getJSONArray("result")
//                    for (i in 0 until jsonArray.length()) {
//                        val jsonObject = jsonArray.getJSONObject(i)
//                        Log.e("_kotlinTitle", jsonObject.optString("judul_slideshow"))
//
//                        txt1.setText(jsonObject.optString("judul_slideshow"))
//                        txt2.setText(jsonObject.optString("url_slideshow"))
//                    }
//                }
//
//                override fun onError(anError: ANError) {
//                    Log.i("_err", anError.toString())
//                }
//            })
//    }
//
//    fun postkeserver(data1:String, data2:String)
//    {
//        AndroidNetworking.post("https://tugas1citra.000webhostapp.com/updateslideshow.php")
//            .addBodyParameter("judul_slideshow", data1)
//            .addBodyParameter("url_slideshow", data2)
//            .setPriority(Priority.MEDIUM)
//            .build()
//            .getAsJSONArray(object : JSONArrayRequestListener {
//                override fun onResponse(response: JSONArray) {
//                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//                }
//
//                override fun onError(anError: ANError) {
//                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//                }
//            })
//    }
}
