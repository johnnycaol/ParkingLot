import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ParkingLot {
    private static final int NUM_REGULAR_SPACE = 20;
    private static final int NUM_HANDICAPPED_SPACE = 5;
    private static final int NUM_COMPACT_SPACE = 10;
    private static final double REGULAR_PRICE = 5.0;
    private static final double HADNDICAPPED_PRICE = 5.0;
    private static final double COMPACT_PRICE = 3.0;

    private List<ParkingSpace> regularSpaces;
    private List<ParkingSpace> handicappedSpaces;
    private List<ParkingSpace> compactSpaces;

    private int id;
    private int companyId;
    private String address;
    private boolean isFull;

    public ParkingLot(int id, int companyId, String address) {
        this.id = id;
        this.companyId = companyId;
        this.address = address;
        regularSpaces = new ArrayList<ParkingSpace>(NUM_REGULAR_SPACE);
        handicappedSpaces = new ArrayList<ParkingSpace>(NUM_HANDICAPPED_SPACE);
        compactSpaces = new ArrayList<ParkingSpace>(NUM_COMPACT_SPACE);
        createSpaces();//generate parking spaces for this lot
    }

    private void createSpaces() {
        for (int i = 1; i <= NUM_REGULAR_SPACE; i++) {
            // Assume parking space are ordered by distance to the entrance
            regularSpaces.add(new RegularParkingSpace(i, id, i));
        }
        for (int i = 1; i <= NUM_HANDICAPPED_SPACE; i++) {
            handicappedSpaces.add(new HandicappedParkingSpace(i, id, i));
        }
        for (int i = 1; i <= NUM_COMPACT_SPACE; i++) {
            compactSpaces.add(new CompactParkingSpace(i, id, i));
        }
    }
    
    private ParkingSpace getNearestVacantSpace(ParkingSpaceType type) {
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
    
    public void park(ParkingSpaceType type, Car car) throws NotEnoughParkingSpaceException {
        if (isFull()) {
            throw new NotEnoughParkingSpaceException();
        }
        
        ParkingSpace parkingSpace = getNearestVacantSpace(type);
        
        if (parkingSpace != null) {
            parkingSpace.park(car);
            return;
        }
        
        throw new NotEnoughParkingSpaceException();
    }
    
    public Boolean isFull() {
        return isFull;
    }
}
