package dialog

import android.app.Dialog
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import com.allens.lib_ios_dialog.R


abstract class BottomDialog(context: Context) : BaseDialog(context) {

    override fun getDialogStyle(): Int {
        return R.style.ActionSheetDialogStyle
    }

    override fun onLayoutViewBefore(dialog: Dialog) {
        val window = dialog.window
        window?.setGravity(Gravity.BOTTOM)
        val layoutParams = window?.attributes
        layoutParams?.x = 0
        layoutParams?.y = 0
        window?.attributes = layoutParams
    }
}