package com.qaprosoft.carina.demo.gui.pages;

import java.lang.invoke.MethodHandles;
import java.util.List;

import com.qaprosoft.carina.core.foundation.utils.Configuration;
import com.qaprosoft.carina.core.foundation.utils.R;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import com.qaprosoft.carina.demo.gui.components.FooterMenu;
import com.qaprosoft.carina.demo.gui.components.WeValuePrivacyAd;


public class HomePage extends AbstractPage {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @FindBy(xpath = "//div[contains(@class,'styles_main-navigation__3aXUl styles_navi__3OKNE')]//a")
    private List<ExtendedWebElement> mainNavigationLinks;

    @FindBy(xpath = "//div[contains(@class,'styles_semi-oval__2qzbp styles_menu-header__1Iprf')]//a")
    private List<ExtendedWebElement> orderOnline;


    public HomePage(WebDriver driver) {
        super(driver);
        setPageAbsoluteURL(R.CONFIG.get(Configuration.Parameter.URL.getKey()));
    }

    public void selectBrand(String mainNav) {
        LOGGER.info("selecting '" + mainNav + "' mainNav...");
        for (ExtendedWebElement mainNavigationLink : mainNavigationLinks) {
            String currentBrand = mainNavigationLink.getText();
            LOGGER.info("currentBrand: " + currentBrand);
            if (mainNav.equalsIgnoreCase(currentBrand)) {
                mainNavigationLink.click();
            }
        }
        throw new RuntimeException("Unable to select Navigation Option: " + mainNav);
    }
    
    public List<ExtendedWebElement> getOrderOnline() {
        return orderOnline;
    }
}
