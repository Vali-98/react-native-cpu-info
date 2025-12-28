#import "ReactNativeCpuInfo.h"
#import <React/RCTLog.h>
#import <Foundation/Foundation.h>

@implementation ReactNativeCpuInfo

RCT_EXPORT_MODULE()

// Returns the number of threads (CPU cores)
RCT_EXPORT_BLOCKING_SYNCHRONOUS_METHOD(getThreads)
{
    int processorCount = (int)[[NSProcessInfo processInfo] processorCount];
    return @(processorCount);
}

// Stub for getCpuFeatures
RCT_EXPORT_METHOD(getCpuFeatures:(RCTPromiseResolveBlock)resolve
                  rejecter:(RCTPromiseRejectBlock)reject)
{
    NSDictionary *cpuFeatures = @{
        @"armv8": @YES,
        @"adreno": @NO,
        @"hexagon": @NO,
        @"dotprod": @NO,   
        @"i8mm": @NO         
    };
    resolve(cpuFeatures);
}

@end
