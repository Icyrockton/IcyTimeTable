package com.icyrockton.icytimetable

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView

class IcyRowTimeInfoDecoration(
    private val width: Int,//宽度
    private val perCourseHeight: Int,// 每节课的高度
    private val textColor: Int,//字体颜色
    private val numberTextSize: Float, //数字的字体大小
    private val timeTextSize: Float,//时间的字体大小
    private val rowNumberList: List<IcyRowInfo>,// 行
    private val totalCoursePerDay:Int//每天的总课程
) : RecyclerView.ItemDecoration() {

    private val rowNumberPaint = Paint().apply {
        color = textColor
        isAntiAlias = true
        textSize = numberTextSize
    }
    private val timePaint = Paint().apply {
        color = textColor
        isAntiAlias = true
        textSize = timeTextSize
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
        val adapter = parent.adapter ?: return
        if (parent.childCount <= 0) return


        val topView = parent.children.filter { it.top <= parent.paddingTop }.minBy { it.top } ?: return//最上方的view
        val topIcyRowInfo=rowNumberList.getOrNull(topView.layoutPosition) ?:return
        var startRow=topIcyRowInfo.rowNumber // 开始行
        var gap= 0
        while (true){
            if (startRow > totalCoursePerDay +1 )
                break
            val top=topView.top+gap
            val bottom=top+perCourseHeight
            c.drawTextAtCenter(startRow.toString(),Rect(0,top,width,bottom),rowNumberPaint)
            if (bottom > parent.height-parent.paddingBottom)
                break
            startRow++
            gap+=perCourseHeight
        }
    }

    fun Canvas.drawTextAtCenter(text: String, rect: Rect, paint: Paint) {
        val baseX = rect.centerX().toFloat() - paint.measureText(text) / 2f
        val textBounds = Rect().apply { paint.getTextBounds(text, 0, text.length - 1, this) }
        val baseY = rect.centerY() +
                if (textBounds.height() != 0) textBounds.height() / 2f
                else -(paint.fontMetrics.ascent + paint.fontMetrics.descent) / 2f
        drawText(text, baseX, baseY, paint)
    }
    private inline val View.layoutPosition
        get() = (layoutParams as RecyclerView.LayoutParams).viewLayoutPosition

    data class IcyRowInfo(val rowNumber: Int)

}