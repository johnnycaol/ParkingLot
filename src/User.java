/*
Company(id, name, address)
Lot(id, company_id, address, regular_spot, handicapped_spot, compact_spot, total_spot, regular_price, handicapped_price, compact_price)
User(id, first_name, last_name, sex, age, phone, address)
Car(vin_id, user_id, plate_no, brand, colour, size)
Space(id, lot_id, type, is_occupied)
Transaction(spot_id, car_id, start, end, total_charge)
*/
public class User {
    private String firstName;
    private String lastName;
    private int age;
    private String sex;
    private String address;
    private String phone;

    public User(String firstName, String lastName, int age, String sex, String address, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.sex = sex;
        this.address = address;
        this.phone = phone;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getFullName() {
        return this.firstName + ' ' + this.lastName;
    }

    public int getAge() {
        return this.age;
    }

    public String getSex() {
        return this.sex;
    }

    public String getAddress() {
        return this.address;
    }

    public String getPhone() {
        return this.phone;
    }
}
