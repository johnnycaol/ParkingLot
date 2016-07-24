
public class RegularParkingSpace extends ParkingSpace {

    public RegularParkingSpace(int id, int lotId, double distanceToEntrance) {
        super(id, lotId, distanceToEntrance);
        type = ParkingSpaceType.REGULAR;
    }

}
