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
    private lateinit var frontAnim: AnimatorSet
    private lateinit var backAnim: AnimatorSet

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val scale: Float = resources.displayMetrics.density
        binding.apply {
            cardViewFront.cameraDistance = 8000 * scale
            cardViewBack.cameraDistance = 8000 * scale
        }

        frontAnim = AnimatorInflater.loadAnimator(applicationContext, R.animator.anim_front) as AnimatorSet
        backAnim = AnimatorInflater.loadAnimator(applicationContext, R.animator.anim_back) as AnimatorSet

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

        binding.editTextCCV.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.textViewCCV.text = s.toString()
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })

        binding.editTextExp.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(start == 1) {
                    binding.editTextExp.append("/")
                }
                binding.textViewExp.text = s.toString()
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })

        binding.editTextNumber.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(start.rem(5) == 3) {
                    binding.editTextNumber.append(" ")
                }
                binding.textViewNumber.text = s.toString()
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null;
    }
}