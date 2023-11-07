import com.google.gson.JsonObject;
import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.BoundingBox;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(DataProviderRunner.class)
public class SampleTest extends BaseTest {
	// @UseDataProvider(value = "getDefaultTestCapability", location =
	// LTCapability.class)
	public void sampleTest1(JsonObject capability) {
		Driver driver;
		Page page = null;
		try {
			driver = super.createConnection(capability);
			page = driver.getPage();
			// Step 1:- Open LambdaTest’s Selenium Playground from
			page.navigate("https://www.lambdatest.com/selenium-playground");
			// Step 2:- Click “Simple Form Demo” under Input Forms.
			page.locator("a:text('Simple Form Demo')").click();
			// Step 3:- Validate that the URL contains “simple-form-demo”.
			String txtlnk = "Simple Form Demo";
			page.url().contains(txtlnk);
			// Step 4:- Create a variable for a string value E.g: “Welcome to LambdaTest”
			String stringValue = "Welcome to LambdaTest";
			// Step 5:- Use this variable to enter values in the “Enter Message” text box.
			page.locator("//input[@id='user-message']").fill(stringValue);
			// Step 6:- Click “Get Checked Value”.
			page.locator("//button[@id='showInput']").click();
			// Step 7:- Validate whether the same text message is displayed in the
			// right-hand
			// panel under the “Your Message:” section.
			String stringValue1 = "Welcome to LambdaTest";
			String message = page.locator("//*[@id=\"message\"]").textContent();
			assertEquals(stringValue1, message);
			System.out.println("Test Scenario 1 is Passed.");
			super.closeConnection(driver);
		} catch (Exception e) {
			e.printStackTrace();
			super.setTestStatus("failed", e.getMessage(), page);
		}

	}

	// @UseDataProvider(value = "getDefaultTestCapability", location =
	// LTCapability.class)
	public void sampleTest2(JsonObject capability) {
		Driver driver;
		Page page = null;
		try {
			driver = super.createConnection(capability);
			page = driver.getPage();
			page.navigate("https://www.lambdatest.com/selenium-playground/");
			page.locator("//*[@id=\"__next\"]/div/section[2]/div/ul/li[13]/a").click();
			ElementHandle slider = page.querySelector("//input[@class='sp__range'][@value = '15']");
			slider.click();
			Thread.sleep(2000);
			BoundingBox srcBound = slider.boundingBox();
			page.mouse().move(srcBound.x + srcBound.width / 2, srcBound.y + srcBound.height / 2);
			page.mouse().down();
			page.mouse().move(srcBound.x + 462, srcBound.y + srcBound.height / 2);
			page.mouse().up();
			Thread.sleep(2000);
			String rangeValue = "//output[@id='rangeSuccess']";
			String expectedValue = page.locator(rangeValue).textContent();
			// validating whether the range value shows 95.
			Thread.sleep(2000);
			assertEquals("95", expectedValue);
			System.out.println("Test Scenario 2 is Passed.");
			super.closeConnection(driver);
		} catch (Exception e) {
			e.printStackTrace();
			super.setTestStatus("failed", e.getMessage(), page);
		}
		

	}

	// @UseDataProvider(value = "getDefaultTestCapability", location =
	// LTCapability.class)
	public void sampleTest3(JsonObject capability) {
		Driver driver;
		Page page = null;
		try {
			driver = super.createConnection(capability);
			page = driver.getPage();
			// Step 1:- Open the https://www.lambdatest.com/selenium-playground page
			page.navigate("https://www.lambdatest.com/selenium-playground");
			// click “Input Form Submit” under “Input Forms
			page.locator("a:text('Input Form Submit')").click();
			// Step 2:- Click “Submit” without filling in any information in the form
			page.locator("//*[@id=\"seleniumform\"]/div[6]/button").click();
			ElementHandle nameInput = page.querySelector("input[name='name']"); // Replace with the appropriate selector
																				// for
																				// your input element

			// Execute JavaScript to check validity and capture validation message
			boolean isValid = (boolean) page.evaluate("el => el.checkValidity()", nameInput);
			String expectedErrorMsg = (String) page.evaluate("el => el.validationMessage", nameInput);
			String actualErrorMsg = "Please fill in the fields";
			// Step 3:- Assert “Please fill in the fields” error message
			try {
				// Use JUnit assertions to compare the two strings
				assertEquals(expectedErrorMsg, actualErrorMsg, "Strings are not equal");
				System.out.println("Strings are equal.");
			} catch (AssertionError error) {
				System.err.println(error.getMessage());
			}

			// Step 4:- Fill in Name, Email, and other fields.
			page.locator("//input[@id='name']").fill("Test");
			page.locator("//input[@id='inputEmail4']").fill("test@gmail.com");
			page.locator("//input[@id='inputPassword4']").fill("Test@123");
			page.locator("//input[@id='company']").fill("Abc");
			page.locator("//input[@id='websitename']").fill("https://abc.com");
			Thread.sleep(2000);
			page.locator("//select[@name='country']").selectOption("UM");
			// Step 5:- From the Country drop-down, select “United States” using the text
			// property.
			page.locator("//input[@id='inputCity']").fill("xyz");
			page.locator("//input[@id='inputAddress1']").fill("xyz");
			page.locator("//input[@id='inputAddress2']").fill("xyz");
			page.locator("//input[@id='inputState']").fill("pqr");
			page.locator("//input[@id='inputZip']").fill("411004");
			// Step 6:- Click on the submit button
			page.locator("button:text('Submit')").click();

			String msg = page.locator("//p[@class='success-msg hidden']").innerText();
			// Step 7:- validate the success message “Thanks for contacting
			// us, we will get back to you shortly.” on the screen
			assertEquals(msg, "Thanks for contacting us, we will get back to you shortly.");
			System.out.println("Test Scenario 3 is Passed.");

			super.closeConnection(driver);
		} catch (Exception e) {
			e.printStackTrace();
			super.setTestStatus("failed", e.getMessage(), page);
		}

	}

	@Test
	@UseDataProvider(value = "getDefaultTestCapability", location = LTCapability.class)
	public void playwrightTest(JsonObject capability) {
		SampleTest smpleTest = new SampleTest();
		smpleTest.sampleTest1(capability);
		smpleTest.sampleTest2(capability);
		smpleTest.sampleTest3(capability);
	}
}
