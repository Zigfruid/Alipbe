package com.example.alipbe.games

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
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
        tvRandomWord.text= items[random]
        divider()
    }
    private fun divider() {
        val st = tvRandomWord.text.toString()
        val chars = st.toCharArray()
        val layout = LinearLayout(requireContext())
        layout.orientation = LinearLayout.HORIZONTAL
        val layoutLP= LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
        layout.layoutParams=layoutLP
        layoutLP.gravity=Gravity.CENTER
          for (i in chars.indices){
            chars[i]
            val button = Button(requireContext())
            val buttonLP= LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            buttonLP.setMargins(20,20,20,20)
            buttonLP.gravity=Gravity.BOTTOM
            button.layoutParams=buttonLP
            button.text = chars[i].toString()
            button.textSize = 25f
            button.setBackgroundResource(R.color.colorPrimary)
            layout.addView(button)
        }
        layout_gameFragment2.addView(layout)
    }
}