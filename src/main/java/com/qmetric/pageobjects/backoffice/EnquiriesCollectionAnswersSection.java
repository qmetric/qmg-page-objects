package com.qmetric.pageobjects.backoffice;

import com.qmetric.domain.QuestionType;
import com.qmetric.utilities.DynamicElementHandler;
import com.qmetric.domain.QuestionTypeMap;
import com.qmetric.utilities.TimeHelper;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA. User: jmartins Date: 11/11/2014
 */
public class EnquiriesCollectionAnswersSection extends EnquiriesAnswersSection
{
    public EnquiriesCollectionAnswersSection(WebDriver driver)
    {
        super(driver);
    }

    public void enterAnswersForCollection(String collectionId, String parentIndex, final List<Map<String, String>> answers) throws Exception
    {
        WebElement collectionQuestionContainer = getCollectionQuestionContainer(null, collectionId, parentIndex);
        enterAnswersForCollection(collectionQuestionContainer, answers);
    }

    private WebElement getCollectionQuestionContainer(WebElement parentCollectionElement, String collectionId, String parentIndex)
    {
        String[] questionIds = collectionId.split("\\.", 2);
        if (questionIds.length == 1)
        {
            return parentCollectionElement.findElement(By.id(collectionId));
        }
        String[] parentIndices = parentIndex.split("\\.", 2);
        collectionId = questionIds[0];
        parentIndex = parentIndices[0];
        WebElement collectionElement = getCollectionElement(parentCollectionElement, collectionId, parentIndex);
        if (questionIds.length > 1)
        {
            if (parentIndices.length > 1)
            {
                parentIndex = parentIndices[1];
            }
            collectionElement = getCollectionQuestionContainer(collectionElement, questionIds[1], parentIndex);
        }
        return collectionElement;
    }

    private WebElement getCollectionElement(WebElement parentCollectionElement, String collectionId, String parentIndex)
    {
        if (parentCollectionElement == null)
        {
            return findElements(By.id(collectionId)).get(Integer.parseInt(parentIndex));
        }
        return parentCollectionElement.findElements(By.id(collectionId)).get(Integer.parseInt(parentIndex));
    }

    public void enterAnswersForCollection(final String collectionId, final List<Map<String, String>> answers) throws Exception
    {
        WebElement collectionQuestionContainer = getCollectionQuestionContainer(collectionId);
        enterAnswersForCollection(collectionQuestionContainer, answers);
    }

    private void selectRadioButtonAnswerForCollection(WebElement collectionQuestionContainer, final String questionId, final String answer)
    {
        int counter = 0;
        boolean radioButtonExists;
        do
        {
            radioButtonExists = doesWebElementExist(collectionQuestionContainer, By.cssSelector("input[data-id=\"" + questionId + counter + "\"]"));
            WebElement radioButton = collectionQuestionContainer.findElement(By.cssSelector("input[data-id=\"" + questionId + counter + "\"]"));
            if (radioButton.getAttribute("value").equals(answer))
            {
                String radioId = radioButton.getAttribute("id");
                WebElement radioLabelButton = findElement(By.cssSelector("label[for=\"" + radioId + "\"]"));
                jsClick(radioLabelButton);
                break;
            }
            counter++;
        }
        while (radioButtonExists);
    }

    private void enterAnswersForCollection(final WebElement collectionQuestionContainer, final List<Map<String, String>> answersMap) throws Exception
    {
        int noOfCollectionAnswers = getNumberOfCollectionAnswers(answersMap);
        for (int i = 1; i <= noOfCollectionAnswers; i++)
        {
            List<WebElement> addCollectionQuestionButtons = collectionQuestionContainer.findElements(By.className("collectionTrigger"));
            WebElement addCollectionQuestionButton = addCollectionQuestionButtons.get(addCollectionQuestionButtons.size() - 1);
            jsClick(addCollectionQuestionButton);
            WebElement latestCollectionQuestionContainer = getLatestCollectionQuestionContainer(collectionQuestionContainer);
            for (Map<String, String> answerMap : answersMap)
            {
                String answer = answerMap.get("Collection Answer " + i);
                String questionId = answerMap.get("Question Id");
                String questionType = answerMap.get("Question Type");
                String answerType = answerMap.get("Answer Type");
                QuestionType type = new QuestionTypeMap().get(questionType);
                selectAnswerForCollection(latestCollectionQuestionContainer, questionId, type, answer, answerType);
            }
        }
    }

    private WebElement getCollectionQuestionContainer(final String collectionId)
    {
        return findElement(By.id(collectionId));
    }

    private int getNumberOfCollectionAnswers(final List<Map<String, String>> answersMap)
    {
        String answer;
        int noOfAnswers = 1;
        do
        {
            answer = answersMap.get(0).get("Collection Answer " + noOfAnswers);
            if (StringUtils.isEmpty(answer))
            {
                noOfAnswers--;
                break;
            }
            noOfAnswers++;
        }
        while (!StringUtils.isEmpty(answer));
        return noOfAnswers;
    }

    public WebElement getLatestCollectionQuestionContainer(final WebElement collectionQuestionContainer)
    {
        List<WebElement> collectionQuestionContainers = collectionQuestionContainer.findElements(By.className("clones"));
        return collectionQuestionContainers.get(collectionQuestionContainers.size() - 1);
    }

    public void selectAnswerForCollection(WebElement collectionQuestionContainer, String questionId, QuestionType questionType, String answer, String answerType) throws Exception
    {
        WebElement answerElement;
        switch (questionType)
        {
            case SELECT:
                answerElement = collectionQuestionContainer.findElement(By.cssSelector("select[data-name=\"" + questionId + "\"]"));
                selectDropDownValueByVisibleText(answerElement, answer);
                break;
            case RADIO:
            case YESNO:
                selectRadioButtonAnswerForCollection(collectionQuestionContainer, questionId, answer);
                break;
            case NUMBER:
                answerElement = collectionQuestionContainer.findElement(By.cssSelector("input[data-name=\"" + questionId + "\"]"));
                enterTextInput(answerElement, answer);
                break;
            case CURRENCY:
            case TEXT:
                answerElement = collectionQuestionContainer.findElement(By.cssSelector("input[data-name=\"" + questionId + "\"]"));
                enterTextInput(answerElement, answer);
                break;
            case VEHICLE:
            case ADDRESS:
                answerElement = collectionQuestionContainer.findElement(By.cssSelector("input[data-name=\"" + questionId + "\"]"));
                enterTextInput(answerElement, answer);
                WebElement lookupButton = findElement(By.cssSelector("input[type=\"button\"][data-question=\"" + questionId + "\"]"));
                jsClick(lookupButton);
                break;
            case DATE:
            case DATENOWORFUTURE:
            case DATEOFBIRTH:
            case DATEMONTHANDYEAR:
                EnquiriesCollectionDateAnswersSection enquiriesCollectionDateAnswersSection = PageFactory.initElements(driver, EnquiriesCollectionDateAnswersSection.class);
                enquiriesCollectionDateAnswersSection.setCollectionQuestionContainer(collectionQuestionContainer);
                enquiriesCollectionDateAnswersSection.selectDateAnswer(questionId, questionType, answer, answerType);
                break;
            case ABICODE:
                if (answerType.equals("select"))
                {
                    answerElement = collectionQuestionContainer.findElement(By.cssSelector("select[data-name=\"" + questionId + "\"]"));
                    selectDropDownValueByVisibleText(answerElement, answer);
                }
                else
                {
                    answerElement = collectionQuestionContainer.findElement(By.cssSelector("input[data-name=\"" + questionId + "\"]"));
                    enterTextInput(answerElement, answer);
                    TimeHelper.waitInSeconds(2);
                    Actions actions = new Actions(driver);
                    actions.sendKeys(Keys.ENTER).perform();
                }
                break;
        }
    }

    public List<Map<String, String>> getCollectionQuestionAnswers(final String collectionId)
    {
        List<List<Map<String, String>>> answersMapList = getAnswersMapList(collectionId);
        List<Map<String, String>> collectionAnswers = new ArrayList<Map<String, String>>();
        for (int i = 0; i < answersMapList.size(); i++)
        {
            List<Map<String, String>> answers = answersMapList.get(i);
            for (int j = 0; j < answers.size(); j++)
            {
                Map<String, String> collectionAnswerMap = putCollectionAnswersToMap(i, answers.get(j));
                if (collectionAnswers.size() == answers.size())
                {
                    Map<String, String> answerMapToUpdate = collectionAnswers.get(j);
                    answerMapToUpdate.putAll(collectionAnswerMap);
                    collectionAnswers.set(j, answerMapToUpdate);
                }
                else
                {
                    collectionAnswers.add(collectionAnswerMap);
                }
            }
        }
        return collectionAnswers;
    }

    private List<List<Map<String, String>>> getAnswersMapList(final String collectionId)
    {
        // To combat stale element reference exception (loading too quickly)
        TimeHelper.waitHalfASecond();
        List<List<Map<String, String>>> answersMapList = new ArrayList<List<Map<String, String>>>();
        List<WebElement> collectionContainerElements = findElements(By.cssSelector("section#" + collectionId + " > section.clones"));
        for (WebElement collectionContainerElement : collectionContainerElements)
        {
            List<Map<String, String>> answers = new ArrayList<Map<String, String>>();
            getCollectionAnswers(answers, collectionContainerElement);
            answersMapList.add(answers);
        }
        return answersMapList;
    }

    private Map<String, String> putCollectionAnswersToMap(final int index, final Map<String, String> answerMap)
    {
        Map<String, String> collectionAnswerMap = new HashMap<String, String>();
        collectionAnswerMap.put("Question To Ask", answerMap.get("Question To Ask"));
        collectionAnswerMap.put("Question Type", answerMap.get("Question Type"));
        collectionAnswerMap.put("Question Id", answerMap.get("Question Id"));
        collectionAnswerMap.put("Collection Answer " + (index + 1), answerMap.get("Answer"));
        return collectionAnswerMap;
    }

    private void getCollectionAnswers(final List<Map<String, String>> answers, final WebElement collectionContainer)
    {
        new DynamicElementHandler<Void>()
        {
            @Override
            public Void handleDynamicElement()
            {
                answers.clear();
                List<WebElement> questionPanelContainers = collectionContainer.findElements(By.cssSelector("section"));
                for (WebElement questionPanelContainer : questionPanelContainers)
                {
                    putAnswersToMap(answers, collectionContainer, questionPanelContainer);
                }
                return null;
            }
        }.execute();
    }

    public void removeCollectionQuestion(final String collectionId, final int rowNumber)
    {
        List<WebElement> collectionContainerElements = findElements(By.cssSelector("section#" + collectionId + " > section.clones"));
        WebElement collectionContainer = collectionContainerElements.get(rowNumber - 1);
        WebElement deleteButton = collectionContainer.findElement(By.className("deletesection"));
        jsClick(deleteButton);
    }
}
