package dialog

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import com.allens.lib_ios_dialog.R

class GeneralDialog(private val context: Context) : BaseDialog(context) {

    private lateinit var dialogParent: LinearLayout
    private lateinit var dialogTitle: TextView
    private lateinit var dialogCancel: TextView
    private lateinit var dialogConfirm: TextView
    private lateinit var dialogFrame: FrameLayout


    //圆角弧度
    private var shapeRadius = 40f

    // 边框线间隔
    private var shapeDashWidth = 0f

    //边框线长度
    private var shapeDashGap = 0f

    //边框宽度
    private var shapeStockWidth = 0

    //边框颜色
    private var shapeStockColor = Color.TRANSPARENT

    //背景的颜色
    private var shapeColor = Color.WHITE

    //添加自定义布局
    private var layoutResId: Int = 0

    //自定义添加的view
    private var customView: View? = null

    //取消
    private var onNegativeListener: OnNegativeListener? = null

    //确认
    private var onPositiveListener: OnPositiveListener? = null

    //自定义布局
    private var onCustomListener: OnCustomListener? = null

    override fun getLayoutId(): Int {
        return R.layout.dialog_general
    }

    override fun onLayoutView(view: View) {
        dialogParent = view.findViewById(R.id.dialog_general_ll)
        dialogTitle = view.findViewById(R.id.dialog_general_title)
        dialogCancel = view.findViewById(R.id.dialog_general_cancel)
        dialogConfirm = view.findViewById(R.id.dialog_general_confirm)
        dialogFrame = view.findViewById(R.id.dialog_general_fl)

        setGone(dialogCancel, true)
        setGone(dialogConfirm, true)

    }

    override fun show() {
        prepareShow()
        super.show()
    }

    private fun prepareShow() {
        setDialogParentDrawable()
        setCancelListener()
        setConfirmListener()
        addCustomLayout()
    }

    private fun addCustomLayout() {
        //这里有个优先级 view > layoutId
        if (customView != null) {
            if (onCustomListener != null) {
                onCustomListener?.onCustom(customView!!)
            }
            dialogFrame.addView(
                customView,
                ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
            )
        } else if (layoutResId != 0) {
            val customView = LayoutInflater.from(context).inflate(layoutResId, dialogFrame, false)
            if (onCustomListener != null) {
                onCustomListener?.onCustom(customView)
            }
            dialogFrame.addView(
                customView,
                ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
            )
        }
    }

    private fun setConfirmListener() {
        if (onPositiveListener != null) {
            dialogConfirm.setOnClickListener {
                onPositiveListener?.onPositive(this)
            }
        } else {
            dialogConfirm.setOnClickListener {
                if (isShowing()) {
                    dismiss()
                }
            }
        }
    }

    private fun setCancelListener() {
        if (onNegativeListener != null) {
            dialogCancel.setOnClickListener {
                onNegativeListener?.onNegative(this)
            }
        } else {
            dialogCancel.setOnClickListener {
                if (isShowing()) {
                    dismiss()
                }
            }
        }
    }

    private fun setDialogParentDrawable() {
        val shapeDrawable = getGradientDrawable()
        dialogParent.background = shapeDrawable
    }

    override fun create(): GeneralDialog {
        super.create()
        return this
    }

    //==============================================================================================
    // 内部方法
    //==============================================================================================
    private fun setGone(view: View, gone: Boolean): GeneralDialog {
        if (gone) {
            view.visibility = View.GONE
        } else {
            view.visibility = View.VISIBLE
        }
        return this
    }


    private fun setVisible(view: View, visible: Boolean): GeneralDialog {
        if (visible) {
            view.visibility = View.VISIBLE
        } else {
            view.visibility = View.INVISIBLE
        }
        return this
    }


    //==============================================================================================
    // 对外的方法
    //==============================================================================================
    fun setTitle(title: String): GeneralDialog {
        dialogTitle.text = title
        setVisible(dialogTitle, true)
        return this
    }


    fun setTitleColor(color: Int): GeneralDialog {
        dialogTitle.setTextColor(color)
        return this
    }


    fun setTitleSize(size: Float): GeneralDialog {
        dialogTitle.textSize = size
        return this
    }


    fun getTitleTextView(): TextView {
        return dialogTitle
    }


    fun setCancel(cancel: String): GeneralDialog {
        dialogCancel.text = cancel
        setGone(dialogCancel, false)
        return this
    }


    fun setCancelColor(color: Int): GeneralDialog {
        dialogCancel.setTextColor(color)
        return this
    }


    fun setCancelSize(size: Float): GeneralDialog {
        dialogCancel.textSize = size
        return this
    }


    fun getCancelTextView(): TextView {
        return dialogCancel
    }

    //点击取消
    fun setNegative(listener: OnNegativeListener): GeneralDialog {
        setGone(dialogCancel, false)
        onNegativeListener = listener
        return this
    }

    //取消 
    fun setNegative(): GeneralDialog {
        setGone(dialogCancel, false)
        return this
    }


    fun setConfirm(confirm: String): GeneralDialog {
        dialogConfirm.text = confirm
        setGone(dialogConfirm, false)
        return this
    }


    fun setConfirmColor(color: Int): GeneralDialog {
        dialogConfirm.setTextColor(color)
        return this
    }


    fun setConfirmSize(size: Float): GeneralDialog {
        dialogConfirm.textSize = size
        return this
    }


    fun getConfirmTextView(): TextView {
        return dialogConfirm
    }


    //确认
    fun setPositive(): GeneralDialog {
        setGone(dialogConfirm, false)
        return this
    }

    //确认
    fun setPositive(listener: OnPositiveListener): GeneralDialog {
        setGone(dialogConfirm, false)
        onPositiveListener = listener
        return this
    }


    //设置圆角大小
    fun setGradientRadius(radius: Float): GeneralDialog {
        shapeRadius = radius
        return this
    }

    //设置背景颜色
    fun setGradientColor(color: Int): GeneralDialog {
        shapeColor = color
        return this
    }

    //设置边框宽度 默认 0
    fun setGradientStockWidth(width: Int): GeneralDialog {
        shapeStockWidth = width
        return this
    }

    //设置边框的颜色 默认无色
    fun setGradientStockColor(color: Int): GeneralDialog {
        shapeStockColor = color
        return this
    }

    // 边框线间隔
    fun setGradientDashWidth(dashWidth: Float): GeneralDialog {
        shapeDashWidth = dashWidth
        return this
    }

    //边框线长度
    fun setGradientDashGap(dashGap: Float): GeneralDialog {
        shapeDashGap = dashGap
        return this
    }

    //添加自定义的布局
    fun setCustomView(layoutResId: Int): GeneralDialog {
        this.layoutResId = layoutResId
        return this
    }

    fun setCustomView(layoutResId: Int, listener: OnCustomListener): GeneralDialog {
        this.layoutResId = layoutResId
        this.onCustomListener = listener
        return this
    }

    fun setCustomView(view: View): GeneralDialog {
        this.customView = view
        return this
    }

    fun setCustomView(view: View,listener: OnCustomListener): GeneralDialog {
        this.customView = view
        this.onCustomListener = listener
        return this
    }



    private fun getGradientDrawable(): GradientDrawable {
        return getShapeDrawable(
            shapeColor,
            shapeRadius,
            shapeStockWidth,
            shapeStockColor,
            shapeDashWidth,
            shapeDashGap
        )
    }


    interface OnNegativeListener {
        fun onNegative(dialog: GeneralDialog)

    }

    interface OnPositiveListener {
        fun onPositive(dialog: GeneralDialog)

    }

    interface OnCustomListener {
        fun onCustom(view: View)

    }


}