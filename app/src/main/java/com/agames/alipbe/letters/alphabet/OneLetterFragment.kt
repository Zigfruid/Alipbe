package com.agames.alipbe.letters.alphabet

import android.media.AudioAttributes
import android.media.SoundPool
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.agames.alipbe.R
import kotlinx.android.synthetic.main.fragment_one_letter.*

class OneLetterFragment : Fragment(R.layout.fragment_one_letter) {

    companion object {
        const val MAX_LETTERS = 34
    }

    private lateinit var soundPool: SoundPool
    private val safeArgs: OneLetterFragmentArgs by navArgs()
    private var currentId: Int = 1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = Navigation.findNavController(view)
        val audioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()
        soundPool = SoundPool.Builder()
            .setMaxStreams(1)
            .setAudioAttributes(audioAttributes)
            .build()
        currentId = safeArgs.letterId
        setImg(currentId)
        var sound = soundPool.load(
            context,
            resources.getIdentifier("letter$currentId", "raw", requireActivity().packageName),
            1
        )
        soundPool.setOnLoadCompleteListener { soundPool, sampleId, _ ->
            soundPool.play(sampleId, 1f, 1f, 0, 1, 1f)
        }
        btnNext.setOnClickListener {
            currentId += 1
            if (currentId <= MAX_LETTERS) {
                setImg(currentId)
            } else {
                currentId = 1
                setImg(currentId)
            }
            sound = soundPool.load(
                context,
                resources.getIdentifier("letter$currentId", "raw", requireActivity().packageName),
                1
            )
        }
        btnPrev.setOnClickListener {
            if (currentId > 1) {
                currentId -= 1
                setImg(currentId)
            } else {
                currentId = MAX_LETTERS
                setImg(currentId)
            }
            sound = soundPool.load(
                context,
                resources.getIdentifier("letter$currentId", "raw", requireActivity().packageName),
                1
            )
        }
        homeBtn.setOnClickListener {
            navController.popBackStack()
        }
        soundBtn.setOnClickListener {
            soundPool.play(sound, 1F, 1F, 0, 0, 1F)
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

    override fun onDestroy() {
        soundPool.release()
        super.onDestroy()
    }
}
