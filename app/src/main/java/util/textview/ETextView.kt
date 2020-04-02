package util.textview

import android.content.Context
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.widget.TextView


/**
 * @Description:  Android的屏幕碎片化严重，各种屏幕分辨率层出不穷，而在不同分辨率的屏幕上显示出一致的效果
 * TextView的行间距屏幕适配问题在研发和视觉之间纠缠已久
 * 一种简单优雅的的TextView行间距适配方案
 * @Author: zhangyan
 * @CreateDate:  2020/3/16 18:04
 */
class ETextView(context: Context) : TextView(context) {

    /**
     * 排除每行文字间的padding
     *
     * @param text
     */
    fun setCustomText(text: CharSequence?) {
        if (text == null) {
            return
        }

        // 获得视觉定义的每行文字的行高
        val lineHeight = textSize.toInt()

        val ssb: SpannableStringBuilder
        if (text is SpannableStringBuilder) {
            ssb = text
            // 设置LineHeightSpan
            ssb.setSpan(
                ExcludeInnerLineSpaceSpan(lineHeight),
                0, text.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        } else {
            ssb = SpannableStringBuilder(text)
            // 设置LineHeightSpan
            ssb.setSpan(
                ExcludeInnerLineSpaceSpan(lineHeight),
                0, text.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }

        // 调用系统setText()方法
        setText(ssb)
    }

}