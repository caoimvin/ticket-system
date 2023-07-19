package com.caoimvin.security.ticket;

import com.caoimvin.security.comment.Comment;
import com.caoimvin.security.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private Status status = Status.OPEN;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties(value = {"user", "hibernateLazyInitializer", "handler"}, allowSetters = true)
    @JsonBackReference
    private User user;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "ticket",
            cascade = CascadeType.ALL
    )
    @JsonIgnoreProperties(value = { "comments", "hibernateLazyInitializer", "handler" }, allowSetters = true)
    @JsonManagedReference
    private List<Comment> comments;

    @Column(name = "created_at")
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;
}
