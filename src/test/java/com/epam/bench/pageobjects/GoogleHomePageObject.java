package com.epam.bench.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Peter_Olah1 on 7/14/2015.
 * <p/>
 * Contains page elements, getters and setters to make other classes to see them.
 */
public class GoogleHomePageObject {

    /**
     * The search field on the google homepage.
     */
    @FindBy(css = "#gs_htif0")
    private WebElement searchField;

    public WebElement getSearchField() {
        return searchField;
    }


}
