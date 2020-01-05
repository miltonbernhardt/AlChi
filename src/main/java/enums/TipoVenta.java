package enums;

public enum TipoVenta {
	G100("100g"),
	G250("250g"),
	G500("500g"),
	G1000("1000g"),
	UNIDAD("Unidad"),
	COMBO("Combo");

	private String value;

    private TipoVenta(String value) {
        this.value = value;
    }

    private String getValue() {
        return value;
    }	

    @Override
    public String toString() {
        return this.getValue();
    }

    public static TipoVenta getEnum(String value) {
        for(TipoVenta v : values()) {
            if(v.getValue().equalsIgnoreCase(value)) return v;
        }
        return null;
    }
}

