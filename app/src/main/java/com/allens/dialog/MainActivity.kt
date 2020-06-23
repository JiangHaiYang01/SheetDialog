package com.allens.dialog

import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import dialog.OnSheetItemClickListener
import dialog.SheetDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var with = 0.9
    private var itemSize = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        mSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    with = ((progress.toDouble() / 10))
                }

            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })

        size.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                if (p2) {
                    itemSize = p1
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })


        btn_switch.setOnClickListener {
            val dialog = SheetDialog(this)
                .setDialogWidth(with) //宽度比例  这个方法要放在create 之前 才有限
                .create()
                .setTitle("请选择") //title 提示
//                .setTitleColor(Color.RED) //title 颜色
//                .setCancelTvColor(Color.RED)//设置cancel  颜色
//                .setCancelTvMsg("取消") //设置cancel 文案
//                .setCancelTvSize(16f)//设置cancel 字体大小
//                .setCancelTvMsg("取消")
//                .setCanceledOnTouchOutside(true) //点击其他位置是否能够取消
//                .setCancelable(true) //是否点击返回能够取消
//                .setLineColor(Color.BLACK)//中间分割线的颜色
                .addSheetItem("照片", object : OnSheetItemClickListener() {
                    override fun onSheetItemClick() {
                        Toast.makeText(this@MainActivity, "照片", Toast.LENGTH_SHORT).show()
                    }
                })
                .addSheetItem("拍照", object : OnSheetItemClickListener() {
                    override fun onSheetItemClick() {
                        Toast.makeText(this@MainActivity, "拍照", Toast.LENGTH_SHORT).show()
                    }
                })
            for (i in 1..itemSize) {
                dialog.addSheetItem("item  $i", object : OnSheetItemClickListener() {
                    override fun onSheetItemClick() {
                        Toast.makeText(this@MainActivity, "item  $i", Toast.LENGTH_SHORT).show()
                    }
                })
            }
            dialog
                .show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if (menu == null)
            return false
        menu.add(1, 1, 1, "暗色模式")
        menu.add(1, 2, 2, "日间模式")
        menu.add(1, 3, 2, "系统")
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            1 -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            2 -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            3 -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            }

        }
        return super.onOptionsItemSelected(item)
    }

}
