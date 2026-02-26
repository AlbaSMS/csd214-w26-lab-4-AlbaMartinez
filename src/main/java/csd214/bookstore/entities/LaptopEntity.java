package csd214.bookstore.entities;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.util.Objects;

@Entity
@DiscriminatorValue("LAPTOP")
public class LaptopEntity extends ElectronicsEntity {
    @Column(name = "screen_size_inches", nullable = false)
    private double screenSizeInches;

    @Column(name = "price", nullable = false)
    private double price;

    public LaptopEntity() {
        super();
    }

    public double getScreenSizeInches() {
        return screenSizeInches;
    }

    public void setScreenSizeInches(double screenSizeInches) {
        this.screenSizeInches = screenSizeInches;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LaptopEntity(String brand, int warrantyMonths, double screenSizeInches, double price, int copies) {
        super(brand, warrantyMonths, copies);
        this.screenSizeInches = screenSizeInches;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        LaptopEntity that = (LaptopEntity) o;
        return Double.compare(screenSizeInches, that.screenSizeInches) == 0 && Double.compare(price, that.price) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), screenSizeInches, price);
    }

    @Override
    public String toString() {
        return "LaptopEntity{" +
                "screenSizeInches=" + screenSizeInches +
                ", price=" + price +
                '}';
    }
}