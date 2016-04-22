package com.qmetric.pageobjects.legacy.question_set.about_your_property.statementsagreement;

import com.qmetric.pageobjects.BasePageObject;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created with IntelliJ IDEA.
 * User: jmartins
 * Date: 08/03/2013
 * Time: 14:07
 */

/**
 * This page object represents the statements agreement modal box that appears when selecting 'No' in the 'About Your Property' form.
 * <p/>
 * The way this works - when you disagree with one statement (use one method to select a check-box that disagrees with one statement) the page factory for that section will be
 * called and all the elements of that section will be initialized
 */
public abstract class StatementsAgreementModalBox extends BasePageObject
{

    @FindBy(id = "property_use_q_1") WebElement mainResidenceAgree;

    @FindBy(id = "property_use_q_2") WebElement mainResidenceDisagree;

    @FindBy(id = "walls_type_standard_1") WebElement wallsBrickStoneConcreteAgree;

    @FindBy(id = "walls_type_standard_2") WebElement wallsBrickStoneConcreteDisagree;

    @FindBy(id = "roof_type_standard_1") WebElement roofTileSlateAgree;

    @FindBy(id = "roof_type_standard_2") WebElement roofTileSlateDisagree;

    @FindBy(id = "property_state_of_repair_1") WebElement goodStateOfRepairAgree;

    @FindBy(id = "property_state_of_repair_2") WebElement goodStateOfRepairDisagree;

    @FindBy(id = "property_flood_affected_10_yr_1") WebElement notAffectedByFloodsAgree;

    @FindBy(id = "property_flood_affected_10_yr_2") WebElement notAffectedByFloodsDisagree;

    @FindBy(id = "subsidence_support_underpinning_1") WebElement noSubsidenceSupportUnderpinningAgree;

    @FindBy(id = "subsidence_support_underpinning_2") WebElement noSubsidenceSupportUnderpinningDisagree;

    @FindBy(id = "landslip_subsidence_heave_1") WebElement noSubsidenceNeighbourPropertiesAgree;

    @FindBy(id = "landslip_subsidence_heave_2") WebElement noSubsidenceNeighbourPropertiesDisagree;

    @FindBy(id = "cracking_external_walls_1") WebElement noCrackingExternalWallsAgree;

    @FindBy(id = "cracking_external_walls_2") WebElement noCrackingExternalWallsDisagree;

    @FindBy(id = "listed_building_q_1") WebElement notListedBuildingAgree;

    @FindBy(id = "listed_building_q_2") WebElement notListedBuildingDisagree;

    @FindBy(id = "current_ongoing_construction_1") WebElement notUnderConstructionAgree;

    @FindBy(id = "current_ongoing_construction_2") WebElement notUnderConstructionDisagree;

    public MainResidenceSection mainResidenceSection;

    public WallTypeSection wallTypeSection;

    public RoofTypeSection roofTypeSection;

    public FloodsSection floodsSection;

    public SubsidenceSection subsidenceSection;

    public CrackingExternalWallsSection crackingExternalWallsSection;

    public ListedBuildingSection listedBuildingSection;

    StatementsAgreementModalBox(WebDriver driver)
    {
        super(driver);
    }

    /**
     * ********                                  Verify presence of elements                              ***********
     */

    public boolean isMainResidenceRadioButtonDisplayed()
    {
        try
        {
            return mainResidenceAgree.isDisplayed() && mainResidenceDisagree.isDisplayed();
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
    }

    public boolean isWallsTypeRadioButtonDisplayed()
    {
        try
        {
            return wallsBrickStoneConcreteAgree.isDisplayed() && wallsBrickStoneConcreteDisagree.isDisplayed();
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
    }

    public boolean isRoofTypeRadioButtonDisplayed()
    {
        try
        {
            return roofTileSlateAgree.isDisplayed() && roofTileSlateDisagree.isDisplayed();
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
    }

    public boolean isGoodStateOfRepairRadioButtonDisplayed()
    {
        try
        {
            return goodStateOfRepairAgree.isDisplayed() && goodStateOfRepairDisagree.isDisplayed();
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
    }

    public boolean isNotAffectedByFloodsRadioButtonDisplayed()
    {
        try
        {
            return notAffectedByFloodsAgree.isDisplayed() && notAffectedByFloodsDisagree.isDisplayed();
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
    }

    public boolean isNoSubsidenceSupportUnderpinningRadioButtonDisplayed()
    {
        try
        {
            return noSubsidenceSupportUnderpinningAgree.isDisplayed() && noSubsidenceSupportUnderpinningDisagree.isDisplayed();
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
    }

    public boolean isNoSubsidenceNeighbourPropertiesRadioButtonDisplayed()
    {
        try
        {
            return noSubsidenceNeighbourPropertiesAgree.isDisplayed() && noSubsidenceNeighbourPropertiesDisagree.isDisplayed();
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
    }

    public boolean isNoCrackingExternalWallsRadioButtonDisplayed()
    {
        try
        {
            return noCrackingExternalWallsAgree.isDisplayed() && noCrackingExternalWallsDisagree.isDisplayed();
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
    }

    public boolean isNotListedBuildingRadioButtonDisplayed()
    {
        try
        {
            return notListedBuildingAgree.isDisplayed() && notListedBuildingDisagree.isDisplayed();
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
    }

    public boolean isNotUnderConstructionRadioButtonDisplayed()
    {
        try
        {
            return notUnderConstructionAgree.isDisplayed() && notUnderConstructionDisagree.isDisplayed();
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
    }

    /***********                                        Select checkboxes                                  ************/

    /**
     * If agree is false it will initialize MainResidenceSection
     *
     * @param agree
     */
    public void selectMainResidenceRadioButton(boolean agree)
    {
        if (agree)
        {
            jsClick(mainResidenceAgree);
        }
        else
        {
            jsClick(mainResidenceDisagree);
            mainResidenceSection = PageFactory.initElements(driver, MainResidenceSection.class);
        }
    }

    /**
     * If agree is false it will initialize WallTypeSection
     *
     * @param agree
     */
    public void selectWallsTypeRadioButton(boolean agree)
    {
        if (agree)
        {
            jsClick(wallsBrickStoneConcreteAgree);
        }
        else
        {
            jsClick(wallsBrickStoneConcreteDisagree);
            wallTypeSection = PageFactory.initElements(driver, WallTypeSection.class);
        }
    }

    /**
     * If agree is false it will initialize RoofTypeSection
     *
     * @param agree
     */
    public void selectRoofTypeRadioButton(boolean agree)
    {
        if (agree)
        {
            jsClick(roofTileSlateAgree);
        }
        else
        {
            jsClick(roofTileSlateDisagree);
            roofTypeSection = PageFactory.initElements(driver, RoofTypeSection.class);
        }
    }

    public void selectGoodStateOfRepairRadioButton(boolean agree)
    {
        if (agree)
        {
            jsClick(goodStateOfRepairAgree);
        }
        else
        {
            jsClick(goodStateOfRepairDisagree);
        }
    }

    /**
     * If agree is false it will initialize FloodsSection
     *
     * @param agree
     */
    public void selectNotAffectedByFloodsRadioButton(boolean agree)
    {
        if (agree)
        {
            jsClick(notAffectedByFloodsAgree);
        }
        else
        {
            jsClick(notAffectedByFloodsDisagree);
            floodsSection = PageFactory.initElements(driver, FloodsSection.class);
        }
    }

    /**
     * If agree is false it will initialize SubsidenceSection
     *
     * @param agree
     */
    public void selectNoSubsidenceSupportUnderpinningRadioButton(boolean agree)
    {
        if (agree)
        {
            jsClick(noSubsidenceSupportUnderpinningAgree);
        }
        else
        {
            jsClick(noSubsidenceSupportUnderpinningDisagree);
            subsidenceSection = PageFactory.initElements(driver, SubsidenceSection.class);
        }
    }

    public void selectNoSubsidenceNeighbourPropertiesRadioButton(boolean agree)
    {
        if (agree)
        {
            jsClick(noSubsidenceNeighbourPropertiesAgree);
        }
        else
        {
            jsClick(noSubsidenceNeighbourPropertiesDisagree);
        }
    }

    /**
     * If agree is false it will initialize CrackingExternalWallsSections
     *
     * @param agree
     */
    public void selectNoCrackingExternalWallsRadioButton(boolean agree)
    {
        if (agree)
        {
            jsClick(noCrackingExternalWallsAgree);
        }
        else
        {
            jsClick(noCrackingExternalWallsDisagree);
            crackingExternalWallsSection = PageFactory.initElements(driver, CrackingExternalWallsSection.class);
        }
    }

    /**
     * If agree is false it will initialize ListedBuildingSection
     *
     * @param agree
     */
    public void selectNotListedBuildingRadioButton(boolean agree)
    {
        if (agree)
        {
            jsClick(notListedBuildingAgree);
        }
        else
        {
            jsClick(notListedBuildingDisagree);
            listedBuildingSection = PageFactory.initElements(driver, ListedBuildingSection.class);
        }
    }

    public void selectNotUnderConstructionRadioButton(boolean agree)
    {
        if (agree)
        {
            jsClick(notUnderConstructionAgree);
        }
        else
        {
            jsClick(notListedBuildingDisagree);
        }
    }

    public abstract void closeStatementsForm();

    public abstract void confirmStatements();

    public abstract boolean isDisplayed();
}
