#import "ReactNativeCpuInfo.h"
#import <Foundation/Foundation.h>

@implementation ReactNativeCpuInfo
RCT_EXPORT_MODULE()

- (NSNumber *)getThreads {
    int processorCount = [[NSProcessInfo processInfo] processorCount];
    return @(processorCount);
}

- (std::shared_ptr<facebook::react::TurboModule>)getTurboModule:
    (const facebook::react::ObjCTurboModule::InitParams &)params
{
    return std::make_shared<facebook::react::NativeReactNativeCpuInfoSpecJSI>(params);
}

@end
