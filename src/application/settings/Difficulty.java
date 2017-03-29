package application.settings;

/**
 * The game difficulty setting.
 *
 * @author Manuel Gallina
 * @author Giosuè Filippini
 */
public enum Difficulty {
    EASY(8, "EASY"), NORMAL(12, "NORMAL"), HARD(14, "HARD"), INSANE(20, "INSANE");

    private int speed;
    private String text;

    /** Constructor. */
    Difficulty(int speed, String text) {
        this.speed = speed;
        this.text = text;
    }

    /** @return The ball speed setting */
    public int getSpeed() {
        return speed;
    }

    /** @return The label text. */
    public String getText() {
        return text;
    }

    /** @return The previous value. */
    public Difficulty previous() {
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
    public Difficulty next() {
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
