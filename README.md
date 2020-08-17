

SheetDialog , 仿造IOS 风格底部弹出的样式,通用的Dialog

<!--more-->


> 在博客中阅读 体验更佳，更新的说明会在博客中持续更新 [点击查看](https://allens.icu/posts/27ac3d24/#more)

# Overview

![仿ios底部弹出](https://gitee.com/_Allens/BlogImage/raw/master/image/20200817141159.gif)


![通用的dialog](https://gitee.com/_Allens/BlogImage/raw/master/image/20200817141507.gif)




# Download

## Step 1. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:

```
allprojects {
	repositories {
		maven { url 'https://www.jitpack.io' }
	}
}
```

## Step 2. Add the dependency

```
dependencies {
        implementation 'com.github.JiangHaiYang01:SheetDialog:0.0.2'
}
```

当前最新版本

  [![](https://www.jitpack.io/v/JiangHaiYang01/SheetDialog.svg)](https://www.jitpack.io/#JiangHaiYang01/SheetDialog)


# Usage

## 仿ios 的底部弹出效果

```
SheetDialog(this)
      .create()
      .setTitle("请选择") //title 提示
      .setCancelTvColor(Color.RED)//设置cancel  颜色
      .setCancelTvMsg("取消") //设置cancel 文案
      .setCancelTvSize(16f)//设置cancel 字体大小
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
      .setCancelTvMsg("取消")
      .show()
```


## 通用的dialog

```java
    GeneralDialog(this)
            .create()
            .setTitle("请输入手机号")
            //取消
            .setNegative(object : GeneralDialog.OnNegativeListener {
                override fun onNegative(dialog: GeneralDialog) {
                    dialog.dismiss()
                }
            })
            //确认
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
            //自定义的布局
            .setCustomView(R.layout.dialog_custom, object : GeneralDialog.OnCustomListener {
                override fun onCustom(view: View) {
                    editText = view.findViewById(R.id.custom_et_phone)
                }
            })
            //添加动画
            .setAnimations(R.style.ActionSheetDialogAnimation)
            .show()
```

# Github

[点击查看](https://github.com/JiangHaiYang01/SheetDialog)

# License
 
```
   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

```