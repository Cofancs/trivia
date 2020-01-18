package com.adaptionsoft.games.uglytrivia.question;

public class Question {
    private QuestionCategory category;

    public Question(QuestionCategory category) {
        this.category = category;
    }

    public QuestionCategory getCategory() {
        return category;
    }

    public void setCategory(QuestionCategory category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Question{" +
                "category=" + category +
                '}';
    }
}
