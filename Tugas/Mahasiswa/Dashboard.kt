package com.example.mahasiswa

import android.annotation.SuppressLint
import android.content.Context
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
import kotlinx.android.synthetic.main.activity_dashboard.*
import org.json.JSONArray
import org.json.JSONObject

class Dashboard : AppCompatActivity() {

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val context = this
        btnAdd.setOnClickListener{
            var data_namamahasiswa: String = editNama.text.toString()
            var data_nomermahasiswa: String = editNomer.text.toString()
            var data_alamatmahasiswa: String = editAlamat.text.toString()

            postkeserver(data_namamahasiswa, data_nomermahasiswa, data_alamatmahasiswa)

//            val intent2 = Intent(context, Dashboard::class.java)
//            startActivity(intent2)
        }

        btnKembali.setOnClickListener{
            val intent1 = Intent(context,HalamanLogin::class.java)
            startActivity(intent1)
        }

    }

    fun postkeserver(data1:String, data2:String, data3:String)
    {
        AndroidNetworking.post("http://192.168.43.191/data_mahasiswa/insert_data.php")
            .addBodyParameter("nama_mhs", data1)
            .addBodyParameter("no_mhs", data2)
            .addBodyParameter("alamat_mhs", data3)
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
