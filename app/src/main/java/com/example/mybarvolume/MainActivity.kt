package com.example.mybarvolume

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var edtLength: EditText
    private lateinit var edtWidth: EditText
    private lateinit var edtHeight: EditText
    private lateinit var btnCalculate: Button
    private lateinit var tvResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtLength = findViewById(R.id.edt_length)
        edtWidth = findViewById(R.id.edt_width)
        edtHeight = findViewById(R.id.edt_height)
        btnCalculate = findViewById(R.id.btn_calculate)
        tvResult = findViewById(R.id.tv_result)
        btnCalculate.setOnClickListener(this)

        if (savedInstanceState != null) {
            val result = savedInstanceState.getString(STATE_RESULT)
            tvResult.text = result ?: "Result not found"
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_RESULT, tvResult.text.toString())
    }

    override fun onClick(view: View?) {
        if (view?.id == R.id.btn_calculate) {
            val inputHeight = edtHeight.text.toString().trim()
            val inputLength = edtLength.text.toString().trim()
            val inputWidth = edtWidth.text.toString().trim()
            var isEmptyFields = false
            val isNotFilled = "Fill this field"

            if (inputLength.isEmpty()) {
                isEmptyFields = true
                edtLength.error = isNotFilled
            }
            if (inputWidth.isEmpty()) {
                isEmptyFields = true
                edtWidth.error = isNotFilled
            }
            if (inputHeight.isEmpty()) {
                isEmptyFields = true
                edtHeight.error = isNotFilled
            }
            if (!isEmptyFields) {
                val volume = inputLength.toDouble() * inputWidth.toDouble() * inputHeight.toDouble()
                tvResult.text = String.format(Locale.getDefault(), volume.toString())
            }
        }
    }

    companion object {
        private const val STATE_RESULT = "state_result"
    }
}
