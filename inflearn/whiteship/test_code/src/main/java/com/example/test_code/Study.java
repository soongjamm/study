package com.example.test_code;

public class Study {

    private StudyStatus status;
    private int limit;
    private String name;
    private Member owner;

    public Study(int limit) {
        if (limit < 0) {
            throw new IllegalArgumentException("limit은 0보다 커야한다.");
        }
        this.limit = limit;
    }

    public Study(int limit, String name) {
        if (limit < 0) {
            throw new IllegalArgumentException("limit은 0보다 커야한다.");
        }
        this.limit = limit;
        this.name = name;
    }

    public Member getOwner() {
        return owner;
    }

    public StudyStatus getStatus() {
        return this.status;
    }

    public int getLimit() {
        return limit;
    }

    public String getName() {
        return name;
    }

    public void setOwner(Member owner) {
        this.owner = owner;
    }
}
