package com.dmi_sauron.models;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor // Der er altid brug for en tom constructor i det mindste fordi Jpa repository har brug for det.
@AllArgsConstructor
@Getter
@Setter
@Entity
public class NinjoServerModel {

    @Id // sammen med @GeneratedValue fortæller databasen at denne int id skal være vores primary key.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
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
//    private LocalDateTime timeReceived;

    // Constructor uden ID (primary key), som er håndteret automatisk i databasen med auto-increment.
    public NinjoServerModel(String servername, String uptime, String diskfree) {
        // extra arguments if needed: String time1, String daysUp, String time2, String usersTotal, String loadAverage, String serverPath, String memoryTotal, String memoryUsed, String memoryFree
        this.servername = servername;
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
//        this.timeReceived = timeReceived;
    }

}
