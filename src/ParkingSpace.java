public abstract class ParkingSpace {
    protected int id;
    protected boolean isOccupied;
    protected ParkingLot parkingLot;
    protected ParkingSpaceType type;

    public ParkingSpace(int id, ParkingLot parkingLot) {
        this.id = id;
        this.parkingLot = parkingLot;
        this.isOccupied = false;
        this.type = null;
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

    public void park() {
        isOccupied = true;
    }

    public void unPark() {
        isOccupied = false;
    }
}
