package com.example.csc221test;

public class QuestionAnswers {
    private static String questionsList [] = {
            "What is the process by which plants make their food?",
            "What is the capital of France?",
            "What is the value of Pi to two decimal places?",
            "Who wrote the play \"Romeo and Juliet\"?",
            "Who was the first President of the US?"
    };

    private static String answerList [][] = {
            {"Photosynthesis", "Respiration", "Digestion", "Fermentation"},
            {"Berlin", "Madrid", "Rome", "Paris"},
            {"3.21", "3.14", "2.71", "1.62"},
            {"John Keats", "William Shakespeare", "Charles Dickens", "Mark Twain"},
            {"Abraham Lincoln", "Thomas Jefferson", "George Washington", "John Adams"}
    };

    private static String correctAns[] = {"Photosynthesis", "Paris", "3.14", "William Shakespeare", "George Washington"};

    public static String getQuestion(int a){
        String question = questionsList[a];
        return question;
    }

    public static String getAnswerOne(int a){
        String answerOne = answerList[a][0];
        return answerOne;
    }
    public static String getAnswerTwo(int a){
        String answerTwo = answerList[a][1];
        return answerTwo;
    }
    public static String getAnswerThree(int a){
        String answerThree = answerList[a][2];
        return answerThree;
    }
    public static String getAnswerFour(int a){
        String answerFour = answerList[a][3];
        return answerFour;
    }
    public static String getSolution(int a){
        String solution = correctAns[a];
        return solution;
    }

}
