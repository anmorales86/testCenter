package com.group.call.almundo.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Anderson Q. on 3/06/2019.
 */
@Table(name = "employee")
@Entity
public class EmployeeEntity implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAMECOMPLETE")
    private String nameComplete;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "POSITION")
    private String position;


    public EmployeeEntity() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameComplete() {
        return nameComplete;
    }

    public void setNameComplete(String nameComplete) {
        this.nameComplete = nameComplete;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "EmployeeEntity{" +
                "id=" + id +
                ", nameComplete='" + nameComplete + '\'' +
                ", status='" + status + '\'' +
                ", position='" + position + '\'' +
                '}';
    }
}
