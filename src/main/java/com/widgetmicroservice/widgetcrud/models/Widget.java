package com.widgetmicroservice.widgetcrud.models;

import com.widgetmicroservice.widgetcrud.enums.Gender;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

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
    @NotEmpty
    @Size(min = 2, message = "name must have at least 2 characters")
    @Column(
            name = "first_name"
    )
    private String firstName;

    @NonNull
    @NotEmpty
    @Size(min = 2, message = "name must have at least 2 characters")
    @Column(
            name = "last_name"
    )
    private String lastName;

    @NonNull
    @Max(value = 150)
    @Min(value = 0)
    @Column(
            name = "age"
    )
    private Integer age;

    @NonNull
    @Size(min = 4, message = "name must be MALE, FEMALE or OTHER")
    @Column(
            name = "gender"
    )
    private Gender gender;

    @NonNull
    @Min(value = 1)
    @Column(
            name = "height"
    )
    private Double height;

    @NonNull
    @Min(value = 1)
    @Column(
            name = "weight"
    )
    private Double weight;
}

