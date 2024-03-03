package com.digitalfuture.vacancy.obj;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Vacancy {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String description;
    private String positionName;
    private int salaryRate;
    private String workExperience;
    private String city;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vacancy vacancy = (Vacancy) o;
        return salaryRate == vacancy.salaryRate && Objects.equals(name, vacancy.name) && Objects.equals(description, vacancy.description) && Objects.equals(positionName, vacancy.positionName) && Objects.equals(workExperience, vacancy.workExperience) && Objects.equals(city, vacancy.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, positionName, salaryRate, workExperience, city);
    }
}
