package org.example;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private long chatId;
    private String telegramId;
    private String name;
    private List<Goal> goals;
    private Role role;
    private UserStep userStep;
}
