public enum CarTypes {
    ELECTROCAR("electrocar"),
    SPORTCAR("sportcar"),
    SEDAN("sedan"),
    HATCHBACK("hatchback"),
    MINIVAN("minivan"),
    CROSSOVER("crossover");

    String types;

    public void setTypes(String types) {
        this.types = types;
    }

    public String getTypes() {
        return types;
    }

    CarTypes(String types) {
        this.types = types;
    }
}
