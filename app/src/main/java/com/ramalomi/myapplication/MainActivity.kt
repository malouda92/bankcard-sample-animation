package com.ramalomi.myapplication

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.ramalomi.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(){

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private val frontAnim: AnimatorSet by lazy { AnimatorInflater.loadAnimator(applicationContext, R.animator.anim_front) as AnimatorSet }
    private val backAnim: AnimatorSet by lazy { AnimatorInflater.loadAnimator(applicationContext, R.animator.anim_back) as AnimatorSet }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val scale: Float = resources.displayMetrics.density
        binding.apply {
            cardViewFront.cameraDistance = 8000 * scale
            cardViewBack.cameraDistance = 8000 * scale
        }

        binding.editTextCCV.setOnFocusChangeListener { _, hasFocus ->
            if(hasFocus) {
                frontAnim.setTarget(binding.cardViewFront)
                backAnim.setTarget(binding.cardViewBack)
                frontAnim.start()
                backAnim.start()
            }else {
                frontAnim.setTarget(binding.cardViewBack)
                backAnim.setTarget(binding.cardViewFront)
                backAnim.start()
                frontAnim.start()
            }
        }

        initializeCardNumber()
        initializeCardExp()
        initializeCardCVC()
    }

    private fun initializeCardNumber()
    {
        binding.apply {
            editTextNumber.addTextChangedListener(object: TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if(start.rem(5) == 3) {
                        editTextNumber.append(" ")
                    }
                    textViewNumber.text = s.toString()
                }

                override fun afterTextChanged(s: Editable?) {}

            })
        }

    }

    private fun initializeCardExp()
    {
        binding.apply{
            editTextExp.addTextChangedListener(object: TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if(start == 1) {
                        editTextExp.append("/")
                    }
                    textViewExp.text = s.toString()
                }

                override fun afterTextChanged(s: Editable?) {}

            })
        }
    }

    private fun initializeCardCVC()
    {
        binding.apply {
            editTextCCV.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    textViewCCV.text = s.toString()
                }

                override fun afterTextChanged(s: Editable?) {}

            })
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null;
    }
}