
public class HandicappedParkingSpace extends ParkingSpace {

    public HandicappedParkingSpace(int id, ParkingLot parkingLot) {
        super(id, parkingLot);
        type = ParkingSpaceType.HANDICAPPED;
    }

}
