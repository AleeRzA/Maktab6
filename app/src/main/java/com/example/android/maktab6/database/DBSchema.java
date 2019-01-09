package com.example.android.maktab6.database;

public class DBSchema {
    public static final String NAME = "tasks.db";
    public static final int VERSION = 1;

        public static final class TaskTable{
            public static final String NAME = "task";

            public static final class TaskColumns{
                public static final String UUID = "uuid";
                public static final String TITLE = "title";
                public static final String DATE = "date";
                public static final String DESCRIPTION = "description";
                public static final String DONE = "done";
                public static final String USER_ID = "user_id";
            }
        }
        public static final class UserTable{
                public static final String NAME = "user";

                public static final class UserColumns{
                    public static final String NAME = "name";
                    public static final String EMAIL = "email";
                    public static final String PASSWORD = "password";
                    public static final String UUID = "uuid";
                }
        }
}
