package enums;

public enum Categoria {
	GRANOLA("Granola"),
	ALMOHADITAS("Almohaditas"),
	CEREALES("Cereales"),
	FRUTOS_SECOS_SUELTOS_Y_SEMILLAS("Frutos secos, sueltos y semillas"),
	MIXES_ECONOMICOS("Mixes económicos"),
	FRUTAS_DESHIDRATADAS("Frutas deshidratadas"),
	VARIOS("Varios");

	private String value;

    private Categoria(String value) {
        this.value = value;
    }

    private String getValue() {
        return value;
    }	

    @Override
    public String toString() {
        return this.getValue();
    }

    public static Categoria getEnum(String value) {
        for(Categoria v : values()) {
            if(v.getValue().equalsIgnoreCase(value)) return v;
        }
        return null;
    }
}
