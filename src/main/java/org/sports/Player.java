package org.sports;

public class Player {
    private final String name;
    private String score;

    public Player(String name) {
        this.name = name;
        score = "0";
    }

    public String name() {
        return name;
    }

    public String score() {
        return score;
    }


    public void updateScore(String score) {
        this.score = score;
    }

}
