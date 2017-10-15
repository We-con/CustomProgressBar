package com.wecon.custom.customprogressbar

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

/**
 * Created by mangob on 2017. 10. 15..
 */
class LineProgressBar : View {

    private lateinit var beginBarPaint: Paint
    private lateinit var midBarPaint: Paint
    private lateinit var finalBarPaint: Paint
    private var intervalSize = 12f
    private var partialSize = 18f

    private var progress: Int = 0

    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        var typedArray: TypedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.LineProgress, 0, 0)
        var beginBarColor: Int
        var midBarColor: Int
        var finalBarColor: Int

        try {
            progress = typedArray.getInt(R.styleable.LineProgress_progress, 0)
            beginBarColor = typedArray.getColor(R.styleable.LineProgress_progress_color_begin, Color.YELLOW)
            midBarColor = typedArray.getColor(R.styleable.LineProgress_progress_color_mid, Color.GREEN)
            finalBarColor = typedArray.getColor(R.styleable.LineProgress_progress_color_final, Color.BLUE)
            intervalSize = typedArray.getDimension(R.styleable.LineProgress_progress_interval, 8f)
            partialSize = typedArray.getDimension(R.styleable.LineProgress_progress_partial, 12f)
        } finally {
            typedArray.recycle()
        }

        beginBarPaint = Paint().apply {
            color = beginBarColor
            style = Paint.Style.STROKE
            strokeWidth = partialSize
        }

        midBarPaint = Paint().apply {
            color = midBarColor
            style = Paint.Style.STROKE
            strokeWidth = partialSize
        }

        finalBarPaint = Paint().apply {
            color = finalBarColor
            style = Paint.Style.STROKE
            strokeWidth = partialSize
        }

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        var curr = partialSize/2
        while(curr < width) {
            if(curr > width*(progress/100f)) {
                beginBarPaint.alpha = 77
                midBarPaint.alpha = 77
                finalBarPaint.alpha = 77
            } else {
                beginBarPaint.alpha = 255
                midBarPaint.alpha = 255
                finalBarPaint.alpha = 255
            }

            if(curr < width*0.3) {
                canvas!!.drawLine(curr, 0f, curr, height.toFloat(), beginBarPaint)
            } else if(curr < width*0.7) {
                canvas!!.drawLine(curr, 0f, curr, height.toFloat(), midBarPaint)
            } else {
                canvas!!.drawLine(curr, 0f, curr, height.toFloat(), finalBarPaint)
            }

            curr += partialSize + intervalSize;
        }

    }

}