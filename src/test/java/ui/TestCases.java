package ui;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

public class TestCases extends BaseSpec {

    @Test(description = "Search, Add and delete products and Verify different costs and total price",priority = 1)
    public void searchProductsAndPerformAddRemoveAndVerifyAmounts() throws InterruptedException {

        Map<String, Double> map = new HashMap<>();
        logger = report.createTest("open browser");
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.agreementMsg();
        logger = report.createTest("Search for the product and add to cart");
        homePage.searchByproductName(excel.getStringData("Data", 2, 0));
        Assert.assertEquals(homePage.getProductName().trim(), excel.getStringData("Data", 2, 0));
        map = homePage.addToPriceCollection(map, homePage.getProductName().trim(), homePage.getProductPrice());
        homePage.selectProduct();
        homePage.addProduct();
        logger = report.createTest("first product added successfully");
        homePage.searchByproductName(excel.getStringData("Data", 3, 0));
        Assert.assertEquals(homePage.getProductName().trim(), excel.getStringData("Data", 3, 0));
        map = homePage.addToPriceCollection(map, homePage.getProductName().trim(), homePage.getProductPrice());
        homePage.selectProduct();
        homePage.addProduct();
        logger = report.createTest("Second product added successfully");
        homePage.searchByproductName(excel.getStringData("Data", 4, 0));
        Assert.assertEquals(homePage.getProductName().trim(), excel.getStringData("Data", 4, 0));
        map = homePage.addToPriceCollection(map, homePage.getProductName().trim(), homePage.getProductPrice());
        homePage.selectProduct();
        homePage.addProduct();
        logger = report.createTest("Third product added successfully");
        homePage.productCountInCart();
        homePage.opencart();
        logger = report.createTest("Verify specific products prices and other shopping cart values");
        ShoppingCartPage shoppingCartPage = PageFactory.initElements(driver, ShoppingCartPage.class);
        String totalPrice = shoppingCartPage.totalPrice();
        String cartPrice = totalPrice.replace("€", "").replace(",", ".");
        BigDecimal priceTotal = new BigDecimal(homePage.getTotalValue(map));
        BigDecimal priceFromMap = priceTotal.setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals(totalPrice,shoppingCartPage.totalShoppingCartAmount());
        Assert.assertEquals(priceFromMap.toString(), cartPrice.trim());
        logger = report.createTest("Verify change quantity on Shopping cart");
        shoppingCartPage.selectQuantity();
        logger = report.createTest("Remove product from Shopping cart and verify prices");
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
        Assert.assertEquals(totalPrice,shoppingCartPage.totalShoppingCartAmount());
        logger = report.createTest("Shopping cart different cost or values are verified successfully");

    }

    @Test(description = "Search a random product and add to cart",priority = 2)
    public void searchRandomProductAndAddToCart() throws InterruptedException {
        logger = report.createTest("open browser");
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.agreementMsg();
        logger = report.createTest("Search for the product and add to cart");
        homePage.searchByproductName(excel.getStringData("Data", 1, 0));
        Assert.assertEquals(homePage.getProductName().trim(), excel.getStringData("Data", 2, 0));
        homePage.selectProduct();
        homePage.addProduct();
        logger = report.createTest("product added to the cart successfully");
    }


}
