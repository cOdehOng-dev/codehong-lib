package com.codehong.library.network

import com.codehong.library.debugtool.log.TimberUtil
import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import com.google.gson.JsonParser
import com.google.gson.JsonSyntaxException
import okhttp3.ConnectionPool
import okhttp3.JavaNetCookieJar
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.net.CookieHandler
import java.util.concurrent.TimeUnit

object HttpClient {

    fun getHttpClientBuilder(
        connectTimeout: Long? = null,
        readTimeout: Long? = null,
        writeTimeout: Long? = null,
    ): OkHttpClient.Builder {
        return OkHttpClient().newBuilder().apply {
            connectionPool(connectionPool)
            connectTimeout(connectTimeout ?: NetworkConst.CONNECT_TIMEOUT, TimeUnit.SECONDS)
            readTimeout(readTimeout ?: NetworkConst.READ_TIMEOUT, TimeUnit.SECONDS)
            writeTimeout(writeTimeout ?: NetworkConst.WRITE_TIMEOUT, TimeUnit.SECONDS)

//            TimberUtil.d("config = ${NetworkManager.config}")

            // Http 로그 확인
            if (NetworkManager.config.isEnabledLogging) {
                addHttpLoggingInterceptor()
            }
        }
    }

    private val connectionPool
        get() = ConnectionPool(
            NetworkConst.MAX_IDLE_CONNECTIONS,
            NetworkConst.CONNECTION_POOL_TIMEOUT_MILLISECONDS,
            TimeUnit.MILLISECONDS
        )

    private fun OkHttpClient.Builder.addJavaNetCookieJar() {
        CookieHandler.getDefault()?.let {
            cookieJar(JavaNetCookieJar(it))
        }
    }

    private fun OkHttpClient.Builder.addHttpLoggingInterceptor() {
        val interceptor = HttpLoggingInterceptor { message ->
            logging(message)
        }.apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        addNetworkInterceptor(interceptor)
    }

    private fun logging(message: String) {
        when {
            NetworkManager.config.isEnabledLoggingLineBreak -> {
                TimberUtil.d(
                    addTag = NetworkManager.NETWORK_LIB_TAG,
                    message = toOneLineString(message)
                )
            }

            NetworkManager.config.isEnabledLoggingJsonFormatter -> {
                val rMessage = toOneLineString(message)
                if (!message.startsWith("{") && !message.startsWith("[")) {
                    TimberUtil.d(
                        addTag = NetworkManager.NETWORK_LIB_TAG,
                        message = rMessage
                    )
                    return
                }

                try {
                    // Timber 와 Gson setPrettyPrinting 를 이용해 json 을 보기 편하게 표시해준다.
                    val gson = GsonBuilder().setPrettyPrinting().create()
                    val jsonElement: JsonElement = JsonParser().parse(rMessage)
                    val prettyJson: String = gson.toJson(jsonElement)
                    TimberUtil.d(prettyJson)
                } catch (e: JsonSyntaxException) {
                    e.printStackTrace()
                    TimberUtil.d(rMessage)
                }
            }

            else -> {
                TimberUtil.d(toOneLineString(message))
            }
        }
    }

    private fun toOneLineString(str: String): String {
        return str.replace("\\r\\n|\\r|\\n|\\n\\r".toRegex(),"")
            .replace("\\s+".toRegex(), " ")
    }
}