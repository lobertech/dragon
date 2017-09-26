package com.tigerjoys.data.dragon.appclient.mock;

import com.tokyohot.shibuya.contract.Conversation;

public abstract class FingerPrintMocker implements QObjectMocker {

    private Conversation conversation;

    public Conversation getConversation() {
        return conversation;
    }

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }

}
