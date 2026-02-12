package com.codehong.library.debugtool.log

import timber.log.Timber
import java.util.UnknownFormatConversionException
import java.util.concurrent.ConcurrentHashMap

/**
 * 개발의 편의성을 위해 로그를 출력하는 기능을 제공하는 클래스.
 * Timber를 커스텀 해서 각 앱, 라이브러리 모듈에서 로그를 출력.
 */
object TimberUtil {

    private const val STACK_TRACE_DEFAULT_DEPTH = 5
    private const val DEFAULT_LOG_MESSAGE = "기본 로그"

    private const val BREAK = 2000

    private const val ALWAYS = "always_"

    private enum class LogType {
        V, D, I, W, E
    }

    private val configMap: ConcurrentHashMap<String, TimberConfig> = ConcurrentHashMap()

    fun init(
        config: TimberConfig
    ) {
        val key = config.key
        configMap[key] = config
    }

    @JvmStatic
    fun v(
        message: String? = null,
        addTag: String? = null,
        throwable: Throwable? = null,
        vararg args: Any? = emptyArray()
    ) = LogType.V.prepareLog(
        addTag = addTag,
        throwable = throwable,
        message = message,
        args = args
    )

    @JvmStatic
    fun forceV(
        message: String? = null,
        addTag: String? = null,
        throwable: Throwable? = null,
        vararg args: Any? = emptyArray()
    ) = LogType.V.prepareLog(
        addTag = "$ALWAYS$addTag",
        throwable = throwable,
        message = message,
        args = args
    )

    @JvmStatic
    fun d(
        message: String? = null,
        addTag: String? = null,
        throwable: Throwable? = null,
        vararg args: Any? = emptyArray()
    ) = LogType.D.prepareLog(
        addTag = addTag,
        throwable = throwable,
        message = message,
        args = args
    )

    @JvmStatic
    fun forceD(
        message: String? = null,
        addTag: String? = null,
        throwable: Throwable? = null,
        vararg args: Any? = emptyArray()
    ) = LogType.D.prepareLog(
        addTag = "$ALWAYS$addTag",
        throwable = throwable,
        message = message,
        args = args
    )

    @JvmStatic
    fun i(
        message: String? = null,
        addTag: String? = null,
        throwable: Throwable? = null,
        vararg args: Any? = emptyArray()
    ) = LogType.I.prepareLog(
        addTag = addTag,
        throwable = throwable,
        message = message,
        args = args
    )

    @JvmStatic
    fun forceI(
        message: String? = null,
        addTag: String? = null,
        throwable: Throwable? = null,
        vararg args: Any? = emptyArray()
    ) = LogType.I.prepareLog(
        addTag = "$ALWAYS$addTag",
        throwable = throwable,
        message = message,
        args = args
    )

    @JvmStatic
    fun w(
        message: String? = null,
        addTag: String? = null,
        throwable: Throwable? = null,
        vararg args: Any? = emptyArray()
    ) = LogType.W.prepareLog(
        addTag = addTag,
        throwable = throwable,
        message = message,
        args = args
    )

    @JvmStatic
    fun forceW(
        message: String? = null,
        addTag: String? = null,
        throwable: Throwable? = null,
        vararg args: Any? = emptyArray()
    ) = LogType.W.prepareLog(
        addTag = "$ALWAYS$addTag",
        throwable = throwable,
        message = message,
        args = args
    )

    @JvmStatic
    fun e(
        message: String? = null,
        addTag: String? = null,
        throwable: Throwable? = null,
        vararg args: Any? = emptyArray()
    ) = LogType.E.prepareLog(
        addTag = addTag,
        throwable = throwable,
        message = message,
        args = args
    )

    @JvmStatic
    fun forceE(
        message: String? = null,
        addTag: String? = null,
        throwable: Throwable? = null,
        vararg args: Any? = emptyArray()
    ) = LogType.E.prepareLog(
        addTag = "$ALWAYS$addTag",
        throwable = throwable,
        message = message,
        args = args
    )

    private fun LogType.prepareLog(
        message: String? = null,
        addTag: String? = null,
        throwable: Throwable? = null,
        vararg args: Any?
    ) {
        this.prepareLog(
            addTag = addTag,
            message = getMessage(message, args),
            throwable = throwable,
        )
    }

    private fun LogType.prepareLog(
        classDepth: Int = STACK_TRACE_DEFAULT_DEPTH,
        addTag: String? = null,
        message: String? = null,
        throwable: Throwable? = null,
    ) {
        var mClassDepth = classDepth
        var stackTraces = Thread.currentThread().stackTrace[mClassDepth]
        var className = stackTraces.className
        var methodName = stackTraces.methodName

        if (className == this@TimberUtil::class.java.canonicalName) {
            mClassDepth += 2
            stackTraces = Thread.currentThread().stackTrace[mClassDepth]
            className = stackTraces.className
            methodName = stackTraces.methodName
        }

        val name = Class.forName(className).`package`?.name ?: ""
        val key = name.findKey()
//        Log.e("TimberUtil", "key: $key, name: $name, className: $className, methodName: $methodName, message: $message")

        val isEnabled = configMap[key]?.enabled == true || addTag?.startsWith(ALWAYS) == true
        if (!isEnabled) return

        checkTimberPlanted()
        val tag = getTag(key, addTag)

        try {
            if (message.isNullOrEmpty()) {
                val printMessage = if (throwable == null) DEFAULT_LOG_MESSAGE else null
                this.printLog(tag, className, methodName, throwable, printMessage)
            } else {
                if (message.length > BREAK) {
                    this.printLog(
                        tag,
                        className,
                        methodName,
                        throwable,
                        message.substring(0, BREAK)
                    )
                    this.prepareLog(
                        classDepth = mClassDepth + 1,
                        addTag = addTag,
                        throwable = throwable,
                        message = message.substring(BREAK)
                    )
                } else {
                    this.printLog(
                        tag,
                        className,
                        methodName,
                        throwable,
                        message
                    )
                }
            }
        } catch (e: OutOfMemoryError) {
            LogType.E.printLog(
                tag,
                className,
                methodName,
                null,
                "출력하고자 하는 로그의 길이가 너무 길어요!!"
            )
        } catch (e: UnknownFormatConversionException) {
            LogType.E.printLog(
                tag,
                className,
                methodName,
                null,
                "출력하고자 하는 로그의 포맷 변경에 실패했어요!!"
            )
        } catch (e: Exception) {
            LogType.E.printLog(
                tag,
                className,
                methodName,
                e,
                null
            )
        }
    }

    private fun LogType.printLog(
        finalTag: String,
        className: String,
        methodName: String,
        t: Throwable?,
        message: String?
    ) {
        val info = arrayListOf<String>()
        className.classFileName().takeIf { it.isNotEmpty() }?.let { info.add(it) }
        methodName.takeIf { it.isNotEmpty() }?.let { info.add(it) }

        val finalMessage = "${info.joinToString("/")} : ${message ?: DEFAULT_LOG_MESSAGE}"

        when (this) {
            LogType.V -> Timber.tag(finalTag).v(t, finalMessage)
            LogType.D -> Timber.tag(finalTag).d(t, finalMessage)
            LogType.I -> Timber.tag(finalTag).i(t, finalMessage)
            LogType.W -> Timber.tag(finalTag).w(t, finalMessage)
            LogType.E -> Timber.tag(finalTag).e(t, finalMessage)
        }
    }

    private fun checkTimberPlanted() {
        if (Timber.treeCount < 1) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun String.findKey(): String {
        configMap.forEach { (key, value) ->
            if (this.startsWith(value.packageName)) {
                return key
//                    .also { Log.i("TimberUtil", "findKey $this > $it") }
            }
        }

        return ""
    }

    private fun getTag(
        key: String,
        addTag: String? = null
    ): String {
        val tags = arrayListOf<String>()
        configMap[key]
            ?.tag
            ?.takeIf { it.isNotEmpty() }
            ?.let { tags.add(it) }
            ?: run {
                if (addTag?.startsWith(ALWAYS) == true) {
                    addTag.split(ALWAYS).lastOrNull() ?: ""
                } else {
                    ""
                }
            }

        addTag
            ?.takeIf { it.isNotEmpty() }
            ?.let { tags.add(it.replace(ALWAYS, "")) }

        return tags.distinct().joinToString("/")
//            .also { Log.d("TimberUtil", "getTag = $it, addTag = $addTag") }
    }

    private fun String.classFileName(): String {
        val lastIndex = this.lastIndexOf(".") + 1
        return this.substring(lastIndex, this.length)
    }

    private fun getMessage(
        message: String?,
        vararg args: Any?
    ): String {
        if (message.isNullOrEmpty()) return ""
        return try {
            String.format(message, args)
        } catch (e: Exception) {
            message
        }
    }
}