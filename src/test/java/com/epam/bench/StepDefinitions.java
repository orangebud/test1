package com.epam.bench;

import com.epam.bench.pageobjects.ItCafePageObject;
import com.epam.bench.pageobjects.ResultsPageObject;
import com.epam.bench.util.FileWriter;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

/**
 * Created by Peter_Olah1 on 7/13/2015.
 * <p/>
 * Contains the step definition methods
 */
public class StepDefinitions {
    private WebDriver driver;
    private static final Logger logger = LoggerFactory.getLogger(StepDefinitions.class);


    @Before
    public void setUp() {
        logger.info("Provides a FirefoxDriver");
        driver = new FirefoxDriver();
    }


    @Given("^I navigate to googlecom \"([^\"]*)\"$")
    public void I_navigate_to_googlecom(String homePage) {
        logger.info("Opens the home page.");
        driver.get(homePage);
    }


    @And("^I search for \"([^\"]*)\"$")
    public void i_search_for(String searchCondition) {
        logger.info("Creates a Results page instance");
        ResultsPageObject resultsPage = PageFactory.initElements(driver, ResultsPageObject.class);

        logger.info("Finds the search field and search for a condition.");
        resultsPage.getSearchField().sendKeys(searchCondition);
        resultsPage.getSearchButton().click();
    }


    @And("^The fourth results title should be \"([^\"]*)\"$")
    public void the_fourth_result_should_be(String expectedTitle) {
        logger.info("Creates a Results page instance");
        ResultsPageObject resultsPage = PageFactory.initElements(driver, ResultsPageObject.class);
        WebElement myDynamicElement = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#resultStats")));

        logger.info("Makes assertion among expected and actual values.");
        String actualTitle = resultsPage.getItCafeLink().getText();
        assertThat(actualTitle, is(expectedTitle));
    }


    @When("^I click on the title \"([^\"]*)\"$")
    public void I_click_on_the_title(String title) {
        logger.info("Creates a Results page instance");
        ResultsPageObject resultsPage = PageFactory.initElements(driver, ResultsPageObject.class);

        logger.info("Verifies that the visible link contains the required string.");
        assertThat((resultsPage.getItCafeLink().getText()), containsString(title));
        resultsPage.getItCafeLink().click();
    }


    @Then("^The header should contain \"([^\"]*)\"$")
    public void the_title_should_contain(String expectedTitle) throws IOException {
        logger.info("Creates a IT café page instance");
        ItCafePageObject itCafePage = PageFactory.initElements(driver, ItCafePageObject.class);
        String actualTitle = itCafePage.getArticleTitle().getText();
        String actualLastSentence = itCafePage.getLastSentence().getText();

        logger.info("Verifies that the visible title contains the required string.");
        assertThat(actualTitle, containsString(expectedTitle));

        logger.info("Writes required stings to a text file");
        FileWriter fW = new FileWriter("target/saveSentencesFromPage.txt", actualTitle, actualLastSentence);
    }


    @And("^The last sentence should be \"([^\"]*)\"$")
    public void the_last_sentence_should_be(String expectedLastSentence) throws IOException {
        logger.info("Creates a IT café page instance");
        ItCafePageObject itCafePage = PageFactory.initElements(driver, ItCafePageObject.class);

        logger.info("Verifies that the visible last sentence is the expected last sentence.");
        String actualLastSentence = itCafePage.getLastSentence().getText();
        assertThat(actualLastSentence, is(expectedLastSentence));
    }


    @Given("^I navigate to \"([^\"]*)\"$")
    public void i_navigate_to(String homePage) {
        logger.info("Opens the home page.");
        driver.get(homePage);
    }

    @When("^I search for the \"([^\"]*)\"$")
    public void i_search_for_the(String condition) {
        logger.info("Creates a Results page instance");
        ResultsPageObject resultsPage = PageFactory.initElements(driver, ResultsPageObject.class);

        logger.info("Finds the search field and search for a condition.");
        resultsPage.getSearchField().sendKeys(condition);
        resultsPage.getSearchButton().click();
    }


    @Then("The first result should (be|not be) equal to \"([^\"]*)\"$")
    public void the_first_result_shouldnt_be_equal_to(String shouldNot, String expectedTitle) {
        logger.info("Creates a result page instance.");
        ResultsPageObject resultsPage = PageFactory.initElements(driver, ResultsPageObject.class);
        WebElement myDynamicElement = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("resultStats")));
        logger.info("Investigates behaviour with right or wrong conditions.");
        String actualTitle = resultsPage.getEpamComTitle().getText();
        if (shouldNot.equals("not be")) {
            assertThat(actualTitle, is(expectedTitle));
        } else {
            logger.info("Expected title: {}.", expectedTitle);
            assertFalse(!actualTitle.equals(expectedTitle));
        }
    }

    @After
    public void tearDown(Scenario scenario) {
        logger.info("Takes a screenshot if a scenario have failed.");
        if (scenario.isFailed()) {
            final byte[] screenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenShot, "image/png");
        }
        try {
            if (driver != null)
                logger.info("Closes the Firefox driver");
            driver.close();
        } catch (NullPointerException e) {
            logger.info("Caught NullPointerException:" + e.getMessage());
        }

    }
}