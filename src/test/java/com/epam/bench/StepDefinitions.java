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

import java.io.IOException;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Peter_Olah1 on 7/13/2015.
 * <p/>
 * Contains the step definition methods
 */
public class StepDefinitions {
    protected WebDriver driver;


    @Before
    public void setUp() {
        driver = new FirefoxDriver();
    }


    @Given("^I navigate to googlecom \"([^\"]*)\"$")
    public void I_navigate_to_googlecom(String homePage) {
        driver.get(homePage);
    }


    @And("^I search for \"([^\"]*)\"$")
    public void i_search_for(String searchCondition) {
        ResultsPageObject resultsPage = PageFactory.initElements(driver, ResultsPageObject.class);
        resultsPage.getSearchField().sendKeys(searchCondition);
        resultsPage.getSearchButton().click();
    }


    @And("^The fourth results title should be \"([^\"]*)\"$")
    public void the_fourth_result_should_be(String expectedTitle) {
        ResultsPageObject resultsPage = PageFactory.initElements(driver, ResultsPageObject.class);
        WebElement myDynamicElement = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#resultStats")));
        String actualTitle = resultsPage.getItCafeLink().getText();
        assertThat(actualTitle, is(expectedTitle));
    }


    @When("^I click on the title \"([^\"]*)\"$")
    public void I_click_on_the_title(String title) {
        ResultsPageObject resultsPage = PageFactory.initElements(driver, ResultsPageObject.class);
        assertThat((resultsPage.getItCafeLink().getText()), containsString(title));
        resultsPage.getItCafeLink().click();
    }


    @Then("^The header should contain \"([^\"]*)\"$")
    public void the_title_should_contain(String expectedTitle) throws IOException {
        ItCafePageObject itCafePage = PageFactory.initElements(driver, ItCafePageObject.class);
        String actualTitle = itCafePage.getArticleTitle().getText();
        String actualLastSentence = itCafePage.getLastSentence().getText();
        assertThat(actualTitle, containsString(expectedTitle));
        FileWriter fW = new FileWriter("target/saveSentencesFromPage.txt", actualTitle, actualLastSentence);

    }


    @And("^The last sentence should be \"([^\"]*)\"$")
    public void the_last_sentence_should_be(String expectedLastSentence) throws IOException {
        ItCafePageObject itCafePage = PageFactory.initElements(driver, ItCafePageObject.class);
        String actualLastSentence = itCafePage.getLastSentence().getText();
        assertThat(actualLastSentence, is(expectedLastSentence));
    }


    @Given("^I navigate to \"([^\"]*)\"$")
    public void i_navigate_to(String homePage) {
        driver.get(homePage);
    }


    @Then("The first result should (be|not be) equal to \"([^\"]*)\"$")
    public void the_first_result_shouldnt_be_equal_to(String shouldNot, String expectedTitle) {
        ResultsPageObject resultsPage = PageFactory.initElements(driver, ResultsPageObject.class);
        String actualTitle = resultsPage.getItCafeLink().getText();
        if (shouldNot.equals("not be")) {
            assertThat(actualTitle, is(expectedTitle));
        } else {
            assertThat(actualTitle, is(expectedTitle));
        }
    }

    @After
    public void tearDown(Scenario scenario) {
        if (!scenario.isFailed()) {
            final byte[] screenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenShot, "image/png");
        }
        driver.close();
    }

}