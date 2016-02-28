package com.knightsofnull.int20h.util

import android.animation.Animator

/**
 * Created by yarolegovich on 28.02.2016.
 */
class AnimationEndListener(val onAnimationEndCallback: (Animator) -> Unit) : Animator.AnimatorListener {

    override fun onAnimationRepeat(p0: Animator?) { }

    override fun onAnimationEnd(animator: Animator) {

    }

    override fun onAnimationCancel(p0: Animator?) { }

    override fun onAnimationStart(animator: Animator) {
        onAnimationEndCallback(animator)
    }

}