package com.example.alipbe.games.firstgame

import android.media.AudioAttributes
import android.media.SoundPool
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.alipbe.R
import kotlinx.android.synthetic.main.activity_first_game.*
import java.util.*
import kotlin.collections.ArrayList

class FirstGameActivity : AppCompatActivity() {

    private val arr:ArrayList<String> = arrayListOf(
        "A", "Á", "B", "D", "E", "F", "G", "Ǵ", "H", "X", "Í", "I", "J", "K",
        "Q", "L", "M", "N", " Ń", "O", "Ó", "P", "R", "S", "T", "U","Ú", "V", "W", "Y", "Z", "Sh", "C", "Ch"
    )
    var res=0
    val a=BooleanArray(arr.size)
    private lateinit var soundPool: SoundPool

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val audioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()
        soundPool = SoundPool.Builder()
            .setMaxStreams(1)
            .setAudioAttributes(audioAttributes)
            .build()
        soundPool.setOnLoadCompleteListener { soundPool, sampleId, status ->
            soundPool.play(sampleId, 1F, 1F, 0, 0, 1F)
        }
        generateQuestion()
    }

    fun generateQuestion(){
        val pos: Int = Random().nextInt(arr.size)
        res=pos
        a[res]=true
        var sound = soundPool.load(this, resources.getIdentifier("l$res", "raw", packageName), 1)
        generateWrongAnswer(btnAnswer1)
        generateWrongAnswer(btnAnswer2)
        generateWrongAnswer(btnAnswer3)
        generateWrongAnswer(btnAnswer4)
        when(Random().nextInt(4)){
            0->{
                btnAnswer1?.text=arr[res]
                btnAnswer1?.setBackgroundResource(R.drawable.background_green)
            }
            1->{
                btnAnswer2?.text=arr[res]
                btnAnswer2?.setBackgroundResource(R.drawable.background_green)
            }
            2->{
                btnAnswer3?.setBackgroundResource(R.drawable.background_green)
                btnAnswer3?.text=arr[res]
            }
            3->{
                btnAnswer4?.setBackgroundResource(R.drawable.background_green)
                btnAnswer4?.text=arr[res]
            }
        }
    }

    fun onClick(view: View){
        val selectedVariant=(view as Button).text.toString()
        if(selectedVariant==arr[res]){
            isEnabled(false)
            when(Random().nextInt(4)){
                0-> {
                    val sound = soundPool.load(this, R.raw.correct1, 1)
                }
                1->{
                    val sound = soundPool.load(this, R.raw.correct2, 1)
                }
                2->{
                    val sound = soundPool.load(this, R.raw.correct3, 1)
                }
                3->{
                    val sound = soundPool.load(this, R.raw.correct4, 1)
                }
            }
            tvAnswer.text=arr[res]
            tvAnswer.visibility=View.VISIBLE
            val myAnim: Animation = AnimationUtils.loadAnimation(this,R.anim.logo_anim)
            tvAnswer.startAnimation(myAnim)
            timer.start()
        }else{
            val sound = soundPool.load(this, R.raw.wrong, 1)
        }
    }

    fun generateWrongAnswer(button:Button?){
        var pos: Int = Random().nextInt(arr.size)
        while (a[pos]){
            pos=Random().nextInt(arr.size)
        }
        button?.text=arr[pos]
        button?.setBackgroundResource(R.drawable.background_red)
        a[pos]=true
    }

    private val timer = object : CountDownTimer(3000, 1000) {
        override fun onFinish() {
            tvAnswer.visibility=View.GONE
            Arrays.fill(a,false)
            generateQuestion()
            isEnabled(true)
        }
        override fun onTick(millisUntilFinished: Long) {
        }
    }
    override fun onDestroy() {
        soundPool.release()
        super.onDestroy()
    }

    private fun isEnabled(enabled:Boolean){
        btnAnswer1.isEnabled=enabled
        btnAnswer2.isEnabled=enabled
        btnAnswer3.isEnabled=enabled
        btnAnswer4.isEnabled=enabled
    }
}