public class Car {
    private User user;
    private String vin;
    private String plate;
    private String brand;
    private String colour;
    private String size;

    public Car(User user, String vin, String plate, String brand, String colour, String size) {
        this.user = user;
        this.plate = plate;
        this.brand = brand;
        this.colour = colour;
        this.size = size;
    }
    
    public User getUser() {
        return user;
    }
    
    public String getVin() {
        return vin;
    }
    
    public String getPlate() {
        return plate;
    }
    
    public String getBrand() {
        return brand;
    }
    
    public String getColour() {
        return colour;
    }
    
    public String getSize() {
        return size;
    }
}
