package com.adressBook;

import java.util.List;


	public interface ContactService {
		
		Contact findById(long id);
		
		Contact findByName(String name);
		
		void saveContact(Contact contact);
				
		void deleteContactById(long id);

		List<Contact> findAllContact();
		
		boolean isContactExist(Contact contact);
		
	}
