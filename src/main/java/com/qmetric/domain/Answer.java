package com.qmetric.domain;

/**
 * Created with IntelliJ IDEA.
 * User: jmartins
 * Date: 10/11/2014
 */
public class Answer {

    private String questionId;
    private QuestionType questionType;
    private String question;
    private String answerValue;

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswerValue() {
        return answerValue;
    }

    public void setAnswerValue(String answerValue) {
        this.answerValue = answerValue;
    }
}
