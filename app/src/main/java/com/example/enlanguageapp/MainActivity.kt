package com.example.enlanguageapp

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.example.enlanguageapp.databinding.ActivityLearnWordBinding

data class VariantLayout(
    val layoutAnswer: LinearLayout,
    val tvVariantNumber: TextView,
    val tvVariantValue: TextView,
)

class MainActivity : AppCompatActivity() {

//    private lateinit var binding: ActivityLearnWordBinding

    private var _binding: ActivityLearnWordBinding? = null
    private val binding get() = _binding?: throw IllegalStateException("Binding for ActivityLearnWordBinding must not be null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        _binding = ActivityLearnWordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val trainer = LearnWordsTrainer()

        var isVariantChecked = false

        val variantsList = listOf<VariantLayout>(
            VariantLayout(binding.layoutAnswer1, binding.tvVariantNumber1, binding.tvVariantValue1),
            VariantLayout(binding.layoutAnswer2, binding.tvVariantNumber2, binding.tvVariantValue2),
            VariantLayout(binding.layoutAnswer3, binding.tvVariantNumber3, binding.tvVariantValue3),
            VariantLayout(binding.layoutAnswer4, binding.tvVariantNumber4, binding.tvVariantValue4),
            )

        fun showVariants () {

            val question = trainer.getNextQuestion()


            binding.tvQuestionWord.text = question?.correctAnswer?.original

            for (index in variantsList.indices){
                variantsList[index].tvVariantValue.text = question?.variants?.get(index)?.translate
            }
        }

        showVariants()

        fun clickVariant(
            index: Int
        ) {
            if (isVariantChecked) return

            if (trainer.checkAnswer(index)) {
                markAnswerCurrent(variantsList[index])
                showResultMessage(true)
            } else {
                markAnswerIncorrect(variantsList[index])
                val validAnswerIndex = trainer.getValidAnswerIndex()
                markAnswerCurrent(variantsList[validAnswerIndex])
                showResultMessage(false)
            }

            isVariantChecked = true
        }

        fun resetVariants () {
            isVariantChecked = false
            hideResultMessage()
            variantsList.forEach {
                markAnswerNeutral(it)
            }
            showVariants()
        }

        with(binding) {
            variantsList.forEachIndexed { index, el ->
                el.layoutAnswer.setOnClickListener {
                    clickVariant(index)
                }
            }

            btnContinue.setOnClickListener {
                resetVariants()
            }

            btnSkip.setOnClickListener {
                resetVariants()
            }
        }
    }

    private fun markAnswerNeutral(
        variant: VariantLayout
    ) {

        variant.layoutAnswer.background = ContextCompat.getDrawable(
            this@MainActivity,
            R.drawable.shape_round_container
        )

        variant.tvVariantNumber.apply {
            background = ContextCompat.getDrawable(
                this@MainActivity,
                R.drawable.shape_round_elements
            )
            setTextColor(ContextCompat.getColor(
                this@MainActivity,
                R.color.textVariantsColor
            ))
        }

        variant.tvVariantValue.setTextColor(ContextCompat.getColor(
            this@MainActivity,
            R.color.textVariantsColor
        ))
    }

    private fun markAnswerIncorrect(
        variant: VariantLayout

    ) {
        variant.layoutAnswer.background = ContextCompat.getDrawable(
            this@MainActivity,
            R.drawable.shape_rounded_containers_incorrect
        )

        variant.tvVariantNumber.background = ContextCompat.getDrawable(
            this@MainActivity,
            R.drawable.shape_rounded_elements_incorrect
        )

        variant.tvVariantValue.setTextColor(ContextCompat.getColor(
            this@MainActivity,
            R.color.surfaceRed
        ))

        variant.tvVariantNumber.setTextColor(ContextCompat.getColor(
            this@MainActivity,
            R.color.white
        ))
    }

    private fun markAnswerCurrent(
        variant: VariantLayout
    ) {
        variant.layoutAnswer.background = ContextCompat.getDrawable(
            this@MainActivity,
            R.drawable.shape_rounded_containers_correct
        )

        variant.tvVariantNumber.background = ContextCompat.getDrawable(
            this@MainActivity,
            R.drawable.shape_rounded_elements_correct
        )

        variant.tvVariantValue.setTextColor(ContextCompat.getColor(
            this@MainActivity,
            R.color.surfaceGreen
        ))

        variant.tvVariantNumber.setTextColor(ContextCompat.getColor(
            this@MainActivity,
            R.color.white
        ))
    }

    private fun showResultMessage (
        isCorrect: Boolean
    ) {
        val color: Int
        val message: String
        val imageResource: Int
        if (isCorrect) {
            color = ContextCompat.getColor(this@MainActivity, R.color.surfaceGreen)
            message = ContextCompat.getString(this@MainActivity, R.string.title_correct)
            imageResource =  R.drawable.ic_correct
        } else {
            color = ContextCompat.getColor(this@MainActivity, R.color.surfaceRed)
            message = ContextCompat.getString(this@MainActivity, R.string.title_incorrect)
            imageResource =  R.drawable.ic_wrong
        }

        with(binding) {
            btnSkip.isVisible = false
            layoutResult.setBackgroundColor(color)
            tvResult.text = message
            ivResult.setImageResource(imageResource)
            btnContinue.setTextColor(color)
            layoutResult.isVisible = true
        }
    }

    private fun hideResultMessage () {
        with(binding) {
            layoutResult.isVisible = false
            btnSkip.isVisible = true
        }
    }
}