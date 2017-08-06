package com.mz.cards

import android.view.View
import android.view.animation.Animation
import android.view.animation.Transformation

/**
 * Written with ❤! By M on 16/04/17.
 */

class ResizeAnimation internal constructor(private val view: View, private val startHeight: Int, private val endHeight: Int) : Animation() {

    init {
        this.duration = 350
    }

    override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
        view.layoutParams.height = (startHeight + (endHeight - startHeight) * interpolatedTime).toInt()
        view.requestLayout()
    }

    override fun willChangeBounds(): Boolean {
        return true
    }
}
