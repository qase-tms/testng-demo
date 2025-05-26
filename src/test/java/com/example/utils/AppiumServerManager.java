package com.example.utils;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class AppiumServerManager {

    private AppiumDriverLocalService service;

    public void startServer() throws IOException { //Added exception handling

        //Get ANDROID_HOME from environment variables, fallback to config file
        String androidHome = System.getenv("ANDROID_HOME");
        if (androidHome == null) {
            androidHome = System.getProperty("android.sdk.path"); // Assumes property is set by config file or build system
            if (androidHome == null) {
                throw new IOException("ANDROID_HOME environment variable or android.sdk.path property not set.");
            }
        }

        Map<String, String> environment = new HashMap<>(System.getenv());
        environment.put("ANDROID_HOME", androidHome);
        environment.put("ANDROID_SDK_ROOT", androidHome);


        // Find Appium JS path dynamically.
        String appiumJSPath = findAppiumJS();
        if(appiumJSPath == null){
            throw new IOException("Appium JS path not found.");
        }

        service = new AppiumServiceBuilder()
                .withAppiumJS(new File(appiumJSPath)) //Use dynamically found path
                .withIPAddress("127.0.0.1")
                .usingPort(4723)
                .withEnvironment(environment)
                // .withArgument(GeneralServerFlag.BASEPATH, "/wd/hub") //Optional argument, uncomment if needed
                .withLogFile(new File("appium.log"))
                .build();
        try{
            service.start();
        }catch (Exception e){
            throw new IOException("Failed to start Appium server: " + e.getMessage(), e); // improved error handling
        }
    }

    // Helper function to find Appium JS path - you might need to adjust this based on your appium installation.
    private String findAppiumJS() {
        //Try to find appium using npm command.  This requires npm to be in PATH.
        try {
            Process process = Runtime.getRuntime().exec("npm root -g");
            process.waitFor();
            String npmGlobalPath = new String(process.getInputStream().readAllBytes()).trim();
            return npmGlobalPath + "/appium/build/lib/main.js"; // This assumes appium is installed globally
        } catch (IOException | InterruptedException e) {
            System.err.println("Error finding Appium JS path: " + e.getMessage());
            return null;
        }
    }


    public void stopServer() {
        if (service != null) {
            service.stop();
        }
    }

    public URL getServiceUrl() {
        return service.getUrl();
    }
}
