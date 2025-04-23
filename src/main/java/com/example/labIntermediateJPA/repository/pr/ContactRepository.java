package com.example.labIntermediateJPA.repository.pr;

import com.example.labIntermediateJPA.model.pr.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    // Buscar por el primer nombre que es parte del objeto embebido Name
    List<Contact> findByName_FirstNameContaining(String firstName);

    // Buscar por apellido que es parte del objeto embebido Name
    List<Contact> findByName_LastNameContaining(String lastName);

    // Buscar por compañía
    List<Contact> findByCompanyContainingIgnoreCase(String company);

    // Ejemplo de consulta nativa para elementos que no se puede hacer con JPQL
    @Query(value = "SELECT * FROM contact c WHERE " +
            "CONCAT(c.salutation, ' ', c.first_name, ' ', COALESCE(c.middle_name, ''), ' ', c.last_name) LIKE %?1%",
            nativeQuery = true)
    List<Contact> findByFullNameContaining(String name);

    // Ejemplo de consulta nativa con función específica de base de datos
    @Query(value = "SELECT * FROM contact c WHERE " +
            "SOUNDEX(c.last_name) = SOUNDEX(?1)",
            nativeQuery = true)
    List<Contact> findBySimilarSoundingLastName(String lastName);
}
