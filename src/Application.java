
public class Application {

    public static void main(String[] args) {
        Company company = new Company("12 University Ave., Toronto", "Impark");
        ParkingLot parkingLot = new ParkingLot("10 Dundas Street, Toronto", company);
        User user = new User("Johnny", "Cao", 27, "m", "110 Univerisity Ave., Toronto", "416-888-8888");
        Car car = new Car(user, "W123456789", "TRT110", "BMW", "white", "compact");
        
        try {
            long token = parkingLot.park(ParkingSpaceType.REGULAR, car);
            int charge = parkingLot.unPark(token);
            System.out.println(charge);
        } catch (NotEnoughParkingSpaceException e) {
            e.printStackTrace();
        }
    }
}
