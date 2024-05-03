package com.example.portal

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import android.widget.Toast
import android.widget.Toolbar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.portal.R

class SecondActivity : AppCompatActivity() {

    lateinit var music: MediaPlayer
    private var currentIndex = 0
    lateinit var seekBar: SeekBar
    lateinit var vtext: EditText
    lateinit var bottomNavigation:Toolbar
    private val song= listOf("Beat_trap","Sitar","Flute")
    private val audiofiles = listOf(R.raw.beat_trap, R.raw.sitar, R.raw.flute)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

        val btnStart: Button = findViewById(R.id.btnstart)
        val btnPause: Button = findViewById(R.id.btnpause)
        val btnNext: Button = findViewById(R.id.btnNext)
        seekBar = findViewById(R.id.seekBar)
        vtext=findViewById(R.id.title)

        val back:Button=findViewById(R.id.back_arrow)
        back.setOnClickListener{
            back_fun()
        }

        music = MediaPlayer()

        //setupseekbar
        setUpSeekBar()



        btnStart.setOnClickListener {
            musicstart()

        }
        btnPause.setOnClickListener { musicpause() }
        btnNext.setOnClickListener { nextmusic() }





    }

    private fun back_fun() {
        music.stop()
        val intent = Intent (this,sign_in::class.java)
        startActivity(intent)
    }


    private fun setdata() {
        vtext.setText(song[currentIndex])

    }

    private fun setUpSeekBar() {
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if(fromUser){
                    music?.seekTo(progress)}
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                music?.pause()
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                music?.start()
            }
        }) }
    private val seekBarUpdateHandler= Handler()
    private val seekBarUpdateRunnable=object :Runnable{
        override fun run() {
            music?.let {
                seekBar.progress = it.currentPosition
                seekBarUpdateHandler.postDelayed(this, 1000)
            }
        }
    }

    private fun startUpdatingSeekBar() {
        seekBarUpdateHandler.post(seekBarUpdateRunnable)
    }


    fun musicstart() {
        try {
            if (music.isPlaying) {
                music.stop()
            }
            val currentAudio = audiofiles[currentIndex]
            val resId = resources.getIdentifier(currentAudio.toString(), "raw", packageName)
            music = MediaPlayer.create(this, resId)
            music.start()
            startUpdatingSeekBar()
            seekBar.max=music!!.duration
            setdata()

            music?.setOnCompletionListener {
                currentIndex++
                nextmusic()
            }

        } catch (e: Exception) {
            Toast.makeText(this, "Error playing audio", Toast.LENGTH_SHORT).show()
        }

    }


    fun musicpause() {
        if (music.isPlaying) {
            music.pause()
        } else {
            music.start()
            seekBar.max=music!!.duration
        }
    }

    fun nextmusic() {
        if (currentIndex < audiofiles.size - 1) {
            currentIndex++
        } else {
            currentIndex = 0 // Start from the beginning if at the end of the list
        }
        musicstart()
    }
}



