package com.example.spiderchart

import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.view.View
import java.nio.channels.FileLock


class ThinCircle(context: Context) : View(context) {

    val paint: Paint = Paint()

    //    val BIG_STROKE = 6f
    val BIG_STROKE = 13f

    //    val SMALL_STROKE = 2f
    val SMALL_STROKE = 1.5f
    val ARC_SWEEP = 45f


    @Volatile
    var lastRadSMall = 20f
//    var lastRadBig = 20f


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        for (i in 0 until 6) {
            drawGrayCircle(canvas, i * (BIG_STROKE + SMALL_STROKE))
//            drawYellowCircle(canvas, i + BIG_STROKE)
//            drawGrayCircle(canvas, i + BIG_STROKE)
        }
        for (i in 0 until 6) {
            draw1(i + 1, -90f + (+45 * i), canvas)
        }
    }

    fun draw1(times: Int, arcStart: Float, canvas: Canvas?) {
        for (i in 0 until times) {
            drawYellowCircle(canvas, i * (BIG_STROKE + SMALL_STROKE), arcStart)
        }
    }

    private fun drawGrayCircle(canvas: Canvas?, rad: Float) {
        val mX = width / 2f
        val mY = height / 2f
        val radius = 20f + (rad - ((BIG_STROKE / 2) + 1f)).fdp()
        lastRadSMall = rad.toFloat()
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = SMALL_STROKE.fdp()
        paint.setColor(Color.BLACK)
        canvas?.drawCircle(mX, mY, radius, paint)
    }

    private fun drawYellowCircle(canvas: Canvas?, rad: Float, arcStart: Float) {
        val mX = width / 2f
        val mY = height / 2f
        val radius = 20f + (rad).fdp()
        lastRadSMall = rad.toFloat()
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = BIG_STROKE.fdp()
        paint.setColor(Color.RED)
        paint.alpha = 100
        val oval = RectF(mX - radius, mY - radius, mX + radius, mY + radius)
        canvas?.drawArc(oval, arcStart, ARC_SWEEP, false, paint);
    }

}
