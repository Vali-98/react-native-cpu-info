import ReactNativeCpuInfo from './NativeReactNativeCpuInfo';

export function getThreads(): number {
  return ReactNativeCpuInfo.getThreads();
}
