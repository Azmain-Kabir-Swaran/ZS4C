import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Index;
import org.hibernate.annotations.Tables;
import org.hibernate.annotations.Table;
import javax.persistence.Index;

@Entity
@Table(name="log_entries",
    indexes = { @Index(name="idx", columnList = "job, version, schedule, dttmRun, pid" ) } )
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