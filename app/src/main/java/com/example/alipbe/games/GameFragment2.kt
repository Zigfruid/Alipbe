package com.example.alipbe.games

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipDescription
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.example.alipbe.R
import kotlinx.android.synthetic.main.fragment_game2.*
import kotlin.random.Random


@Suppress("DEPRECATED_IDENTITY_EQUALS")
class GameFragment2 : Fragment(R.layout.fragment_game2) {

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Ǵǵ Úú Ńń Íı Óó Áá
        divider()

    }
    @SuppressLint("ClickableViewAccessibility")
    private fun divider() {
        val st = items[random]
        val chars = st.toCharArray()
        val layout = LinearLayout(requireContext())
        layout.orientation = LinearLayout.HORIZONTAL
        val lp = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
        layout.gravity = Gravity.CENTER
        layout.layoutParams = lp

        val bottomLayout = LinearLayout(requireContext())
        bottomLayout.orientation = LinearLayout.HORIZONTAL
        bottomLayout.gravity = Gravity.CENTER
        //val lpBottom = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        val lpZero = LinearLayout.LayoutParams(toInt(100), toInt(100))
        bottomLayout.setBackgroundResource(R.color.colorPrimary)

        for (i in chars.indices){
            chars[i]
            val dinamicButton = Button(requireContext())
            dinamicButton.text = chars[i].toString()
            dinamicButton.textSize = 55f
            dragListener()




            val dinamicButtonForm = LinearLayout(requireContext())
            dinamicButtonForm.layoutParams = lpZero
            dinamicButtonForm.weightSum = chars.size.toFloat()
            dinamicButtonForm.gravity = Gravity.CENTER
            dinamicButtonForm.setBackgroundResource(R.color.colorWhite)
            dinamicButtonForm.setOnDragListener(dragListener())


            dinamicButton.setOnLongClickListener {
                val clipText = "Дурыс"
                val item = ClipData.Item(clipText)
                val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
                val data = ClipData(clipText, mimeTypes, item)
                val dragShadowBuilder = View.DragShadowBuilder(it)
                it.startDragAndDrop(data, dragShadowBuilder, it, 0)
                it.visibility = View.VISIBLE
                true
            }

            dinamicButton.setOnTouchListener { v, me ->
                if (me.action === MotionEvent.ACTION_DOWN) {
                    val oldXvalue = me.x
                    val oldYvalue = me.y
                    Log.i("myTag", "Action Down $oldXvalue,$oldYvalue")
                } else if (me.action === MotionEvent.ACTION_MOVE) {
                    val params = LinearLayout.LayoutParams(v.width, v.height)
                    params.setMargins((me.rawX - (v.width / 5)).toInt(),
                        (me.rawY - (v.height)).toInt(),
                        (me.rawY - (v.width / 5)).toInt(),
                        (me.rawX - (v.height)).toInt())
                    v.layoutParams = params

                }
                true
            }

            layout.addView(dinamicButton)
            bottomLayout.addView(dinamicButtonForm)

        }
        layout.setOnDragListener(dragListener())
        layout_gameFragment2.addView(bottomLayout)
        layout_gameFragment2.addView(layout)

    }

    private fun dragListener(): View.OnDragListener? {
        return View.OnDragListener { view, event ->
            when (event.action) {
                DragEvent.ACTION_DRAG_STARTED -> {
                    event.clipDescription.hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)
                }
                DragEvent.ACTION_DRAG_ENTERED -> {
                    view.invalidate()
                    true
                }
                DragEvent.ACTION_DRAG_LOCATION -> true
                DragEvent.ACTION_DRAG_EXITED -> {
                    view.invalidate()
                    true
                }
                DragEvent.ACTION_DROP -> {
                    val item = event.clipData.getItemAt(0)
                    val dragData = item.text
                    Toast.makeText(requireContext(), dragData, Toast.LENGTH_SHORT).show()
                    view.invalidate()

                    val v = event.localState as View
                    val owner = v.parent as ViewGroup
                    owner.removeView(v)
                    val destination = view as LinearLayout
                    destination.addView(v)
                    v.visibility = View.VISIBLE
                    true
                }
                DragEvent.ACTION_DRAG_ENDED -> {
                    view.invalidate()
                    true
                }
                else -> false
            }
        }
    }
    private fun toInt(param: Int): Int{
        return (param * Resources.getSystem().displayMetrics.density + 0.5f).toInt()
    }
}