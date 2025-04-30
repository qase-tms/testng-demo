# How to install Appium and run tests on Android devices

## Install Appium

- Install Node.js (if not already installed)
- Install Appium using npm:

  ```bash
    npm install -g appium
  ```

- Verify the installation:

  ```bash
    appium -v
  ```

## Install Android driver

- Install the Android driver using npm:
  ```bash
  appium driver install uiautomator2
  ```

- Verify the installation:
  ```bash
    appium driver list --installed
    ```

## Install Android Studio

- Download and install Android Studio from the official website: [Android Studio](https://developer.android.com/studio)
- Install the Android SDK and necessary tools using the SDK Manager in Android Studio.
- Set up the Android emulator
    - Open Android Studio and go to "AVD Manager" (Android Virtual Device Manager).
    - Create a new virtual device with the desired configuration (e.g., Pixel 4, API level 30).
    - Start the emulator.

## Run tests

Appium server automatically starts when you run the tests and connected to the device.
