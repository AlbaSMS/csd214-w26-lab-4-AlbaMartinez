package csd214.bookstore.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.util.Objects;

@Entity
public class VehicleEntity extends ProductEntity {
    @Column(name = "make")
    private String make;

    @Column(name = "model")
    private String model;

    @Column(name = "year", nullable = false)
    private int year;

    @Column(name = "mileage", nullable = false)
    private int mileage;

    public VehicleEntity() {}

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public VehicleEntity(String make, int year, int mileage, String model) {
        this.make = make;
        this.year = year;
        this.mileage = mileage;
        this.model = model;
    }

    public VehicleEntity(String name, double price, String make, int mileage, int year, String model) {
        super(name, price);
        this.make = make;
        this.mileage = mileage;
        this.year = year;
        this.model = model;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        VehicleEntity that = (VehicleEntity) o;
        return year == that.year && mileage == that.mileage && Objects.equals(make, that.make) && Objects.equals(model, that.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), make, model, year, mileage);
    }

    @Override
    public String toString() {
        return "VehicleEntity{" +
                "make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", mileage=" + mileage +
                '}';
    }
}