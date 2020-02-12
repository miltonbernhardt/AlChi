package enums;

public enum TipoPaquete {
	G100("100g"),
	G250("250g"),
	G500("500g"),
	G1000("1000g"),
	G2000("2000g"),
	UNIDAD("Unidad");

	private String value;

    private TipoPaquete(String value) {
        this.value = value;
    }

    private String getValue() {
        return value;
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

