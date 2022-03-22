package com.mitskevich.task2.entity;

public class PackageOfMedicine {
    private String type;
    private int count;
    private double price;

    public PackageOfMedicine() {
    }

    public PackageOfMedicine(String type, int count, double price) {
        this.type = type;
        this.count = count;
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PackageOfMedicine that = (PackageOfMedicine) o;
        if (count != that.count) {
            return false;
        }
        if (Double.compare(that.price, price) != 0) {
            return false;
        }
        return type != null ? type.equals(that.type) : that.type == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = type != null ? type.hashCode() : 0;
        result = 31 * result + count;
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PackageOfMedicine{");
        sb.append("type='").append(type).append('\'');
        sb.append(", count=").append(count);
        sb.append(", price=").append(price);
        sb.append('}');
        return sb.toString();
    }
}