package com.example.labinfomanagement;

public class modelLab {
    String lab_name,lab_slot,lab_class_count;
    int lab_id;

    public modelLab(String lab_name, String lab_slot, String lab_class_count,int lab_id) {
        this.lab_name = lab_name;
        this.lab_slot = lab_slot;
        this.lab_class_count = lab_class_count;
        this.lab_id = lab_id;
    }

    public String getLab_name() {
        return lab_name;
    }

    public int getLab_id() {
        return lab_id;
    }

    public String getLab_slot() {
        return lab_slot;
    }

    public String getLab_class_count() {
        return lab_class_count;
    }

}
