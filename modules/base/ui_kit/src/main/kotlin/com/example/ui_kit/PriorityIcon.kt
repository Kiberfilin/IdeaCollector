package com.example.ui_kit

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView

class PriorityIcon : FrameLayout {
    private lateinit var container: FrameLayout
    private lateinit var rightBlueCorner: ImageView

    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(
        context: Context,
        attrs: AttributeSet,
        defStyle: Int
    ) : super(context, attrs, defStyle) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        val inflater = context
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.priority_icon_layout, this)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        container = findViewById(R.id.priorityIconContainer)
        rightBlueCorner =
            findViewById<ImageView?>(R.id.rightBlueCorner).apply { visibility = INVISIBLE }
    }

    companion object {
        const val PRIORITY_RED: Int = 0
        const val PRIORITY_YELLOW: Int = 1
        const val PRIORITY_GREEN: Int = 2
    }

    fun setPriority(priority: Int) {
        val backgroundColor = when (priority) {
            PRIORITY_RED    -> R.color.priority_color_red
            PRIORITY_YELLOW -> R.color.priority_color_yellow
            PRIORITY_GREEN  -> R.color.priority_color_green
            else            -> throw IllegalArgumentException(
                "Priority must be lower or equals PRIORITY_GREEN(2)"
            )
        }
        container.setBackgroundColor(resources.getColor(backgroundColor, context.theme))
    }

    fun setBlueCornerVisible(isBlueCornerVisible: Boolean) {
        rightBlueCorner.visibility = if (isBlueCornerVisible) {
            VISIBLE
        } else {
            INVISIBLE
        }
    }
}