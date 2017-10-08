/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.syr.cyberseed.sage.integrationclient.entities;

import lombok.Data;

import java.util.Date;


@Data
public class CorrespondenceRecord {

    private Integer noteId;
    private Integer id;
    private String doctor;
    private Date note_date;
    private String note_text;

    void getDoctor(String d)
    {
        this.doctor=d;
    }
    private CorrespondenceRecord(){}

    public CorrespondenceRecord(Integer id, String doctor)
    {
        this.id=id;
        this.doctor=doctor;
    }

    public CorrespondenceRecord(Integer id, Date d, String n)
    {
        this.id=id;
        this.note_date=d;
        this.note_text=n;
    }



}