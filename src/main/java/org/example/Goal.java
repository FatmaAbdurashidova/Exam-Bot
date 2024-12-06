package org.example;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Goal {
    private long chatId;
    private String title;
    private String description;
    private LocalDateTime deadline;
    private boolean completed;
}
