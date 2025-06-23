package com.codehong.lib.sample.dynamicisland

import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.codehong.lib.sample.databinding.ActivityDynamicIslandBinding
import com.codehong.library.widget.dynamicisland.DynamicIslandInfo
import com.codehong.library.widget.dynamicisland.DynamicIslandManager
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.util.HongToastUtil
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class SampleDynamicIslandActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDynamicIslandBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDynamicIslandBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.vHeader.init(HongWidgetType.DYNAMIC_ISLAND.value) {
            finish()
        }

        if (DynamicIslandManager.isGranted(this)) {
            checkDynamicIslandPermission(true)
        } else {
            checkDynamicIslandPermission(false)
        }
    }

    private fun checkDynamicIslandPermission(isCheck: Boolean) {
        binding.scDynamicIsland.setOnCheckedChangeListener(null)
        binding.scDynamicIsland.isChecked = isCheck
        binding.scDynamicIsland.setOnCheckedChangeListener { _, isChecked ->
            DynamicIslandManager.setPermission(
                this,
                isChecked = isChecked,
                launcher = requestOverlayPermissionLauncher
            ) {
                startDynamicIsland()
            }
        }
    }

    private fun startDynamicIsland() {
        val dateFormat = SimpleDateFormat("yyyyMMddHHmmss", Locale.KOREA)
        dateFormat.timeZone = TimeZone.getTimeZone("Asia/Seoul")
        val startFutureTime = Date(System.currentTimeMillis() + 1000 * 60 * 60) // 1시간 후
        val start = dateFormat.format(startFutureTime)
        val endFutureTime = Date(System.currentTimeMillis() + 1000 * 60 * 120) // 2시간 후
        val end = dateFormat.format(endFutureTime)
        val ticketInfo = DynamicIslandInfo(
            thumbnailUrl = "https://images.unsplash.com/photo-1664190426315-b3abf1cf07ad?q=80&w=2187&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            fromCity = "FROM   SEOUL/INCHEON",
            toCity = "TO         TOKYO",
            startDate = start,
            endDate = end
        )
        if (DynamicIslandManager.isRunning()) {
            DynamicIslandManager.reset(ticketInfo)
        } else {
            DynamicIslandManager.schedule(this, ticketInfo)
        }
    }

    private val requestOverlayPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { _ ->
            if (DynamicIslandManager.isGranted(this)) {
                startDynamicIsland()
                checkDynamicIslandPermission(true)
            } else {
                HongToastUtil.showToast(this, "다이나믹 아일랜드 권한을 거부하였습니다")
                checkDynamicIslandPermission(false)
            }
        }
}
