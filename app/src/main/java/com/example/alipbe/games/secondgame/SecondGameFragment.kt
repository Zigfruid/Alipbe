package com.example.alipbe.games.secondgame

import android.content.res.Resources
import android.graphics.Color
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.DragEvent
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.view.marginEnd
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.transition.Slide
import com.example.alipbe.DataHolder
import com.example.alipbe.R
import kotlinx.android.synthetic.main.fragment_second_game.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import nl.dionsegijn.konfetti.models.Shape
import nl.dionsegijn.konfetti.models.Size

class SecondGameFragment : Fragment(R.layout.fragment_second_game), View.OnDragListener {

    companion object {
        const val MAX_LETTERS = 33
        const val TEXT_SIZE = 50f
    }

    private var random = (0..MAX_LETTERS).random()
    private val items: List<String> = DataHolder.questionWords
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = Navigation.findNavController(view)
        divider()
        homeBtn.setOnClickListener {
            navController.popBackStack()
        }
    }

    private fun divider() {
        val st = items[random]
        imgQuestion.setImageResource(
            resources.getIdentifier(
                "letter_${random+1}",
                "drawable",
                requireContext().packageName
            )
        )
        layout_gameFragment2.setBackgroundResource(
            resources.getIdentifier(
                "game${(1..10).random()}",
                "drawable",
                requireContext().packageName
            )
        )
        val chars = st.toCharArray()
        val params = FrameLayout.LayoutParams(
            200,
            200
        )
        params.setMargins(8,8,8,8)
        for (i in chars.indices) {
            val dynamicButton = Button(requireContext())
            dynamicButton.text = chars[i].toString()
            dynamicButton.textSize = TEXT_SIZE
            dynamicButton.setTextColor(Color.WHITE)
            dynamicButton.setBackgroundResource(R.drawable.ic_btn5)
            dynamicButton.layoutParams = FrameLayout.LayoutParams(
                200,
                200
            )
            dynamicButton.setPadding(8,0,8,0)
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
            dynamicBtn.id = i
            dynamicBtn.text = chars[i].toString()
            dynamicBtn.setBackgroundResource(R.drawable.ic_btn6)
            dynamicBtn.setTextColor(Color.WHITE)
            dynamicBtn.textSize = TEXT_SIZE
            dynamicBtn.setPadding(8,0,8,0)
            dynamicBtn.layoutParams = params
            dynamicBtn.setOnDragListener(this)
            dynamicBtn.tag = "button"
            llQuestion.addView(dynamicBtn)
        }
        flAnswer.setOnDragListener(this)
    }
    private val Int.dp: Int
        get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()

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
                    rightLetter?.setBackgroundResource(R.drawable.ic_btn4)
                    rightLetter?.setTextColor(Color.WHITE)
                    rightLetter?.tag = "1"
                    var string = 0
                    for (i in items[random].indices) {
                        val button = requireActivity().findViewById<Button>(i)
                        if (button.tag == "1") {
                            string += 1
                        }
                    }
                    if (string == items[random].length) {
                        random = (0..MAX_LETTERS).random()
                        viewKonfetti.build()
                            .addColors(Color.YELLOW, Color.GREEN, Color.MAGENTA)
                            .setDirection(0.0, 359.0)
                            .setSpeed(5f, 10f)
                            .setFadeOutEnabled(true)
                            .setTimeToLive(1000L)
                            .addShapes(Shape.Square, Shape.Circle)
                            .addSizes(Size(12))
                            .setPosition(-50f, viewKonfetti.width + 50f, -50f, -50f)
                            .streamFor(300,2000L)
                        GlobalScope.launch {
                            delay(3000L)
                            activity?.runOnUiThread {
                                removeView()
                                divider()
                            }
                        }
                    }
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

    private fun removeView() {
        flAnswer.removeAllViewsInLayout()
        flAnswer.invalidate()
        llQuestion.removeAllViewsInLayout()
        llQuestion.invalidate()
    }
}
