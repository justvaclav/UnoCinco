package com.unocinco.data;

/**
 * Created by User on 05.04.2018.
 */

import android.provider.BaseColumns;

public final class QuestExample {

    private QuestExample() {
    };

    public static final class GuestEntry implements BaseColumns {
        public final static String TABLE_NAME = "Questions";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_ID = "id";
        public final static String COLUMN_QUESTION = "Question";
        public final static String COLUMN_ANSWER1 = "Answer1";
        public final static String COLUMN_ANSWER2 = "Answer2";
        public final static String COLUMN_ANSWER3 = "Answer3";
        public final static String COLUMN_ANSWER4 = "Answer4";
        public final static String COLUMN_RIGHTANSWER = "RightAnswer";
        public final static String COLUMN_PRICE = "price";
    }
}