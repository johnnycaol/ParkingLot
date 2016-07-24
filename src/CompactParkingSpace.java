
public class CompactParkingSpace extends ParkingSpace {

    public CompactParkingSpace(int id, ParkingLot parkingLot) {
        super(id, parkingLot);
        type = ParkingSpaceType.COMPACT;
    }

}
