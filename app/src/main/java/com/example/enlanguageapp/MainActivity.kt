package com.example.enlanguageapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.example.enlanguageapp.databinding.ActivityLearnWordBinding

class MainActivity : AppCompatActivity() {

//    private lateinit var binding: ActivityLearnWordBinding

    private var _binding: ActivityLearnWordBinding? = null
    private val binding get() = _binding?: throw IllegalStateException("Binding for ActivityLearnWordBinding must not be null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        _binding = ActivityLearnWordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // нейтральный
        // корректный
        // некорректный

        binding.layoutAnswer3.setOnClickListener {
            markAnswerCurrent()
        }



    }

    private fun markAnswerCurrent() {
        binding.layoutAnswer3.background = ContextCompat.getDrawable(
            this@MainActivity,
            R.drawable.shape_rounded_containers_correct
        )

        binding.tvVariantNumber3.background = ContextCompat.getDrawable(
            this@MainActivity,
            R.drawable.shape_rounded_elements_correct
        )

        binding.tvVariantValue3.setTextColor(ContextCompat.getColor(
            this@MainActivity,
            R.color.surfaceGreen
        ))

        binding.tvVariantNumber3.setTextColor(ContextCompat.getColor(
            this@MainActivity,
            R.color.white
        ))

        binding.btnSkip.isVisible = false

        binding.layoutResult.setBackgroundColor(
            ContextCompat.getColor(
                this@MainActivity,
                R.color.surfaceGreen
            )
        )

        binding.imageView.setImageDrawable(
            ContextCompat.getDrawable(
                this@MainActivity,
                R.drawable.ic_correct
            )
        )

        binding.tvResult.text = resources.getString(R.string.title_correct)

        binding.btnContinue.setTextColor(ContextCompat.getColor(this@MainActivity, R.color.surfaceGreen))

        binding.layoutResult.isVisible = true
    }
}