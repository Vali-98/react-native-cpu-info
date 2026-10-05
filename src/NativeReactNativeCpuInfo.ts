import type { TurboModule } from 'react-native';
import { TurboModuleRegistry } from 'react-native';

export interface CpuFeatures {
  armv8: boolean;
  adreno: boolean;
  hexagon: boolean;
  dotprod: boolean;
  i8mm: boolean;
}

export interface Spec extends TurboModule {
  getThreads(): number;
  getTotalMemory(): number;
  getCpuFeatures(): Promise<CpuFeatures>;
}

export default TurboModuleRegistry.getEnforcing<Spec>('ReactNativeCpuInfo');
