import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.IndexColumn;
import javax.persistence.ForeignKey;

@Entity
@Table(name = "tblUser")
public class hibernate_class_13 implements java.io.Serializable, Cloneable {

    @Id
    @Column(name = "CountyId", nullable = false)
    private Integer countyId;

    @Id
    @Column(name = "Username", length = 25, nullable = false)
    private String username;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "CountyId", nullable = false, insertable=false, updatable=false)
    @ForeignKey(name="FK_CountyID")
    private County county;

    @Column(name = "Name", length = 50, nullable = true)
    private String name;

    @Column(name = "Password", length = 30, nullable = true)
    private String password;

    @Column(name = "Role", nullable = false)
    private Integer role;

    @ManyToMany(targetEntity=Permission.class,
         cascade={ CascadeType.PERSIST, CascadeType.MERGE } )
    @JoinTable(name="tblUserPermission",
         joinColumns = { @JoinColumn(name="Username", referencedColumnName="Username"), @JoinColumn(name="CountyId", referencedColumnName="CountyId") },
         inverseJoinColumns = { @JoinColumn(name="PermissionId", referencedColumnName="PermissionId") })
   private Collection<Permission> permissions;

    @OneToMany(fetch=FetchType.LAZY, mappedBy="county")
    @IndexColumn(name="version")
    private List<Version> versions;

}