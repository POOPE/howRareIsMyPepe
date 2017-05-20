package aiss.model;

public class Pepe {

    private String url;
    private double rarity;
    private String date;

    public Pepe() {}

    public Pepe(String url, double rarity, String date) {
        this.url = url;
        this.rarity = rarity;
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public double getRarity() {
        return rarity;
    }

    public void setRarity(double rarity) {
        this.rarity = rarity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pepe pepe = (Pepe) o;

        if (Double.compare(pepe.rarity, rarity) != 0) return false;
        if (!url.equals(pepe.url)) return false;
        return date.equals(pepe.date);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = url.hashCode();
        temp = Double.doubleToLongBits(rarity);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + date.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Pepe{" +
                "url=" + url +
                ", rarity=" + rarity +
                ", date=" + date +
                '}';
    }
}
