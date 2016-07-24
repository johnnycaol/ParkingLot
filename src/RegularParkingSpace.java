
public class RegularParkingSpace extends ParkingSpace {

    public RegularParkingSpace(int id, ParkingLot parkingLot) {
        super(id, parkingLot);
        type = ParkingSpaceType.REGULAR;
    }

}
