package edu.syr.cyberseed.sage.integrationclient.entities;


import lombok.Data;

import java.util.Date;

@Data
public class MedicalRecord {

    private Integer id;
    private String record_type;
    private String edit;
    private String view;
    private String owner;
    private String patient;
    private Date date;

    protected MedicalRecord() {
    }

    // Constructor when id is not specified
    public MedicalRecord(String record_type, Date date, String owner, String patient, String edit, String view) {
        this.record_type = record_type;
        this.date = date;
        this.owner = owner;
        this.patient = patient;
        this.edit = edit;
        this.view = view;
    }

    // Constructor when id is specified
    public MedicalRecord(Integer id, String record_type, Date date, String owner, String patient, String edit, String view) {
        this.id = id;
        this.record_type = record_type;
        this.date = date;
        this.owner = owner;
        this.patient = patient;
        this.edit = edit;
        this.view = view;
    }

    @Override
    public String toString() {
        return "MedicalRecord{" +
                "id=" + id +
                ", record_type='" + record_type + '\'' +
                ", date=" + date +
                '}';
    }

}