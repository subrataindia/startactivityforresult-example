package com.example.startactivityforresult

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import java.io.IOException

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var iv: ImageView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnSelectImage = findViewById<Button>(R.id.btnSelectImage)
        btnSelectImage.setOnClickListener(this)

        iv = findViewById(R.id.iv1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val imageBitmap: Bitmap? = data?.extras?.get("data") as Bitmap?


        if (imageBitmap != null)
            iv.setImageBitmap(imageBitmap)
        else
            iv.setImageURI(data?.data)
        Log.d("TAGY", "requestcode: $requestCode resultcode: $resultCode data: $data")
    }

    override fun onClick(v: View?) {
        Log.d("TAGY", "Clicked!")
        when (v?.id) {
            R.id.btnSelectImage -> {
                Log.d("TAGY", "Select Image Clicked!")
                val intent = Intent(this, SelectImageActivity::class.java)
                startActivityForResult(intent, 1)
            }
        }
    }
}
