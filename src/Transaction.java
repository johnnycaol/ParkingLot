import java.util.Date;

public class Transaction {
    private ParkingSpace parkingSpace;
    private Car car;
    private Date startTime;
    private Date endTime;
    private int charge;
    
    public Transaction(ParkingSpace parkingSpace, Car car, Date startTime) {
        this.parkingSpace = parkingSpace;
        this.car = car;
        this.startTime = startTime;
        this.endTime = null;
        this.charge = 0;
    }
    
    public ParkingSpace getParkingSpace() {
        return parkingSpace;
    }
    
    public Car getCar() {
        return car;
    }
    
    public Date getStartTime() {
        return startTime;
    }
    
    public Date getEndTime() {
        return endTime;
    }
    
    public int getCharge() {
        return charge;
    }
    
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
    
    public void setCharge(int charge) {
        this.charge = charge;
    }
}
