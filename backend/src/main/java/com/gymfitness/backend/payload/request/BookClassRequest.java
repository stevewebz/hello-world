package com.gymfitness.backend.payload.request;

import javax.validation.constraints.NotNull;

public class BookClassRequest {
    private String email;

    @NotNull(message = "Please provide class id")
    private Long classid;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getClassid() {
        return classid;
    }

    public void setClassid(Long classid) {
        this.classid = classid;
    }
}