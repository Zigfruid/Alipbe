package com.example.alipbe.games

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.alipbe.R
import kotlinx.android.synthetic.main.fragment_game2.*
import kotlin.random.Random

class GameFragment2 : Fragment(R.layout.fragment_game2) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Ǵǵ Úú Ńń Íı Óó Áá
        val random = Random.nextInt(0,34)
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
        tvRandomWord.text= items[random]
        divider()
    }
    private fun divider() {
        val st = tvRandomWord.text.toString()
        val chars = st.toCharArray()
        val layout = LinearLayout(requireContext())
        layout.orientation = LinearLayout.VERTICAL
        layout.gravity = Gravity.CENTER_VERTICAL
        val innerLayout = LinearLayout(requireContext())
        innerLayout.orientation = LinearLayout.VERTICAL
        for (i in 1 until chars.size-1){
            chars[i]
            val tv = TextView(requireContext())
            tv.text = chars[i].toString()
            tv.textSize = 55f
            innerLayout.addView(tv)
        }
        layout.addView(innerLayout)
        layout_gameFragment2.addView(layout)

        tvFirstLetter.text = chars[0].toString()
        tvLastLetter.text = chars.last().toString()


    }
}