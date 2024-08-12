package magentoWebsiteTesting;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import dev.failsafe.internal.util.Assert;

public class MagentoWebsiteTesting {

	WebDriver driver = new ChromeDriver();
	String driverWebSite = "https://magento.softwaretestingboard.com/";
	String singOutPage = "https://magento.softwaretestingboard.com/customer/account/logout/";
	Random rand = new Random();
	String password = "HelloWorld#123";
	String emailAddressToSignInPage = "";
	String nikeNameReview = "";

	@BeforeTest
	public void setup() {

		driver.manage().window().maximize();
		driver.get(driverWebSite);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	}

	@Test(priority = 1, enabled = true)
	public void createAnAccountTest() {

		String[] firs_tNames = { "Alice", "Bob", "Charlie", "David", "Eva", "Frank", "Grace", "Helen", "Ivan", "Judy",
				"Kathy", "Leo", "Mona", "Nina", "Oscar" };

		String[] last_Names = { "Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller", "Davis",
				"Rodriguez", "Martinez", "Hernandez", "Lopez", "Gonzalez", "Wilson", "Anderson" };

		int randomIndexForFirstName = rand.nextInt(firs_tNames.length);
		int randomIndexForLastName = rand.nextInt(last_Names.length);
		String firstName = firs_tNames[randomIndexForFirstName];
		String lastName = last_Names[randomIndexForLastName];
		int randomNumber = rand.nextInt(9841);
		String domaimName = "@Gmail.com";

//		System.out.println(randomIndexForFirstName);
//		System.out.println(randomIndexForLastName);

//		 xpath_selector
//		WebElement createAccountPage = driver.findElement(By.xpath(
//				"//a[@href='https://magento.softwaretestingboard.com/customer/account/create/']"));
//		partialLinkText_selector
//	    WebElement createAccountPage = driver.findElement(By.partialLinkText("Create"));

//		linkText_selector
		WebElement createAccountPage = driver.findElement(By.linkText("Create an Account"));
		createAccountPage.click();
		WebElement firstNameInput = driver.findElement(By.id("firstname"));
		WebElement lastNameInput = driver.findElement(By.id("lastname"));
		WebElement emailInpit = driver.findElement(By.id("email_address"));
		WebElement passwordInput = driver.findElement(By.id("password"));
		WebElement confirmPasswordInput = driver.findElement(By.id("password-confirmation"));
		WebElement createAnAccountButton = driver.findElement(By.cssSelector(".action.submit.primary"));

		firstNameInput.sendKeys(firstName);
		lastNameInput.sendKeys(lastName);
		emailInpit.sendKeys(firstName + lastName + randomNumber + domaimName);
		passwordInput.sendKeys(password);
		confirmPasswordInput.sendKeys(password);
		createAnAccountButton.click();

		emailAddressToSignInPage = firstName + lastName + randomNumber + domaimName;
		nikeNameReview = firstName;

		WebElement messageAsWebElement = driver.findElement(By.className("messages"));
		String theActualMessage = messageAsWebElement.getText();
		String expectedMessage = "Thank you for registering with Main Website Store.";
		org.testng.Assert.assertEquals(theActualMessage, expectedMessage);
	}

	@Test(priority = 2, enabled = true)
	public void singOutTest() throws InterruptedException {
		driver.get(singOutPage);

		WebElement signOutAsElement = driver.findElement(By.xpath("//span[@data-ui-id='page-title-wrapper']"));
		String actualSignOut = signOutAsElement.getText();
		String expectedSignOut = "You are signed out";
		org.testng.Assert.assertEquals(actualSignOut, expectedSignOut);

//		<span class="base" data-ui-id="page-title-wrapper" xpath="1">You are signed out</span>
//	Thread.sleep(10000);

	}

	@Test(priority = 3, enabled = true)
	public void signInTest() {
		WebElement singInPage = driver.findElement(By.linkText("Sign In"));
		singInPage.click();

		WebElement emailInput = driver.findElement(By.id("email"));
		WebElement passwordInput = driver.findElement(By.id("pass"));
		WebElement signInButton = driver.findElement(By.id("send2"));
		emailInput.sendKeys(emailAddressToSignInPage);
		passwordInput.sendKeys(password);
		signInButton.click();
		String signInAsElement = driver.findElement(By.className("logged-in")).getText();
		boolean actualSignIn = signInAsElement.contains("Welcome");
		boolean expectedSignIn = true;
		org.testng.Assert.assertEquals(actualSignIn, expectedSignIn);

	}

	@Test(priority = 4, enabled = true)
	public void addMenItems() {
		int randItems = rand.nextInt();
		WebElement menSection = driver.findElement(By.id("ui-id-5"));
		menSection.click();
		WebElement itemsContainer = driver.findElement(By.cssSelector(".product-items.widget-product-grid"));

		List<WebElement> items = itemsContainer.findElements(By.tagName("li"));
		randItems = rand.nextInt(items.size());
		items.get(randItems).click();
//		System.out.println(items);
		WebElement sizeContainer = driver
				.findElement(By.cssSelector("div[class='swatch-attribute size'] div[role='listbox']"));
		List<WebElement> sizes = sizeContainer.findElements(By.tagName("div"));
		randItems = rand.nextInt(sizes.size());
		sizes.get(randItems).click();

		WebElement colorsContainer = driver
				.findElement(By.cssSelector("div[class='swatch-attribute color'] div[role='listbox']"));
		List<WebElement> colors = colorsContainer.findElements(By.tagName("div"));

		randItems = rand.nextInt(colors.size());
		colors.get(randItems).click();

		WebElement addToCart = driver.findElement(By.id("product-addtocart-button"));
		addToCart.click();

		String addMenAsElement = driver.findElement(By.className("message-success")).getText();
		boolean actualaddMen = addMenAsElement.contains("You added");
		boolean expectedaddMen = true;
		org.testng.Assert.assertEquals(actualaddMen, expectedaddMen);

		driver.navigate().back();

	}

	@Test(priority = 5, enabled = true)
	public void addWomenlItems() {

		int randItems = rand.nextInt();

		WebElement womenSection = driver.findElement(By.id("ui-id-4"));
		womenSection.click();

		WebElement itemsContainer = driver.findElement(By.cssSelector(".product-items.widget-product-grid"));
		List<WebElement> items = itemsContainer.findElements(By.tagName("li"));

		randItems = rand.nextInt(items.size());
		items.get(randItems).click();

		WebElement sizeContaier = driver
				.findElement(By.cssSelector("div[class='swatch-attribute size'] div[role='listbox']"));
		List<WebElement> sizes = sizeContaier.findElements(By.tagName("div"));

		randItems = rand.nextInt(sizes.size());
		sizes.get(randItems).click();

		WebElement colorsContainer = driver
				.findElement(By.cssSelector("div[class='swatch-attribute color'] div[role='listbox']"));
		List<WebElement> colors = colorsContainer.findElements(By.tagName("div"));

		randItems = rand.nextInt(colors.size());
		colors.get(randItems).click();

//      add to cart
		WebElement addToCart = driver.findElement(By.id("product-addtocart-button"));
		addToCart.click();

		driver.navigate().refresh();

//		 review section 
		WebElement reviewContainer = driver.findElement(By.cssSelector("#tab-label-reviews-title"));
		reviewContainer.click();

		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("window.scrollTo(0,1200)");

		WebElement RatingStars = driver.findElement(By.cssSelector(".control.review-control-vote"));
		System.out.println(RatingStars.findElements(By.tagName("label")).size() + "*****************");

		String[] ids = { "Rating_1", "Rating_2", "Rating_3", "Rating_4", "Rating_5" };
		int randomIndex = rand.nextInt(ids.length);
		WebElement element = driver.findElement(By.id(ids[randomIndex]));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);

//		nickname_field
		WebElement nicknameField = driver.findElement(By.id("nickname_field"));
		nicknameField.sendKeys(nikeNameReview);

//		summary_field
		WebElement summaryField = driver.findElement(By.id("summary_field"));
		summaryField.sendKeys("good");

//		review_field
		WebElement reviewField = driver.findElement(By.id("review_field"));
		reviewField.sendKeys("These sports clothes are comfortable and fit well!");
//		submit_review 
		WebElement submitReview = driver.findElement(By.cssSelector("button[class='action submit primary']"));
		submitReview.click();

//		message-success 
		WebElement messageReviewAsWebElement = driver.findElement(By.className("message-success"));
		String theActualMessage = messageReviewAsWebElement.getText();
		String expectedMessage = "You submitted your review for moderation.";
		org.testng.Assert.assertEquals(theActualMessage, expectedMessage);

	}

}
