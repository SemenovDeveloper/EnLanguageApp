package com.example.enlanguageapp

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.enlanguageapp.databinding.ActivitySecondDemoBinding

class SecondDemoActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondDemoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySecondDemoBinding.inflate(layoutInflater)

        setContentView(binding.root)

        with(binding) {
            btnNavigateToFirstActivity.setOnClickListener{
                val intent = Intent(this@SecondDemoActivity, FirstDemoActivity::class.java)
                startActivity(intent)
            }

//            val text = intent.getStringExtra("EXTRA_STRING")
//            val number = intent.getIntExtra("EXTRA_INT", 0)

            // Serializable

//            val word = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//                intent.getSerializableExtra("EXTRA_WORD", FirstDemoActivity.ExtraWord::class.java)
//            } else {
//                intent.getSerializableExtra("EXTRA_WORD") as FirstDemoActivity.ExtraWord
//            }

            // Parcelable

//            val word = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//                intent.getParcelableExtra("EXTRA_WORD", FirstDemoActivity.ExtraWord::class.java)
//            } else {
//               intent.getParcelableExtra("EXTRA_WORD")
//            }

            // Bundle

            val bundle = intent.extras

            val text = bundle?.getString("EXTRA_STRING")
            val number = bundle?.getInt("EXTRA_INT")
            val word = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                bundle?.getSerializable("EXTRA_WORD", FirstDemoActivity.ExtraWord::class.java)
            } else {
                bundle?.getSerializable("EXTRA_WORD") as FirstDemoActivity.ExtraWord
            }

            tvTitle.text = text
            tvSubtitle.text = number.toString()

            tvWordReceived.text = word?.original
        }
    }
}