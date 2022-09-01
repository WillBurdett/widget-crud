package com.widgetmicroservice.widgetcrud.models;

import com.widgetmicroservice.widgetcrud.enums.Gender;
import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity(name = "widget")
@Table(name = "widgets")
public class Widget {
    @Id
    @Column(
            name = "id"
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @NonNull
    @Column(
            name = "first_name"
    )
    private String firstName;
    @NonNull
    @Column(
            name = "last_name"
    )
    private String lastName;
    @NonNull
    @Column(
            name = "age"
    )
    private Integer age;
    @NonNull
    @Column(
            name = "gender"
    )
    private Gender gender;
    @NonNull
    @Column(
            name = "height"
    )
    private Double height;
    @NonNull
    @Column(
            name = "weight"
    )
    private Double weight;
}

