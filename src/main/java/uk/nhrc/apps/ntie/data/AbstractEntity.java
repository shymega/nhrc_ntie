package uk.nhrc.apps.ntie.data;

import jakarta.persistence.*;

import java.util.UUID;

@MappedSuperclass
public abstract class AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
}
