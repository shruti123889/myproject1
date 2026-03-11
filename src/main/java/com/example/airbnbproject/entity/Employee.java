package com.example.airbnbproject.entity;
import com.example.airbnbproject.entity.User;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    private String phoneNumber;

    private String designation; // Manager, Developer, Sales Executive, etc.

    @Column(nullable = false)
    private Double baseSalary;

    private LocalDate joiningDate;

    private Boolean isActive = true;

    // Optional: Agar aap Employee ko User (Login) se connect karna chahte hain
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    // Helper method to get full name
    public String getFullName() {
        return firstName + " " + lastName;
    }
}