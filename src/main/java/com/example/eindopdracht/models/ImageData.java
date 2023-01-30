package com.example.eindopdracht.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "image")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ImageData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nameOfImage;

    @Column
    private String typeOfImage;

    @Lob
    private byte[] imageData;

    @OneToOne(mappedBy = "imageData", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private GameOwner gameOwner;


}
