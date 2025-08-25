# ConcurrentModificationException Reproduction Guide

## Overview
This project is designed to reproduce the `ConcurrentModificationException` that occurs in the Qase TestNG reporter when running tests in parallel mode. The exception happens due to thread safety issues in the shared `ArrayList` used to store test results.

## Root Cause
The issue stems from the `TestopsReporter` class in the Qase TestNG reporter, which uses a non-thread-safe `ArrayList` to store test results. When multiple TestNG threads run in parallel, they simultaneously access this shared collection, leading to concurrent modification exceptions.

**Key Problem Areas:**
- `TestopsReporter.java:27` - Uses `new ArrayList<>()` (not thread-safe)
- `TestopsReporter.java:48-58` - Concurrent access during `addResult()` and batch uploads
- `CoreReporter.java:51` - Multiple threads calling `addResult()` simultaneously

## Project Structure

```
testng-demo/
├── pom.xml                                    # Minimal dependencies + parallel config
├── qase.config.json                          # Small batch size (5) to trigger frequent uploads
├── src/test/java/com/example/tests/
│   ├── ParallelTestClass1.java               # 10 test methods with varying sleep times
│   ├── ParallelTestClass2.java               # 10 test methods with varying sleep times  
│   ├── ParallelTestClass3.java               # 5 methods with threadPoolSize=4, invocationCount=8
│   └── ParallelTestClass4.java               # 15 rapid-fire tests (1ms sleep each)
├── src/test/resources/
│   └── parallel-suite.xml                    # TestNG suite with aggressive parallel config
└── REPRODUCTION_GUIDE.md                     # This file
```

## Configuration Details

### Maven Configuration (`pom.xml`)
- **Parallel Execution**: `<parallel>methods</parallel>` with `<threadCount>8</threadCount>`
- **Essential Dependencies**: Only Qase TestNG reporter, TestNG, and AspectJ weaver
- **Surefire Plugin**: Configured with Qase listener and AspectJ agent

### Qase Configuration (`qase.config.json`)
- **Batch Size**: Set to `5` (small batch size triggers frequent uploads)
- **Debug Mode**: Enabled for detailed logging
- **TestOps Mode**: Required to trigger the reporter code path with the bug

### TestNG Suite (`parallel-suite.xml`)
- **Suite-level Parallelism**: `parallel="methods"` with `thread-count="8"`
- **Multiple Test Groups**: Two test groups for maximum concurrency pressure
- **Qase Listener**: Explicitly configured

## Test Design Strategy

### Test Classes Overview
1. **ParallelTestClass1 & ParallelTestClass2**: Standard test methods with varying execution times (1-20ms)
2. **ParallelTestClass3**: Uses TestNG's `threadPoolSize` and `invocationCount` for high concurrency
3. **ParallelTestClass4**: Rapid-fire tests with minimal execution time (1ms each)

### Why This Design Works
- **High Test Volume**: 65+ total test executions create many concurrent result additions
- **Small Batch Size**: Batch size of 5 means uploads happen frequently during parallel execution
- **Varying Execution Times**: Different sleep times ensure tests complete at different intervals
- **Thread Pool Tests**: `ParallelTestClass3` creates additional concurrency pressure
- **Rapid Tests**: `ParallelTestClass4` maximizes the rate of concurrent access

## Reproduction Steps

### Prerequisites
1. Java 8 or higher
2. Maven 3.6+
3. Valid Qase API token and project code

### Setup
1. **Clone/Navigate to the project**:
   ```bash
   cd /Users/manju/qase-tms/testng-demo
   ```

2. **Update Qase Configuration**:
   Edit `qase.config.json` and replace with your actual Qase credentials:
   ```json
   {
     "mode": "testops",
     "debug": true,
     "testops": {
       "api": {
         "token": "YOUR_ACTUAL_QASE_API_TOKEN"
       },
       "project": "YOUR_PROJECT_CODE"
     }
   }
   ```

### Execution Methods

#### Method 1: Using Maven Surefire (Recommended)
```bash
mvn clean test
```

#### Method 2: Direct Maven with Parallel Parameters
```bash
mvn clean test -Dparallel=methods -DthreadCount=8
```

#### Method 3: Using Specific Suite
```bash
mvn clean test -DsuiteXmlFile=src/test/resources/parallel-suite.xml
```

#### Method 4: Maximum Concurrency (Most Likely to Reproduce)
```bash
mvn clean test -Dparallel=methods -DthreadCount=12 -DuseUnlimitedThreads=false
```

## Expected Behavior

### Successful Reproduction
When the ConcurrentModificationException occurs, you'll see a stack trace similar to:

```
java.util.ConcurrentModificationException
    at java.base/java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1714)
    at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
    at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
    at java.base/java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:921)
    at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
    at java.base/java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:682)
    at io.qase.commons.client.ApiClientV2.uploadResults(ApiClientV2.java:54)
    at io.qase.commons.reporters.TestopsReporter.addResult(TestopsReporter.java:56)
    at io.qase.commons.reporters.CoreReporter.lambda$addResult$2(CoreReporter.java:51)
    at io.qase.commons.reporters.CoreReporter.executeWithFallback(CoreReporter.java:72)
    at io.qase.commons.reporters.CoreReporter.addResult(CoreReporter.java:51)
    at io.qase.testng.QaseListener.stopTestCase(QaseListener.java:90)
    at io.qase.testng.QaseListener.onTestSuccess(QaseListener.java:51)
```

### Normal Execution (No Bug)
- All tests pass successfully
- You'll see thread names in console output showing parallel execution
- Qase results are uploaded in batches of 5

## Troubleshooting

### If Exception Doesn't Occur
1. **Increase Thread Count**: Try `-DthreadCount=16` or higher
2. **Reduce Batch Size**: Set batch size to 3 or even 2 in `qase.config.json`
3. **Run Multiple Times**: The race condition may not occur on every run
4. **Add More Tests**: Create additional test classes with more methods

### If Tests Don't Run in Parallel
1. **Check Maven Surefire Version**: Ensure version 3.0.0-M5 or higher
2. **Verify TestNG Version**: Should be 7.8.0 or compatible
3. **Check Suite Configuration**: Ensure `parallel="methods"` is set correctly

### Common Issues
1. **Invalid Qase Credentials**: Tests will fail if API token/project is incorrect
2. **Network Issues**: Batch uploads may fail due to connectivity
3. **Java Version**: Ensure Java 8+ for proper parallel execution support

## Technical Analysis

### Why Small Batch Size Matters
- Batch size of 5 means every 5th test completion triggers an upload
- With 65+ test executions, this creates ~13 upload attempts
- Each upload iterates over the shared ArrayList while other threads are adding results

### Why Thread Pool Tests Are Effective
- `ParallelTestClass3` uses `threadPoolSize=4` and `invocationCount=8`
- This creates 4×8×5 = 160 total test executions from just 5 test methods
- Massive concurrency pressure on the shared results collection

### Timing Factors
- Different sleep times (1-20ms) ensure tests complete at staggered intervals
- This maximizes the chance that one thread is uploading while others are adding results
- The race condition window is small but frequent

## Success Metrics

A successful reproduction should show:
1. **Console Output**: Multiple thread names indicating parallel execution
2. **Exception Stack Trace**: ConcurrentModificationException from ArrayList
3. **Timing**: Exception typically occurs within first few seconds of test execution
4. **Batch Logging**: Debug logs showing batch upload attempts

## Next Steps

Once you've reproduced the issue, potential fixes include:
1. **Thread-Safe Collection**: Replace ArrayList with ConcurrentLinkedQueue
2. **Synchronization**: Add synchronized blocks around critical sections
3. **Atomic Operations**: Use atomic operations for batch size checking
4. **Separate Collections**: Use per-thread collections with periodic aggregation

This reproduction case demonstrates a classic race condition in multi-threaded environments and provides a reliable way to trigger the ConcurrentModificationException for debugging and testing fixes.
