package com.example.alipbe.games

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipDescription
import android.content.res.Resources
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.*
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.alipbe.R
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.fragment_game2.*
import kotlin.random.Random


@Suppress("DEPRECATED_IDENTITY_EQUALS")
class GameFragment2 : Fragment(R.layout.fragment_game2), View.OnDragListener {

    val random = Random.nextInt(0, 34)
    val items: List<String> = listOf(
        "Alma",
        "Átkónshek",
        "Baliq",
        "Duwtar",
        "Etik",
        "Fontan",
        "Gul",
        "Ǵarbiz",
        "Hákke",
        "Xat",
        "Ílaq",
        "Iyne",
        "Júzim",
        "Kitap",
        "Qalem",
        "Lágen",
        "Muz",
        "Nan",
        "Qońiraw",
        "Oraq",
        "Órmekshi",
        "Piyaz",
        "Radio",
        "Sabin",
        "Tarezi",
        "Un",
        "Úyrek",
        "Vertolyot",
        "Waqit",
        "Yolka",
        "Zamark",
        "Shar",
        "Circul",
        "Chemodan"
    )

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Ǵǵ Úú Ńń Íı Óó Áá
        divider()

    }

    @RequiresApi(Build.VERSION_CODES.N)
    @SuppressLint("ClickableViewAccessibility")
    private fun divider() {
        val st = items[random]
        val chars = st.toCharArray()
//        val layout = FrameLayout(requireContext())
//        val lp = LinearLayout.LayoutParams(
//            LinearLayout.LayoutParams.MATCH_PARENT,
//            LinearLayout.LayoutParams.MATCH_PARENT
//        )
//        layout.layoutParams = lp
        for (i in chars.indices) {
            val dynamicButton = Button(requireContext())
            dynamicButton.text = chars[i].toString()
            dynamicButton.textSize = 50f
            dynamicButton.setTextColor(Color.WHITE)
            dynamicButton.setBackgroundColor(Color.BLUE)
            dynamicButton.layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT
            )
            val displayMetrics = DisplayMetrics()
            requireActivity().windowManager.defaultDisplay.getMetrics(displayMetrics)
            val x = (0..displayMetrics.widthPixels - 100).random()
            val y = (0..displayMetrics.heightPixels / 2 - 100).random()
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
            dynamicBtn.textSize = 50f
            dynamicBtn.layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT
            )
            dynamicBtn.setOnDragListener(this)
            dynamicBtn.tag = "button"
            llQuestion.addView(dynamicBtn)
//            dynamicButton.setOnTouchListener { view, motionEvent ->
//                if (motionEvent.action == MotionEvent.ACTION_MOVE) {
//                    view.y = motionEvent.rawY - view.height/2
//                    view.x = motionEvent.rawX - view.width/2
//                }
//                true
//            }
        }
        flAnswer.setOnDragListener(this)
        //llQuestion.setOnDragListener(this)
    }

    private fun toInt(param: Int): Int {
        return (param * Resources.getSystem().displayMetrics.density + 0.5f).toInt()
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
                Log.d("location x", event.x.toString())
                Log.d("location y", event.y.toString())
                return true
            }
            DragEvent.ACTION_DRAG_EXITED -> {
                return true
            }
            DragEvent.ACTION_DROP -> {
                //if (v == )
                val rightLetter: Button? = v as? Button
                val view: Button = event.localState as Button
                if (rightLetter?.text == view.text) {
                    rightLetter?.setBackgroundColor(Color.BLUE)
                    view.visibility = View.GONE
                } else {
                    val owner: ViewGroup
                    val button: Button = event.localState as Button
                    var destination: FrameLayout? = null
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