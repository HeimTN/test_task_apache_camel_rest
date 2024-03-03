package com.digitalfuture.vacancy.dto;


public class VacancyDTO {
    private String name;
    private String description;
    private String positionName;
    private int salaryRate;
    private String workExperience;
    private String city;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getPositionName() {
        return positionName;
    }
    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }
    public int getSalaryRate() {
        return salaryRate;
    }
    public void setSalaryRate(int salaryRate) {
        this.salaryRate = salaryRate;
    }
    public String getWorkExperience() {
        return workExperience;
    }
    public void setWorkExperience(String workExperience) {
        this.workExperience = workExperience;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
}
