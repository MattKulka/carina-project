package com.qaprosoft.carina.demo;
import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.qaprosoft.carina.core.foundation.utils.ownership.MethodOwner;
import com.qaprosoft.carina.demo.gui.pages.CompareModelsPage;
import com.qaprosoft.carina.demo.gui.pages.HomePage;
import com.qaprosoft.carina.demo.gui.pages.FoodMenuPage;
import com.zebrunner.agent.core.annotation.TestLabel;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class WebTesting implements IAbstractTest {


    @Test
    @MethodOwner(owner = "MattKulka")
    public void testOpenHomePage() {
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home page is not opened");
    }

    @Test
    @MethodOwner(owner = "MattKulka")
    public void testCloseBannerAd() {
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        homePage.getBannerAd().closeAdIfPresent();
        Assert.assertFalse(homePage.getBannerAd().isUIObjectPresent(2), "Banner advert should be closed.");
    }

    @Test
    @MethodOwner(owner = "MattKulka")
    public void testOpenFoodMenuPage() {
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        FoodMenuPage FoodMenuPage = homePage.getOrderOnline().get(0).clickLink();
        Assert.assertTrue(FoodMenuPage.isPageOpened(), "Home page is not opened");
    }
}