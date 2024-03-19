package org.jetbrains.assignment.dto;

public final class MovementDTO {
    private String direction;
    private int steps;

    public String getDirection() {
        return direction;
    }

    public void setDirection(final String direction) {
        this.direction = direction;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(final int steps) {
        this.steps = steps;
    }
}