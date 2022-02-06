package com.example.shayan.hotchTest.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.content.Context;
import android.support.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Entity(tableName = "persons")
public class Person {

    @PrimaryKey(autoGenerate = true)
    private int personId;

    @NonNull
    @ColumnInfo(name = "person_name")
    private String personName;

    @NonNull
    @ColumnInfo(name = "person_email")
    private String personEmail;

    @NonNull
    @ColumnInfo(name = "person_interest")
    private String personInterest;

    @NonNull
    @ColumnInfo(name = "date_time")
    private long dateTime;

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    @NonNull
    @ColumnInfo(name = "person_number")
    private String personNumber;

    @NonNull
    @ColumnInfo(name = "isactive")
    private boolean isActive;

    public Person(@NonNull String personName, @NonNull String personEmail, @NonNull String personInterest, long dateTime, String personNumber, boolean isActive) {
        this.personName = personName;
        this.personInterest = personInterest;
        this.dateTime = dateTime;
        this.personNumber = personNumber;
        this.personEmail = personEmail;
        this.isActive = isActive;
    }

    @NonNull
    public String getPersonName() {
        return personName;
    }

    public void setPersonName(@NonNull String personName) {
        this.personName = personName;
    }

    @NonNull
    public String getPersonEmail() {
        return personEmail;
    }

    public void setPersonEmail(@NonNull String personEmail) {
        this.personEmail = personEmail;
    }

    @NonNull
    public String getPersonInterest() {
        return personInterest;
    }

    public void setPersonInterest(@NonNull String personInterest) {
        this.personInterest = personInterest;
    }

    public long getDateTime() {
        return dateTime;
    }

    public void setDateTime(long dateTime) {
        this.dateTime = dateTime;
    }

    public String getPersonNumber() {
        return personNumber;
    }

    public void setPersonNumber(String personNumber) {
        this.personNumber = personNumber;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getDateTimeFormatted(Context context){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss",
                context.getResources().getConfiguration().locale);
        simpleDateFormat.setTimeZone(TimeZone.getDefault());
        return simpleDateFormat.format(new Date(dateTime));
    }
}
