import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ParkingLot {
    private static final int REGULAR_SPACE_CAPACITY = 20;
    private static final int HANDICAPPED_SPACE_CAPACITY = 5;
    private static final int COMPACT_SPACE_CAPACITY = 10;
    private static final int REGULAR_PRICE = 500;// 500 cents or $5 per hour, use int here instead of double
    private static final int HANDICAPPED_PRICE = 500;
    private static final int COMPACT_PRICE = 300;

    private List<ParkingSpace> regularSpaces;
    private List<ParkingSpace> handicappedSpaces;
    private List<ParkingSpace> compactSpaces;

    private Company company;
    private String address;
    public Map<Long, Transaction> transactions;

    public ParkingLot(String address, Company company) {
        this.company = company;
        this.address = address;
        regularSpaces = new ArrayList<ParkingSpace>(REGULAR_SPACE_CAPACITY);
        handicappedSpaces = new ArrayList<ParkingSpace>(HANDICAPPED_SPACE_CAPACITY);
        compactSpaces = new ArrayList<ParkingSpace>(COMPACT_SPACE_CAPACITY);
        createSpaces();
        transactions = new HashMap<Long, Transaction>();
    }

    private void createSpaces() {
        for (int i = 1; i <= REGULAR_SPACE_CAPACITY; i++) {
            // Assume parking space are ordered by distance to the entrance
            regularSpaces.add(new RegularParkingSpace(i, this));
        }
        for (int i = 1; i <= HANDICAPPED_SPACE_CAPACITY; i++) {
            handicappedSpaces.add(new HandicappedParkingSpace(i, this));
        }
        for (int i = 1; i <= COMPACT_SPACE_CAPACITY; i++) {
            compactSpaces.add(new CompactParkingSpace(i, this));
        }
    }

    private ParkingSpace getFirstVacantSpace(ParkingSpaceType parkingSpaceType) {
        Iterator<ParkingSpace> iterator;
        switch (parkingSpaceType) {
            case REGULAR:
                iterator = regularSpaces.iterator();
                break;
            case HANDICAPPED:
                iterator = handicappedSpaces.iterator();
                break;
            case COMPACT:
                iterator = compactSpaces.iterator();
                break;
            default:
                throw new IllegalArgumentException();
        }

        while (iterator.hasNext()) {
            ParkingSpace parkingSpace = iterator.next();
            if (!parkingSpace.isOccupied()) {
                return parkingSpace;
            }
        }

        return null;
    }
    
    private Boolean isFull() {
        int regularOccupiedCount = 0;
        int handicappedOccupiedCount = 0;
        int compactOccupiedCount = 0;

        for (ParkingSpace parkingSpace : regularSpaces) {
            if (parkingSpace.isOccupied()) {
                regularOccupiedCount += 1;
            }
        }

        for (ParkingSpace parkingSpace : handicappedSpaces) {
            if (parkingSpace.isOccupied()) {
                handicappedOccupiedCount += 1;
            }
        }

        for (ParkingSpace parkingSpace : compactSpaces) {
            if (parkingSpace.isOccupied()) {
                compactOccupiedCount += 1;
            }
        }

        return regularOccupiedCount + handicappedOccupiedCount + compactOccupiedCount >= 
                REGULAR_SPACE_CAPACITY + HANDICAPPED_SPACE_CAPACITY + COMPACT_SPACE_CAPACITY;
    }
    
    // Algorithm to calculate charge
    private int calculateCharge(Date startTime, Date endTime, ParkingSpaceType parkingSpaceType) {
        int price;
        switch (parkingSpaceType) {
            case REGULAR:
                price = REGULAR_PRICE;
                break;
            case HANDICAPPED:
                price = HANDICAPPED_PRICE;
                break;
            case COMPACT:
                price = COMPACT_PRICE;
                break;
            default:
                throw new IllegalArgumentException();
        }
        
        long diffSeconds = getDateDiff(startTime, endTime, TimeUnit.SECONDS);
        int hours = (int) diffSeconds/3600;
        int mod = (int) diffSeconds%3600;
        if (mod > 0) {
            hours += 1;
        }
        
        return price*hours;
    }

    // return a token
    public long park(ParkingSpaceType type, Car car) throws NotEnoughParkingSpaceException {
        if (isFull()) {
            throw new NotEnoughParkingSpaceException();
        }

        ParkingSpace parkingSpace = getFirstVacantSpace(type);

        if (parkingSpace != null) {
            parkingSpace.park();
            Transaction transaction = new Transaction(parkingSpace, car, new Date());
            long token = car.hashCode() * 43;// TODO: how to generate unique token
            transactions.put(token, transaction);
            return token;
        }

        throw new NotEnoughParkingSpaceException();
    }

    // return a charge
    public int unPark(long token) {
        // Get the transaction
        Transaction transaction = transactions.get(token);
        transaction.setEndTime(new Date());
        
        // Get the parking space
        ParkingSpace parkingSpace = transaction.getParkingSpace();

        // Get start and end time
        Date startTime = transaction.getStartTime();
        Date endTime = transaction.getEndTime();

        // Calculate the charge based on start and end time
        int charge = calculateCharge(startTime, endTime, parkingSpace.getType());
        transaction.setCharge(charge);// this can be saved to database

        // Final clean up so that this parking space is available again
        transactions.remove(token);
        parkingSpace.unPark();
        return charge;
    }

    public Company getCompany() {
        return company;
    }

    public String getAddress() {
        return address;
    }
    
    private static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
        long diffInMillies = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
    }
}
