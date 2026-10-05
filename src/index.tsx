import ReactNativeCpuInfo, {
  type CpuFeatures,
} from './NativeReactNativeCpuInfo';

export function getThreads(): number {
  return ReactNativeCpuInfo.getThreads();
}

export function getTotalMemory(): number {
  return ReactNativeCpuInfo.getTotalMemory();
}

export function getCpuFeatures(): Promise<CpuFeatures> {
  return ReactNativeCpuInfo.getCpuFeatures();
}
