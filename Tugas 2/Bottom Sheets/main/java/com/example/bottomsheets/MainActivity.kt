package com.example.bottomsheets

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.bottom_sheet_dialog.*

class MainActivity : AppCompatActivity() {
    var mBottomSheetDialog : BottomSheetDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun ShowBottomSheetDialog(view: View){
        val BottomSheetLayout = layoutInflater.inflate(R.layout.bottom_sheet_dialog,null)
        mBottomSheetDialog = BottomSheetDialog(this)
        mBottomSheetDialog!!.setContentView(BottomSheetLayout)
        mBottomSheetDialog!!.show()
    }
}
