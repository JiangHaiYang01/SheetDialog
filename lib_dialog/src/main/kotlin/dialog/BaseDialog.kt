package dialog

import android.app.Dialog
import android.content.Context
import android.os.Build
import android.transition.Transition
import android.view.*
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import com.allens.lib_ios_dialog.R

abstract class BaseDialog(private val context: Context) {
    private var windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    var display: Display = windowManager.defaultDisplay

    lateinit var dialog: Dialog


    open fun create(): BaseDialog {
        val view = LayoutInflater.from(context).inflate(getLayoutId(), null)
        dialog = Dialog(context, getDialogStyle())
        onBaseDialog(dialog)
        dialog.setContentView(view)


        val params = FrameLayout.LayoutParams(
            if (getInflateWidthContent()) {
                LinearLayout.LayoutParams.WRAP_CONTENT
            } else {
                (display.width * getDialogWidth()).toInt()
            },
            if (getInflateHeightFull()) {
                display.height
            } else {
                LinearLayout.LayoutParams.WRAP_CONTENT
            }
        )
        view?.layoutParams = params

        onLayoutViewBefore(dialog)

        onLayoutView(view)
        return this
    }


    //加载布局之前处理一些其他事情
    open fun onLayoutViewBefore(dialog: Dialog) {

    }

    //加载布局
    abstract fun getLayoutId(): Int

    //传递view
    abstract fun onLayoutView(view: View)

    //dialog 样式
    open fun getDialogStyle(): Int {
        return R.style.AlertDialogStyle
    }

    //返回dialog
    open fun onBaseDialog(dialog: Dialog) {

    }

    //是否撑满高度
    open fun getInflateHeightFull(): Boolean {
        return false
    }

    //是否自适应宽度
    open fun getInflateWidthContent(): Boolean {
        return false
    }

    // dialog 宽度 * 屏幕宽度 max 1.0
    open fun getDialogWidth(): Double {
        return 0.8
    }


    //点击其他位置是否能够取消
    open fun setCanceledOnTouchOutside(cancel: Boolean): BaseDialog {
        dialog.setCanceledOnTouchOutside(cancel)
        return this
    }


    // 是否点击返回能够取消
    open fun setCancelable(cancel: Boolean): BaseDialog {
        dialog.setCancelable(cancel)
        return this
    }

    open fun show() {
        dialog.show()
    }

    open fun dismiss() {
        if (dialog.isShowing)
            dialog.dismiss()
    }

    open fun isShowing(): Boolean {
        return dialog.isShowing
    }


    fun setWindowAnimations(resId: Int) {
        dialog.window?.setWindowAnimations(resId)
    }
}