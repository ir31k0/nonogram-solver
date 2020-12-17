package de.ir31k0.nonogramsolver.data;

public enum Field {
    UNKNOWN("_"), FILLED("■"), EMPTY("");

    private final String character;

    Field(String character) {
        this.character = character;
    }

    public String getCharacter() {
        return character;
    }
}
