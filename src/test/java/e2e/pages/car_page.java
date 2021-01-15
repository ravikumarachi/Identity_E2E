package e2e.pages;

import e2e.common.webdriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;

public class car_page extends webdriver {

    @FindBy(id = "vrm-input")
    private WebElement enter_registration_box;

    @FindBy(xpath = "//button[contains(.,'Free Car Check')]")
    private WebElement FreeCareChkButton;

    @FindBy(xpath = "//div[2]/dl/dd")
    private WebElement Registration_ele;

    @FindBy(xpath = "//div[2]/dl[2]/dd")
    private WebElement Make_ele;


    @FindBy(xpath = "//div[2]/dl[3]/dd")
    private WebElement Model_ele;


    @FindBy(xpath = "//div[2]/dl[4]/dd")
    private WebElement Colour_ele;

    @FindBy(xpath = "//div[2]/dl[5]/dd")
    private WebElement Year_ele;

    @FindBy(xpath = "/html/body/div/div/div[2]/div[10]/div/div/dl/div/dd[2]/a")
    private WebElement TryAgain;


    String filePath = System.getProperty("user.dir") + "/src/test/java/e2e/";
    ArrayList<List<String>> lines = new ArrayList<List<String>>();
    ArrayList<String> reg_numbers = new ArrayList<String>();

    WebDriverWait wait = new WebDriverWait(driver, 30);

    public ArrayList<String> readtextfile(String file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filePath + "inputData/" + file));

        String line = br.readLine();
        while (line != null) {
            Pattern r = Pattern.compile("[A-Z]{1,2}[0-9]{1,2}\\s[A-Z]{1,3}" + "|[A-Z]{1,2}[0-9]{1,2}[A-Z]{1,3}");
            Matcher m = r.matcher(line);
            while (m.find()) {
                reg_numbers.add(m.group());

            }
            line = br.readLine();

        }

        br.close();

        return reg_numbers;


    }


    public void enterregnumbersandcompare(ArrayList<String> reg_numbers, String output_file) throws IOException, InterruptedException {
        ArrayList<String> ilines = new ArrayList<String>();

        BufferedReader br = new BufferedReader(new FileReader(filePath + "outputData/" + output_file));
//        br.readLine();

        String line = br.readLine();
        while (line != null) {
            line = br.readLine();
            ilines.add(line);
        }

        String fileName = "failedRegistrationNos.txt";

        File file = new File(filePath + "failed/" + fileName);

        FileWriter fw = null;
        if (!file.exists()) {
            file.createNewFile();
        }

        FileWriter fileWriter = new FileWriter(filePath + "/failed/" + fileName);
        for (int i = 0; i < reg_numbers.size(); i++) {
            enter_registration_box.sendKeys(reg_numbers.get(i));
            FreeCareChkButton.click();

            wait.until(ExpectedConditions.visibilityOf(Registration_ele));
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

            String Actual_Reg_Make_Model_Colour_Year = Registration_ele.getText() + "," + Make_ele.getText() + "," + Model_ele.getText() + "," + Colour_ele.getText() + "," + Year_ele.getText();

            assertEquals(ilines.get(i), Actual_Reg_Make_Model_Colour_Year);

            driver.navigate().back();


        }
        br.close();

    }

}
