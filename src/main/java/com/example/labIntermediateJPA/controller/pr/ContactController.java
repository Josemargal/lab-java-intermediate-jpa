package com.example.labIntermediateJPA.controller.pr;

import com.example.labIntermediateJPA.model.pr.Contact;
import com.example.labIntermediateJPA.service.pr.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {

    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping
    public ResponseEntity<List<Contact>> getAllContacts() {
        return ResponseEntity.ok(contactService.getAllContacts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contact> getContactById(@PathVariable Long id) {
        return contactService.getContactById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Contact> createContact(@RequestBody Map<String, Object> payload) {
        Map<String, String> nameMap = (Map<String, String>) payload.get("name");
        String salutation = nameMap.get("salutation");
        String firstName = nameMap.get("firstName");
        String middleName = nameMap.getOrDefault("middleName", "");
        String lastName = nameMap.get("lastName");
        String title = (String) payload.get("title");
        String company = (String) payload.get("company");

        Contact contact = contactService.createContact(salutation, firstName, middleName, lastName, title, company);
        return new ResponseEntity<>(contact, HttpStatus.CREATED);
    }

    @GetMapping("/search/firstName")
    public ResponseEntity<List<Contact>> findContactsByFirstName(@RequestParam String firstName) {
        return ResponseEntity.ok(contactService.findContactsByFirstName(firstName));
    }

    @GetMapping("/search/lastName")
    public ResponseEntity<List<Contact>> findContactsByLastName(@RequestParam String lastName) {
        return ResponseEntity.ok(contactService.findContactsByLastName(lastName));
    }

    @GetMapping("/search/company")
    public ResponseEntity<List<Contact>> findContactsByCompany(@RequestParam String company) {
        return ResponseEntity.ok(contactService.findContactsByCompany(company));
    }

    @GetMapping("/search/fullName")
    public ResponseEntity<List<Contact>> findByFullName(@RequestParam String fullName) {
        return ResponseEntity.ok(contactService.findContactsByFullName(fullName));
    }

    @GetMapping("/search/soundex")
    public ResponseEntity<List<Contact>> findBySimilarSoundingLastName(@RequestParam String lastName) {
        return ResponseEntity.ok(contactService.findContactsBySimilarSoundingLastName(lastName));
    }
}
