package com.example.utsandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import kotlinx.android.synthetic.main.activity_dashboard.*
import org.json.JSONArray

class Dashboard : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val context = this
        btnAdd.setOnClickListener{
            var data_namapenduduk: String = editNama.text.toString()
            var data_ttlpenduduk: String = editTtl.text.toString()
            var data_hppenduduk: String = editNomer.text.toString()
            var data_alamatpenduduk: String = editAlamat.text.toString()

            postkeserver(data_namapenduduk, data_ttlpenduduk, data_hppenduduk, data_alamatpenduduk)

//            val intent2 = Intent(context, Dashboard::class.java)
//            startActivity(intent2)
        }

        btnKembali.setOnClickListener{
            val intent1 = Intent(context,HalamanLogin::class.java)
            startActivity(intent1)
        }
    }

    fun postkeserver(data1:String, data2:String, data3:String, data4:String)
    {
        AndroidNetworking.post("http://192.168.43.191/utsmobileterapan/insert_data.php")
            .addBodyParameter("nama_penduduk", data1)
            .addBodyParameter("ttl_penduduk", data2)
            .addBodyParameter("hp_penduduk", data3)
            .addBodyParameter("alamat_penduduk", data4)
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
