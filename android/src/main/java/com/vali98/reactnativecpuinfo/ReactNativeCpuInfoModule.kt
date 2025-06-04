package com.vali98.reactnativecpuinfo

import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.module.annotations.ReactModule
import java.lang.Runtime

@ReactModule(name = ReactNativeCpuInfoModule.NAME)
class ReactNativeCpuInfoModule(reactContext: ReactApplicationContext) :
  NativeReactNativeCpuInfoSpec(reactContext) {

  override fun getName(): String {
    return NAME
  }

  override fun getThreads(): Double {
    return Runtime.getRuntime().availableProcessors().toDouble()
  }

  companion object {
    const val NAME = "ReactNativeCpuInfo"
  }
}
