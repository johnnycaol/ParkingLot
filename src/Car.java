public class Car {
    private User user;
    private String vin;
    private String plate;
    private String make;
    private String model;
    private String colour;

    public Car(User user, String vin, String plate, String make, String model, String colour) {
        this.user = user;
        this.plate = plate;
        this.make = make;
        this.model = model;
        this.colour = colour;
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
    
    public String getMake() {
        return make;
    }
    
    public String getModel() {
        return model;
    }
    
    public String getColour() {
        return colour;
    }
}
