package com.example.alipbe.games.secondgame

import android.graphics.Color
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.DragEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.example.alipbe.DataHolder
import com.example.alipbe.R
import kotlinx.android.synthetic.main.fragment_game2.*

class SecondGameFragment : Fragment(R.layout.fragment_game2), View.OnDragListener {

    companion object {
        const val MAX_LETTERS = 33
        const val TEXT_SIZE = 50f
    }

    private val random = (0..MAX_LETTERS).random()
    private val items: List<String> = DataHolder.questionWords
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        divider()
    }

    private fun divider() {
        val st = items[random]
        val chars = st.toCharArray()
        for (i in chars.indices) {
            val dynamicButton = Button(requireContext())
            dynamicButton.text = chars[i].toString()
            dynamicButton.textSize = TEXT_SIZE
            dynamicButton.setTextColor(Color.WHITE)
            dynamicButton.setBackgroundColor(Color.BLUE)
            dynamicButton.layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT
            )
            val displayMetrics = DisplayMetrics()
            requireActivity().windowManager.defaultDisplay.getMetrics(displayMetrics)
            val x = (0..displayMetrics.widthPixels - 150).random()
            val y = (0..displayMetrics.heightPixels / 2 - 150).random()
            dynamicButton.x = x.toFloat()
            dynamicButton.y = y.toFloat()
            dynamicButton.setOnLongClickListener {
                val dragShadowBuilder = View.DragShadowBuilder(it)
                it.startDragAndDrop(null, dragShadowBuilder, it, 0)
                it.visibility = View.VISIBLE
                true
            }
            flAnswer.addView(dynamicButton)
            val dynamicBtn = Button(requireContext())
            dynamicBtn.text = chars[i].toString()
            dynamicBtn.setBackgroundColor(Color.TRANSPARENT)
            dynamicBtn.setTextColor(Color.WHITE)
            dynamicBtn.textSize = TEXT_SIZE
            dynamicBtn.layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT
            )
            dynamicBtn.setOnDragListener(this)
            dynamicBtn.tag = "button"
            llQuestion.addView(dynamicBtn)
        }
        flAnswer.setOnDragListener(this)
    }

    override fun onDrag(v: View?, event: DragEvent?): Boolean {
        when (event?.action) {
            DragEvent.ACTION_DRAG_STARTED -> {
                return true
            }
            DragEvent.ACTION_DRAG_ENTERED -> {
                return true
            }
            DragEvent.ACTION_DRAG_LOCATION -> {
                return true
            }
            DragEvent.ACTION_DRAG_EXITED -> {
                return true
            }
            DragEvent.ACTION_DROP -> {
                val rightLetter: Button? = v as? Button
                val view: Button = event.localState as Button
                if (rightLetter?.text == view.text) {
                    rightLetter?.setBackgroundColor(Color.BLUE)
                    view.visibility = View.GONE
                } else {
                    val owner: ViewGroup
                    val button: Button = event.localState as Button
                    val destination: FrameLayout?
                    if (v?.tag == "layout") {
                        owner = button.parent as ViewGroup
                        owner.removeView(view)
                        destination = v as? FrameLayout
                        destination?.addView(view)
                        button.visibility = View.VISIBLE
                        button.x = event.x - view.width / 2
                        button.y = event.y - view.height / 2
                    }
                }
                return true
            }
            DragEvent.ACTION_DRAG_ENDED -> {
                return true
            }
            else -> {
                return false
            }
        }
    }
}
