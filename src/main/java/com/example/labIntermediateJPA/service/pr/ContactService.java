package com.example.labIntermediateJPA.service.pr;

import com.example.labIntermediateJPA.model.pr.Contact;
import com.example.labIntermediateJPA.model.pr.Name;
import com.example.labIntermediateJPA.repository.pr.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    private final ContactRepository contactRepository;

    @Autowired
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public Contact createContact(String salutation, String firstName, String middleName,
                                 String lastName, String title, String company) {
        Name name = new Name(salutation, firstName, middleName, lastName);
        Contact contact = new Contact(name, title, company);
        return contactRepository.save(contact);
    }

    public Optional<Contact> getContactById(Long id) {
        return contactRepository.findById(id);
    }

    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    public List<Contact> findContactsByFirstName(String firstName) {
        return contactRepository.findByName_FirstNameContaining(firstName);
    }

    public List<Contact> findContactsByLastName(String lastName) {
        return contactRepository.findByName_LastNameContaining(lastName);
    }

    public List<Contact> findContactsByCompany(String company) {
        return contactRepository.findByCompanyContainingIgnoreCase(company);
    }

    public List<Contact> findContactsByFullName(String fullName) {
        return contactRepository.findByFullNameContaining(fullName);
    }

    public List<Contact> findContactsBySimilarSoundingLastName(String lastName) {
        return contactRepository.findBySimilarSoundingLastName(lastName);
    }
}
