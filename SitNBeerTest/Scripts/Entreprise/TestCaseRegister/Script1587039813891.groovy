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

WebUI.openBrowser('')

WebUI.navigateToUrl('http://127.0.0.1:8181/registration')

WebUI.setText(findTestObject('Object Repository/Page_SitNBeer - Registration/input_Email _email'), 'katalon@entreprise.com')

WebUI.setText(findTestObject('Object Repository/Page_SitNBeer - Registration/input_Username _username'), 'KatalonEntreprise')

WebUI.setEncryptedText(findTestObject('Object Repository/Page_SitNBeer - Registration/input_Password _password'), '8SQVv/p9jVQbixvz/0SAXQ==')

WebUI.setEncryptedText(findTestObject('Object Repository/Page_SitNBeer - Registration/input_Confirm password _passwordConfirm'), 
    '8SQVv/p9jVQbixvz/0SAXQ==')

WebUI.click(findTestObject('Object Repository/Page_SitNBeer - Registration/input_Confirm password _btn'))

