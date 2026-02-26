package csd214.bookstore.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.util.Objects;

@Entity
public class ElectronicsEntity extends ProductEntity {
    @Column(name = "warranty_months", nullable = false)
    private int warrantyMonths;

    @Column(name = "brand")
    private String brand;

    @Column(name = "copies", nullable = false)
    private int copies;

    public ElectronicsEntity() { }

    public int getWarrantyMonths() {
        return warrantyMonths;
    }

    public void setWarrantyMonths(int warrantyMonths) {
        this.warrantyMonths = warrantyMonths;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    public ElectronicsEntity(String brand, int warrantyMonths, int copies) {
        this.warrantyMonths = warrantyMonths;
        this.brand = brand;
        this.copies = copies;
    }

    public ElectronicsEntity(String name, double price, int warrantyMonths, String brand, int copies) {
        super(name, price);
        this.warrantyMonths = warrantyMonths;
        this.brand = brand;
        this.copies = copies;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ElectronicsEntity that = (ElectronicsEntity) o;
        return warrantyMonths == that.warrantyMonths && copies == that.copies && Objects.equals(brand, that.brand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), warrantyMonths, brand, copies);
    }

    @Override
    public String toString() {
        return "ElectronicsEntity{" +
                "warrantyMonths=" + warrantyMonths +
                ", brand='" + brand + '\'' +
                ", copies=" + copies +
                '}';
    }
}