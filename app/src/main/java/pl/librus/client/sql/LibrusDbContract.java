package pl.librus.client.sql;

import android.provider.BaseColumns;

/**
 * Created by szyme on 27.01.2017.
 */

public class LibrusDbContract {

    public static final int DB_VERSION = 6;
    public static final String DB_NAME = "database.db";
    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";
    private static final String NOT_NULL = " NOT NULL";

    private LibrusDbContract() {
    }

    public static abstract class Lessons implements BaseColumns {

        public static final String TABLE_NAME = "lessons";

        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_DATE = "date";
        public static final String COLUMN_NAME_LESSON_NUMBER = "lesson_number";
        public static final String COLUMN_NAME_SUBJECT_ID = "subject_id";
        public static final String COLUMN_NAME_SUBJECT_NAME = "subject_name";
        public static final String COLUMN_NAME_TEACHER_ID = "teacher_id";
        public static final String COLUMN_NAME_TEACHER_FIRST_NAME = "teacher_first_name";
        public static final String COLUMN_NAME_TEACHER_LAST_NAME = "teacher_last_name";
        public static final String COLUMN_NAME_SUBSTITUTION = "substitution";
        public static final String COLUMN_NAME_CANCELED = "canceled";
        public static final String COLUMN_NAME_ORG_TEACHER_ID = "org_teacher_id";
        public static final String COLUMN_NAME_ORG_SUBJECT_ID = "org_subject_id";

        public static final String CREATE_TABLE = "CREATE TABLE " +
                TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY," +
                COLUMN_NAME_ID + TEXT_TYPE + NOT_NULL + COMMA_SEP +
                COLUMN_NAME_DATE + INTEGER_TYPE + NOT_NULL + COMMA_SEP +
                COLUMN_NAME_LESSON_NUMBER + INTEGER_TYPE + NOT_NULL + COMMA_SEP +
                COLUMN_NAME_SUBJECT_ID + TEXT_TYPE + COMMA_SEP +
                COLUMN_NAME_SUBJECT_NAME + TEXT_TYPE + COMMA_SEP +
                COLUMN_NAME_TEACHER_ID + TEXT_TYPE + COMMA_SEP +
                COLUMN_NAME_TEACHER_FIRST_NAME + TEXT_TYPE + COMMA_SEP +
                COLUMN_NAME_TEACHER_LAST_NAME + TEXT_TYPE + COMMA_SEP +
                COLUMN_NAME_SUBSTITUTION + INTEGER_TYPE + COMMA_SEP +
                COLUMN_NAME_CANCELED + INTEGER_TYPE + COMMA_SEP +
                COLUMN_NAME_ORG_SUBJECT_ID + TEXT_TYPE + COMMA_SEP +
                COLUMN_NAME_ORG_TEACHER_ID + TEXT_TYPE + " )";

        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    public static abstract class Account implements BaseColumns {

        public static final String TABLE_NAME = "account";

        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_CLASS_ID = "class_id";
        public static final String COLUMN_NAME_FIRST_NAME = "first_name";
        public static final String COLUMN_NAME_LAST_NAME = "last_name";
        public static final String COLUMN_NAME_USERNAME = "username";
        public static final String COLUMN_NAME_EMAIL = "email";


        public static final String CREATE_TABLE = "CREATE TABLE " +
                TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY," +
                COLUMN_NAME_ID + TEXT_TYPE + COMMA_SEP +
                COLUMN_NAME_CLASS_ID + TEXT_TYPE + COMMA_SEP +
                COLUMN_NAME_FIRST_NAME + TEXT_TYPE + COMMA_SEP +
                COLUMN_NAME_LAST_NAME + TEXT_TYPE + COMMA_SEP +
                COLUMN_NAME_USERNAME + TEXT_TYPE + COMMA_SEP +
                COLUMN_NAME_EMAIL + TEXT_TYPE + " )";

        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    public static abstract class Teachers implements BaseColumns {
        public static final String TABLE_NAME = "teachers";

        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_FIRST_NAME = "first_name";
        public static final String COLUMN_NAME_LAST_NAME = "last_name";

        public static final String CREATE_TABLE = "CREATE TABLE " +
                TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY," +
                COLUMN_NAME_ID + TEXT_TYPE + COMMA_SEP +
                COLUMN_NAME_FIRST_NAME + TEXT_TYPE + COMMA_SEP +
                COLUMN_NAME_LAST_NAME + TEXT_TYPE + " )";

        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    public static abstract class Subjects implements BaseColumns {
        public static final String TABLE_NAME = "subjects";

        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_NAME = "name";

        public static final String CREATE_TABLE = "CREATE TABLE " +
                TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY," +
                COLUMN_NAME_ID + TEXT_TYPE + COMMA_SEP +
                COLUMN_NAME_NAME + TEXT_TYPE + " )";

        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    public static abstract class Grades implements BaseColumns {

        public static final String TABLE_NAME = "grades";

        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_GRADE = "grade";
        public static final String COLUMN_NAME_SUBJECT_ID = "subject_id";
        public static final String COLUMN_NAME_LESSON_ID = "lesson_id";
        public static final String COLUMN_NAME_CATEGORY_ID = "category_id";
        public static final String COLUMN_NAME_COMMENT_ID = "comment_id";
        public static final String COLUMN_NAME_ADDED_BY_ID = "added_by_id";
        public static final String COLUMN_NAME_SEMESTER = "semester";
        public static final String COLUMN_NAME_DATE = "date";
        public static final String COLUMN_NAME_ADD_DATE = "add_date";
        public static final String COLUMN_NAME_TYPE = "type";

        public static final String CREATE_TABLE = "CREATE TABLE " +
                TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY," +
                COLUMN_NAME_ID + TEXT_TYPE + NOT_NULL + COMMA_SEP +
                COLUMN_NAME_SUBJECT_ID + TEXT_TYPE + COMMA_SEP +
                COLUMN_NAME_LESSON_ID + TEXT_TYPE + COMMA_SEP +
                COLUMN_NAME_CATEGORY_ID + TEXT_TYPE + COMMA_SEP +
                COLUMN_NAME_COMMENT_ID + TEXT_TYPE + COMMA_SEP +
                COLUMN_NAME_ADDED_BY_ID + TEXT_TYPE + COMMA_SEP +
                COLUMN_NAME_SEMESTER + INTEGER_TYPE + COMMA_SEP +
                COLUMN_NAME_DATE + INTEGER_TYPE + COMMA_SEP +
                COLUMN_NAME_ADD_DATE + INTEGER_TYPE + COMMA_SEP +
                COLUMN_NAME_TYPE + INTEGER_TYPE + COMMA_SEP +
                COLUMN_NAME_GRADE + TEXT_TYPE + " )";

        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    public static abstract class GradeCategories implements BaseColumns {
        public static final String TABLE_NAME = "grade_categories";

        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_WEIGHT = "weight";

        public static final String CREATE_TABLE = "CREATE TABLE " +
                TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY," +
                COLUMN_NAME_ID + TEXT_TYPE + NOT_NULL + COMMA_SEP +
                COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP +
                COLUMN_NAME_WEIGHT + INTEGER_TYPE + " )";

        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    }
}