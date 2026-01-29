package com.hongji.library.util.network

class NetworkConfig {

    internal var isEnabledLogging: Boolean = false
    internal var isEnabledLoggingLineBreak: Boolean = false
    internal var isEnabledLoggingJsonFormatter: Boolean = false

    class Builder {

        private var loggingEnabled: Boolean = false
        private var loggingLineBreakEnabled: Boolean = false
        private var loggingJsonFormatterEnabled: Boolean = false

        fun isEnabledLogging(enable: Boolean) = apply {
            this.loggingEnabled = enable
        }

        fun isEnabledLoggingLineBreak(enable: Boolean) = apply {
            this.loggingLineBreakEnabled = enable
        }

        fun isEnabledLoggingJsonFormatter(enable: Boolean) = apply {
            this.loggingJsonFormatterEnabled = enable
        }

        fun build() = NetworkConfig().apply {
            this.isEnabledLogging = this@Builder.loggingEnabled
            this.isEnabledLoggingLineBreak = this@Builder.loggingLineBreakEnabled
            this.isEnabledLoggingJsonFormatter = this@Builder.loggingJsonFormatterEnabled
        }
    }

    override fun toString(): String {
        return "NetworkConfig(" +
                "isEnabledLogging=$isEnabledLogging, " +
                "isEnabledLoggingLineBreak=$isEnabledLoggingLineBreak, " +
                "isEnabledLoggingJsonFormatter=$isEnabledLoggingJsonFormatter, " +
                ")"
    }
}
