package com.qmetric.pageobjects.legacy;

import com.qmetric.pageobjects.BasePageObject;
import com.qmetric.pageobjects.legacy.question_set.about_your_property.AboutYourPropertyPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Created with IntelliJ IDEA. User: jmartins Date: 30/04/2013
 */
public class EnquiryQuestionSetPage extends BasePageObject
{

    public EnquiryQuestionSetPage(WebDriver driver)
    {
        super(driver);
    }

    public AboutYourPropertyPage getAboutYourPropertyPage()
    {
        return PageFactory.initElements(driver, AboutYourPropertyPage.class);
    }
}
