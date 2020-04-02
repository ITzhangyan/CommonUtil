package util.textview

import android.graphics.Paint
import android.text.style.LineHeightSpan



/**
 * @Description: java类作用描述
 * @Author: zhangyan
 * @CreateDate:  2020/3/16 18:03
 */
class ExcludeInnerLineSpaceSpan : LineHeightSpan {
    // TextView行高
    private val mHeight: Int

    constructor(height: Int) {
        mHeight = height
    }

    override fun chooseHeight(
        text: CharSequence, start: Int, end: Int,
        spanstartv: Int, lineHeight: Int,
        fm: Paint.FontMetricsInt
    ) {
        // 原始行高
        val originHeight = fm.descent - fm.ascent
        if (originHeight <= 0) {
            return
        }
        // 计算比例值
        val ratio = mHeight * 1.0f / originHeight
        // 根据最新行高，修改descent
        fm.descent = Math.round(fm.descent * ratio)
        // 根据最新行高，修改ascent
        fm.ascent = fm.descent - mHeight
    }
}