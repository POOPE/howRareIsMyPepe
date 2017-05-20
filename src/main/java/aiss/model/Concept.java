package aiss.model;

public class Concept {

    private String id;
    private String name;
    private String createdAt;
    private String updatedAt;
    private String appID;
    private double value;

    public Concept() {}

    public Concept(String id, String name, String createdAt, String updatedAt, String appID, double value) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.appID = appID;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getAppID() {
        return appID;
    }

    public double getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Concept concept = (Concept) o;

        if (Double.compare(concept.value, value) != 0) return false;
        if (!id.equals(concept.id)) return false;
        if (!name.equals(concept.name)) return false;
        if (createdAt != null ? !createdAt.equals(concept.createdAt) : concept.createdAt != null) return false;
        if (updatedAt != null ? !updatedAt.equals(concept.updatedAt) : concept.updatedAt != null) return false;
        return appID.equals(concept.appID);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        result = 31 * result + appID.hashCode();
        temp = Double.doubleToLongBits(value);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Concept{" +
                "id=" + id +
                ", name=" + name +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", appID=" + appID +
                ", value=" + value +
                '}';
    }
}
