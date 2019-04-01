package com.londonappbrewery.quizzler;

public class TrueFalse {

    private int mQuestionID;
    private boolean mAnswer;

    public TrueFalse(int mQuestionRID, boolean boolAns) {
        mQuestionID = mQuestionRID;
        mAnswer = boolAns;
    }

    public int getmQuestionID() {
        return mQuestionID;
    }

    public void setmQuestionID(int questionID) {
        mQuestionID = questionID;
    }

    public boolean ismAnswer() {
        return mAnswer;
    }

    public void setmAnswer(boolean answer) {
        mAnswer = answer;
    }
}
