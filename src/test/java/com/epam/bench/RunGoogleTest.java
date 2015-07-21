package com.epam.bench;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Created by Peter_Olah1 on 7/13/2015.
 * <p/>
 * Runner class to make scenarios run with the specified options.
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        format = {"pretty",
                "html:target/testReport",
                "json:target/report.json"
        },
        features = "src/test/java/resources/"
)

public class RunGoogleTest {

}