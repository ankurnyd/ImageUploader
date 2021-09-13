package ImageHoster.model;


import javax.persistence.*;
import java.time.LocalDate;


// Entity Annotation
@Entity
// Table creation
@Table(name = "comments")
public class Comment {

    //declared Primary key
    @Id
    //Auto generation
    @GeneratedValue(strategy = GenerationType.AUTO)
    //declared column name
    @Column(name = "id")
    private Integer id;

    @Column(name = "text", columnDefinition = "TEXT")
    private String text;

    @Column(name = "created_date")
    private LocalDate createdDate;


    //Comments table mapped to users and one user can have multiple comments.
    @ManyToOne(fetch = FetchType.EAGER)

    @JoinColumn(name = "user_id")
    private User user;

    //Comments mapped to images and one image can have multiple comments.
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "image_id")
    private Image image;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}





