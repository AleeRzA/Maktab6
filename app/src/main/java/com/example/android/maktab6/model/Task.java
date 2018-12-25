package com.example.android.maktab6.model;

import java.util.Date;
import java.util.UUID;

/**
 * Here is the Task class which has four fields
 * Title, Description, Date and an unique Id...
 */
public class Task {
    private String mTitle;
    private String mDescription;
    private Date mDate;
    private UUID mId;

    public Task( ){
        mId = UUID.randomUUID();
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public UUID getId() {
        return mId;
    }

}
