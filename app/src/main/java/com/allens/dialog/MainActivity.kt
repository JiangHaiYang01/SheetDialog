package com.allens.dialog

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.transition.Explode
import android.transition.Fade
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import dialog.GeneralDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var editText: EditText
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btn_2.setOnClickListener {
            startActivity(Intent(this, SheetDialogActivity::class.java))
        }

        btn_1.setOnClickListener {
            createGeneralDialog()
        }

    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun createGeneralDialog() {
        GeneralDialog(this)
            .create()
            .setTitle("请输入手机号")
            .setNegative(object : GeneralDialog.OnNegativeListener {
                override fun onNegative(dialog: GeneralDialog) {
                    dialog.dismiss()
                }
            })
            .setPositive(object : GeneralDialog.OnPositiveListener {
                override fun onPositive(dialog: GeneralDialog) {
                    dialog.dismiss()
                    Toast.makeText(
                        applicationContext,
                        editText.text.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
            .setGradientRadius(40f)
            .setCustomView(R.layout.dialog_custom, object : GeneralDialog.OnCustomListener {
                override fun onCustom(view: View) {
                    editText = view.findViewById(R.id.custom_et_phone)
                }
            })
            .setAnimations(R.style.ActionSheetDialogAnimation)
            .show()
    }
}