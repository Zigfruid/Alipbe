package com.agames.alipbe.letters

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.agames.alipbe.R
import com.agames.alipbe.dp
import kotlinx.android.synthetic.main.fragment_letters.*

class LettersFragment : Fragment(R.layout.fragment_letters), View.OnClickListener {

    private lateinit var navController: NavController
    private lateinit var params: FrameLayout.LayoutParams

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        val displayMetrics = DisplayMetrics()
        requireActivity().windowManager.defaultDisplay.getMetrics(displayMetrics)
        params = if (displayMetrics.widthPixels <= 1000) {
            FrameLayout.LayoutParams(
                55.dp,
                60.dp
            )
        } else {
            FrameLayout.LayoutParams(
                70.dp,
                60.dp
            )
        }
        params.setMargins(0, 0, 8, 0)
        for (i in 1..8) {
            val imgView = ImageView(requireContext())
            imgView.setOnClickListener(this)
            imgView.layoutParams = params
            imgView.setImageResource(resources.getIdentifier("letter$i", "drawable", requireContext().packageName))
            imgView.tag = i
            ll1.addView(imgView)
        }
        for (i in 9..16) {
            val imgView = ImageView(requireContext())
            imgView.setOnClickListener(this)
            imgView.layoutParams = params
            imgView.tag = i
            imgView.setImageResource(resources.getIdentifier("letter$i", "drawable", requireContext().packageName))
            ll2.addView(imgView)
        }
        for (i in 17..24) {
            val imgView = ImageView(requireContext())
            imgView.setOnClickListener(this)
            imgView.layoutParams = params
            imgView.tag = i
            imgView.setImageResource(resources.getIdentifier("letter$i", "drawable", requireContext().packageName))
            ll3.addView(imgView)
        }
        for (i in 25..32) {
            val imgView = ImageView(requireContext())
            imgView.setOnClickListener(this)
            imgView.layoutParams = params
            imgView.tag = i
            imgView.setImageResource(resources.getIdentifier("letter$i", "drawable", requireContext().packageName))
            ll4.addView(imgView)
        }
        for (i in 33..34) {
            val imgView = ImageView(requireContext())
            imgView.setOnClickListener(this)
            imgView.layoutParams = params
            imgView.tag = i
            imgView.setImageResource(resources.getIdentifier("letter$i", "drawable", requireContext().packageName))
            ll5.addView(imgView)
        }
    }

    override fun onClick(v: View?) {
        val action = LettersFragmentDirections.actionLettersFragmentToOneLetterFragment(v?.tag.toString().toInt())
        navController.navigate(action)
    }
}
