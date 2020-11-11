package com.example.alipbe.games

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.alipbe.R
import kotlinx.android.synthetic.main.fragment_game_menu.*

class GameMenuFragment : Fragment(R.layout.fragment_game_menu) {

    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        btnGame1.setOnClickListener {
            navController.navigate(R.id.action_gameMenuFragment_to_firstGameFragment)
        }
        btnGame2.setOnClickListener {
            navController.navigate(R.id.action_gameMenuFragment_to_gameFragment2)
        }
    }
}
