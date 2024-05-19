package com.example.pfa2021.entities;
import javax.persistence.*;

@Entity
@Table(name = "experts")
public class Expert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;
    
    private String username ;

    @Column(name = "projet_id")
    private Long projetId;

    public Expert() {
    }

    public Expert(String email, String password, Long projetId,String username) {
        this.email = email;
        this.password = password;
        this.username = username;

        this.projetId = projetId;
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    

    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getProjetId() {
        return projetId;
    }

    public void setProjetId(Long projetId) {
       this.projetId=projetId;
       }
    }
