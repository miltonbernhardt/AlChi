package enums;

public enum Porcentaje {
	VENTA(33f);

	private Float value;

    private Porcentaje(Float value) {
        this.value = value;
    }

    public Float getValue() {
        return value;
    }
}
