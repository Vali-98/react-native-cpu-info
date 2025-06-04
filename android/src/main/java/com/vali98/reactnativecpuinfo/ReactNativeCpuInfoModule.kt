package com.vali98.reactnativecpuinfo

import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.module.annotations.ReactModule

@ReactModule(name = ReactNativeCpuInfoModule.NAME)
class ReactNativeCpuInfoModule(reactContext: ReactApplicationContext) :
  NativeReactNativeCpuInfoSpec(reactContext) {

  override fun getName(): String {
    return NAME
  }

  // Example method
  // See https://reactnative.dev/docs/native-modules-android
  override fun multiply(a: Double, b: Double): Double {
    return a * b
  }

  companion object {
    const val NAME = "ReactNativeCpuInfo"
  }
}
