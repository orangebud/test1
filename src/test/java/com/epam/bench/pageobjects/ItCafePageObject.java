package com.epam.bench.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Peter_Olah1 on 7/13/2015.
 * <p/>
 * Contains page elements, getters and setters to make other classes to see them.
 */
public class ItCafePageObject {

    /**
     * The IT café article's title.
     */
    @FindBy(css = ".anyag.hir>h1")
    private WebElement articleTitle;

    /**
     * The last sentence of the IT café article.
     */
    @FindBy(css = "p:nth-child(4)")
    private WebElement lastSentence;

    public WebElement getLastSentence() {
        return lastSentence;
    }

    public WebElement getArticleTitle() {
        return articleTitle;
    }
}
