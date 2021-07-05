package com.example.labinfomanagement;

public class modelStudent {
    String usn,name,attendance;
    int id;

    public modelStudent(String usn, String name, String attendance, int id) {
        this.usn = usn;
        this.name = name;
        this.attendance = attendance;
        this.id = id;
    }

    public String getUsn() {
        return usn;
    }


    public String getName() {
        return name;
    }

    public String getAttendance() {
        return attendance;
    }

    public int getId() {
        return id;
    }
}
