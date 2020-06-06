/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author bhavik
 */
public class Redeems {
    private int BusinessID;
    private String BusinessName;
    private Long BankAccountNo;
    private String IFSC;
    private String BankName;
    private String ReservationNo;
    private String CustomerCareNo;
    private String EmailID;
    private Long RedeemAmount;
    
    public Redeems(Object[] columns) {
        this.BusinessID = (int) columns[0];
        this.BusinessName = (String) columns[1];
        this.BankAccountNo = (Long) columns[2];
        this.IFSC = (String) columns[3];
        this.BankName = (String) columns[4];
        this.ReservationNo = (String) columns[5];
        this.CustomerCareNo = (String) columns[6];
        this.EmailID = (String) columns[7];
        this.RedeemAmount = (Long) columns[8];
    }
    public Redeems() {
        
    }

    public int getBusinessID() {
        return BusinessID;
    }

    public void setBusinessID(int BusinessID) {
        this.BusinessID = BusinessID;
    }

    public String getBusinessName() {
        return BusinessName;
    }

    public void setBusinessName(String BusinessName) {
        this.BusinessName = BusinessName;
    }

    public Long getBankAccountNo() {
        return BankAccountNo;
    }

    public void setBankAccountNo(Long BankAccountNo) {
        this.BankAccountNo = BankAccountNo;
    }

    public String getIFSC() {
        return IFSC;
    }

    public void setIFSC(String IFSC) {
        this.IFSC = IFSC;
    }

    public String getBankName() {
        return BankName;
    }

    public void setBankName(String BankName) {
        this.BankName = BankName;
    }

    public String getReservationNo() {
        return ReservationNo;
    }

    public void setReservationNo(String ReservationNo) {
        this.ReservationNo = ReservationNo;
    }

    public String getCustomerCareNo() {
        return CustomerCareNo;
    }

    public void setCustomerCareNo(String CustomerCareNo) {
        this.CustomerCareNo = CustomerCareNo;
    }

    public String getEmailID() {
        return EmailID;
    }

    public void setEmailID(String EmailID) {
        this.EmailID = EmailID;
    }

    public Long getRedeemAmount() {
        return RedeemAmount;
    }

    public void setRedeemAmount(Long RedeemAmount) {
        this.RedeemAmount = RedeemAmount;
    }
}
