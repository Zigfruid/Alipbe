package com.example.alipbe.letters

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.alipbe.R
import kotlinx.android.synthetic.main.fragment_letters.*

class LettersFragment: Fragment(R.layout.fragment_letters) {

    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        letterAlma.setOnClickListener {
             navController.navigate(R.id.action_lettersFragment_to_letterA2)
        }
        letterAtkonshek.setOnClickListener {
            navController.navigate(R.id.action_lettersFragment_to_letterAt)

        }

        letterBaliq.setOnClickListener {
            navController.navigate(R.id.action_lettersFragment_to_letterB)

        }
        letterDuwtar.setOnClickListener {
            navController.navigate(R.id.action_lettersFragment_to_letterD)

        }
        letterFontan.setOnClickListener {
            navController.navigate(R.id.action_lettersFragment_to_letterF)

        }
        letterEtik.setOnClickListener {
            navController.navigate(R.id.action_lettersFragment_to_letterE)

        }

        letterGarbiz.setOnClickListener {
            navController.navigate(R.id.action_lettersFragment_to_letterGa)

        }
        letterGul.setOnClickListener {
            navController.navigate(R.id.action_lettersFragment_to_letterG)

        }
        letterHakke.setOnClickListener {
            navController.navigate(R.id.action_lettersFragment_to_letterH)

        }
        letterXat.setOnClickListener {
            navController.navigate(R.id.action_lettersFragment_to_letterX)

        }
        letterIlaq.setOnClickListener {
            navController.navigate(R.id.action_lettersFragment_to_letterIl)

        }
        letterIyne.setOnClickListener {
            navController.navigate(R.id.action_lettersFragment_to_letterIy)

        }
        letterJuzim.setOnClickListener {
            navController.navigate(R.id.action_lettersFragment_to_letterJ)

        }

        letterKitap.setOnClickListener {
            navController.navigate(R.id.action_lettersFragment_to_letterK)

        }
        letterQalem.setOnClickListener {
            navController.navigate(R.id.action_lettersFragment_to_letterQ)

        }
        letterLagen.setOnClickListener {
            navController.navigate(R.id.action_lettersFragment_to_letterL)

        }
        letterMuz.setOnClickListener {
            navController.navigate(R.id.action_lettersFragment_to_letterM)

        }
        letterNan.setOnClickListener {
            navController.navigate(R.id.action_lettersFragment_to_letterN)

        }
        letterQoniraw.setOnClickListener {
            navController.navigate(R.id.action_lettersFragment_to_letterQoniraw2)

        }
        letterOraq.setOnClickListener {
            navController.navigate(R.id.action_lettersFragment_to_letterO)

        }
        letterOrmekshi.setOnClickListener {
            navController.navigate(R.id.action_lettersFragment_to_letterOrmek)

        }
        letterPiyaz.setOnClickListener {
            navController.navigate(R.id.action_lettersFragment_to_letterP)

        }
        letterRadio.setOnClickListener {
            navController.navigate(R.id.action_lettersFragment_to_letterR)

        }
        letterSabin.setOnClickListener {
            navController.navigate(R.id.action_lettersFragment_to_letterS)

        }
        letterTarezi.setOnClickListener {
            navController.navigate(R.id.action_lettersFragment_to_letterT)

        }
        letterUn.setOnClickListener {
            navController.navigate(R.id.action_lettersFragment_to_letterU)

        }
        letterUyrek.setOnClickListener {
            navController.navigate(R.id.action_lettersFragment_to_letterUy)

        }
        letterVertolyot.setOnClickListener {
            navController.navigate(R.id.action_lettersFragment_to_letterV)

        }
        letterWaqit.setOnClickListener {
            navController.navigate(R.id.action_lettersFragment_to_letterW)

        }
        letterYolka.setOnClickListener {
            navController.navigate(R.id.action_lettersFragment_to_letterY)

        }
        letterZamark.setOnClickListener {
            navController.navigate(R.id.action_lettersFragment_to_letterZ)

        }
        letterShar.setOnClickListener {
            navController.navigate(R.id.action_lettersFragment_to_letterSh)

        }
        letterCircul.setOnClickListener {
            navController.navigate(R.id.action_lettersFragment_to_letterC)

        }
        letterChemodan.setOnClickListener {
            navController.navigate(R.id.action_lettersFragment_to_letterCh)

        }

    }
}