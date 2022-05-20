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

    @FindBy(xpath = "(//table[@class='totals-table ']//tr//td)[5]")
    WebElement tbl_shippingPrice;

    public void selectQuantity() {
        BaseSpec.waitForElement(select_productQuantity);
        BaseSpec.selectValue(select_productQuantity, "visibletext", "2");
    }

    public void removeArticleFromCart() {
        BaseSpec.click(lnk_removeArticle);
    }

    public String totalPrice() {
        String totalPrice = tbl_totalPrice.getText();
        return totalPrice;
    }


}
