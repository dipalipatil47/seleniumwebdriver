package com.seleniumWebdriver.datadriven;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FDCalculator {

    private static final String SHEET = "FD Test Data";

    public static void main(String[] args) throws IOException, InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        String filePath = System.getProperty("user.dir")
                + File.separator + "testdata"
                + File.separator + "fd_calculator_test_data_5_records.xlsx";

        driver.get(
                "https://www.moneycontrol.com/fixed-income/calculator/state-bank-of-india-sbi/"
                + "fixed-deposit-calculator-SBI-BSB001.html?classic=true"
        );

        int rows = ExcelUtils.getRowCount(filePath, SHEET);

        for (int i = 1; i <= rows; i++) {

            System.out.println();
            System.out.println("========================================");
            System.out.println("Executing Test Case : " + i);
            System.out.println("========================================");

            String result = "Fail";

            try {
                closeAllPopups(driver);

                String investmentAmount =
                        ExcelUtils.getCellData(filePath, SHEET, i, 0);

                String investmentPeriod =
                        ExcelUtils.getCellData(filePath, SHEET, i, 1);

                String rateOfReturn =
                        ExcelUtils.getCellData(filePath, SHEET, i, 2);

                String interestFrequency =
                        ExcelUtils.getCellData(filePath, SHEET, i, 3);

                String taxRate =
                        ExcelUtils.getCellData(filePath, SHEET, i, 4);

                // Column F = numeric expected maturity
                String expectedMaturity =
                        ExcelUtils.getCellData(filePath, SHEET, i, 5);

                // Column G = expected status
                String expectedStatus =
                        ExcelUtils.getCellData(filePath, SHEET, i, 6);

                System.out.println("Investment Amount : " + investmentAmount);
                System.out.println("Investment Period : " + investmentPeriod);
                System.out.println("Rate of Return    : " + rateOfReturn);
                System.out.println("Interest Frequency: " + interestFrequency);
                System.out.println("Tax Rate          : " + taxRate);
                System.out.println("Expected Maturity : " + expectedMaturity);
                System.out.println("Expected Result   : " + expectedStatus);

                // =====================================================
                // ENTER INVESTMENT AMOUNT
                // =====================================================

                enterValue(
                        driver,
                        wait,
                        "//input[@id='edulonvalue_1']",
                        investmentAmount
                );

                // =====================================================
                // ENTER INVESTMENT PERIOD
                // =====================================================

                enterValue(
                        driver,
                        wait,
                        "//input[@id='edulonvalue_2']",
                        investmentPeriod
                );

                // =====================================================
                // ENTER RATE OF RETURN
                // =====================================================

                enterValue(
                        driver,
                        wait,
                        "//input[@id='edulonvalue_3']",
                        rateOfReturn
                );

                // =====================================================
                // INTEREST FREQUENCY
                // =====================================================

                closeAllPopups(driver);

                String websiteFrequency = interestFrequency;

                // Moneycontrol currently spells this option "Quaterly"
                // in the HTML, while our Excel uses "Quarterly".
                if (interestFrequency.equalsIgnoreCase("Quarterly")) {
                    websiteFrequency = "Quaterly";
                }

                String frequencyXpath =
                        "//span[normalize-space()='Interest Frequency']"
                        + "/ancestor::div[contains(@class,'range_block')]"
                        + "//span[normalize-space()='"
                        + websiteFrequency
                        + "']";

                WebElement frequency =
                        wait.until(
                                ExpectedConditions.presenceOfElementLocated(
                                        By.xpath(frequencyXpath)
                                )
                        );

                ((JavascriptExecutor) driver).executeScript(
                        "arguments[0].scrollIntoView({block:'center'});"
                        + "arguments[0].click();",
                        frequency
                );

                System.out.println(
                        "Frequency selected : " + interestFrequency
                );

                // =====================================================
                // TAX RATE
                // =====================================================

                enterTaxRate(
                        driver,
                        wait,
                        taxRate
                );

                // =====================================================
                // SUBMIT
                // =====================================================

                closeAllPopups(driver);

                WebElement submit =
                        wait.until(
                                ExpectedConditions.presenceOfElementLocated(
                                        By.xpath("//a[normalize-space()='Submit']")
                                )
                        );

                ((JavascriptExecutor) driver).executeScript(
                        "arguments[0].scrollIntoView({block:'center'});",
                        submit
                );

                ((JavascriptExecutor) driver).executeScript(
                        "arguments[0].click();",
                        submit
                );

                System.out.println("Submit clicked.");

                // Give the calculator time to update the result.
                Thread.sleep(1500);

                // =====================================================
                // GET ACTUAL RESULT
                // =====================================================

                String actualMaturity =
                        getResultAmount(driver, wait);

                System.out.println(
                        "Expected Maturity : " + expectedMaturity
                );

                System.out.println(
                        "Actual Maturity   : " + actualMaturity
                );

                double expectedValue =
                        parseAmount(expectedMaturity);

                double actualValue =
                        parseAmount(actualMaturity);

                double difference =
                        Math.abs(expectedValue - actualValue);

                System.out.println(
                        "Expected Value : " + expectedValue
                );

                System.out.println(
                        "Actual Value   : " + actualValue
                );

                System.out.println(
                        "Difference     : " + difference
                );

                // =====================================================
                // PASS / FAIL
                // =====================================================

                if (difference <= 1.0) {
                    result = "Pass";
                } else {
                    result = "Fail";
                }

                System.out.println(
                        "Test Case " + i + " : " + result.toUpperCase()
                );

            } catch (Exception e) {

                result = "Fail";

                System.out.println(
                        "Test Case " + i + " : FAIL"
                );

                System.out.println(
                        "Reason : " + e.getMessage()
                );
            }

            // =========================================================
            // WRITE RESULT
            //
            // G = Expected
            // H = Result
            // =========================================================

            ExcelUtils.setCellData(
                    filePath,
                    SHEET,
                    i,
                    6,
                    "Pass"
            );

            ExcelUtils.setCellData(
                    filePath,
                    SHEET,
                    i,
                    7,
                    result
            );

            if (result.equalsIgnoreCase("Pass")) {

                ExcelUtils.fillGreenColor(
                        filePath,
                        SHEET,
                        i,
                        7
                );

            } else {

                ExcelUtils.fillRedColor(
                        filePath,
                        SHEET,
                        i,
                        7
                );
            }

            // =========================================================
            // RESET
            // =========================================================

            try {

                closeAllPopups(driver);

                WebElement reset =
                        wait.until(
                                ExpectedConditions.presenceOfElementLocated(
                                        By.xpath("//a[@id='reset_btn']")
                                )
                        );

                ((JavascriptExecutor) driver).executeScript(
                        "arguments[0].click();",
                        reset
                );

                Thread.sleep(700);

            } catch (Exception e) {

                System.out.println(
                        "Reset button not found."
                );
            }
        }

        driver.quit();

        System.out.println();
        System.out.println("========================================");
        System.out.println("All Test Cases Completed");
        System.out.println("========================================");
    }


    // =============================================================
    // ENTER NORMAL INPUT
    // =============================================================

    private static void enterValue(
            WebDriver driver,
            WebDriverWait wait,
            String xpath,
            String value) {

        closeAllPopups(driver);

        WebElement input =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                By.xpath(xpath)
                        )
                );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                input
        );

        input.click();
        input.clear();

        // Remove commas before entering numeric values.
        String cleanValue =
                value.replace(",", "").trim();

        input.sendKeys(cleanValue);

        System.out.println(
                "Value entered : " + cleanValue
        );
    }


    // =============================================================
    // ENTER TAX RATE
    // =============================================================

    private static void enterTaxRate(
            WebDriver driver,
            WebDriverWait wait,
            String taxRate) {

        closeAllPopups(driver);

        WebElement taxInput =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                By.xpath(
                                        "//span[normalize-space()='Tax Rate']"
                                        + "/ancestor::div[contains(@class,'range_block')]"
                                        + "//input"
                                )
                        )
                );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                taxInput
        );

        taxInput.click();
        taxInput.clear();
        taxInput.sendKeys(taxRate.replace(",", "").trim());

        System.out.println(
                "Tax Rate set to : " + taxRate
        );
    }


    // =============================================================
    // CLOSE POPUPS / OVERLAYS
    // =============================================================

    public static void closeAllPopups(WebDriver driver) {

        try {

            JavascriptExecutor js =
                    (JavascriptExecutor) driver;

            // Click "No thanks", if present.
            try {

                WebElement noThanks =
                        driver.findElement(
                                By.xpath(
                                        "//*[normalize-space()='No thanks']"
                                )
                        );

                if (noThanks.isDisplayed()) {

                    js.executeScript(
                            "arguments[0].click();",
                            noThanks
                    );

                    Thread.sleep(300);
                }

            } catch (Exception ignored) {
            }

            // Hide known blocking overlays.
            js.executeScript(
                    "var selectors = ["
                    + "'#main_popup_modal_div',"
                    + "'.the_main_popup',"
                    + "'.modal-backdrop',"
                    + "'.wzrk-overlay'"
                    + "];"

                    + "for (var i=0; i<selectors.length; i++) {"
                    + "  try {"
                    + "    document.querySelectorAll(selectors[i])"
                    + "      .forEach(function(el) {"
                    + "        el.style.display='none';"
                    + "        el.style.visibility='hidden';"
                    + "        el.style.opacity='0';"
                    + "        el.style.pointerEvents='none';"
                    + "      });"
                    + "  } catch(e) {}"
                    + "}"

                    + "document.body.style.overflow='auto';"
            );

        } catch (Exception ignored) {
            // Popup is optional; do not fail the test because of it.
        }
    }


    // =============================================================
    // GET RESULT AMOUNT
    // =============================================================

    private static String getResultAmount(
            WebDriver driver,
            WebDriverWait wait) {

        try {

            return wait.until(
                    d -> {

                        JavascriptExecutor js =
                                (JavascriptExecutor) d;

                        Object value =
                                js.executeScript(

                                        "var resultLabels = [];"
                                        + "var all = document.querySelectorAll('*');"

                                        + "for (var i=0; i<all.length; i++) {"
                                        + "  var el = all[i];"
                                        + "  if (el.textContent && "
                                        + "      el.textContent.trim() === 'Result') {"
                                        + "    resultLabels.push(el);"
                                        + "  }"
                                        + "}"

                                        + "for (var r=0; r<resultLabels.length; r++) {"

                                        + "  var node = resultLabels[r];"

                                        + "  for (var level=0; level<6 && node; level++) {"

                                        + "    node = node.parentElement;"

                                        + "    if (!node) break;"

                                        + "    var children = node.querySelectorAll('*');"

                                        + "    for (var j=0; j<children.length; j++) {"

                                        + "      var c = children[j];"

                                        + "      var txt = (c.textContent || '').trim();"

                                        + "      var style = window.getComputedStyle(c);"

                                        + "      var visible = "
                                        + "        style.display !== 'none' && "
                                        + "        style.visibility !== 'hidden' && "
                                        + "        c.offsetParent !== null;"

                                        + "      if (visible && "
                                        + "          /^₹\\s*[\\d,]+(?:\\.\\d+)?$/.test(txt)) {"
                                        + "        return txt;"
                                        + "      }"
                                        + "    }"
                                        + "  }"
                                        + "}"

                                        + "return null;"
                                );

                        if (value != null) {
                            return value.toString();
                        }

                        return null;
                    }
            );

        } catch (Exception e) {

            throw new RuntimeException(
                    "Could not find the Moneycontrol Result amount.",
                    e
            );
        }
    }


    // =============================================================
    // PARSE MONEY AMOUNT
    // =============================================================

    public static double parseAmount(String value) {

        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Amount is empty."
            );
        }

        String cleaned =
                value.replace("₹", "")
                     .replace("Rs.", "")
                     .replace("Rs", "")
                     .replace(",", "")
                     .trim();

        return Double.parseDouble(cleaned);
    }
}
