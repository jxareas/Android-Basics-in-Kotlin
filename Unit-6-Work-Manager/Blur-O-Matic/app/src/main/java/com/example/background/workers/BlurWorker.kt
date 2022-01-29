package com.example.background.workers

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.text.TextUtils
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.example.background.KEY_IMAGE_URI
import com.example.background.R

class BlurWorker(context : Context, params : WorkerParameters) : Worker(context, params) {

    companion object {
         val TAG : String by lazy { this::class.java.simpleName.toString() }
    }

    override fun doWork(): Result = try {
        val appContext : Context = applicationContext

        val resourceUri = inputData.getString(KEY_IMAGE_URI)

        makeStatusNotification("Blurring image", appContext)

        // ADD THIS TO SLOW DOWN THE WORKER
        sleep()
        // ^^^^


//        val picture = BitmapFactory.decodeResource(appContext.resources, R.drawable.android_cupcake)
        if (TextUtils.isEmpty(resourceUri)) {
            Log.e(TAG, "Invalid input uri")
            throw IllegalArgumentException("Invalid input uri")
        }

        val resolver = appContext.contentResolver

        val picture = BitmapFactory.decodeStream(
            resolver.openInputStream(Uri.parse(resourceUri)))

        val blurredPicture = blurBitmap(picture, appContext)

        // Write bitmap to a temporary file
        val outputUri = writeBitmapToFile(appContext, blurredPicture)

        makeStatusNotification("Output is $outputUri", appContext)

        val outputData = workDataOf(KEY_IMAGE_URI to outputUri.toString())
        Result.success(outputData)
    } catch(error : Throwable) {
        Log.e(TAG, "Error Applying Blur ")
        error.printStackTrace()
        Result.failure()
    }

}