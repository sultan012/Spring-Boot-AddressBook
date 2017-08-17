package com.adressBook;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

@Service("contactService")
public class ContactServiceImpl implements ContactService {

private static final AtomicLong counter = new AtomicLong();
	
	private static List<Contact> contacts;
	
	static{
		contacts= populateDummyContacts();
	}


	public Contact findById(long id) {
		for(Contact contact : contacts){
			if(contact.getId() == id){
				return contact;
			}
		}
		return null;

	}

	
	public Contact findByName(String name) {
		for(Contact contact : contacts){
			if(contact.getFirstName().equalsIgnoreCase(name) ||
					contact.getLastName().equalsIgnoreCase(name)){
				return contact;
			}
		}
		return null;
	}

	public void saveContact(Contact contact) {
		contact.setId(counter.incrementAndGet());
		contacts.add(contact);

	}


	public void deleteContactById(long id) {
		for (Iterator<Contact> iterator = contacts.iterator(); iterator.hasNext(); ) {
			Contact contact = iterator.next();
		    if (contact.getId() == id) {
		        iterator.remove();
		    }
		}

		
	}

	public List<Contact> findAllContact() {
		return contacts;
	}


	public boolean isContactExist(Contact contact) {
		return findByName(contact.getFirstName())!=null;

	}
	private static List<Contact> populateDummyContacts(){
		List<Contact> contacts = new ArrayList<Contact>();
		contacts.add(new Contact(counter.incrementAndGet(),"Abed","Sultan","a.asultan@hotmail.com", "073276778"));
		contacts.add(new Contact(counter.incrementAndGet(),"Sara","Larsson","sara@msn.se","018767634"));
		contacts.add(new Contact(counter.incrementAndGet(),"Ola","Karlson","Ola@gmail.com","0823465523"));
		contacts.add(new Contact(counter.incrementAndGet(),"Linus","Karl","linus@gmail.com","0704080212"));
		return contacts;
	}


}
