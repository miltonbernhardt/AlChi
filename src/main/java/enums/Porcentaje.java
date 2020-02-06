package enums;

public enum Porcentaje {
	//TODO fijarse si no conviene calcular directamente los precios con estos porcentajes en vez de usar precios fijados
	NORMAL(33f),
	COMBO(20f);

	private Float value;

    private Porcentaje(Float value) {
        this.value = value;
    }

    public Float getValue() {
        return value;
    }
}
