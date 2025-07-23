package com.codehong.lib.sample.captureshare

import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.codehong.lib.sample.base.BaseActivity
import com.codehong.lib.sample.databinding.ActivityCaptureShareBinding
import com.codehong.library.widget.captureshare.CaptureShareManager
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.util.PermissionManager

class SampleCaptureShareActivity : BaseActivity() {

    private lateinit var binding: ActivityCaptureShareBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCaptureShareBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.vHeader.init(
            title = "${HongWidgetType.CAPTURE_SHARE} 샘플",
            back = {
                finish()
            }
        )

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
