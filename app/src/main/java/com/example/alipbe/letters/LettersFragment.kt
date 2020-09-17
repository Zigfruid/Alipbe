package com.example.alipbe.letters

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.alipbe.R
import kotlinx.android.synthetic.main.fragment_letters.*

class LettersFragment : Fragment(R.layout.fragment_letters), View.OnClickListener {

    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
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
        letterChemodan.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val action = LettersFragmentDirections.actionLettersFragmentToOneLetterFragment(v?.tag.toString().toInt())
        navController.navigate(action)
    }
}
