package reservationSystem.entity;

import com.fasterxml.jackson.databind.DatabindException;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

// Customer/reservation object with all reservation information
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "date")
    private String date;
    @Column(name = "duration")
    private String duration;
    @Column(name = "email")
    private String email;

    @Column(name = "time")
    private LocalDate datum;

    public Customer() {
    }

    public Customer(String name, String date, String duration, String email, LocalDate datum) {
        super();
        this.name = name;
        this.date = date;
        this.duration = duration;
        this.email = email;
        this.datum = datum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }
}
