package e2e.stepDefs;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import e2e.common.webdriver;
import e2e.pages.car_page;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.ArrayList;

public class car_sd extends webdriver {

    public car_page carPage = PageFactory.initElements(driver, car_page.class);
    ArrayList<String> reg_numbers = new ArrayList<String>();


    @Given("^read text file \"([^\"]*)\"$")
    public void readTextFile(String input_textfile) throws IOException {
        reg_numbers = carPage.readtextfile(input_textfile);
    }

    @Then("^I entered registration numbers which are extracted from the text file and compare with output file \"([^\"]*)\"$")
    public void iEnteredRegistrationNumbersWhichAreExtractedFromTheTextFileAndCompareWithOutputFile(String output_file) throws IOException, InterruptedException {
        carPage.enterregnumbersandcompare(reg_numbers,output_file);
    }
}
