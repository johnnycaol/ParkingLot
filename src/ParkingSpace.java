abstract public class ParkingSpace {
    protected int id;
    protected int lotId;
    protected double distanceToEntrance;
    protected boolean isOccupied;
    protected ParkingSpaceType type;
    protected Car car;

    public ParkingSpace(int id, int lotId, double distanceToEntrance) {
        this.id = id;
        this.lotId = lotId;
        this.distanceToEntrance = distanceToEntrance;
        this.isOccupied = false;
        this.car = null;
    }

    public int getId() {
        return id;
    }

    public double getDistanceToEntrance() {
        return distanceToEntrance;
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
