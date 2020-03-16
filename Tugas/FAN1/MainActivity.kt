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
import kotlinx.android.synthetic.main.activity_menu_utama.*
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val context = this

        btnKembali.setOnClickListener {
            val intent = Intent(context, MenuUtama::class.java)
            startActivity(intent)
        }

        getdariserver()

        btnSimpan.setOnClickListener {
            var data_shubuh = shubuh.text.toString()
            var data_dhuhur = dhuhur.text.toString()
            var data_ashar = ashar.text.toString()
            var data_maghrib = maghrib.text.toString()
            var data_isha = isha.text.toString()
            var data_dhuha = dhuha.text.toString()

            postkeserver(data_shubuh, data_dhuhur, data_ashar, data_maghrib, data_isha, data_dhuha)

            val intent2 = Intent(context, MenuUtama::class.java)
            startActivity(intent2)
        }
    }

        fun getdariserver() {
            AndroidNetworking.get("https://tugas1citra.000webhostapp.com/contoh_jadwal_json.php")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject) {
                        Log.e("_kotlinResponse", response.toString())

                        val jsonArray = response.getJSONArray("result")
                        for (i in 0 until jsonArray.length()) {
                            val jsonObject = jsonArray.getJSONObject(i)
                            Log.e("_kotlinTitle", jsonObject.optString("shubuh"))

                            txt1.setText(jsonObject.optString("shubuh"))
                            txt2.setText(jsonObject.optString("dhuhur"))
                            txt3.setText(jsonObject.optString("ashar"))
                            txt4.setText(jsonObject.optString("maghrib"))
                            txt5.setText(jsonObject.optString("isha"))
                            txt6.setText(jsonObject.optString("dhuha"))
                        }
                    }

                    override fun onError(anError: ANError) {
                        Log.i("_err", anError.toString())
                    }
                })
        }


    fun postkeserver(data1:String, data2:String, data3:String, data4:String, data5:String, data6:String)
    {
        AndroidNetworking.post("https://tugas1citra.000webhostapp.com/contoh_jadwal_json.php")
            .addBodyParameter("shubuh", data1)
            .addBodyParameter("dhuhur", data2)
            .addBodyParameter("ashar", data3)
            .addBodyParameter("maghrib", data4)
            .addBodyParameter("isha", data5)
            .addBodyParameter("dhuha", data6)
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
