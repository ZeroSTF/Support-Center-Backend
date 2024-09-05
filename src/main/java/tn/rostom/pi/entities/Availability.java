package tn.rostom.pi.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.*;
import lombok.experimental.*;
import jakarta.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Availability implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    LocalDateTime startTime;
    LocalDateTime endTime;

    @ManyToOne
    Expert expert;
}
