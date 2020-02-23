package enums;

public enum TipoPaquete {
	G100("100g", 100f),
	G250("250g", 250f),
	G500("500g", 500f),
	G1000("1000g", 1000f),
	G2000("2000g", 2000f),
	UNIDAD("Unidad", 1f);

	private String value;
	private Float cantidad;

    private TipoPaquete(String value, Float cantidad) {
        this.value = value;
        this.cantidad = cantidad;
    }

    private String getValue() {
        return value;
    }
    
	public Float getCantidad() {
		return cantidad;
	}

    @Override
    public String toString() {
        return this.getValue();
    }

    public static TipoPaquete getEnum(String value) {
        for(TipoPaquete v : values()) {
            if(v.getValue().equalsIgnoreCase(value)) return v;
        }
        return null;
    }


}

