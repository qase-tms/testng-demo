#!/bin/bash

# ConcurrentModificationException Reproduction Script
# This script runs the tests multiple times with different configurations
# to maximize the chance of reproducing the race condition

echo "=========================================="
echo "Qase TestNG ConcurrentModificationException Reproduction"
echo "=========================================="

# Check if qase.config.json has valid credentials
if grep -q "YOUR_ACTUAL_QASE_API_TOKEN" qase.config.json; then
    echo "⚠️  WARNING: Please update qase.config.json with your actual Qase API credentials"
    echo "   Edit the file and replace:"
    echo "   - YOUR_ACTUAL_QASE_API_TOKEN with your real API token"
    echo "   - YOUR_PROJECT_CODE with your real project code"
    echo ""
    read -p "Press Enter to continue anyway (tests may fail) or Ctrl+C to exit..."
fi

echo ""
echo "🔄 Attempting to reproduce ConcurrentModificationException..."
echo "   This may take several attempts as it's a race condition"
echo ""

# Function to run tests with specific configuration
run_test_attempt() {
    local attempt=$1
    local config=$2
    local command=$3
    
    echo "----------------------------------------"
    echo "Attempt $attempt: $config"
    echo "Command: $command"
    echo "----------------------------------------"
    
    # Run the test and capture both stdout and stderr
    if eval "$command" 2>&1 | tee "test-output-attempt-$attempt.log"; then
        # Check if ConcurrentModificationException occurred
        if grep -q "ConcurrentModificationException" "test-output-attempt-$attempt.log"; then
            echo ""
            echo "🎯 SUCCESS! ConcurrentModificationException reproduced in attempt $attempt"
            echo "📋 Check test-output-attempt-$attempt.log for full details"
            echo ""
            echo "Stack trace preview:"
            grep -A 10 "ConcurrentModificationException" "test-output-attempt-$attempt.log"
            return 0
        else
            echo "❌ Attempt $attempt: No exception occurred"
        fi
    else
        echo "❌ Attempt $attempt: Test execution failed"
    fi
    
    return 1
}

# Clean previous logs
rm -f test-output-attempt-*.log

# Attempt 1: Standard parallel execution
if run_test_attempt 1 "Standard Parallel (8 threads)" "mvn clean test -q"; then
    exit 0
fi

# Attempt 2: Higher thread count
if run_test_attempt 2 "High Concurrency (12 threads)" "mvn clean test -Dparallel=methods -DthreadCount=12 -q"; then
    exit 0
fi

# Attempt 3: Maximum concurrency
if run_test_attempt 3 "Maximum Concurrency (16 threads)" "mvn clean test -Dparallel=methods -DthreadCount=16 -q"; then
    exit 0
fi

# Attempt 4: Using specific suite
if run_test_attempt 4 "Custom Suite Configuration" "mvn clean test -DsuiteXmlFile=src/test/resources/parallel-suite.xml -q"; then
    exit 0
fi

# Attempt 5: Aggressive settings
if run_test_attempt 5 "Aggressive Settings (20 threads)" "mvn clean test -Dparallel=methods -DthreadCount=20 -DuseUnlimitedThreads=false -q"; then
    exit 0
fi

echo ""
echo "=========================================="
echo "🤔 No ConcurrentModificationException reproduced after 5 attempts"
echo ""
echo "Troubleshooting suggestions:"
echo "1. Verify Qase credentials in qase.config.json"
echo "2. Try reducing batch size to 3 or 2 in qase.config.json"
echo "3. Run the script multiple times (race conditions are unpredictable)"
echo "4. Check test-output-attempt-*.log files for any errors"
echo ""
echo "Manual retry command:"
echo "mvn clean test -Dparallel=methods -DthreadCount=16"
echo "=========================================="

exit 1
