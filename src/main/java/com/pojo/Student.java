package com.pojo;

public class Student {
    private Integer studentId;
    private String name;
    private String gender;
    private String ethnicity;
    private String major;
    private String group;
    private String contact;
    private Integer dormitoryId;
    private Integer roomId;
    private String dormitoryName;
    private String roomNumber;

    public Integer getStudentId() {
        return studentId;
    }

    public String getDormitoryName() {
        return dormitoryName;
    }

    public void setDormitoryName(String dormitoryName) {
        this.dormitoryName = dormitoryName;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEthnicity() {
        return ethnicity;
    }

    public void setEthnicity(String ethnicity) {
        this.ethnicity = ethnicity;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Integer getDormitoryId() {
        return dormitoryId;
    }

    public void setDormitoryId(Integer dormitoryId) {
        this.dormitoryId = dormitoryId;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", ethnicity='" + ethnicity + '\'' +
                ", major='" + major + '\'' +
                ", group='" + group + '\'' +
                ", contact='" + contact + '\'' +
                ", dormitoryId=" + dormitoryId +
                ", roomId=" + roomId +
                ", dormitoryName='" + dormitoryName + '\'' +
                ", roomNumber='" + roomNumber + '\'' +
                '}';
    }
}
