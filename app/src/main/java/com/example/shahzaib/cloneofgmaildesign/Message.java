package com.example.shahzaib.cloneofgmaildesign;


public class Message {

    private  String messageFrom, messageSubject, messageBody, messageTime;
    private  int profilePicture;
    private  boolean isUserHasPicture;
    private  boolean isRead;
    private  boolean isStared;
    private  boolean isMessageSelected;



    public Message()
    {
        messageFrom = null;
        messageSubject = null;
        messageBody = null;
        messageTime = null;
        profilePicture = -1;

        isRead = false;
        isStared = false;
        isUserHasPicture = false;
        isMessageSelected = false;
    }



    public String getMessageFrom() {
        return messageFrom;
    }

    public void setMessageFrom(String messageFrom) {
        this.messageFrom = messageFrom;
    }

    public String getMessageSubject() {
        return messageSubject;
    }

    public void setMessageSubject(String messageSubject) {
        this.messageSubject = messageSubject;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public String getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(String messageTime) {
        this.messageTime = messageTime;
    }


    public int getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(int profilePicture) {
        this.profilePicture = profilePicture;
        isUserHasPicture  = true;
    }

    public boolean isUserHasPicture() {
        return isUserHasPicture;
    }




    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public boolean isStared() {
        return isStared;
    }

    public void setStared(boolean stared) {
        isStared = stared;
    }


    public boolean isMessageSelected(){
        return isMessageSelected;
    }

    public void setMessageSelected(boolean messageSelected) {
        isMessageSelected = messageSelected;
    }
}
