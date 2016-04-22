package com.qmetric.pageobjects.legacy.question_set.aboutyou;

import com.qmetric.pageobjects.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created with IntelliJ IDEA. User: jmartins Date: 14/03/2013
 */
public class JointPolicyHolderSection extends BasePageObject
{

    @FindBy(id = "joint_policy_holders_1") WebElement jointHolderYesRadioButton;

    @FindBy(id = "joint_policy_holders_2") WebElement jointHolderNoRadioButton;

    @FindBy(id = "policy_joint_holder_1") WebElement firstJointPolicyHolderContainer;

    @FindBy(id = "policy_joint_holder_2") WebElement secondJointPolicyHolderContainer;

    @FindBy(id = "policy_joint_holder_3") WebElement thirdJointPolicyHolderContainer;

    @FindBy(className = "arrowUp") WebElement addAnotherPolicyHolderButton;

    @FindBy(id = "joinPolicyHolderTable") WebElement policyHolderTable;

    @FindBy(css = "p.max-items") WebElement maxNumPolicyHoldersMessage;

    public NewJointPolicyHolder firstPolicyHolder = null;

    public NewJointPolicyHolder secondPolicyHolder = null;

    public NewJointPolicyHolder thirdPolicyHolder = null;

    public JointPolicyHolderSection(WebDriver driver)
    {
        super(driver);
    }

    public boolean isJointHolderRadioButtonDisplayed()
    {
        try
        {
            return jointHolderYesRadioButton.isDisplayed() && jointHolderNoRadioButton.isDisplayed();
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
    }

    public boolean isMaxNumPolicyHoldersMessageDisplayed()
    {
        try
        {
            return maxNumPolicyHoldersMessage.isDisplayed();
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public int getNumberOfPolicyHoldersAlreadySaved()
    {
        return policyHolderTable.findElements(By.cssSelector("tbody > tr")).size();
    }

    public void selectJointHolderRadioButton(boolean yes)
    {
        if (yes)
        {
            jsClick(jointHolderYesRadioButton);
            firstPolicyHolder = new NewJointPolicyHolder(driver, firstJointPolicyHolderContainer, 1);
        }
        else
        {
            jsClick(jointHolderNoRadioButton);
            cleanPolicyHolders();
        }
    }

    private void cleanPolicyHolders()
    {
        firstPolicyHolder = null;
        secondPolicyHolder = null;
        thirdPolicyHolder = null;
    }

    void clickAddNewPolicyHolder()
    {
        jsClick(addAnotherPolicyHolderButton);
    }

    public void addNewJointPolicyHolder()
    {
        int numberOfPolicyHolders = getNumberOfPolicyHoldersAlreadySaved();
        if (numberOfPolicyHolders == 1)
        {
            clickAddNewPolicyHolder();
            secondPolicyHolder = new NewJointPolicyHolder(driver, secondJointPolicyHolderContainer, 2);
        }
        else if (numberOfPolicyHolders == 2)
        {
            clickAddNewPolicyHolder();
            thirdPolicyHolder = new NewJointPolicyHolder(driver, thirdJointPolicyHolderContainer, 3);
        }
        else if (numberOfPolicyHolders == 3)
        {
            clickAddNewPolicyHolder(); //should expect message saying that you can't add more policy holders
        }
    }

    /**
     * @param index starts in 1
     */
    public void deletePolicyHolder(int index)
    {
        if (index > getNumberOfPolicyHoldersAlreadySaved())
        {
            throw new IllegalStateException("Index cannot be greater thant the number of policy holders already saved");
        }
        else
        {
            WebElement deleteLink = policyHolderTable.findElements(By.cssSelector("tbody > tr a.deleteLink")).get(index - 1);
            jsClick(deleteLink);
        }
    }

    /**
     * @param index starts in 1
     */
    public void updatePolicyHolder(int index)
    {
        if (index > getNumberOfPolicyHoldersAlreadySaved())
        {
            throw new IllegalStateException("Index cannot be greater thant the number of policy holders already saved");
        }
        else
        {
            WebElement updateLink = policyHolderTable.findElements(By.cssSelector("tbody > tr a.amendLink")).get(index - 1);
            jsClick(updateLink);
        }
    }
}



