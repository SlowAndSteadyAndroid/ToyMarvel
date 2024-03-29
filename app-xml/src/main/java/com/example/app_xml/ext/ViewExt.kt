package com.example.app_xml.ext

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


fun AppCompatActivity.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}