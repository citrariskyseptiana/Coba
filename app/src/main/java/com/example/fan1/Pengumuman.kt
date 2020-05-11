package com.example.fan1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
import kotlinx.android.synthetic.main.activity_pengumuman.*
import org.json.JSONArray
import org.json.JSONObject

class Pengumuman : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pengumuman)

        val context = this

        btnKembali.setOnClickListener{
            val intent = Intent(context,MenuUtama::class.java)
            startActivity(intent)
        }

        btnUpdt.setOnClickListener{
            var data_judulpengumuman = editp1.text.toString()
            var data_isipengumuman = editp2.text.toString()

            postkeserver(data_judulpengumuman, data_isipengumuman)

            val intent2 = Intent(context,MenuUtama::class.java)
            startActivity(intent2)
        }

        getdariserver()
    }

    fun getdariserver(){
        AndroidNetworking.get("https://tugas1citra.000webhostapp.com/contoh_pengumuman_json.php")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    Log.e("_kotlinResponse", response.toString())

                    val jsonArray = response.getJSONArray("result")
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)
                        Log.e("_kotlinTitle", jsonObject.optString("judul_pengumuman"))

                        txt1.setText(jsonObject.optString("judul_pengumuman"))
                        txt2.setText(jsonObject.optString("isi_pengumuman"))
                    }
                }

                override fun onError(anError: ANError) {
                    Log.i("_err", anError.toString())
                }
            })
    }

    fun postkeserver(data1:String, data2:String)
    {
        AndroidNetworking.post("https://tugas1citra.000webhostapp.com/updatepengumuman.php")
            .addBodyParameter("judul_pengumuman", data1)
            .addBodyParameter("isi_pengumuman", data2)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONArray(object : JSONArrayRequestListener {
                override fun onResponse(response: JSONArray) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onError(anError: ANError) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }
            })
    }
}
