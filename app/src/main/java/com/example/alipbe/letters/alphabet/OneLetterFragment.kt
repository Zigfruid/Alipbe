package com.example.alipbe.letters.alphabet

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.alipbe.R
import kotlinx.android.synthetic.main.fragment_one_letter.*

class OneLetterFragment : Fragment(R.layout.fragment_one_letter) {

    private val safeArgs: OneLetterFragmentArgs by navArgs()
    private var currentId: Int = 1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = Navigation.findNavController(view)
        currentId = safeArgs.letterId
        setImg(currentId)
        btnNext.setOnClickListener {
            currentId += 1
            if (currentId < 35) {
                setImg(currentId)
            } else {
                currentId = 1
                setImg(currentId)
            }
        }
        btnPrev.setOnClickListener {
            if (currentId > 1) {
                currentId -= 1
                setImg(currentId)
            } else {
                currentId = 34
                setImg(currentId)
            }
        }
        homeBtn.setOnClickListener {
            navController.popBackStack()
        }
    }

    private fun setImg(id: Int) {
        letterImg.setImageResource(
            resources.getIdentifier(
                "dialog$id",
                "drawable",
                requireActivity().packageName
            )
        )
    }
}
