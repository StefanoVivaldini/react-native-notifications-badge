import NotificationsBadge from './NativeNotificationsBadge';

export function setNotificationsBadgeCount(a: number) {
  console.log('count', a);
  return NotificationsBadge.setBadge(a);
}

export function getNotificationsBadgeCount() {
  return NotificationsBadge.getBadge();
}

export function clearNotificationsBadgeCount() {
  return NotificationsBadge.clearBadge();
}
