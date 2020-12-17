package de.ir31k0.nonogramsolver.data;

/**
 * Representation of a field of the nonogram.
 */
public enum Field {
    /** Field is unknown */
    UNKNOWN("_"),

    /** Field is filled */
    FILLED("■"),

    /** Field must be empty */
    EMPTY("");

    /** Visual character of field */
    private final String character;

    Field(String character) {
        this.character = character;
    }

    /**
     * Gets the visual character representation of field.
     *
     * @return the character as string
     */
    public String getCharacter() {
        return character;
    }
}
