import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ParkingLot {
    private static final int NUM_REGULAR_SPACE = 20;
    private static final int NUM_HANDICAPPED_SPACE = 5;
    private static final int NUM_COMPACT_SPACE = 10;
    private static final int REGULAR_PRICE = 500;//500 cents or $5 per hour
    private static final int HADNDICAPPED_PRICE = 500;
    private static final int COMPACT_PRICE = 300;

    private List<ParkingSpace> regularSpaces;
    private List<ParkingSpace> handicappedSpaces;
    private List<ParkingSpace> compactSpaces;

    private Company company;
    private String address;
    private boolean isFull;
    public Map<Long, Transaction> transactions;

    public ParkingLot(String address, Company company) {
        this.company = company;
        this.address = address;
        regularSpaces = new ArrayList<ParkingSpace>(NUM_REGULAR_SPACE);
        handicappedSpaces = new ArrayList<ParkingSpace>(NUM_HANDICAPPED_SPACE);
        compactSpaces = new ArrayList<ParkingSpace>(NUM_COMPACT_SPACE);
        createSpaces();
        transactions = new HashMap<Long, Transaction>();
    }

    private void createSpaces() {
        for (int i = 1; i <= NUM_REGULAR_SPACE; i++) {
            // Assume parking space are ordered by distance to the entrance
            regularSpaces.add(new RegularParkingSpace(i, this));
        }
        for (int i = 1; i <= NUM_HANDICAPPED_SPACE; i++) {
            handicappedSpaces.add(new HandicappedParkingSpace(i, this));
        }
        for (int i = 1; i <= NUM_COMPACT_SPACE; i++) {
            compactSpaces.add(new CompactParkingSpace(i, this));
        }
    }
    
    private ParkingSpace getFirstVacantSpace(ParkingSpaceType type) {
        Iterator<ParkingSpace> itrator;
        switch(type) {
            case REGULAR:
                itrator = regularSpaces.iterator();
                break;
            case HANDICAPPED:
                itrator = handicappedSpaces.iterator();
                break;
            case COMPACT:
                itrator = compactSpaces.iterator();
                break;
            default:
                throw new IllegalArgumentException();
        }
            
        while(itrator.hasNext()) {
            ParkingSpace parkingSpace = itrator.next();
            if (!parkingSpace.isOccupied()) {
                return parkingSpace;
            }
        }
        
        return null;
    }
    
    //return a token
    public long park(ParkingSpaceType type, Car car) throws NotEnoughParkingSpaceException {
        if (isFull()) {
            throw new NotEnoughParkingSpaceException();
        }
        
        ParkingSpace parkingSpace = getFirstVacantSpace(type);
        
        if (parkingSpace != null) {
            parkingSpace.park();
            //TODO: update isFull variable
            Transaction transaction = new Transaction(parkingSpace, car, new Date());
            long token = car.hashCode() * 43;//TODO: how to generate unique token
            transactions.put(token, transaction);
            return token;
        }
        
        throw new NotEnoughParkingSpaceException();
    }
    
    //return a charge
    public int unPark(long token) {
        // Get the transaction
        Transaction transaction = transactions.get(token);
        transaction.setEndTime(new Date());
        
        // Get start and end time
        Date startTime = transaction.getStartTime();
        Date endTime = transaction.getEndTime();
        
        System.out.println(startTime);
        
        // Calculate the charge based on start and end time
        int charge = calculateCharge(startTime, endTime);
        transaction.setCharge(charge);//this can be saved to database
        
        // Final clean up so that this parking space is available again
        transactions.remove(token);
        ParkingSpace parkingSpace = transaction.getParkingSpace();
        parkingSpace.unPark();
        return charge;
    }
    
    // Algorithm to calculate charge
    public int calculateCharge(Date startTime, Date endTime) {
        return 0;
    }
    
    public Boolean isFull() {
        return isFull;
    }
    
    public Company getCompany() {
        return company;
    }
    
    public String getAddress() {
        return address;
    }
}
