package com.qmetric.pageobjects.legacy.question_set.about_your_property;

import com.qmetric.pageobjects.BasePageObject;
import com.qmetric.pageobjects.enquiry_forms.legacy.PropertyDetails;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA. User: jmartins Date: 08/03/2013 Time: 10:59
 */
public class AboutSafetyAndSecuritySection extends BasePageObject
{

    /**
     * **                                               UI mappings                                               ****
     */

    @FindBy(id = "property_windows_locks_1") WebElement windowLockYesRadioButton;

    @FindBy(id = "property_windows_locks_2") WebElement windowLockNoRadioButton;

    @FindBy(id = "self_contained_1") WebElement mainLockYesRadioButton;

    @FindBy(id = "self_contained_2") WebElement mainLockNoRadioButton;

    @FindBy(id = "lock_property_main_door_locks1") WebElement mainLockMorticeDeadlockButton;

    @FindBy(id = "lock_property_main_door_locks2") WebElement mainLockMorticeBs3621Button;

    @FindBy(id = "lock_property_main_door_locks3") WebElement mainLockKeyOperatedMultipointButton;

    @FindBy(id = "lock_property_main_door_locks4") WebElement mainLockRimDeadlockButton;

    @FindBy(id = "lock_property_main_door_locks5") WebElement mainLockKeyOperatedDeadboltButton;

    @FindBy(id = "lock_property_main_door_locks6") WebElement mainLockOtherButton;

    @FindBy(id = "sliding_door_1") WebElement patioLockYesRadioButton;

    @FindBy(id = "sliding_door_2") WebElement patioLockNoRadioButton;

    @FindBy(id = "lock_property_patio_locks1") WebElement patioLockKeyOperatedMultipointButton;

    @FindBy(id = "lock_property_patio_locks2") WebElement patioLockAntiLiftButton;

    @FindBy(id = "lock_property_patio_locks3") WebElement patioLockKeyOperatedButton;

    @FindBy(id = "lock_property_patio_locks4") WebElement patioLockOtherButton;

    @FindBy(id = "other_external_doors_branch_question_1") WebElement externalLockYesRadioButton;

    @FindBy(id = "other_external_doors_branch_question_2") WebElement externalLockNoRadioButton;

    @FindBy(id = "lock_property_other_external_locks1") WebElement externalLockMorticeDeadlockButton;

    @FindBy(id = "lock_property_other_external_locks2") WebElement externalLockMorticeBs3621Button;

    @FindBy(id = "lock_property_other_external_locks3") WebElement externalLockKeyOperatedMultipointButton;

    @FindBy(id = "lock_property_other_external_locks4") WebElement externalLockRimDeadlockButton;

    @FindBy(id = "lock_property_other_external_locks5") WebElement externalLockKeyOperatedDeadboltButton;

    @FindBy(id = "lock_property_other_external_locks6") WebElement externalLockOtherButton;

    @FindBy(id = "property_alarm_1") WebElement alarmYesRadioButton;

    @FindBy(id = "property_alarm_2") WebElement alarmNoRadioButton;

    @FindBy(id = "lock_property_alarm_type1") WebElement alarmBellsOnlyButton;

    @FindBy(id = "lock_property_alarm_type2") WebElement alarmNotifiesButton;

    @FindBy(id = "property_alarm_contractor_1") WebElement alarmContractorYesRadioButton;

    @FindBy(id = "property_alarm_contractor_2") WebElement alarmContractorNoRadioButton;

    /**
     * **************************************************************************************************************
     */

    private Map<String, WebElement> mainLockMap;

    private Map<String, WebElement> patioDoorsLockMap;

    private Map<String, WebElement> externalLockMap;

    private Map<String, WebElement> alarmMap;

    public AboutSafetyAndSecuritySection(WebDriver driver)
    {
        super(driver);
    }

    public void initialiseMainLockMap()
    {
        mainLockMap = new HashMap<String, WebElement>();
        mainLockMap.put("5 lever mortice deadlock", mainLockMorticeDeadlockButton);
        mainLockMap.put("5 lever mortice deadlock conforming to BS3621", mainLockMorticeBs3621Button);
        mainLockMap.put("Key operated multipoint locking system", mainLockKeyOperatedMultipointButton);
        mainLockMap.put("Rim deadlock", mainLockRimDeadlockButton);
        mainLockMap.put("Key operated deadbolts with any other lock", mainLockKeyOperatedDeadboltButton);
        mainLockMap.put("Other locks", mainLockOtherButton);
    }

    public void initialisePatioDoorsLockMap()
    {
        patioDoorsLockMap = new HashMap<String, WebElement>();
        patioDoorsLockMap.put("Key operated multipoint locking system", patioLockKeyOperatedMultipointButton);
        patioDoorsLockMap.put("Anti-lift key operated lock with any other lock", patioLockAntiLiftButton);
        patioDoorsLockMap.put("Key operated lock", patioLockKeyOperatedButton);
        patioDoorsLockMap.put("Other locks", patioLockOtherButton);
    }

    public void initialiseExternalLockMap()
    {
        externalLockMap = new HashMap<String, WebElement>();
        externalLockMap.put("5 lever mortice deadlock", externalLockMorticeDeadlockButton);
        externalLockMap.put("5 lever mortice deadlock conforming to BS3621", externalLockMorticeBs3621Button);
        externalLockMap.put("Key operated multipoint locking system", externalLockKeyOperatedMultipointButton);
        externalLockMap.put("Rim deadlock", externalLockRimDeadlockButton);
        externalLockMap.put("Key operated deadbolts with any other lock", externalLockKeyOperatedDeadboltButton);
        externalLockMap.put("Other locks", externalLockOtherButton);
    }

    public void initialiseAlarmMap()
    {
        alarmMap = new HashMap<String, WebElement>();
        alarmMap.put("Bells-only alarm", alarmBellsOnlyButton);
        alarmMap.put("Alarm that notifies the police or alarm company", alarmNotifiesButton);
    }

    public void selectWindowLocksOption(final PropertyDetails propertyDetails)
    {
        if (propertyDetails.isHasLocksOnWindows())
        {
            jsClick(windowLockYesRadioButton);
        }
        else
        {
            jsClick(windowLockNoRadioButton);
        }
    }

    public void selectMainLockOption(final PropertyDetails propertyDetails)
    {
        if (propertyDetails.isHasMainDoorLock())
        {
            jsClick(mainLockYesRadioButton);
            WebElement mainLockButton = mainLockMap.get(propertyDetails.getMainDoorLockDescription());
            jsClick(mainLockButton);
            waitForElementNotVisible(10, mainLockButton);
        }
        else
        {
            jsClick(mainLockNoRadioButton);
        }
    }

    public void setPatioLockOption(final PropertyDetails propertyDetails)
    {
        if (propertyDetails.isHasPatioDoorsLock())
        {
            jsClick(patioLockYesRadioButton);
            WebElement patioLockButton = patioDoorsLockMap.get(propertyDetails.getPatioDoorsLockDescription());
            jsClick(patioLockButton);
            waitForElementNotVisible(10, patioLockButton);
        }
        else
        {
            jsClick(patioLockNoRadioButton);
        }
    }

    public void setExternalLockOption(final PropertyDetails propertyDetails)
    {
        if (propertyDetails.isHasOtherExternalDoorsLock())
        {
            jsClick(externalLockYesRadioButton);
            WebElement externalLockButton = externalLockMap.get(propertyDetails.getOtherExternalDoorsLockDescription());
            jsClick(externalLockButton);
            waitForElementNotVisible(10, externalLockButton);
        }
        else
        {
            jsClick(externalLockNoRadioButton);
        }
    }

    public void setAlarmOption(final PropertyDetails propertyDetails)
    {
        if (propertyDetails.isHasAlarm())
        {
            jsClick(alarmYesRadioButton);
            jsClick(alarmMap.get(propertyDetails.getAlarmType()));
            setAlarmNotificationOption(propertyDetails);
        }
        else
        {
            jsClick(alarmNoRadioButton);
        }
    }

    void setAlarmNotificationOption(final PropertyDetails propertyDetails)
    {
        if (propertyDetails.getAlarmType().equals("Alarm that notifies the police or alarm company"))
        {
            if (propertyDetails.isVerifiedAlarm())
            {
                jsClick(alarmContractorYesRadioButton);
            }
            else
            {
                jsClick(alarmContractorNoRadioButton);
            }
        }
    }
}
