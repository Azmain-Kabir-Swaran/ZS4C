package hibernate;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.hibernate.annotations.IndexColumn;

@Entity
public class hibernate_class_28 {
    @Id
    @GeneratedValue
    private int id;
    
    @ManyToOne
    @JoinColumn(name="user_id")
    private hibernate.User user;
    
    @OneToMany(targetEntity=hibernate_class_28.class)
    @JoinColumn(name="parent_id")
    @IndexColumn(name="id", base=0)
    private List<hibernate_class_28> comments = new ArrayList<>();
    
    @Column(name="article_id", length=10)
    private int articleId;
    
    @Column(name="text", length=8192)
    private String text;
    
    public int getArticleId() {
        return articleId;
    }
    
    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getText() {
        return text;
    }
    
    public void setText(String text) {
        this.text = text;
    }
    
    public hibernate.User getUser() {
        return user;
    }
    
    public void setUser(hibernate.User user) {
        this.user = user;
    }
    
    public List<hibernate_class_28> getComments() {
        return comments;
    }
    
    public void setComments(List<hibernate_class_28> comments) {
        this.comments = comments;
    }
}