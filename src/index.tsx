import ReactNativeCpuInfo, {
  type CpuFeatures,
} from './NativeReactNativeCpuInfo';

export function getThreads(): number {
  return ReactNativeCpuInfo.getThreads();
}

export function getCpuFeatures(): Promise<CpuFeatures> {
  return ReactNativeCpuInfo.getCpuFeatures();
}
