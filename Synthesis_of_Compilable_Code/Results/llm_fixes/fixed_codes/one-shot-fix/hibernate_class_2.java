package hibernate;
import java.util.List;
import javax.persistence.Id;
import java.io.Serializable;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence.Entity;
import javax.persistence.CascadeType;
import javax.persistence.Column;

public class hibernate_class_2{
    /**
     * Entity implementation class for Entity: Part
     * 
     */
    @Entity
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    public class Part implements Serializable {

        private int id;
        private String name;
        private static final long serialVersionUID = 1L;
        private Part mainPart;
        private List<Part> parts;

        public Part() {
            super();
        }

        @Id
        public int getId() {
            return this.id;
        }

        public void setId(int id) {
            this.id = id;
        }

        @Column(name = "PART_NAME")
        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @ManyToOne(cascade = CascadeType.ALL)
        public Part getMainPart() {
            return mainPart;
        }

        public void setMainPart(Part mainPart) {
            this.mainPart = mainPart;
        }

        @OneToMany(cascade = CascadeType.ALL)
        @JoinColumn(name = "mainPart_id", referencedColumnName = "id")
        @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
        public List<Part> getParts() {
            return parts;
        }

        public void setParts(List<Part> parts) {
            this.parts = parts;
        }

    }
}