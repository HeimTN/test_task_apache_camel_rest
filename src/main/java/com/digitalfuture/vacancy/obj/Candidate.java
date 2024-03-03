package com.digitalfuture.vacancy.obj;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Candidate {
    @Id
    @GeneratedValue
    private long id;
    private String email;
    private String name;
    private String city;
    private String positionInterest;
    private boolean jobSubscription;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getPositionInterest() {
        return positionInterest;
    }
    public void setPositionInterest(String positionInterest) {
        this.positionInterest = positionInterest;
    }
    public boolean isJobSubscription() {
        return jobSubscription;
    }
    public void setJobSubscription(boolean jobSubscription) {
        this.jobSubscription = jobSubscription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Candidate candidate = (Candidate) o;
        return jobSubscription == candidate.jobSubscription && Objects.equals(email, candidate.email) && Objects.equals(name, candidate.name) && Objects.equals(city, candidate.city) && Objects.equals(positionInterest, candidate.positionInterest);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, name, city, positionInterest, jobSubscription);
    }
}
