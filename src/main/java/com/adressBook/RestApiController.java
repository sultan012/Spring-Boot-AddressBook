package com.adressBook;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api")

public class RestApiController {

	@Autowired
    ContactService contactService;
	
	@RequestMapping(value = "/contact/", method = RequestMethod.GET)
    public ResponseEntity<List<Contact>> listAllContacts() {
        List<Contact> contacts = contactService.findAllContact();
        if (contacts.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            
        }
        return new ResponseEntity<List<Contact>>(contacts, HttpStatus.OK);
    }
	
	// -------------------Create a Contact-------------------------------------------
	//  EX. POST ----> Body
							/*{
								"id": 1,
								"firstName": "Test",
								"lastName": "Sultan",
								"phone": "073276778",
								"email": "a.asultan@hotmail.com"
								} */
	 
    @RequestMapping(value = "/contact/", method = RequestMethod.POST)
    public ResponseEntity<?> createContact(@RequestBody Contact contact, UriComponentsBuilder ucBuilder) {
        contactService.saveContact(contact);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/contact/{id}").buildAndExpand(contact.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }
 
    // ------------------- Delete a Contact-----------------------------------------
	// 	// -- EX.--  DELETE     http://localhost:8080/AddressBookApp/api/contact/2 (id = 2)

 	@RequestMapping(value = "/contact/{id}", method = RequestMethod.DELETE)
 	public ResponseEntity<?> deleteContact(@PathVariable("id") long id) {
		contactService.deleteContactById(id);
 		return new ResponseEntity<Contact>(HttpStatus.NO_CONTENT);
 	}

 // -------------------Search Single Contact by id------------------------------------------
		// -- EX.--  GET        http://localhost:8080/AddressBookApp/api/contact/4 (id = 4)
 	@RequestMapping(value = "/contact/{id}", method = RequestMethod.GET)
 	public ResponseEntity<?> getContactById(@PathVariable("id") long id) {
 		
 		Contact contact = contactService.findById(id);

 		return new ResponseEntity<Contact>(contact, HttpStatus.OK);
 	}

// -------------------Search Single Contact by Name (First Name or Last Name)------------------------------------------
// -- EX.--  GET      http://localhost:8080/AddressBookApp/api/contact?name=sultan   (name = Sultan)
	@RequestMapping(value = "/contact", method = RequestMethod.GET)

	public ResponseEntity<?> getContactByName(@RequestParam("name") String name) {

		Contact contact = contactService.findByName(name);

		return new ResponseEntity<Contact>(contact, HttpStatus.OK);
	}

}
