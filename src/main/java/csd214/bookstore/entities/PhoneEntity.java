package csd214.bookstore.entities;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.util.Objects;

@Entity
@DiscriminatorValue("PHONE")
public class PhoneEntity extends ElectronicsEntity {
    @Column(name = "supports_5_g", nullable = false)
    private boolean supports5G;

    @Column(name = "price", nullable = false)
    private double price;

    public PhoneEntity() {
        super();
    }

    public boolean getSupports5G() {
        return supports5G;
    }

    public void setSupports5G(boolean supports5G) { this.supports5G = supports5G; }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public PhoneEntity(String brand, int warrantyMonths, boolean supports5G, double price, int copies) {
        super(brand, warrantyMonths, copies);
        this.supports5G = supports5G;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PhoneEntity that = (PhoneEntity) o;
        return supports5G == that.supports5G && Double.compare(price, that.price) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), supports5G, price);
    }

    @Override
    public String toString() {
        return "PhoneEntity{" +
                "supports5G=" + supports5G +
                ", price=" + price +
                '}';
    }
}