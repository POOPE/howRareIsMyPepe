package aiss.api.model;

import aiss.model.Pepe;

public class PepeID {

    private String id;
    private Pepe pepe;

    public PepeID() {}

    public PepeID(String id, Pepe pepe) {
        this.id = id;
        this.pepe = pepe;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Pepe getPepe() {
        return pepe;
    }

    public void setPepe(Pepe pepe) {
        this.pepe = pepe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PepeID pepeID = (PepeID) o;

        if (!id.equals(pepeID.id)) return false;
        return pepe.equals(pepeID.pepe);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + pepe.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "PepeID{" +
                "id='" + id + '\'' +
                ", pepe=" + pepe +
                '}';
    }
}
