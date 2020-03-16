package com.example.intent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val context = this

        btnIntent1.setOnClickListener{
            val intent = Intent(context,Activity1::class.java)

            val name:String = inputText.text.toString()
            intent.putExtra("name", name)
            startActivity(intent)
            finish()
        }

        btnIntent2.setOnClickListener{
            val intent = Intent(context,Activity2::class.java)
            startActivity(intent)
            finish()
        }
    }
}
