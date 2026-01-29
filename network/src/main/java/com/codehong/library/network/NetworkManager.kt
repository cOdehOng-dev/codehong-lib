package com.codehong.library.network
import android.content.Context
import com.codehong.library.network.debug.TimberConfig
import com.codehong.library.network.debug.TimberUtil
import com.google.gson.Gson
import com.hongji.library.util.network.NetworkConfig
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

object NetworkManager {

    internal var NETWORK_LIB_TAG = "네트워크_lib"

    internal var applicationContext: Context? = null
    internal var config = NetworkConfig()

    @JvmStatic
    fun init(
        applicationContext: Context,
        config: NetworkConfig
    ) {
        this.applicationContext = applicationContext
        this.config = config

        TimberUtil.init(
            config = TimberConfig.Builder()
                .isEnabled(config.isEnabledLogging)
                .packageName(this::class.java.`package`?.name ?: "")
                .tag(NETWORK_LIB_TAG)
                .build()
        )
    }

    @JvmStatic
    fun getRetrofitBuilder(
        connectTimeout: Long? = null,
        readTimeout: Long? = null,
        writeTimeout: Long? = null,
    ): Retrofit.Builder {
        return Retrofit.Builder().apply {
            client(
                HttpClient
                    .getHttpClientBuilder(
                        connectTimeout = connectTimeout,
                        readTimeout = readTimeout,
                        writeTimeout = writeTimeout,
                    )
                    .build()
            )
        }
    }

    inline fun <reified T>getApiService(
        baseUrl: String,
        convertType: ConvertType = ConvertType.JSON,
        converters: List<Converter.Factory>? = null,
        gson: Gson? = null,
        connectTimeout: Long? = null,
        readTimeout: Long? = null,
        writeTimeout: Long? = null,
    ): T {
        val converter = getConverter(convertType, gson)

        val builder =  getRetrofitBuilder(
            connectTimeout = connectTimeout,
            readTimeout = readTimeout,
            writeTimeout = writeTimeout,
        )
            .baseUrl(baseUrl)
            .addConverterFactory(converter)

        converters?.forEach {
            builder.addConverterFactory(it)
        }

        return builder
            .build()
            .create(T::class.java)
    }

    fun getConverter(
        convertType: ConvertType,
        gson: Gson? = null
    ): Converter.Factory {
        return when (convertType) {
            ConvertType.JSON -> GsonConverterFactory.create(gson ?: Gson())
            ConvertType.SCALARS -> ScalarsConverterFactory.create()
            ConvertType.XML -> SimpleXmlConverterFactory.create()
        }
    }
}