package oqu.today.initital.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "chapters")
public class Chapter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "media")
    private String media;

    @OneToMany
    @JsonIgnore
    @JoinColumn(name = "lesson_id")
    private List<Lesson> lesson;

    public Chapter(String name, String description, String media) {
        this.name = name;
        this.description = description;
        this.media = media;
    }

    public Chapter() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    @Override
    public String toString() {
        return "Chapter{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", media='" + media + '\'' +
                '}';
    }
}
