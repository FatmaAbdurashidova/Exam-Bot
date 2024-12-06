package org.example;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GoalService {
    List<Goal> goalList = new ArrayList<>();
    private MyBot myBot;

    public GoalService(MyBot myBot) {
        this.myBot = myBot;
    }

    public boolean addGoal(long chatId, String message) {
        String[] words = message.split(",");
        String title = words[0];
        LocalDateTime deadLine = LocalDateTime.now();
        String description = words[2];

        Goal goal = Goal.builder()
                .title(title)
                .deadline(deadLine)
                .description(description)
                .chatId(chatId)
                .build();
        goalList.add(goal);

        System.out.println(goalList);

        return true;
    }

    public boolean updateGoal(long chatId, String message) {
        String[] words = message.split(":");
        String title = words[0];
        String newDeadline = words[1];
        String newDescription = words[2];

        for (Goal goal : goalList) {
            if (goal.getTitle().equals(title)) {
                goal.setDeadline(LocalDateTime.parse(newDeadline));
                goal.setDescription(newDescription);

                System.out.println("Goal updated: " + goal);
                return true;
            }
        }
        return true;
    }

    public void deleteGoal(long chatId, String message) {

        for (Goal goal : goalList) {
            if (goal.getTitle().equals(message)) {
                goalList.remove(goal);
                System.out.println("Goal deleted: " + goal);
                break;
            }
        }
    }

    public String viewAllGoals() {
        return String.valueOf(goalList);
    }
}
