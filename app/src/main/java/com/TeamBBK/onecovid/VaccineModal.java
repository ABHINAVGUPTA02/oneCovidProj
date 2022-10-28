package com.TeamBBK.onecovid;

public class VaccineModal {

    private String vaccineCenter;
    private String vaccinationCharges;
    private String vaccinationAge;
    private String vaccineName;                                                                                               ;
    private String vaccineAvailable;
    private String vaccineCenterTime;
    private String vaccineCenterAddress;
    private String vaccinationTimings;


    public VaccineModal(String vaccineCenter, String vaccinationCharges, String vaccinationAge, String vaccineName, String vaccineAvailable, String vaccineCenterTime, String vaccineCenterAddress, String vaccinationTimings) {
        this.vaccineCenter = vaccineCenter;
        this.vaccinationCharges = vaccinationCharges;
        this.vaccinationAge = vaccinationAge;
        this.vaccineName = vaccineName;
        this.vaccineAvailable = vaccineAvailable;
        this.vaccineCenterTime = vaccineCenterTime;
        this.vaccineCenterAddress = vaccineCenterAddress;
        this.vaccinationTimings = vaccinationTimings;
    }

    public VaccineModal() {
    }

    public String getVaccineCenter() {
        return vaccineCenter;
    }

    public void setVaccineCenter(String vaccineCenter) {
        this.vaccineCenter = vaccineCenter;
    }

    public String getVaccinationCharges() {
        return vaccinationCharges;
    }

    public void setVaccinationCharges(String vaccinationCharges) {
        this.vaccinationCharges = vaccinationCharges;
    }

    public String getVaccinationAge() {
        return vaccinationAge;
    }

    public void setVaccinationAge(String vaccinationAge) {
        this.vaccinationAge = vaccinationAge;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }

    public String getVaccineAvailable() {
        return vaccineAvailable;
    }

    public void setVaccineAvailable(String vaccineAvailable) {
        this.vaccineAvailable = vaccineAvailable;
    }

    public String getVaccineCenterTime() {
        return vaccineCenterTime;
    }

    public void setVaccineCenterTime(String vaccineCenterTime) {
        this.vaccineCenterTime = vaccineCenterTime;
    }

    public String getVaccineCenterAddress() {
        return vaccineCenterAddress;
    }

    public void setVaccineCenterAddress(String vaccineCenterAddress) {
        this.vaccineCenterAddress = vaccineCenterAddress;
    }

    public String getVaccinationTimings() {
        return vaccinationTimings;
    }

    public void setVaccinationTimings(String vaccinationTimings) {
        this.vaccinationTimings = vaccinationTimings;
    }
}
