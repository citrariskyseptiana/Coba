package com.example.fan1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_menu_utama.*

class MenuUtama : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_utama)

        val context = this

        btnSholat.setOnClickListener{
            val intent = Intent(context,MainActivity::class.java)
            startActivity(intent)
        }

        btnPengumuman.setOnClickListener{
            val intent = Intent(context,Pengumuman::class.java)
            startActivity(intent)
        }

//        btnSlideshow.setOnClickListener{
//            val intent = Intent(context,Slideshow::class.java)
//            startActivity(intent)
//        }
//
//        btnTagline.setOnClickListener{
//            val intent = Intent(context,Tagline::class.java)
//            startActivity(intent)
//        }
//
//        btnMarquee.setOnClickListener{
//            val intent = Intent(context,Marquee::class.java)
//            startActivity(intent)
//        }

        btnIdentitas.setOnClickListener{
            val intent = Intent(context,Identitas::class.java)
            startActivity(intent)
        }
    }
}
