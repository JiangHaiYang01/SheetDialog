package dialog

import android.content.Context
import android.view.View
import android.widget.LinearLayout
import com.allens.lib_ios_dialog.R
import kotlinx.android.synthetic.main.dialog_loading.view.*

/**

 * @Author allens
 * @Date 2019-12-19-21:02
 * @Email 18856907654@163.com
 */
class LoadIngDialog constructor(context: Context) :
    BaseDialog(context = context) {

    override fun getLayoutId(): Int {
        return R.layout.dialog_loading
    }

    override fun onLayoutView(view: View) {

    }


    override fun create(): LoadIngDialog {
        super.create()
        return this
    }

    override fun show() {
        dialog.setCancelable(false)
        dialog.setCanceledOnTouchOutside(false)
        super.show()
    }

    override fun getInflateWidthContent(): Boolean {
        return true
    }

}