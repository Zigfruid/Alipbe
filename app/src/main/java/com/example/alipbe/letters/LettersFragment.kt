package com.example.alipbe.letters

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.alipbe.R
import com.example.alipbe.dp
import kotlinx.android.synthetic.main.fragment_letters.*

class LettersFragment : Fragment(R.layout.fragment_letters), View.OnClickListener {

    private lateinit var navController: NavController
    private lateinit var params: FrameLayout.LayoutParams

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        val displayMetrics = DisplayMetrics()
        requireActivity().windowManager.defaultDisplay.getMetrics(displayMetrics)
        if (displayMetrics.widthPixels <= 1000) {
             params = FrameLayout.LayoutParams(
                55.dp,
                60.dp
            )
        } else {
            params = FrameLayout.LayoutParams(
                70.dp,
                60.dp
            )
        }
        params.setMargins(0, 0, 8, 0)
        for (i in 1..8){
            val imgView = ImageView(requireContext())
            imgView.setOnClickListener(this)
            imgView.layoutParams = params
            imgView.setImageResource(resources.getIdentifier("letter$i", "drawable", requireContext().packageName))
            imgView.tag = i
            ll1.addView(imgView)
        }
        for (i in 9..16){
            val imgView = ImageView(requireContext())
            imgView.setOnClickListener(this)
            imgView.layoutParams = params
            imgView.tag = i
            imgView.setImageResource(resources.getIdentifier("letter$i", "drawable", requireContext().packageName))
            ll2.addView(imgView)
        }
        for (i in 17..24){
            val imgView = ImageView(requireContext())
            imgView.setOnClickListener(this)
            imgView.layoutParams = params
            imgView.tag = i
            imgView.setImageResource(resources.getIdentifier("letter$i", "drawable", requireContext().packageName))
            ll3.addView(imgView)
        }
        for (i in 25..32){
            val imgView = ImageView(requireContext())
            imgView.setOnClickListener(this)
            imgView.layoutParams = params
            imgView.tag = i
            imgView.setImageResource(resources.getIdentifier("letter$i", "drawable", requireContext().packageName))
            ll4.addView(imgView)
        }
        for (i in 33..34){
            val imgView = ImageView(requireContext())
            imgView.setOnClickListener(this)
            imgView.layoutParams = params
            imgView.tag = i
            imgView.setImageResource(resources.getIdentifier("letter$i", "drawable", requireContext().packageName))
            ll5.addView(imgView)
        }
        /*
        letterAlma.setOnClickListener(this)
        letterAtkonshek.setOnClickListener(this)
        letterBaliq.setOnClickListener(this)
        letterDuwtar.setOnClickListener(this)
        letterEtik.setOnClickListener(this)
        letterFontan.setOnClickListener(this)
        letterGul.setOnClickListener(this)
        letterGarbiz.setOnClickListener(this)
        letterHakke.setOnClickListener(this)
        letterXat.setOnClickListener(this)
        letterIlaq.setOnClickListener(this)
        letterIyne.setOnClickListener(this)
        letterJuzim.setOnClickListener(this)
        letterKitap.setOnClickListener(this)
        letterQalem.setOnClickListener(this)
        letterLagen.setOnClickListener(this)
        letterMuz.setOnClickListener(this)
        letterNan.setOnClickListener(this)
        letterQoniraw.setOnClickListener(this)
        letterOraq.setOnClickListener(this)
        letterOrmekshi.setOnClickListener(this)
        letterPiyaz.setOnClickListener(this)
        letterRadio.setOnClickListener(this)
        letterSabin.setOnClickListener(this)
        letterTarezi.setOnClickListener(this)
        letterUn.setOnClickListener(this)
        letterUyrek.setOnClickListener(this)
        letterVertolyot.setOnClickListener(this)
        letterWaqit.setOnClickListener(this)
        letterYolka.setOnClickListener(this)
        letterZamark.setOnClickListener(this)
        letterShar.setOnClickListener(this)
        letterCircul.setOnClickListener(this)
        letterChemodan.setOnClickListener(this)*/
    }

    override fun onClick(v: View?) {
        val action = LettersFragmentDirections.actionLettersFragmentToOneLetterFragment(v?.tag.toString().toInt())
        navController.navigate(action)
    }
}
