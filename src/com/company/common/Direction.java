package com.company.common;

public enum Direction {
    UP("U"),
    DOWN("D"),
    LEFT("L"),
    RIGHT("R");

    public final String input;

    Direction(String input) {
        this.input = input;
    }

    public String getInput() {
        return input;
    }

    public static Direction getValue(String input) {
        for(Direction direction: Direction.values()) {
            if(direction.input.equalsIgnoreCase(input)) {
                return direction;
            }
        }
        return null;// not found
    }
}
