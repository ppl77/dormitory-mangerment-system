package com.pojo;


public class Dormitory {
    private Integer dormitoryId;
    private String dormitoryName;
    private Integer floors;
    private Integer rooms;
    private String startDate;

    public Integer getDormitoryId() {
        return dormitoryId;
    }

    public void setDormitoryId(Integer dormitoryId) {
        this.dormitoryId = dormitoryId;
    }

    public String getDormitoryName() {
        return dormitoryName;
    }

    public void setDormitoryName(String buildingNumber) {
        this.dormitoryName = buildingNumber;
    }

    public Integer getFloors() {
        return floors;
    }

    public void setFloors(Integer floors) {
        this.floors = floors;
    }

    public Integer getRooms() {
        return rooms;
    }

    public void setRooms(Integer rooms) {
        this.rooms = rooms;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return "Dormitory{" +
                "dormitoryId=" + dormitoryId +
                ", dormitoryName='" + dormitoryName + '\'' +
                ", floors=" + floors +
                ", rooms=" + rooms +
                ", startDate='" + startDate + '\'' +
                '}';
    }
}
