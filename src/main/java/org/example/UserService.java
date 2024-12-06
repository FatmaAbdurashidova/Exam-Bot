package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserService {
    private List<User> userList = new ArrayList<>();
    private MyBot myBot;

    public UserService(MyBot myBot) {
        this.myBot = myBot;
    }

    public User getUserByChatId(Long chatId) {
        Optional<User> optionalUser = userList.stream()
                .filter(u -> u.getChatId() == chatId)
                .findFirst();

        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }
        return create(chatId);
    }

    public User create(Long chatId) {
        User user = User.builder()
                .chatId(chatId)
                .userStep(UserStep.REGISTERED)
                .build();
        userList.add(user);
        return user;
    }
}
