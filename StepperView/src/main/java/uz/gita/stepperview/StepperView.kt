package uz.gita.stepperview

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View

@SuppressLint("CustomViewStyleable")
class StepperView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) :
    View(context, attrs, defStyle) {
    private var count = 5

    init {
        val attr = context.obtainStyledAttributes(attrs, R.styleable.StepperView, 0, 0)
        count = attr.getInt(R.styleable.StepperView_android_stepSize, 7)

        attr.recycle()
    }

    private val paint = Paint().apply {
        style = Paint.Style.FILL
        color = Color.RED
    }
    private val paint2 = Paint().apply {
        style = Paint.Style.STROKE
        strokeWidth = 3.dp
        color = Color.BLACK
    }

    private val paintText = Paint().apply {
        textSize = 20.sp
        color = Color.WHITE
    }

    override fun onDraw(canvas: Canvas) {
        val radius = width / (count + 0.5f) / 2f * 0.6f
        val padding = radius / count * 8.5f
        for (i in 0 until count) {
            canvas.drawCircle(i * 2 * radius + radius + padding * i, height / 2f, radius, paint)
        }
        val bounds = Rect()
        paintText.getTextBounds("1", 0, 1, bounds)
        val textHeight = bounds.height()
        val textWidth = bounds.width()

        var k = 3
        var l = 1
        for (i in 0 until count) {

            canvas.drawText(
                (i + 1).toString(),
                radius * (k - 2) + padding * i - textWidth,
                height / 2f + textHeight / 2,
                paintText
            )
            k += 2
        }
        for (i in 0 until count - 1) {

            canvas.drawLine(
                radius * 2 * l + padding * i,
                height / 2f,
                radius * 2 * l + padding * (i + 1),
                height / 2f,
                paint2
            )
            l += 1
        }

    }
}