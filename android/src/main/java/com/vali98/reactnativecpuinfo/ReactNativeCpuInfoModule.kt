package com.vali98.reactnativecpuinfo

import com.facebook.react.bridge.*
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.Executors
import java.util.regex.Pattern

class ReactNativeCpuInfoModule(reactContext: ReactApplicationContext) :
    NativeReactNativeCpuInfoSpec(reactContext) {

    private val executorService = Executors.newCachedThreadPool()
    private val tasks = ConcurrentHashMap<java.util.concurrent.Future<WritableMap>, String>()

    override fun getName(): String = NAME

    override fun getThreads(): Double = Runtime.getRuntime().availableProcessors().toDouble()

    // Make sure this method signature matches your spec
    override fun getCpuFeatures(promise: Promise) {
        val future = executorService.submit<WritableMap> {
            val result: WritableMap = Arguments.createMap()

            val isV8 = isArm64V8a()
            result.putBoolean("armv8", isV8)

            val gpuInfo = (android.os.Build.HARDWARE + " " +
                    android.os.Build.MANUFACTURER + " " +
                    android.os.Build.MODEL).lowercase()
            val hasAdreno = Pattern.compile("(adreno|qcom|qualcomm)").matcher(gpuInfo).find()
            result.putBoolean("adreno", hasAdreno)

            if (isV8) {
                val cpuFeatures = getCpuFeaturesString()
                val hasDotProd = cpuFeatures.contains("dotprod") || cpuFeatures.contains("asimddp")
                val hasInt8Matmul = cpuFeatures.contains("i8mm")
                val hasHexagon = isHexagonSupported()
                result.putBoolean("hexagon", hasHexagon)
                result.putBoolean("i8mm", hasInt8Matmul)
                result.putBoolean("dotprod", hasDotProd)
            } else {
                result.putBoolean("i8mm", false)
                result.putBoolean("dotprod", false)
                result.putBoolean("hexagon", false)
            }

            result
        }

        tasks[future] = "getCpuFeatures"

        executorService.execute {
            try {
                val result = future.get()
                promise.resolve(result)
            } catch (e: Exception) {
                promise.reject("CPU_FEATURE_ERROR", e)
            } finally {
                tasks.remove(future)
            }
        }
    }

    private fun getCpuFeaturesString(): String {
        val file = File("/proc/cpuinfo")
        return try {
            BufferedReader(FileReader(file)).use { reader ->
                var line: String?
                while (reader.readLine().also { line = it } != null) {
                    if (line!!.startsWith("Features")) return line!!
                }
                ""
            }
        } catch (e: Exception) {
            ""
        }
    }

    private fun isHexagonSupported(): Boolean {
        return if (android.os.Build.VERSION.SDK_INT >= 31) {
            val socModel = android.os.Build.SOC_MODEL?.uppercase() ?: ""
            socModel.matches(Regex(".*(SM8450|SM8550|SM8650|SM8635|SM8750).*"))
        } else {
            false
        }
    }

    private fun isArm64V8a(): Boolean {
        return android.os.Build.SUPPORTED_ABIS.contains("arm64-v8a")
    }

    companion object {
        const val NAME = "ReactNativeCpuInfo"
    }
}
