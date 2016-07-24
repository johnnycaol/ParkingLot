
public class CompactParkingSpace extends ParkingSpace {

    public CompactParkingSpace(int id, int lotId, double distanceToEntrance) {
        super(id, lotId, distanceToEntrance);
        type = ParkingSpaceType.COMPACT;
    }

}
