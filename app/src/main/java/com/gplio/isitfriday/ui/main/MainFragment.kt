package com.gplio.isitfriday.ui.main

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.gplio.isitfriday.R
import kotlinx.android.synthetic.main.main_fragment.*
import nl.dionsegijn.konfetti.models.Shape
import nl.dionsegijn.konfetti.models.Size

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // This application ** totally ** needs view models (am I right???)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.result.observe(viewLifecycleOwner, Observer<Result> {
            when (it) {
                Result.YES -> tv_message.setText(R.string.message_positive)
                Result.NO -> tv_message.setText(R.string.message_negative)
                else -> tv_message.setText(R.string.message_negative)
            }
        })

        btn_try_again.setOnClickListener {
            viewModel.tryAgain()
            confettiFromCenter()

        }
    }

    private fun confettiFromCenter() {
        val colors = intArrayOf(
            R.color.lt_yellow,
            R.color.lt_orange,
            R.color.lt_purple,
            R.color.lt_pink,
            R.color.dk_cyan,
            R.color.dk_green,
            R.color.dk_red,
            R.color.dk_blue
        ).map { ContextCompat.getColor(context!!, it) }

        kv_confetti.build()
            .addColors(colors)
            .setDirection(0.0, 359.0)
            .setSpeed(1f, 5f)
            .setFadeOutEnabled(true)
            .setTimeToLive(2000L)
            .addShapes(Shape.RECT, Shape.CIRCLE)
            .addSizes(Size(12))
            .setPosition(-50f, kv_confetti.width + 50f, -50f, -50f)
            .streamFor(300, 5000L)
    }
}