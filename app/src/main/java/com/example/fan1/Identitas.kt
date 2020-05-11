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
import org.json.JSONArray
import org.json.JSONObject

class Identitas : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_identitas)

        val context = this

        btnKembali.setOnClickListener{
            val intent = Intent(context,MenuUtama::class.java)
            startActivity(intent)
        }

        btnUpdate.setOnClickListener{
            var data_namamasjid = editident1.text.toString()
            var data_alamatmasjid = editident2.text.toString()

            postkeserver(data_namamasjid, data_alamatmasjid)

            val intent2 = Intent(context,MenuUtama::class.java)
            startActivity(intent2)
        }

        getdariserver()
    }

    fun getdariserver(){
        AndroidNetworking.get("https://tugas1citra.000webhostapp.com/contoh_identitas_json.php")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    Log.e("_kotlinResponse", response.toString())

                    val jsonArray = response.getJSONArray("result")
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)
                        Log.e("_kotlinTitle", jsonObject.optString("nama_masjid"))

                        txt1.setText(jsonObject.optString("nama_masjid"))
                        txt2.setText(jsonObject.optString("alamat_masjid"))
                    }
                }

                override fun onError(anError: ANError) {
                    Log.i("_err", anError.toString())
                }
            })
    }

    fun postkeserver(data1:String, data2:String)
    {
        AndroidNetworking.post("https://tugas1citra.000webhostapp.com/contoh_identitas_json.php")
            .addBodyParameter("nama_masjid", data1)
            .addBodyParameter("alamat_masjid", data2)
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
