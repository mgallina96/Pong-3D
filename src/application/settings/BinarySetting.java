package application.settings;

/**
 * A setting that can only be on or off.
 *
 * @author Manuel Gallina
 */
public enum BinarySetting {
    ON("ON"), OFF("OFF");

    private String text;

    /** Constructor. */
    BinarySetting(String text) {
        this.text = text;
    }

    /** @return The label text. */
    public String getText() {
        return text;
    }

    /** @return ON if it's off, OFF if it's on. */
    public BinarySetting switchValue() {
        switch (this) {
            case ON:
                return OFF;
            case OFF:
                return ON;
            default:
                return this;
        }
    }
}
