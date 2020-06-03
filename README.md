# SheetDialog


SheetDialog , 仿造IOS 风格底部弹出的样式，自适应夜间和日间模式


# Overview

![日间模式](https://allens-blog.oss-cn-beijing.aliyuncs.com/uPic/2020-05-20-16-03-01-1589961781%20.png)

![夜间模式](https://allens-blog.oss-cn-beijing.aliyuncs.com/uPic/2020-05-20-16-04-30-1589961870%20.png)

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
        implementation 'com.github.JiangHaiYang01:SheetDialog:0.0.1'
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