package com.example.enlanguageapp

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import com.example.enlanguageapp.databinding.ActivityFirstDemoBinding
import kotlinx.parcelize.Parcelize
import java.io.Serializable

class FirstDemoActivity: AppCompatActivity() {
    private lateinit var binding: ActivityFirstDemoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFirstDemoBinding.inflate(layoutInflater)

        setContentView(binding.root)

        // 1. без передачи данных
//        with(binding) {
//            btnNavigateToSecondActivity.setOnClickListener {
//                val intent = Intent(this@FirstDemoActivity, SecondDemoActivity::class.java)
//                startActivity(intent)
//            }
//        }
        // 2. с передачей данных




        val word = ExtraWord(
            "Galaxy",
            "Галактика",
            false
        )

        with(binding) {
            btnNavigateToSecondActivity.setOnClickListener {
                val intent = Intent(this@FirstDemoActivity, SecondDemoActivity::class.java)
//                intent.putExtra("EXTRA_STRING", "don't panic")
//                intent.putExtra("EXTRA_INT", 42)
//                intent.putExtra("EXTRA_WORD", word)

                val bundle = Bundle()

                intent.putExtras(
                    bundleOf(
                        "EXTRA_STRING" to "don't panic",
                        "EXTRA_INT" to 42,
                        "EXTRA_WORD" to word
                    )
                )

                startActivity(intent)

            }


        }

        // Serializable

        // Bundle
    }

    data class ExtraWord(
        val original: String,
        val translate: String,
        var learned: Boolean = false,
    ) : Serializable


    // Parcelable

    // 1.

//        data class ExtraWord(
//        val original: String,
//        val translate: String,
//        var learned: Boolean = false,
//    ) : Parcelable {
//            override fun describeContents(): Int {
//                return 0
//            }
//
//            override fun writeToParcel(dest: Parcel, flags: Int) {
//                dest.writeString(original)
//                dest.writeString(translate)
//                dest.writeByte(if (learned) 1 else 0)
//            }
//
//            constructor(parcel: Parcel): this(
//                original = parcel.readString().toString(),
//                translate = parcel.readString().toString(),
//                learned = parcel.readByte() != 0.toByte()
//            )
//
//            companion object CREATOR : Parcelable.Creator<ExtraWord> {
//                override fun createFromParcel(parcel: Parcel): ExtraWord {
//                    return ExtraWord(parcel)
//                }
//
//                override fun newArray(size: Int): Array<ExtraWord?> {
//                    return arrayOfNulls(size)
//                }
//            }
//
//        }

    // 2.
//    @Parcelize
//    data class ExtraWord(
//        val original: String,
//        val translate: String,
//        var learned: Boolean = false,
//    ) : Parcelable
}