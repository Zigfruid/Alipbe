package com.example.alipbe.games.firstgame

import android.media.AudioAttributes
import android.media.SoundPool
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.alipbe.DataHolder
import com.example.alipbe.R
import java.util.Arrays
import java.util.Random
import kotlin.collections.ArrayList
import kotlinx.android.synthetic.main.fragment_first_game.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FirstGameFragment : Fragment(R.layout.fragment_first_game), View.OnClickListener {

    private val arr: ArrayList<String> = DataHolder.arr
    private var res = 0
    private val usedLetters = BooleanArray(arr.size)
    private lateinit var soundPool: SoundPool

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val audioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()
        soundPool = SoundPool.Builder()
            .setMaxStreams(1)
            .setAudioAttributes(audioAttributes)
            .build()
        soundPool.setOnLoadCompleteListener { soundPool, sampleId, _ ->
            soundPool.play(sampleId, 1F, 1F, 0, 0, 1F)
        }
        btnAnswer1.setOnClickListener(this)
        btnAnswer2.setOnClickListener(this)
        btnAnswer3.setOnClickListener(this)
        btnAnswer4.setOnClickListener(this)
        generateQuestion()
    }

    private fun generateQuestion() {
        val pos: Int = (0 until arr.size).random()
        isEnabled(true)
        res = pos
        usedLetters[res] = true
        soundPool.load(
            requireContext(),
            resources.getIdentifier("l$res", "raw", activity?.packageName),
            1
        )
        generateWrongAnswer(btnAnswer1)
        generateWrongAnswer(btnAnswer2)
        generateWrongAnswer(btnAnswer3)
        generateWrongAnswer(btnAnswer4)
        when ((0..4).random()) {
            0 -> {
                btnAnswer1.text = arr[res]
                btnAnswer1.setBackgroundResource(R.drawable.background_green)
            }
            1 -> {
                btnAnswer2.text = arr[res]
                btnAnswer2.setBackgroundResource(R.drawable.background_green)
            }
            2 -> {
                btnAnswer3.setBackgroundResource(R.drawable.background_green)
                btnAnswer3.text = arr[res]
            }
            3 -> {
                btnAnswer4.setBackgroundResource(R.drawable.background_green)
                btnAnswer4.text = arr[res]
            }
        }
    }

    override fun onClick(view: View) {
        val selectedVariant = (view as Button).text.toString()
        if (selectedVariant == arr[res]) {
            isEnabled(false)
            soundPool.load(
                context,
                resources.getIdentifier("correct${(1..4).random()}", "raw", activity?.packageName),
                1
            )
            tvAnswer.text = arr[res]
            tvAnswer.visibility = View.VISIBLE
            val myAnim: Animation = AnimationUtils.loadAnimation(requireContext(), R.anim.logo_anim)
            tvAnswer.startAnimation(myAnim)
            GlobalScope.launch {
                delay(3000)
                activity?.runOnUiThread {
                    generateQuestion()
                    tvAnswer.visibility = View.GONE
                    Arrays.fill(usedLetters, false)
                    isEnabled(true)
                }
            }
        } else {
            soundPool.load(requireContext(), R.raw.wrong, 1)
        }
    }

    private fun generateWrongAnswer(button: Button) {
        var pos: Int = Random().nextInt(arr.size)
        while (usedLetters[pos]) {
            pos = Random().nextInt(arr.size)
        }
        button.text = arr[pos]
        button.setBackgroundResource(R.drawable.background_red)
        usedLetters[pos] = true
    }

    override fun onDestroy() {
        soundPool.release()
        super.onDestroy()
    }

    private fun isEnabled(enabled: Boolean) {
        btnAnswer1.isEnabled = enabled
        btnAnswer2.isEnabled = enabled
        btnAnswer3.isEnabled = enabled
        btnAnswer4.isEnabled = enabled
    }
}
