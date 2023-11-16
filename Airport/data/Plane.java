public class Plane {
    private String model;
    private int airlinesId;

    private int amountOfPlaces;
    private String planeNumber;
    public Plane(String model, int airlinesId, int amountOfPlaces, String planeNumber){
        this.model = model;
        this.airlinesId = airlinesId;
        this.amountOfPlaces = amountOfPlaces;
        this.planeNumber = planeNumber;
    }

    public String getModel() {
        return model;
    }


    public int getAirlines_id() {
        return airlinesId;
    }


    public int getAmountOfPlaces() {
        return amountOfPlaces;
    }

    public String getPlaneNumber() {
        return planeNumber;
    }
}
