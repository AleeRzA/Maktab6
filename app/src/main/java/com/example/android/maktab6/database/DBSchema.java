package com.example.android.maktab6.database;

public class DBSchema {
    public static final String NAME = "tasks.db";
    public static final int VERSION = 1;

        public static final class TaskTable{
            public static final String NAME = "task";

            public static final class Columns{
                public static final String UUID = "uuid";
                public static final String TITLE = "title";
                public static final String DATE = "date";
                public static final String DESCRIPTION = "description";
                public static final String DONE = "done";
            }
        }
        public static final class UserTable{

        }
}
