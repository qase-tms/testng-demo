package com.example.utils;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class AppiumServerManager {

    private AppiumDriverLocalService service;

    public void startServer() {

        Map<String, String> environment = new HashMap<>(System.getenv());
        environment.put("ANDROID_HOME", "/Users/gda/Library/Android/sdk");
        environment.put("ANDROID_SDK_ROOT", "/Users/gda/Library/Android/sdk");

        service = new AppiumServiceBuilder()
                .withAppiumJS(new File("/Users/gda/.nvm/versions/node/v16.20.2/lib/node_modules/appium/build/lib/main.js")) // путь к appium main.js
                .withIPAddress("127.0.0.1")
                .usingPort(4723)
                .withEnvironment(environment)
                .withArgument(GeneralServerFlag.BASEPATH, "/wd/hub")
                .withLogFile(new File("appium.log"))
                .build();
        service.start();
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
