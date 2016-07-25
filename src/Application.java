import java.util.concurrent.TimeUnit;

public class Application {

    public static void main(String[] args) {
        Company company = new Company("12 University Ave., Toronto", "Impark Inc.");
        ParkingLot parkingLot = new ParkingLot("10 Dundas Street, Toronto", company);
        User user = new User("Johnny", "Cao", 27, "Male", "110 Univerisity Ave., Toronto", "416-888-8888");
        Car car = new Car(user, "W123456789", "TRT110", "BMW", "328xi", "white");
        
        try {
            long token = parkingLot.park(ParkingSpaceType.REGULAR, car);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            int charge = parkingLot.unPark(token);
            System.out.println(charge);
        } catch (NotEnoughParkingSpaceException e) {
            e.printStackTrace();
        }
    }
}
