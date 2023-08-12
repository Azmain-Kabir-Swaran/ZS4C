package hibernate;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
//ID = 3287351

@Entity
@Table(name="log_entries")
@org.hibernate.annotations.Table(appliesTo="log_entries",
    indexes = { @Index(name="idx", columnNames = {"job", "version", "schedule", "dttmRun", "pid" } ) } )
public class hibernate_class_32 {
  @Id @GeneratedValue
  Long id;
  String job;
  String version;
  String schedule;
  String dttmRun;
  int pid;
  String command;
  int duration;

  // getters and setters...
}