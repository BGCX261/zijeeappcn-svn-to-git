package com.zhongs.hibernate;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "EVENTS")

// 2 level cachel
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Event {
    // create table EVENTS (id varchar(64),TITLE varchar(256), event_date date)
    // drop table events
    // select * from events

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @Column
    private String title;
    @Column(name = "EVENT_DATE")
    private Date date;

    public Event() {
        super();
    }

    public Event(String title, Date date) {
        super();
        this.title = title;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Event [id=" + id + ", title=" + title + ", date=" + date + "]";
    }

}
