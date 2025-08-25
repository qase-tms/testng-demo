# Qase TestNG ConcurrentModificationException Reproduction Project

This project is specifically designed to reproduce the `ConcurrentModificationException` that occurs in the Qase TestNG reporter when running tests in parallel mode.

## 🎯 Quick Start

### Option 1: Automated Reproduction (Recommended)
```bash
# Update qase.config.json with your credentials first
./reproduce-issue.sh
```

### Option 2: Manual Execution
```bash
# Standard approach
mvn clean test

# High concurrency approach (more likely to reproduce)
mvn clean test -Dparallel=methods -DthreadCount=16
```

## 📋 Setup Instructions

1. **Update Qase Configuration**:
   Edit `qase.config.json` and replace the placeholder values:
   ```json
   {
     "testops": {
       "api": {
         "token": "YOUR_ACTUAL_QASE_API_TOKEN"
       },
       "project": "YOUR_PROJECT_CODE"
     }
   }
   ```

2. **Run the Reproduction**:
   ```bash
   ./reproduce-issue.sh
   ```

## 🔍 What This Project Does

- **65+ Test Methods**: Across 4 test classes designed for maximum concurrency
- **Small Batch Size**: Set to 5 to trigger frequent uploads during parallel execution  
- **Aggressive Parallelism**: 8+ threads running simultaneously
- **Race Condition Target**: Specifically targets the non-thread-safe ArrayList in TestopsReporter

## 📊 Expected Results

### Success (Bug Reproduced)
```
java.util.ConcurrentModificationException
    at java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1714)
    at io.qase.commons.client.ApiClientV2.uploadResults(ApiClientV2.java:54)
    at io.qase.commons.reporters.TestopsReporter.addResult(TestopsReporter.java:56)
    ...
```

### Normal Execution (Bug Not Triggered)
- All tests pass successfully
- Console shows multiple thread names indicating parallel execution
- Qase results uploaded in batches

## 📚 Documentation

- **[REPRODUCTION_GUIDE.md](REPRODUCTION_GUIDE.md)**: Comprehensive technical analysis and reproduction steps
- **[reproduce-issue.sh](reproduce-issue.sh)**: Automated script that tries multiple configurations

## 🛠 Project Structure

```
testng-demo/
├── pom.xml                           # Minimal deps + parallel config
├── qase.config.json                  # Small batch size configuration
├── reproduce-issue.sh                # Automated reproduction script
├── REPRODUCTION_GUIDE.md             # Detailed technical guide
└── src/test/java/com/example/tests/
    ├── ParallelTestClass1.java       # 10 standard test methods
    ├── ParallelTestClass2.java       # 10 standard test methods  
    ├── ParallelTestClass3.java       # High-concurrency thread pool tests
    └── ParallelTestClass4.java       # 15 rapid-fire tests
```

## 🎯 Root Cause

The issue occurs in `TestopsReporter.java` where a non-thread-safe `ArrayList` is used to store test results. When multiple TestNG threads run in parallel, they simultaneously access this shared collection during:

1. **Adding Results**: Multiple threads call `addResult()` 
2. **Batch Uploads**: One thread iterates over the list while others modify it
3. **List Operations**: Concurrent `add()` and `clear()` operations

## 🔧 Technical Details

- **Thread Count**: 8-16 parallel threads
- **Batch Size**: 5 (triggers frequent uploads)
- **Test Volume**: 65+ total test executions
- **Timing Strategy**: Varying sleep times (1-20ms) to stagger completions

---

## Legacy Documentation

### Syntax Examples
This repository also contains syntax examples in the `src/test/java` directory for general Qase TestNG usage.

### Frequently Asked Questions
