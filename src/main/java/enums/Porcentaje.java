package enums;

public enum Porcentaje {
	NORMAL(1.33f),
	COMBO(1.20f);

	private Float value;

    private Porcentaje(Float value) {
        this.value = value;
    }

    public Float getValue() {
        return value;
    }
}
