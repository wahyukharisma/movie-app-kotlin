package com.dayplug.movieticket.view.auth

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dayplug.movieticket.R
import com.dayplug.movieticket.databinding.ActivitySignUpPhotoBinding
import com.dayplug.movieticket.utils.Preferences
import com.dayplug.movieticket.view.HomeActivity
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class SignUpPhotoActivity : AppCompatActivity(), MultiplePermissionsListener {
    private lateinit var _binding : ActivitySignUpPhotoBinding
    private lateinit var currentPhotoPath: String

    companion object{
        const val REQUEST_IMAGE_CAPTURE = 1
    }

    private var _statusAdd: Boolean = false
    private lateinit var _filePath : Uri
    private lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySignUpPhotoBinding.inflate(layoutInflater)
        val view = _binding.root
        setContentView(view)

        preferences = Preferences(this)

        with(_binding){
            tvGreeting.text = getString(R.string.title_greeting, intent.getStringExtra("name"))

            ivImageSelector.setOnClickListener {
                when(_statusAdd){
                    true -> {
                        _statusAdd = false
                        btnSavePhoto.visibility = View.GONE
                        ivImageSelector.setImageResource(R.drawable.ic_add)
                        ivPhotoEmpty.setImageResource(R.drawable.foto_empty)
                    }
                    false -> {
                        Dexter.withContext(this@SignUpPhotoActivity)
                            .withPermissions(
                                android.Manifest.permission.CAMERA,
                                android.Manifest.permission.READ_EXTERNAL_STORAGE,
                                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                            ).withListener(this@SignUpPhotoActivity)
                            .check()
                    }

                }
            }

            btnUploadLater.setOnClickListener {
               goToHomePage()
            }

            btnSavePhoto.setOnClickListener {
                if(_filePath != null){
//                    Toast.makeText(this@SignUpPhotoActivity,"Sedang Upload",Toast.LENGTH_LONG).show()
//
//                    val ref = Firebase.mStorage.child("images/"+UUID.randomUUID().toString())
//                    ref.putFile(_filePath)
//                        .addOnSuccessListener {
//                            Toast.makeText(this@SignUpPhotoActivity,"Berhasil Upload",Toast.LENGTH_LONG).show()
//
//                            ref.downloadUrl.addOnSuccessListener {
//                                preferences.setValues("url",it.toString())
//                            }
//
//                            goToHomePage()
//                        }
//                        .addOnFailureListener {
//                            Toast.makeText(this@SignUpPhotoActivity,"Gagal Upload",Toast.LENGTH_LONG).show()
//                        }
//                        .addOnProgressListener {
//                            val progress = 100.0 * it.bytesTransferred / it.totalByteCount
//                            Toast.makeText(this@SignUpPhotoActivity,"Sudah terupload $progress",Toast.LENGTH_LONG).show()
//                        }
                }else{
                    Toast.makeText(this@SignUpPhotoActivity,"Gagal Upload",Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun goToHomePage(){
        finishAffinity()
        startActivity(Intent(this@SignUpPhotoActivity, HomeActivity::class.java))
    }

    private fun createImageFile(): File {
        val timeStamp: String = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH).format(Date())
        Log.d("timestamp", timeStamp)
        val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "JPEG_${timeStamp}_",
            ".jpg",
            storageDir
        ).apply {
            currentPhotoPath = absolutePath
            Log.d("path", currentPhotoPath)
        }
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            val bitmap = data?.extras?.get("data") as Bitmap
            _statusAdd = true
            Toast.makeText(this@SignUpPhotoActivity,data.data?.toString(),Toast.LENGTH_LONG).show()

            data.data?.let {
                _filePath = it
            }

            Glide.with(this)
                .load(bitmap)
                .apply(RequestOptions.circleCropTransform())
                .into(_binding.ivPhotoEmpty)

            _binding.btnSavePhoto.visibility = View.VISIBLE
            _binding.ivImageSelector.setImageResource(R.drawable.ic_delete)
        }
    }

    override fun onPermissionsChecked(p0: MultiplePermissionsReport?) {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also {
                takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                val photoFile: File? = try{
                    createImageFile()
                }catch (ex : IOException){
                    Log.d("failed_file",ex.message.toString())
                    null
                }


                photoFile?.also {
//                    val photoURI: Uri = FileProvider.getUriForFile(
//                        this,
//                        BuildConfig.APPLICATION_ID + ".fileprovider",
//                        it
//                    )

                    //takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.parse(it.toString()))
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
                }
            }
        }
    }

    override fun onPermissionRationaleShouldBeShown(
        p0: MutableList<PermissionRequest>?,
        p1: PermissionToken?
    ) {
        TODO("Not yet implemented")
    }
}