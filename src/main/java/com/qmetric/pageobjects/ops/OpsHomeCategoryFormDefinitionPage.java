package com.qmetric.pageobjects.ops;

import com.qmetric.pageobjects.BasePageObject;
import com.qmetric.utilities.DynamicElementHandler;
import com.qmetric.utilities.TimeHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by pgnanasekaran on 18/06/2015.
 */
public class OpsHomeCategoryFormDefinitionPage extends BasePageObject
{
    @FindBy(css = "p[class=\"questiontext ng-binding\"]") List<WebElement> questionSections;

    @FindBy(id = "restrictpast") WebElement restrictPastCheckBox;

    @FindBy(css = "textarea[class=\"validationRuleIdInput ng-pristine ng-valid\"]") List<WebElement> validationRules;

    @FindBy(css = "input[class=\"validationRuleTextInput ng-pristine ng-valid\"]") List<WebElement> validationRuleText;

    @FindBy(className = "removeValidationRuleBtn") List<WebElement> validationRuleRemoveButtons;

    @FindBy(id = "validationRuleMappingBtn") WebElement validationRuleAddButton;

    @FindBy(id = "saveFormDefinitionBtnHead") WebElement saveFormButton;

    @FindBy(id = "publishBusinessLineBtnHead") WebElement publishFormButton;

    @FindBy(css = "#infoMessage") WebElement infoMessage;

    public OpsHomeCategoryFormDefinitionPage(WebDriver driver)
    {
        super(driver);
    }

    public void selectCoverOptions()
    {
        new DynamicElementHandler<Void>()
        {
            @Override
            public Void handleDynamicElement()
            {
                for (WebElement questionSection : questionSections)
                {
                    if (questionSection.getText().equalsIgnoreCase("Cover Options"))
                    {
                        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(questionSection));
                        jsClick(questionSection);
                        TimeHelper.waitInSeconds(1);
                        break;
                    }
                }
                return null;
            }
        }.execute();
    }

    public boolean hasCorrectInfoMessage(String infoMsg)
    {
        boolean isInfoCorrect = false;
        return new DynamicElementHandler<Boolean>()
        {
            @Override
            public Boolean handleDynamicElement()
            {
                new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(infoMessage));
                int retries = 0;
                do
                {
                    try
                    {
                        if (infoMessage.getText().contains(infoMsg))
                        {
                            return true;
                        }
                    }
                    catch (Exception e)
                    {
                        LOG.warn("Info Message not updated yet. Retrying...");
                        retries++;
                    }
                }
                while (!isInfoCorrect && retries < 10);
                return isInfoCorrect;
            }
        }.execute();
    }

    public void saveChanges()
    {
        saveFormButton.click();
        if (hasCorrectInfoMessage("Form definition loaded"))
        {
            System.out.println("Form Saved");
        }
        else
        {
            System.out.println("Form cannot be saved");
        }
    }

    public void publishChanges()
    {
        publishFormButton.click();
        TimeHelper.waitInSeconds(5);
        if (hasCorrectInfoMessage("Form definition loaded"))
        {
            System.out.println("Form Published and Saved");
        }
        else
        {
            System.out.println("Form cannot be Published");
        }
    }

    public void checkValidation(String option)
    {
        jsClick(restrictPastCheckBox);
        TimeHelper.waitInSeconds(1);
    }

    public void removeValidationRules()
    {
        new DynamicElementHandler<Void>()
        {
            @Override
            public Void handleDynamicElement()
            {
                int index = 0;
                for (WebElement validationRule : validationRules)
                {
                    new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(validationRule));
                    jsClick(validationRuleRemoveButtons.get(index));
                    TimeHelper.waitInSeconds(1);
                    index++;
                }
                return null;
            }
        }.execute();
    }

    public void addValidationRules(String rule, String ruleText)
    {

        new DynamicElementHandler<Void>()
        {
            @Override
            public Void handleDynamicElement()
            {
                int index = validationRules.size() - 1;
                enterTextInput(validationRules.get(index), rule);
                enterTextInput(validationRuleText.get(index), ruleText);
                return null;
            }
        }.execute();
    }

    public void addConsentRulesAlone()
    {
        for (String consentRule : SharedData.consentRule.keySet())
        {
            String ruleText = SharedData.consentRule.get(consentRule);
            jsClick(validationRuleAddButton);
            addValidationRules(consentRule, ruleText);
        }
    }

    public void addStartDateRuleAlone()
    {
        for (String startDateRule : SharedData.startDateRule.keySet())
        {
            String ruleText = SharedData.startDateRule.get(startDateRule);
            jsClick(validationRuleAddButton);
            addValidationRules(startDateRule, ruleText);
            break;
        }
    }
}
