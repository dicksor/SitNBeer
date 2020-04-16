import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

WebUI.navigateToUrl('http://127.0.0.1:8181/beer/add/')

WebUI.setText(findTestObject('Object Repository/Page_SitNBeer - Beers/input_Beer name _name'), 'KatalonBeer')

WebUI.setText(findTestObject('Object Repository/Page_SitNBeer - Beers/input_Beer manufacturer _manufacturer'), 'KatalonManufacturer')

WebUI.setText(findTestObject('Object Repository/Page_SitNBeer - Beers/input_Beer price _price'), '7')

WebUI.setText(findTestObject('Object Repository/Page_SitNBeer - Beers/input_Beer volume _volume'), '8')

WebUI.setText(findTestObject('Object Repository/Page_SitNBeer - Beers/input_Stock quantity _stockQuantity'), '20')

WebUI.click(findTestObject('Object Repository/Page_SitNBeer - Beers/input_Stock quantity _btn'))

