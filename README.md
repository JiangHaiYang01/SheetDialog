 [![Build Status](https://travis-ci.org/JiangHaiYang01/SheetDialog.svg?branch=master)](https://travis-ci.org/JiangHaiYang01/SheetDialog)      [![HitCount](http://hits.dwyl.com/JiangHaiYang01/https://githubcom/JiangHaiYang01/SheetDialog.svg)](http://hits.dwyl.com/JiangHaiYang01/https://githubcom/JiangHaiYang01/SheetDialog)      [![](https://www.jitpack.io/v/JiangHaiYang01/SheetDialog.svg)](https://www.jitpack.io/#JiangHaiYang01/SheetDialog)


# SheetDialog

     


SheetDialog , 仿造IOS 风格底部弹出的样式，自适应夜间和日间模式


# Overview

![日间模式](http://allens-blog.oss-cn-beijing.aliyuncs.com/allens-blog/tm9pe.jpg)![夜间模式](http://allens-blog.oss-cn-beijing.aliyuncs.com/allens-blog/64laz.jpg)

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

# Usage

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

## 设计思路

- BaseDialog

基础的dialog 控制dialog 大小，显示隐藏等基本功能

- BottomDialog

继承BaseDialog，实现重底部弹出的效果

- SheetDialog

继承BottomDialog 


#  More

可以扫描二维码下载体验Demo

![](http://allens-blog.oss-cn-beijing.aliyuncs.com/allens-blog/zt9qr.png)


# 博客 （更新日志）

[博客地址](https://jianghaiyang01.github.io/posts/27ac3d24/)

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