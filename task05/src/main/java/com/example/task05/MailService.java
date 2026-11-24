package com.example.task05;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class MailService<T> implements Consumer<Mail<T>> {
    private final Map<String, List<T>> mailBox = new HashMap<>() {
        @Override
        public List<T> get(Object recipient) {
            return getOrDefault(recipient, List.of());
        }
    };

    @Override
    public void accept(Mail<T> mail) {
        List<T> messages = mailBox.computeIfAbsent(mail.getTo(), recipient -> new ArrayList<>());
        messages.add(mail.getContent());
    }

    public Map<String, List<T>> getMailBox() {
        return mailBox;
    }
}
