package com.icyrockton.icytimetable

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView

class IcyColInfoDecoration(
    private val columnCount:Int, //列数    若输入为7 有7列     0-6列有值
    private val height:Int, //列信息的高度
    private val colTextColor:Int,
    private val colTextSize: Float
)
    :RecyclerView.ItemDecoration() {

    private val textPaint = Paint().apply {
        color = colTextColor
        isAntiAlias = true
        textSize = colTextSize
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
        if (parent.childCount < 0) return
        val leftView=parent.children.minBy { it.left } ?:return
        val columnWidth=leftView.width//宽度
        val top=0
        val bottom=height
        for (i in 1..columnCount){
            val left=leftView.left + (i-1) * columnWidth
            val right=left+columnWidth
            c.drawTextAtCenter(i.toString(),Rect(left, top, right, bottom),textPaint)
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
    companion object{
        private const val TAG = "IcyColInfoDecoration"
    }
}