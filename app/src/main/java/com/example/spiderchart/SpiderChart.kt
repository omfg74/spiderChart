package com.example.spiderchart

import android.content.Context
import android.graphics.*
import android.view.View

class SpiderChart(context: Context) : View(context) {

    private var arcStepDeg = 0f
    private val startPositionDeg = -45f
    private val bigLineStep = 10.dp()
    private val dividerWidth = 1f
    private val weightCount = 10//need to setUp max weight
    private var items: ArrayList<SpiderItem>? = null
    private val paintMain = Paint()
    private val paintLines = Paint()
    private val paintStraightLines = Paint()
    private val paintTextLines = Paint()
    private val paintText = Paint()
    private var dotArray: ArrayList<Pair<Float, Float>>? = ArrayList()


    fun setItems(items: ArrayList<SpiderItem>) {
        if (items.size > 8) return
        arcStepDeg = 360f / items.size
        this.items = items
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        for (i in 0 until (items?.size ?: 0)) {
            drawArc(canvas, items?.get(i), i)
        }
        for (i in weightCount downTo 0) {
            drawCircle(canvas, i)
        }
        for (i in 0 until (items?.size ?: 0)) {
            drawLines(canvas, i)
        }
        for (i in 0 until (items?.size ?: 0)) {
            drawTextLine(canvas, i, items?.get(i))
        }
    }

    private fun drawTextLine(canvas: Canvas?, count: Int, item: SpiderItem?) {
        val textPath: Path = Path()
        paintTextLines.color = Color.BLACK
        paintTextLines.strokeWidth = dividerWidth
        val x1 = dotArray?.get(count)?.first ?: return
        val y1 = dotArray?.get(count)?.second ?: return
        var x2: Float = 0f
        var y2 = 0f
        if (count == (dotArray?.size ?: 0) - 1) {
            x2 = dotArray?.get(0)?.first ?: return
            y2 = dotArray?.get(0)?.second ?: return
        } else {
            x2 = dotArray?.get(count + 1)?.first ?: return
            y2 = dotArray?.get(count + 1)?.second ?: return
        }

//        canvas?.drawLine(x1, y1, x2, y2, paintTextLines)
        textPath.moveTo(x1, y1)
        textPath.lineTo(x2, y2)
//        canvas?.drawPath(textPath, paintTextLines)
        paintText.setTextSize(20.fdp())
        paintText.color = Color.GREEN
        paintText.style = Paint.Style.FILL
        paintText.isAntiAlias = true
        val measurePath = PathMeasure(textPath, false)
        val measureText = paintText.measureText(item?.name ?: "")
        canvas?.drawTextOnPath(
            item?.name ?: "",
            textPath,
            ((measurePath.length / 2) - (measureText / 2)),
            0f,
            paintText
        )
//        canvas?.drawRect(rectF, paintMain)
//        canvas?.drawTextOnPath()

//        textPath.addRect()

    }


    private fun drawCircle(canvas: Canvas?, count: Int) {
        val mX = width / 2f
        val mY = height / 2f
        val radius = (count * (bigLineStep)).toFloat()
        paintLines.style = Paint.Style.STROKE
        paintLines.strokeWidth = dividerWidth
        paintLines.color = Color.BLACK
        canvas?.drawCircle(mX, mY, radius, paintLines)
    }


    private fun drawLines(canvas: Canvas?, count: Int) {
        val mX = width / 2f
        val mY = height / 2f
        paintStraightLines.color = Color.BLACK
        paintStraightLines.strokeWidth = dividerWidth
        val rad = ((bigLineStep) * (weightCount - 2))
        val cos = Math.cos(Math.toRadians(count * arcStepDeg.toDouble()))
        val sin = Math.sin(Math.toRadians(count * arcStepDeg.toDouble()))
        val x1 = (mX + (rad * cos) - (rad * sin)).toFloat()
        val y1 = (mY + (rad * sin) + (rad * cos)).toFloat()
        canvas?.drawLine(
            mX,
            mY,
            x1,
            y1,
            paintStraightLines
        )
        dotArray?.add(Pair(x1, y1))
    }


    private fun drawArc(canvas: Canvas?, item: SpiderItem?, count: Int) {
        val mX = width / 2f
        val mY = height / 2f
        if ((item?.weight ?: 0) > 10) {
            item?.weight = 10
        }
//        item?.weight = (item?.weight ?: 0) + 4.dp()
        paintMain.color = item?.color ?: Color.YELLOW
//        paint.alpha = 100
        paintMain.style = Paint.Style.FILL_AND_STROKE
        val oval = RectF(
            (mX - (item?.weight?.times(bigLineStep) ?: 0)).toFloat(),
            (mY - (item?.weight?.times(bigLineStep) ?: 0)).toFloat(),
            (mX + (item?.weight?.times(bigLineStep) ?: 0)).toFloat(),
            (mY + (item?.weight?.times(bigLineStep) ?: 0)).toFloat()
        )
        canvas?.drawArc(oval, startPositionDeg + (count * arcStepDeg), arcStepDeg, true, paintMain)
    }
}