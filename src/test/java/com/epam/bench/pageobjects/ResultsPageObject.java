package com.epam.bench.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Peter_Olah1 on 7/13/2015.
 * <p/>
 * Contains page elements, getters and setters to make other classes to see them.
 */
public class ResultsPageObject {

    /**
     * The search field on the results page.
     */
    @FindBy(css = "#lst-ib")
    private WebElement searchField;

    /**
     * The results page's search button.
     */
    @FindBy(css = "#sblsbb")
    private WebElement searchButton;

    /**
     * The direct link to the IT café article.
     */
    @FindBy(css = "#rso>div.srg>li:nth-child(1)>div>h3>a")
    private WebElement itCafeLink;

    /**
     * The epam's homepage among search results.
     */
    @FindBy(css = "#rso>li>div>div>h3>a")
    private WebElement epamComTitle;

    /**
     * A page element what is given to the webdriver to wait for. Helps to make sure that the page is fully loaded.
     */
    @FindBy(css = "#resultStats")
    private WebElement resultStats;

    public WebElement getEpamComTitle() {
        return epamComTitle;
    }

    public WebElement getResultStats() {
        return resultStats;
    }

    public WebElement getSearchField() {
        return searchField;
    }

    public WebElement getItCafeLink() {
        return itCafeLink;
    }

    public WebElement getSearchButton() {
        return searchButton;
    }

}
