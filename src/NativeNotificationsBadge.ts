import type { TurboModule } from 'react-native';
import { TurboModuleRegistry } from 'react-native';

export interface Spec extends TurboModule {
  setBadge(count: number): void;
  clearBadge(): void;
  getBadge(): Promise<number>;
}

const NativeNotificationsBadge =
    TurboModuleRegistry.getEnforcing<Spec>('NotificationsBadge');

export default NativeNotificationsBadge;

