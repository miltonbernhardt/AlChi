package enums;

public enum TipoPaquete {
	G100("100g", 0.10f),
	G250("250g", 0.25f),
	G500("500g", 0.50f),
	G1000("1000g", 1f),
	G2000("2000g", 2f),
	UNIDAD("Unidad", 1f);

	private String value;
	private Float cantidad;
	
	TipoPaquete(String value) {
		this.value = value;
        this.cantidad = null;
    }

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

