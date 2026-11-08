package org.factory.settings;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;

public class BrowserChromeOptions implements IBrowserSettings{

    @Override
    public AbstractDriverOptions settings() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--window-size=1366,768");
        return options;
    }
}
