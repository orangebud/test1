package com.epam.bench.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Peter_Olah1 on 7/13/2015.
 * <p/>
 * Contains page elements, getters and setters to make other classes to see them.
 */
public class ResultsPageObject {


    @FindBy(css = "#lst-ib")
    private WebElement searchField;

    @FindBy(css = "#sblsbb")
    private WebElement searchButton;

    @FindBy(css = "#rso>div.srg>li:nth-child(1)>div>h3>a")
    private WebElement itCafeLink;

    @FindBy(css = "#rso>li>div>div>h3>a")
    private WebElement epamComTitle;

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

    public void getResultStats(WebElement resultStats) {
        this.resultStats = resultStats;
    }

    public WebElement getItCafeLink() {
        return itCafeLink;
    }

    public WebElement getSearchButton() {
        return searchButton;
    }

}
