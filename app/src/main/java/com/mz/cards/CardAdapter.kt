package com.mz.cards

import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.OvershootInterpolator
import android.view.animation.ScaleAnimation
import android.view.animation.TranslateAnimation
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView

import com.google.android.flexbox.FlexboxLayout
import com.loopeer.cardstack.CardStackView

import de.hdodenhof.circleimageview.CircleImageView

class CardAdapter(context: Context) : com.loopeer.cardstack.StackAdapter<Person>(context) {

    private val mTextExpandAnimation = AnimationSet(false)
    private val mTextCollapseAnimation = AnimationSet(false)
    private val mProfileIconExpandAnimation = ScaleAnimation(1.0f, 1.4f, 1.0f, 1.4f)
    private val mProfileIconCollapseAnimation = ScaleAnimation(1.4f, 1.0f, 1.4f, 1.0f)
    private var mInnerLayoutAnimation: TranslateAnimation? = null

    init {
        initializeTextAnimations()
        initializeIconAnimations()
        initializeInnerLayoutAnimation()
    }

    private fun initializeTextAnimations() {
        mTextExpandAnimation.addAnimation(TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.095f, Animation.ABSOLUTE, 0.0f, Animation.ABSOLUTE, 0.0f))
        val scaleAnimation = ScaleAnimation(1.0f, 1.12f, 1.0f, 1.12f, ScaleAnimation.RELATIVE_TO_SELF, 0.0f, ScaleAnimation.RELATIVE_TO_SELF, 0.0f)
        scaleAnimation.interpolator = OvershootInterpolator(4.0f)
        mTextExpandAnimation.addAnimation(scaleAnimation)
        mTextExpandAnimation.fillAfter = true
        mTextExpandAnimation.duration = 400
        mTextExpandAnimation.startOffset = 70
        mTextCollapseAnimation.addAnimation(TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0.095f, Animation.RELATIVE_TO_PARENT, 0.0f, Animation.ABSOLUTE, 0.0f, Animation.ABSOLUTE, 0.0f))
        mTextCollapseAnimation.addAnimation(ScaleAnimation(1.12f, 1.0f, 1.12f, 1.0f, ScaleAnimation.RELATIVE_TO_SELF, 0.0f, ScaleAnimation.RELATIVE_TO_SELF, 0.0f))
        mTextCollapseAnimation.fillAfter = true
        mTextCollapseAnimation.duration = 300
    }

    private fun initializeIconAnimations() {
        mProfileIconExpandAnimation.duration = 600
        mProfileIconExpandAnimation.interpolator = OvershootInterpolator(2.9f)
        mProfileIconExpandAnimation.fillAfter = true
        mProfileIconExpandAnimation.startOffset = 70
        mProfileIconCollapseAnimation.duration = 300
        mProfileIconCollapseAnimation.fillAfter = true
    }

    private fun initializeInnerLayoutAnimation() {
        mInnerLayoutAnimation = TranslateAnimation(Animation.RELATIVE_TO_SELF, -0.175f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f)
        mInnerLayoutAnimation!!.duration = 750
    }

    override fun getItemCount(): Int {
        return super.getItemCount()
    }

    override fun onCreateView(parent: ViewGroup, viewType: Int): CardStackView.ViewHolder {
        val view = layoutInflater.inflate(R.layout.collegue_item, parent, false)
        return ColorItemViewHolder(view)
    }

    override fun bindView(data: Person, position: Int, holder: CardStackView.ViewHolder) {
        if (holder is ColorItemViewHolder) {
            holder.onBind(data, position)
        }
    }

    internal inner class ColorItemViewHolder(private val view: View) : CardStackView.ViewHolder(view) {
        var dragLayout: RelativeLayout = view.findViewById(R.id.dragLayout) as RelativeLayout
        var innerLayout: LinearLayout = view.findViewById(R.id.inner) as LinearLayout
        var profileIconContainer: FlexboxLayout = view.findViewById(R.id.profileIconContainer) as FlexboxLayout
        var profileIcon: CircleImageView = view.findViewById(R.id.profileIcon) as CircleImageView
        var identifier: CircleImageView = view.findViewById(R.id.identifier) as CircleImageView
        var companyIcon: CircleImageView = view.findViewById(R.id.companyIcon) as CircleImageView
        var colleagueName: TextView = view.findViewById(R.id.colleagueName) as TextView
        var companyName: TextView = view.findViewById(R.id.companyName) as TextView
        var position: TextView = view.findViewById(R.id.position) as TextView
        private var firstTime = true

        private val dragListener = object : View.OnTouchListener {

            private var startPointY = 0.0f
            private var movementY = 0.0f
            private var initialYPosi = 0.0f
            private var startPointX = 0.0f
            private var movementX = 0.0f
            private var initialXPosi = 0.0f

            override fun onTouch(v: View, event: MotionEvent): Boolean {
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        startPointY = event.rawY
                        startPointX = event.rawX
                        if (initialYPosi == 0.0f) {
                            initialYPosi = view.translationY
                        }
                        if (initialXPosi == 0.0f) {
                            initialXPosi = view.translationX
                        }
                    }
                    MotionEvent.ACTION_MOVE -> {
                        movementY = event.rawY - startPointY
                        movementX = event.rawX - startPointX
                        view.translationY = initialYPosi + movementY
                        view.translationX = initialXPosi + movementX
                    }
                    MotionEvent.ACTION_UP -> {
                        if (movementY > 350) {
                            view.translationY = initialYPosi
                            view.translationX = initialXPosi
                            view.performClick()
                        } else {
                            view.animate().translationY(initialYPosi).translationX(initialXPosi)
                        }
                        startPointY = 0.0f
                        movementY = 0.0f
                        startPointX = 0.0f
                        movementX = 0.0f
                    }
                }
                return true
            }
        }

        init {
            dragLayout.setOnTouchListener(dragListener)
            innerLayout.setOnTouchListener(dragListener)
        }

        override fun onItemExpand(b: Boolean) {
            innerLayout.visibility = if (b) View.VISIBLE else View.GONE
            dragLayout.visibility = if (b) View.VISIBLE else View.GONE
            if (b && !firstTime) {
                (context as MainActivity).expandCards()
                innerLayout.startAnimation(mInnerLayoutAnimation)
                colleagueName.startAnimation(mTextExpandAnimation)
                companyName.startAnimation(mTextExpandAnimation)
                profileIconContainer.startAnimation(mProfileIconExpandAnimation)
            } else if (!firstTime) {
                (context as MainActivity).shrinkCards()
                colleagueName.startAnimation(mTextCollapseAnimation)
                companyName.startAnimation(mTextCollapseAnimation)
                profileIconContainer.startAnimation(mProfileIconCollapseAnimation)
            } else {
                firstTime = false
            }
        }

        fun onBind(data: Person, position: Int) {
            colleagueName.text = data.name
            companyName.text = data.company
            this.position.text = data.position
            when (data.type) {
                PersonType.FRIEND -> identifier.setImageResource(R.color.green)
                PersonType.COLLEAGUE -> identifier.setImageResource(R.color.yellow)
                PersonType.BUYER -> identifier.setImageResource(R.color.pink)
            }
            when (data.id % 6) {
                0 -> {
                    profileIcon.setImageResource(R.drawable.a_123)
                    companyIcon.setImageResource(R.drawable.dell)
                }
                1 -> {
                    profileIcon.setImageResource(R.drawable.b_128)
                    companyIcon.setImageResource(R.drawable.bg)
                }
                2 -> {
                    profileIcon.setImageResource(R.drawable.c_127)
                    companyIcon.setImageResource(R.drawable.star)
                }
                3 -> {
                    profileIcon.setImageResource(R.drawable.e_126)
                    companyIcon.setImageResource(R.drawable.twitter_new)
                }
                4 -> {
                    profileIcon.setImageResource(R.drawable.f_125)
                    companyIcon.setImageResource(R.drawable.lego)
                }
                5 -> {
                    profileIcon.setImageResource(R.drawable.g_124)
                    companyIcon.setImageResource(R.drawable.br)
                }
            }
        }
    }
}