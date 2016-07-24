
public class HandicappedParkingSpace extends ParkingSpace {

    public HandicappedParkingSpace(int id, int lotId, double distanceToEntrance) {
        super(id, lotId, distanceToEntrance);
        type = ParkingSpaceType.HANDICAPPED;
    }

}
