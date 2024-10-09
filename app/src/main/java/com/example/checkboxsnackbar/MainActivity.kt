package com.example.checkboxsnackbar

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var textET: EditText
    private lateinit var resultTV: TextView
    private lateinit var saveBTN: Button
    private lateinit var deleteBTN: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        textET = findViewById(R.id.edit_text)
        resultTV = findViewById(R.id.resultTV)
        saveBTN = findViewById(R.id.save_dataBTN)
        deleteBTN = findViewById(R.id.deleteBTN)

        saveBTN.setOnClickListener(this::onClick)
        deleteBTN.setOnClickListener(this::onClick)
    }

    fun onClick(view: View?) {
        when(view?.id) {
            R.id.save_dataBTN -> resultTV.setText(textET.text)
            R.id.deleteBTN -> {
                Snackbar
                    .make(
                        view,
                        getString(R.string.confirmation),
                        Snackbar.LENGTH_LONG
                    ).setAction(getString(R.string.delete)) {
                        textET.text.clear()
                        resultTV.setText(getString(R.string.result))
                        Snackbar
                            .make(
                                view,
                                getString(R.string.data_has_been_deleted),
                                Snackbar.LENGTH_LONG)
                            .show()

                    }
                    .show()
            }
        }

    }
}