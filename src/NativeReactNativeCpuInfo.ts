import type { TurboModule } from 'react-native';
import { TurboModuleRegistry } from 'react-native';

export interface Spec extends TurboModule {
  getThreads(): number;
}

export default TurboModuleRegistry.getEnforcing<Spec>('ReactNativeCpuInfo');
