package com.example.alipbe.games.firstgame

import android.graphics.Color
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
import kotlinx.android.synthetic.main.fragment_second_game.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import nl.dionsegijn.konfetti.models.Shape
import nl.dionsegijn.konfetti.models.Size

class FirstGameFragment : Fragment(R.layout.fragment_first_game), View.OnClickListener {

    companion object {
        const val TIME = 3000
    }
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

        btnRefreshSound.setOnClickListener {
            soundPool.load(
                requireContext(),
                resources.getIdentifier("l$res", "raw", activity?.packageName),
                1
            )
        }

        generateWrongAnswer(btnAnswer1)
        generateWrongAnswer(btnAnswer2)
        generateWrongAnswer(btnAnswer3)
        generateWrongAnswer(btnAnswer4)
        when ((1..4).random()) {
            1 -> {
                btnAnswer1.text = arr[res]
                btnAnswer1.setBackgroundResource(R.drawable.background_green)

            }
            2 -> {
                btnAnswer2.text = arr[res]
                btnAnswer2.setBackgroundResource(R.drawable.background_green)
            }
            3 -> {
                btnAnswer3.setBackgroundResource(R.drawable.background_green)
                btnAnswer3.text = arr[res]
            }
            4 -> {
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
            viewKonfettiFG.build()
                .addColors(Color.YELLOW, Color.GREEN, Color.MAGENTA)
                .setDirection(0.0, 359.0)
                .setSpeed(1f, 5f)
                .setFadeOutEnabled(true)
                .setTimeToLive(1000L)
                .addShapes(Shape.Square, Shape.Circle)
                .addSizes(Size(12))
                .setPosition(-50f, viewKonfettiFG.width + 50f, -50f, -50f)
                .streamFor(300, 2000L)
            tvAnswer.startAnimation(myAnim)
            GlobalScope.launch {
                delay(TIME.toLong())
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
