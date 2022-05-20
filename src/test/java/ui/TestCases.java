package ui;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

public class TestCases extends BaseSpec {

    @Test
    public void searchForProducts() throws InterruptedException {

        Map<String, Double> map = new HashMap<>();
        logger = report.createTest("open browser");
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.agreementMsg();
        homePage.searchByproductName(excel.getStringData("Data", 2, 0));
        Assert.assertEquals(homePage.getProductName().trim(), excel.getStringData("Data", 2, 0));
        map = homePage.addToPriceCollection(map, homePage.getProductName().trim(), homePage.getProductPrice());
        homePage.selectProduct();
        homePage.addProduct();
        homePage.searchByproductName(excel.getStringData("Data", 3, 0));
        Assert.assertEquals(homePage.getProductName().trim(), excel.getStringData("Data", 3, 0));
        map = homePage.addToPriceCollection(map, homePage.getProductName().trim(), homePage.getProductPrice());
        homePage.selectProduct();
        homePage.addProduct();
        homePage.searchByproductName(excel.getStringData("Data", 4, 0));
        Assert.assertEquals(homePage.getProductName().trim(), excel.getStringData("Data", 4, 0));
        map = homePage.addToPriceCollection(map, homePage.getProductName().trim(), homePage.getProductPrice());
        homePage.selectProduct();
        homePage.addProduct();
        homePage.productCountInCart();
        homePage.opencart();
        ShoppingCartPage shoppingCartPage = PageFactory.initElements(driver, ShoppingCartPage.class);
        String totalPrice = shoppingCartPage.totalPrice();
        String cartPrice = totalPrice.toString().replace("€", "").replace(",", ".");
        BigDecimal priceTotal = new BigDecimal(homePage.getTotalValue(map));
        BigDecimal priceFromMap = priceTotal.setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals(priceFromMap.toString(), cartPrice.trim());
        shoppingCartPage.selectQuantity();
        shoppingCartPage.removeArticleFromCart();
        map = homePage.removeFromPriceCollection(map, excel.getStringData("Data", 2, 0));
        Assert.assertEquals(priceFromMap.toString(), cartPrice.trim());
        homePage.searchByproductName(excel.getStringData("Data", 2, 0));
        Assert.assertEquals(homePage.getProductName().trim(), excel.getStringData("Data", 2, 0));
        map = homePage.addToPriceCollection(map, homePage.getProductName().trim(), homePage.getProductPrice());
        homePage.selectProduct();
        homePage.addProduct();
        homePage.opencart();
        Assert.assertEquals(priceFromMap.toString(), cartPrice.trim());


    }


}
