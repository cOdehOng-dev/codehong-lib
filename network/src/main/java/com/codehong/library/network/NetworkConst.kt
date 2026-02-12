package com.codehong.library.network

object NetworkConst {
    const val CONNECT_TIMEOUT: Long = 30 * 3
    const val WRITE_TIMEOUT: Long = 15 * 3
    const val READ_TIMEOUT: Long = 15 * 3
    const val CONNECTION_POOL_TIMEOUT_MILLISECONDS: Long = 60 * 60 * 1000
    const val MAX_IDLE_CONNECTIONS = 8
}