#import "NotificationsBadge.h"
#import <React/RCTUtils.h>

@implementation NotificationsBadge

RCT_EXPORT_MODULE()

- (NSNumber *)setBadge:(double)count {
  dispatch_async(dispatch_get_main_queue(), ^{
    [UIApplication sharedApplication].applicationIconBadgeNumber = (NSInteger)count;
  });
  return @(count);
}

- (void)clearBadge {
  dispatch_async(dispatch_get_main_queue(), ^{
    [UIApplication sharedApplication].applicationIconBadgeNumber = 0;
  });
}

- (NSNumber *)getBadge {
  NSInteger current = [UIApplication sharedApplication].applicationIconBadgeNumber;
  return @(current);
}

- (std::shared_ptr<facebook::react::TurboModule>)getTurboModule:
  (const facebook::react::ObjCTurboModule::InitParams &)params
{
  return std::make_shared<facebook::react::NativeNotificationsBadgeSpecJSI>(params);
}

@end
