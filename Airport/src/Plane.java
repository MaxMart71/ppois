public class Plane {
    private String model;
    private int airlinesId;

    private int amountOfAvailablePlaces;
    public Plane(String model, int airlinesId, int amountOfAvailablePlaces){
        this.model = model;
        this.airlinesId = airlinesId;
        this.amountOfAvailablePlaces = amountOfAvailablePlaces;
    }

    public String getModel() {
        return model;
    }


    public int getAirlines_id() {
        return airlinesId;
    }


    public int getAmountOfPlaces() {
        return amountOfAvailablePlaces;
    }

}
