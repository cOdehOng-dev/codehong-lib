package com.codehong.library.debugtool.log

class TimberConfig {
    internal var packageName: String = ""
    internal var key: String = ""
    internal var tag: String = ""
    internal var enabled: Boolean = false

    class Builder {
        private var packageName: String = ""
        private var key: String = ""
        private var tag: String = ""
        private var enabled: Boolean = false

        fun packageName(packageName: String) = apply {
            this.packageName = packageName
                .replace(".dev", "")
                .replace(".qa", "")
                .replace(".prd", "")

            when {
                packageName.startsWith("com.hongji.library.util.") -> {
                    this.key = this.packageName
                        .substringAfterLast("com.hongji.library.util.")
                        .split(".")
                        .firstOrNull() ?: ""
                }

                packageName.startsWith("com.hongji.library.") -> {
                    this.key = this.packageName
                        .substringAfterLast("com.hongji.library.")
                        .split(".")
                        .firstOrNull() ?: ""
                }

                packageName.startsWith("com.hongji.") -> {
                    this.key = this.packageName
                        .substringAfter("com.hongji.")
                        .split(".")
                        .firstOrNull() ?: ""
                }

                else -> {
                    this.key = this.packageName
                }
            }
        }

        fun tag(tag: String) = apply {
            this.tag = tag
        }

        fun isEnabled(enabled: Boolean) = apply {
            this.enabled = enabled
        }

        fun build() = TimberConfig().apply {
            this.key = this@Builder.key
            this.packageName = this@Builder.packageName
            this.tag = this@Builder.tag
            this.enabled = this@Builder.enabled
        }
    }

    override fun toString(): String {
        return "TimberConfig(" +
                "key=$key, " +
                "packageName=$packageName, " +
                "tag=$tag, " +
                "enabled=$enabled" +
                ")"
    }
}
