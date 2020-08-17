package dialog

import android.graphics.Color
import android.graphics.drawable.GradientDrawable


/**
 * 画边框方法
 * @param color      背景色
 * @param radius     圆角
 * @param stockWidth 边框宽度
 * @param stockColor 边框颜色
 * @param dashWidth  边框线间隔
 * @param dashGap    边框线长度
 * @return
 */
fun getShapeDrawable(
    color: Int,
    radius: Float,
    stockWidth: Int? = null,
    stockColor: Int? = null,
    dashWidth: Float? = null,
    dashGap: Float? = null
): GradientDrawable {
    val gradientDrawable = GradientDrawable()
    gradientDrawable.cornerRadius = radius
    gradientDrawable.setColor(color)
    gradientDrawable.setStroke(
        stockWidth ?: 0,
        stockColor ?: Color.parseColor("#ffffff"),
        dashWidth ?: 0f,
        dashGap ?: 1f
    )
    return gradientDrawable
}

/**
 * 画边框  倒圆角
 * @param color
 * @param topLeftRadius
 * @param topRightRadius
 * @param bottomLeftRadius
 * @param bottomRightRadius
 * @param stockWidth
 * @param stockColor
 * @return
 */
fun getShapeDrawable(
    color: Int,
    topLeftRadius: Float,
    topRightRadius: Float,
    bottomLeftRadius: Float,
    bottomRightRadius: Float,
    stockWidth: Int?,
    stockColor: Int?
): GradientDrawable {
    val gradientDrawable = GradientDrawable()
    val f = floatArrayOf(
        topLeftRadius,
        topLeftRadius,
        topRightRadius,
        topRightRadius,
        bottomLeftRadius,
        bottomLeftRadius,
        bottomRightRadius,
        bottomRightRadius
    )
    gradientDrawable.cornerRadii = f
    gradientDrawable.setColor(color)
    gradientDrawable.setStroke(stockWidth ?: 0, stockColor ?: Color.parseColor("#ffffff"))
    return gradientDrawable
}
