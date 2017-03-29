package application.settings;

/**
 * The ai difficulty setting.
 *
 * @author Manuel Gallina
 * @author Giosuè Filippini
 */
public enum AiDifficulty {
    EASY(8, 175, "EASY"), NORMAL(10, 165, "NORMAL"), HARD(12, 155, "HARD"), INSANE(15, 140, "INSANE");

    private int speed;
    private int precision;
    private String text;

    /** Constructor. */
    AiDifficulty(int speed, int precision, String text) {
        this.speed = speed;
        this.text = text;
        this.precision = precision;
    }

    /** @return The player speed setting */
    public int getSpeed() {
        return speed;
    }

    /** @return The AI precision. */
    public int getPrecision() {
        return precision;
    }

    /** @return The label text. */
    public String getText() {
        return text;
    }

    /** @return The previous value. */
    public AiDifficulty previous() {
        switch (this) {
            case EASY:
                return INSANE;
            case NORMAL:
                return EASY;
            case HARD:
                return NORMAL;
            case INSANE:
                return HARD;
            default:
                return this;
        }
    }

    /** @return The next value. */
    public AiDifficulty next() {
        switch (this) {
            case EASY:
                return NORMAL;
            case NORMAL:
                return HARD;
            case HARD:
                return INSANE;
            case INSANE:
                return EASY;
            default:
                return this;
        }
    }
}
