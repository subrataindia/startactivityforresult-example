package com.example.startactivityforresult

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class SelectImageActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var btnGallery : Button
    lateinit var btnCamera: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_image)

        btnGallery = findViewById(R.id.btnGallery)
        btnGallery.setOnClickListener(this)
        btnCamera = findViewById(R.id.btnCamera)
        btnCamera.setOnClickListener(this)

    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        // Create an intent to pick an image from the gallery
//        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
//        // Start the intent and wait for a result
//        startActivityForResult(intent, 1)
//    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

//        if (requestCode == 1 && resultCode == RESULT_OK) {
//            // Return the selected image URI to the calling activity
//            val imageUri = data?.data
//            val resultIntent = Intent()
//            resultIntent.data = imageUri
//            setResult(RESULT_OK, resultIntent)
//            finish()
//        } else
            if (requestCode == 1 && resultCode == RESULT_OK) {
            var resultIntent = Intent()
            resultIntent = data!!
            setResult(RESULT_OK, resultIntent)
            finish()
        }else {
            // Return a canceled result if the user did not select an image
            setResult(RESULT_CANCELED)
            finish()
        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btnGallery -> {
                // Create an intent to pick an image from the gallery
                val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                // Start the intent and wait for a result
                startActivityForResult(intent, 1)
            }
            R.id.btnCamera -> {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE), 1)
                } else {
//                    Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { intent ->
//                        intent.resolveActivity(packageManager)?.also {
//                            startActivityForResult(intent, 1)
//                        }
//                    }
                    val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    startActivityForResult(intent, 1)
                }
//                // Create an intent to pick an image from the Camera
//                val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.ACTION_IMAGE_CAPTURE)
//                // Start the intent and wait for a result
//                startActivityForResult(intent, 1)
            }
        }
    }

}
