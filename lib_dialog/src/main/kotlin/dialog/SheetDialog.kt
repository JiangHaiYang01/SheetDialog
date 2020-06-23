package dialog

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import com.allens.lib_ios_dialog.R

class SheetDialog(private val context: Context) : BottomDialog(context = context) {


    //cancel 的样式
    private val bgCancel = R.drawable.bg_cancel

    //当item 多个的时候  中间item的样式
    private val bgMultipleCenter = R.drawable.bg_item_center

    //当 item 只有一个的时候  item  样式
    private val bgSingleCenter = R.drawable.bg_item_single

    //当item 多个的时候 最下面的一个item 样式
    private val bgMultipleBottom = R.drawable.bg_single

    //当item 多个的时候 最上面的一个item 样式
    private val bgMultipleTop = R.drawable.bg_top

    //title 的样式
    private val bgTitle = R.drawable.bg_top

    //view
    private lateinit var scrollView: ScrollView
    private lateinit var linearLayout: LinearLayout
    private lateinit var tvTitle: TextView
    private lateinit var tvCancel: TextView


    //单条数据的高度
    private var itemHeight = 50

    //单条数据的字体大小
    private var itemTvSize = 12f

    //宽度比例
    private var width: Double = 0.9

    //取消的字体大小
    //是否显示设置标记
    private var showTitle = false

    //item 数据集合
    private var itemList = mutableListOf<SheetItem>()


    //是否显示线
    private var isShowLine = true

    //中间线的颜色
    private var lineColor = R.color.color_text_sprite

    //线的高度
    private var lineHeight = 1

    //item 的颜色
    private var itemColor = R.color.color_text_line


    //最大单项数目  如果超过次数字  会有滚动效果
    private var maxItem = 7


    override fun getLayoutId(): Int {
        return R.layout.view_actionsheet
    }

    override fun onLayoutView(view: View) {
        scrollView = view.findViewById(R.id.sLayout_content)
        linearLayout = view.findViewById(R.id.lLayout_content)
        tvTitle = view.findViewById(R.id.txt_title)
        tvCancel = view.findViewById(R.id.txt_cancel)

    }

    override fun create(): SheetDialog {
        super.create()
        return this
    }


    //==============================================================================================
    // line
    //==============================================================================================

    fun showLine(isShowLine: Boolean): SheetDialog {
        this.isShowLine = isShowLine
        return this
    }

    fun setLineColor(color: Int): SheetDialog {
        this.lineColor = color
        return this
    }

    fun setLineHeight(height: Int): SheetDialog {
        this.lineHeight = height
        return this
    }


    //==============================================================================================
    // title
    //==============================================================================================
    fun setTitle(title: String): SheetDialog {
        showTitle = true
        tvTitle.text = title
        tvTitle.visibility = View.VISIBLE
        return this
    }

    fun setTitleColor(color: Int): SheetDialog {
        tvTitle.setTextColor(color)
        return this
    }


    fun getTitleTextView(): TextView {
        return tvTitle
    }


    //==============================================================================================
    // cancel
    //==============================================================================================
    fun setCancelTvColor(color: Int): SheetDialog {
        tvCancel.setTextColor(color)
        return this
    }

    fun setCancelTvMsg(info: String): SheetDialog {
        tvCancel.text = info
        tvCancel.visibility = View.VISIBLE
        return this
    }

    fun setCancelTvSize(size: Float): SheetDialog {
        tvCancel.textSize = size
        return this
    }


    fun getCancelTextView(): TextView {
        return tvCancel
    }


    //==============================================================================================
    // item
    //==============================================================================================
    fun setItemTvSize(itemTvSize: Float): SheetDialog {
        this.itemTvSize = itemTvSize
        return this
    }

    fun setItemHeight(itemHeight: Int): SheetDialog {
        this.itemHeight = itemHeight
        return this
    }

    //最大单项数目  如果超过次数字  会有滚动效果
    fun setMaxItemSize(size: Int): SheetDialog {
        this.maxItem = size
        return this
    }

    fun setItemColor(color: Int): SheetDialog {
        this.itemColor = color
        return this
    }

    //==============================================================================================
    // add
    //==============================================================================================


    fun addSheetItem(itemName: String): SheetDialog {
        addSheetItem(itemName, context.resources.getColor(R.color.color_text_line), null)
        return this
    }

    fun addSheetItem(itemName: String, color: Int): SheetDialog {
        addSheetItem(itemName, color, null)
        return this
    }

    fun addSheetItem(itemName: String, listener: OnSheetItemClickListener?): SheetDialog {
        addSheetItem(itemName, context.resources.getColor(R.color.color_text_line), listener)
        return this
    }


    fun addSheetItem(
        itemName: String,
        color: Int,
        listener: OnSheetItemClickListener?
    ): SheetDialog {
        itemList.add(SheetItem(itemName, color, listener))
        return this
    }

    //==============================================================================================
    // dialog
    //==============================================================================================
    //点击其他位置是否能够取消
    override fun setCanceledOnTouchOutside(cancel: Boolean): SheetDialog {
        dialog.setCanceledOnTouchOutside(cancel)
        return this
    }


    // 是否点击返回能够取消
    override fun setCancelable(cancel: Boolean): SheetDialog {
        dialog.setCancelable(cancel)
        return this
    }

    override fun getDialogWidth(): Double {
        return width
    }

    fun setDialogWidth(width: Double): SheetDialog {
        this.width = width
        return this
    }

    override fun show() {
        prepareShow()
        super.show()
    }

    private fun prepareShow() {
        val size: Int = itemList.size
        //设置 滚动布局的高度为屏幕的一半
        if (size >= maxItem) {
            val params = scrollView.layoutParams
            params.height = display.height / 3
            scrollView.layoutParams = params
        }


        //title cancel 样式
        tvCancel.setBackgroundResource(bgCancel)
        tvTitle.setBackgroundResource(bgTitle)
        tvCancel.setOnClickListener {
            dialog.dismiss()
        }

        itemList.indices.forEach {

            val index = it

            //是否添加title 下面的line
            if (isShowLine && it == 0 && showTitle) {
                addLine()
            }

            val textView = TextView(context)
            textView.text = itemList[index].name
            textView.textSize = itemTvSize
            textView.gravity = Gravity.CENTER
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                textView.setTextAppearance(R.style.style_water)
            }



            if (showTitle) {
                when (index) {
                    itemList.size - 1 -> {
                        textView.setBackgroundResource(bgMultipleBottom)
                    }
                    else -> {
                        textView.setBackgroundResource(bgMultipleCenter)
                    }
                }
            } else {
                when (index) {
                    0 -> {
                        textView.setBackgroundResource(bgMultipleTop)
                    }
                    itemList.size - 1 -> {
                        textView.setBackgroundResource(bgMultipleBottom)
                    }
                    else -> {
                        textView.setBackgroundResource(bgMultipleCenter)
                    }
                }
            }


            textView.layoutParams =
                LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    dip2px(context, itemHeight)
                )
            if (itemList[index].color == -1) {
                textView.setTextColor(context.resources.getColor(itemColor))
            } else
                textView.setTextColor(itemList[index].color)

            textView.setOnClickListener {
                dialog.dismiss()
                itemList[index].listener?.onSheetItemClick(index)
                itemList[index].listener?.onSheetItemClick()
                itemList[index].listener?.onSheetItemClick(index, textView, itemList[index].name)
            }

            linearLayout.addView(textView)
            if (isShowLine && index < itemList.size - 1) {
                addLine()
            }
        }
    }

    private fun addLine() {
        if (!isShowLine) {
            return
        }
        val line = View(context)
        line.background =  ColorDrawable(lineColor)
        line.layoutParams =
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                dip2px(context, lineHeight)
            )
        linearLayout.addView(line)
    }

}


private fun dip2px(context: Context, dpValue: Int): Int {
    val scale = context.resources.displayMetrics.density
    return (dpValue * scale + 0.5f).toInt()
}


data class SheetItem(
    val name: String,
    val color: Int = -1,
    val listener: OnSheetItemClickListener?
)


abstract class OnSheetItemClickListener {


    abstract fun onSheetItemClick()

    fun onSheetItemClick(which: Int) {

    }

    fun onSheetItemClick(index: Int, textView: TextView, info: String) {

    }


}

/***
 * 默认颜色
 */
enum class SheetItemColor(name: String) {
    Blue("#0088FF"),
    Red("#C24B44");


    fun getColor(): Int {
        return Color.parseColor(name)
    }

}