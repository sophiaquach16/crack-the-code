package org.springframework.boot.crackthecode.models;

import java.time.LocalDateTime;

public class Game {

    private String answer;
    private String status;
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAnswer() {
        return answer;
    }

    public String getStatus() {
        return status;
    }

    public int getId() {
        return id;
    }

}
