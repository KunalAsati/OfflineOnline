package tecnodart.com.offlineonline;

public class RationQuota {
    int rice, sugar , wheat , kerosene;

    public RationQuota(int rice, int sugar, int wheat, int kerosene) {
        this.rice = rice;
        this.sugar = sugar;
        this.wheat = wheat;
        this.kerosene = kerosene;
    }

    public RationQuota() {
    }

    public int getRice() {

        return rice;
    }

    public void setRice(int rice) {
        this.rice = rice;
    }

    public int getSugar() {
        return sugar;
    }

    public void setSugar(int sugar) {
        this.sugar = sugar;
    }

    public int getWheat() {
        return wheat;
    }

    public void setWheat(int wheat) {
        this.wheat = wheat;
    }

    public int getKerosene() {
        return kerosene;
    }

    public void setKerosene(int kerosene) {
        this.kerosene = kerosene;
    }
}
