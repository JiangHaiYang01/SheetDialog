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

    override fun getDialogFromBottom(): Boolean {
        return true
    }
}