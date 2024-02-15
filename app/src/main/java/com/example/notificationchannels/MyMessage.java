package com.example.notificationchannels;

import androidx.core.app.Person;

public class MyMessage {

    private CharSequence text;
    private long timestamp;
    private Person sender;

    public MyMessage(CharSequence text, Person sender) {
        this.text = text;
        this.sender = sender;
        timestamp = System.currentTimeMillis();
    }

    public CharSequence getText() {
        return text;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public Person getSender() {
        return sender;
    }
}
