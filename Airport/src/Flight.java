import java.security.Timestamp;

public class Flight {
    private int departureAirportId;
    private int arrivalAirportId;
    private int airlinesId;
    private int planeId;
    private String flightName;
    private java.sql.Timestamp departureTime;
    private java.sql.Timestamp arrivalTime;
    public Flight(int arrivalAirportId, int departureAirportId, int airlinesId, int planeId, String flightName, java.sql.Timestamp departureTime, java.sql.Timestamp arrivalTime){
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

    public void setFlightName(String flightName) {
        this.flightName = flightName;
    }

    public int getPlaneId() {
        return planeId;
    }

    public void setPlaneId(int planeId) {
        this.planeId = planeId;
    }


    public int getArrivalAirportId() {
        return arrivalAirportId;
    }

    public void setArrivalAirportId(int arrivalAirportId) {
        this.arrivalAirportId = arrivalAirportId;
    }

    public int getDepartureAirportId() {
        return departureAirportId;
    }

    public void setDepartureAirportId(int departureAirportId) {
        this.departureAirportId = departureAirportId;
    }

    public java.sql.Timestamp getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(java.sql.Timestamp departureTime) {
        this.departureTime = departureTime;
    }

    public java.sql.Timestamp getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(java.sql.Timestamp arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getAirlinesId() {
        return airlinesId;
    }

    public void setAirlinesId(int airlinesId) {
        this.airlinesId = airlinesId;
    }
}
