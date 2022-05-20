package ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ShoppingCartPage {

    WebDriver driver;

    public ShoppingCartPage(WebDriver ldriver) {
        this.driver = ldriver;
    }

    @FindBy(xpath = "(//select[@class='qty js-qty-select '])[1]")
    WebElement select_productQuantity;

    @FindBy(xpath = "(//a[contains(text(),'Artikel entfernen')])[1]")
    WebElement lnk_removeArticle;

    @FindBy(xpath = "(//p//span[@class='price'])[1]")
    WebElement lbl_productPriceInCart;

    @FindBy(xpath = "(//table[@class='totals-table ']//tr//td)[2]")
    WebElement tbl_totalPrice;

    @FindBy(xpath = "(//table[@class='totals-table ']//tr//td//span)[5]")
    WebElement tbl_shippingPrice;

    @FindBy(xpath = "(//table[@class='totals-table ']//tr//td//span)[4]")
    WebElement tbl_subTotal;

    @FindBy(xpath = "(//table[@class='totals-table ']//tr//td//span)[6]")
    WebElement tbl_bulkyGoodsSurcharge;

    public void selectQuantity() {
        BaseSpec.waitForElement(select_productQuantity);
        BaseSpec.selectValue(select_productQuantity, "visibletext", "2");
    }

    public void removeArticleFromCart() {
        BaseSpec.click(lnk_removeArticle);
    }

    public String totalPrice() {
        return tbl_totalPrice.getText();
    }

    public String shippingPrice() {
        return tbl_shippingPrice.getText();
    }

    public String subTotal() {
        return tbl_subTotal.getText();
    }

    public String bulkyGoodsSurcharge() {
        return tbl_bulkyGoodsSurcharge.getText();
    }

    public String totalShoppingCartAmount() {
        String totalAmount = subTotal() + shippingPrice() + bulkyGoodsSurcharge();
        return totalAmount.replace("0,00 €", "");
    }


}
