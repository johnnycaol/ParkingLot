public class Car {
    private Integer userId;
    private String vin;
    private String plate;
    private String brand;
    private String colour;
    private String size;

    public Car(Integer userId, String vin, String plate, String brand, String colour, String size) {
        this.userId = userId;
        this.plate = plate;
        this.brand = brand;
        this.colour = colour;
        this.size = size;
    }
}
