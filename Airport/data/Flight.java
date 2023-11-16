import java.sql.Timestamp;

public class Flight {
    private final int departureAirportId;
    private final int arrivalAirportId;
    private final int airlinesId;
    private final int planeId;
    private final String flightName;
    private final Timestamp departureTime;
    private final Timestamp arrivalTime;
    public Flight(int arrivalAirportId, int departureAirportId, int airlinesId, int planeId, String flightName, Timestamp departureTime, Timestamp arrivalTime){
        this.arrivalAirportId = arrivalAirportId;
        this.departureAirportId = departureAirportId;
        this.airlinesId = airlinesId;
        this.planeId = planeId;
        this.flightName = flightName;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
    }


    public String getFlightName() {
        return flightName;
    }
    public int getPlaneId() {
        return planeId;
    }
    public int getArrivalAirportId() {
        return arrivalAirportId;
    }
    public int getDepartureAirportId() {
        return departureAirportId;
    }
    public Timestamp getDepartureTime() {
        return departureTime;
    }
    public Timestamp getArrivalTime() {
        return arrivalTime;
    }
    public int getAirlinesId() {
        return airlinesId;
    }

}
