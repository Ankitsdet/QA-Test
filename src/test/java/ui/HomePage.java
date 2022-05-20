package ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Map;

public class HomePage {

    WebDriver driver;

    public HomePage(WebDriver ldriver) {
        this.driver = ldriver;
    }

    @FindBy(xpath = "//a[@class='cmpboxbtn cmpboxbtnyes']")
    WebElement btn_agree;

    @FindBy(id = "search")
    WebElement txt_search;

    @FindBy(xpath = "//ul[@class='products-grid']//li[1]")
    WebElement li_selectProduct;

    @FindBy(id = "btnAddToCart")
    WebElement btn_addProduct;

    @FindBy(xpath = "//span[contains(@class,'js-minicart-count')]")
    WebElement lbl_cartCount;

    @FindBy(xpath = "(//p[@class='special-price'])[1]")
    WebElement lbl_productPrice;

    @FindBy(xpath = "//p[@class='product-name']//ancestor::a")
    List<WebElement> products;

    @FindBy(xpath = "//p[@class='product-name']")
    WebElement lbl_productName;

    public void searchBox() {
        BaseSpec.waitForPageLoad();
        txt_search.submit();
    }

    public void searchByproductName(String productName) throws InterruptedException {
        BaseSpec.waitForPageLoad();
        BaseSpec.type(txt_search, productName);
        txt_search.submit();
    }

    public String getProductName() {
        String productName = lbl_productName.getText();
        return productName;
    }

    public void agreementMsg() {
        BaseSpec.waitForPageLoad();
        driver.navigate().refresh();
        BaseSpec.waitForPageLoad();
        if (btn_agree.isDisplayed()) {
            btn_agree.click();
        } else {
            System.out.println("Popup is not available");
        }
    }

    public void selectProduct() throws InterruptedException {
        BaseSpec.click(li_selectProduct);
    }

    public void addProduct() {
        BaseSpec.click(btn_addProduct);
    }

    public void productCountInCart() {
        BaseSpec.waitForElement(lbl_cartCount);
        String cartCount = lbl_cartCount.getText();
        System.out.println("This is the product count     :" + cartCount);
    }

    public void opencart() {
        BaseSpec.waitForElement(lbl_cartCount);
        BaseSpec.click(lbl_cartCount);
    }

    public Double getProductPrice() throws InterruptedException {
        Thread.sleep(3000);
        String cartProductPrice = lbl_productPrice.getText().replace(",", ".");
        Double price = Double.valueOf(cartProductPrice);
        return price;
    }

    public void getAllProducts() throws InterruptedException {
        for (WebElement product : products) {
            product.click();
            searchBox();
        }

    }

    public Map<String, Double> addToPriceCollection(Map<String, Double> map, String productName, Double productPrice) {
        if (map.get(productName) != null) {
            map.put(productName, Double.sum(map.get(productName), productPrice));
        } else
            map.put(productName, productPrice);
        return map;
    }

    public Map<String, Double> removeFromPriceCollection(Map<String, Double> map, String productName) {
        map.remove(productName);
        return map;
    }

    public Double getTotalValue(Map<String, Double> map) {
        Double sum = 0.0;
        for (Double f : map.values()) {
            sum += f;
        }
        return sum;
    }

}
