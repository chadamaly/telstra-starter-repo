package au.com.telstra.simcardactivator.dto;

public class Sim {

    private String iccid;
    private String customerEmail;

    public Sim(String iccid, String customerEmail) {
        this.iccid = iccid;
        this.customerEmail = customerEmail;
    }

    public String getIccid() {
        return iccid;
    }

    public void setIccid(String iccid) {
        this.iccid = iccid;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    @Override
    public String toString() {
        return "Sim{" +
                "iccid='" + iccid + '\'' +
                ", customerEmail='" + customerEmail + '\'' +
                '}';
    }

}
