package com.dmi_sauron.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//@Data
@NoArgsConstructor // Der er altid brug for en tom constructor i det mindste fordi Jpa repository har brug for det.
@AllArgsConstructor
@Getter
@Setter
@Entity
public class NinjoServerModel {

    @Id // sammen med @GeneratedValue fortæller databasen at denne Long id skal være vores primary key.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSS")
//    private LocalDateTime createdAt;
//    @Column(name = "date", columnDefinition = "TIMESTAMP")
    private String date;

    private String servername;
    private String uptime;
    private String diskfree;

//    private String time1;
//    private String daysUp;
//    private String time2;
//    private String usersTotal;
//    private String loadAverage;
//    private String serverPath;
//    private String memoryTotal;
//    private String memoryUsed;
//    private String memoryFree;

//    Constructor uden ID (primary key), som er håndteret automatisk i databasen med auto-increment.
//    public NinjoServerModel(String uptime, String diskfree) {

    public NinjoServerModel(String uptime, String diskfree, String date) {
        // extra arguments if needed: String time1, String daysUp, String time2, String usersTotal, String loadAverage, String serverPath, String memoryTotal, String memoryUsed, String memoryFree
        // this.servername = servername; // burde kunne hentes fra i JSON filnavnet eller i diskfree scopet
        this.uptime = uptime;
        this.diskfree = diskfree;
//        this.time1 = time1;
//        this.daysUp = daysUp;
//        this.time2 = time2;
//        this.usersTotal = usersTotal;
//        this.loadAverage = loadAverage;
//        this.serverPath = serverPath;
//        this.memoryTotal = memoryTotal;
//        this.memoryUsed = memoryUsed;
//        this.memoryFree = memoryFree;
//        this.date = LocalDateTime.now();
        this.date = date;

    }
}
