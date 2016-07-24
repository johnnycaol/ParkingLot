abstract public class ParkingSpace {
    protected int id;
    protected ParkingLot parkingLot;
    protected boolean isOccupied;
    protected ParkingSpaceType type;
    protected Car car;

    public ParkingSpace(int id, ParkingLot parkingLot) {
        this.id = id;
        this.parkingLot = parkingLot;
        this.isOccupied = false;
        this.car = null;
    }

    public int getId() {
        return id;
    }

    public boolean isOccupied() {
        return isOccupied;
    }
    
    public ParkingSpaceType getType() {
        return type;
    }

    public void park(Car car) {
        isOccupied = true;
        this.car = car;
    }

    public void unPark() {
        isOccupied = false;
        this.car = null;
    }
}
