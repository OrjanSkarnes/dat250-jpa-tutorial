package no.hvl.dat250.jpa.tutorial.votingapp;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "Users") // "User" is a reserved keyword in SQL
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private Roles role;

    @OneToMany(mappedBy = "creator")
    private List<Poll> createdPolls = new ArrayList<>();

    @ManyToMany(mappedBy = "users")
    private List<Poll> participatedPolls = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Vote> votes = new ArrayList<>();

    @ManyToMany
    private List<Group> groups = new ArrayList<>();
}

