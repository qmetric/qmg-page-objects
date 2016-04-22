package com.qmetric.pageobjects.legacy.question_set.coveroptions;

import com.google.common.collect.Lists;
import com.qmetric.pageobjects.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created with IntelliJ IDEA. User: jmartins Date: 19/03/2013
 */
public class ContentsCoverSection extends BasePageObject
{

    @FindBy(id = "contents_si") WebElement contentsValueInputBox;

    @FindBy(id = "contents_accidental_damage_1") WebElement accidentalDamageCoverYes;

    @FindBy(id = "contents_accidental_damage_2") WebElement accidentalDamageCoverNo;

    @FindBy(id = "valuables_total_cover_q_1") WebElement enoughToCoverYes;

    @FindBy(id = "valuables_total_cover_q_2") WebElement enoughToCoverNo;

    @FindBy(id = "valuables_total_cover") WebElement valuablesTotalCoverDropDown;

    @FindBy(id = "more_than_2000_1") WebElement personalValuesMoreThan2000Yes;

    @FindBy(id = "more_than_2000_2") WebElement personalValuesMoreThan2000No;

    @FindBy(id = "bicycle_cover_1") WebElement bicycleCoverYes;

    @FindBy(id = "bicycle_cover_2") WebElement bicycleCoverNo;

    @FindBy(id = "unspecified_items_branch_1") WebElement unspecifiedItemsCoverYes;

    @FindBy(id = "unspecified_items_branch_2") WebElement unspecifiedItemsCoverNo;

    @FindBy(id = "unspecified_items") WebElement unspecifiedItemsCoverValueDropDown;

    @FindBy(id = "questionbicycle_cover") WebElement questionBicycleCover;

    @FindBy(id = "questionmore_than_2000") WebElement questionMoreThan2000;

    public List<NewBicycle> bicycleList = Lists.newArrayList();

    public List<NewPersonalItem> personalItemList = Lists.newArrayList();

    public ContentsCoverSection(WebDriver driver)
    {
        super(driver);
    }

    /**
     * **                                     Verify element presence                                            ****
     */

    public boolean isContentsValueInputBoxDisplayed()
    {
        try
        {
            return contentsValueInputBox.isDisplayed();
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public boolean isAccidentalDamageCoverRadioButtonDisplayed()
    {
        try
        {
            return accidentalDamageCoverYes.isDisplayed() && accidentalDamageCoverNo.isDisplayed();
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public boolean isEnoughToCoverRadioButtonDisplayed()
    {
        try
        {
            return enoughToCoverYes.isDisplayed() && enoughToCoverNo.isDisplayed();
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public boolean isValuablesTotalCoverDropDownDisplayed()
    {
        try
        {
            return valuablesTotalCoverDropDown.isDisplayed();
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public boolean isPersonalValuesMoreThan2000RadioButtonDisplayed()
    {
        try
        {
            return personalValuesMoreThan2000Yes.isDisplayed() && personalValuesMoreThan2000No.isDisplayed();
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public boolean isBicycleCoverRadioButtonDisplayed()
    {
        try
        {
            return bicycleCoverYes.isDisplayed() && bicycleCoverNo.isDisplayed();
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public boolean isUnspecifiedItemsCoverRadioButtonDisplayed()
    {
        try
        {
            return unspecifiedItemsCoverYes.isDisplayed() && unspecifiedItemsCoverNo.isDisplayed();
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public boolean isUnspecifiedItemsCoverValueDropDownDisplayed()
    {
        try
        {
            return unspecifiedItemsCoverYes.isDisplayed() && unspecifiedItemsCoverNo.isDisplayed();
        }
        catch (Exception e)
        {
            return false;
        }
    }

    /**
     * *                                            User interactions                                             ***
     */

    public void enterContentsValue(String contentsValue)
    {
        enterTextInput(contentsValueInputBox, contentsValue);
    }

    public void selectAccidentalDamageCover(boolean yes)
    {
        if (yes)
        {
            jsClick(accidentalDamageCoverYes);
        }
        else
        {
            jsClick(accidentalDamageCoverNo);
        }
    }

    public void selectEnoughToCover(boolean yes)
    {
        if (yes)
        {
            jsClick(enoughToCoverYes);
        }
        else
        {
            jsClick(enoughToCoverNo);
        }
    }

    public void selectValuableTotalCover(String valuableTotalCover) throws Exception
    {
        selectDropDownValueByVisibleText(valuablesTotalCoverDropDown, valuableTotalCover);
    }

    /**
     * This will add a new personalItem to personalItemList if the argument 'yes' is true If 'yes' is false it will empty personalItemList
     *
     * @param yes
     */
    public void selectPersonalValuesMoreThan2000(boolean yes)
    {
        if (yes)
        {
            jsClick(personalValuesMoreThan2000Yes);
            WebElement personalItemElem = driver.findElement(By.id("specified_item_category1"));
            NewPersonalItem firstPersonalItem = new NewPersonalItem(driver, personalItemElem, 1);
            addNewPersonalItemToList(firstPersonalItem);
        }
        else
        {
            jsClick(personalValuesMoreThan2000No);
            emptyPersonalItemList();
        }
    }

    public NewPersonalItem addNewPersonalItem()
    {
        int indexOfPersonalItem = personalItemList.size() + 1;
        WebElement addPersonalItemButton = questionMoreThan2000.findElement(By.className("addition"));
        jsClick(addPersonalItemButton);
        WebElement personalItemElem = driver.findElement(By.id("specified_item_category" + indexOfPersonalItem));
        NewPersonalItem newPersonalItem = new NewPersonalItem(driver, personalItemElem, indexOfPersonalItem);
        addNewPersonalItemToList(newPersonalItem);
        return newPersonalItem;
    }

    public void deletePersonalItem(int index)
    {
        WebElement personalItemRow = questionMoreThan2000.findElements(By.cssSelector("tbody > tr")).get(index);
        jsClick(personalItemRow.findElement(By.cssSelector("a.deleteLink")));
    }

    /**
     * This will add a new bicycle to bicycleList if the argument 'yes' is true If 'yes' is false it will empty bicycleList
     *
     * @param yes
     */
    public void selectBicycleCover(boolean yes)
    {
        if (yes)
        {
            jsClick(bicycleCoverYes);
            WebElement bicycleElem = driver.findElement(By.id("pedal_cycle1"));
            NewBicycle firstBicycle = new NewBicycle(driver, bicycleElem, 1);
            addNewBicycleToList(firstBicycle);
        }
        else
        {
            jsClick(bicycleCoverNo);
            emptyBicycleList();
        }
    }

    public NewBicycle addNewBicycle()
    {
        int indexOfBicycle = bicycleList.size() + 1;
        WebElement addBicycleButton = questionBicycleCover.findElement(By.className("addition"));
        jsClick(addBicycleButton);
        WebElement bicycleElem = driver.findElement(By.id("pedal_cycle" + indexOfBicycle));
        NewBicycle newBicycle = new NewBicycle(driver, bicycleElem, indexOfBicycle);
        addNewBicycleToList(newBicycle);
        return newBicycle;
    }

    public void deleteBicycle(int index)
    {
        WebElement personalItemRow = questionBicycleCover.findElements(By.cssSelector("tbody > tr")).get(index);
        jsClick(personalItemRow.findElement(By.cssSelector("a.deleteLink")));
    }

    public void selectUnspecifiedItemsCover(boolean yes)
    {
        if (yes)
        {
            jsClick(unspecifiedItemsCoverYes);
        }
        else
        {
            jsClick(unspecifiedItemsCoverNo);
        }
    }

    public void selectUnspecifiedItemsCoverValue(String value) throws Exception
    {
        selectDropDownByOptionValue(unspecifiedItemsCoverValueDropDown, value);
    }

    public int getNumberOfPersonalItemsInTable()
    {
        return questionMoreThan2000.findElements(By.cssSelector("tbody > tr")).size();
    }

    public int getNumberOfBicyclesInTable()
    {
        return questionBicycleCover.findElements(By.cssSelector("tbody > tr")).size();
    }

    /**
     * *
     */
    private void addNewBicycleToList(NewBicycle bicycle)
    {
        bicycleList.add(bicycle);
    }

    private void emptyBicycleList()
    {
        bicycleList = Lists.newArrayList();
    }

    private void addNewPersonalItemToList(NewPersonalItem personalItem)
    {
        personalItemList.add(personalItem);
    }

    private void emptyPersonalItemList()
    {
        personalItemList = Lists.newArrayList();
    }
}
