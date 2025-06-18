package com.codehong.lib.sample.captureshare

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import com.codehong.lib.sample.databinding.ActivityCaptureShareBinding
import com.codehong.library.widget.captureshare.CaptureShareManager
import com.codehong.library.widget.util.PermissionManager

class SampleCaptureShareActivity : ComponentActivity() {

    private lateinit var binding: ActivityCaptureShareBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCaptureShareBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.vCaptureShare.initialScreenShotShareView(
            this,
            requestPermissionLauncher
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.vCaptureShare.removeAutoDismissHandler(this)
    }

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { _ ->
            if (!PermissionManager.checkPhotoAccessIsLimited(this)) {
                CaptureShareManager.showSettingDialog(this)
            }
        }
}
