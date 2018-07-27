package com.eletroinfo.eletroinfo.comparator.notification;

import com.eletroinfo.eletroinfo.comparator.enumeration.TypeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope(value="request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class NotificationHandler {

    @Autowired
    private MessageSourceUtil messageUtil;

    private Enum<TypeMessage> type;
    private List<String> messages = new ArrayList<>();

    public boolean hasNotifications(){
        return getMessages() != null && !getMessages().isEmpty();
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public Enum<TypeMessage> getType() {
        return type;
    }

    public void setType(Enum<TypeMessage> type) {
        this.type = type;
    }

    public void addMessageinternationalized(TypeMessage typeMessage, String key){
        this.type = TypeMessage.message_warn;
        this.getMessages().add(messageUtil.getMessage(key));
    }

    public void getMessage404NotFound(){
        this.type = TypeMessage.message_warn;
        this.getMessages().add(messageUtil.getMessage("sem.registros"));
    }

    public void getMessage400BadRequest(List<String> errors){
        this.type = TypeMessage.message_warn;
        this.getMessages().addAll(errors);
    }

}
