package com.argumpamungkas.moviesapps.ui.detail.videos

import android.R
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.argumpamungkas.moviesapps.databinding.ActivityPlayVideoMovieBinding
import com.argumpamungkas.moviesapps.model.Constant
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import org.koin.dsl.module


val modulePlayVideoMovie = module {
    factory { PlayVideoMovie() }
}

class PlayVideoMovie : AppCompatActivity() {

    private lateinit var binding: ActivityPlayVideoMovieBinding
//    private lateinit var youTubePlayer: YouTubePlayer
    private val keyVideos by lazy {
        intent.getStringExtra(Constant.KEY_VIDEOS)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayVideoMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN
        )


    }

    override fun onStart() {
        super.onStart()
        lifecycle.addObserver(binding.youtubePlayerView)
        binding.youtubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                keyVideos?.let {
                    youTubePlayer.loadVideo(it, 0f)
                }
            }
        })
    }
}