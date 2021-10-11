package com.dkit.gd2.johnloane;

import java.util.ArrayList;

public class MobilePhone
{
    private String myNumber;
    private ArrayList<Contact> myContacts;

    public MobilePhone(String myNumber)
    {
        this.myNumber = myNumber;
        this.myContacts = new ArrayList<Contact>();
    }

    public boolean addNewContact(Contact contact)
    {
        if(findContact(contact.getName()) >= 0)
        {
            System.out.println("Contact already stored");
            return false;
        }
        myContacts.add(contact);
        return true;
    }

    public boolean updateContact(Contact oldContact, Contact newContact)
    {
        int foundPosition = findContact(oldContact);
        if(foundPosition < 0)
        {
            System.out.println(oldContact.getName() + ", was not found");
            return false;
        }
        this.myContacts.set(foundPosition, newContact);
        return true;
    }

    //Remove contact
    public boolean removeContact(Contact contact)
    {
        int foundPosition = findContact(contact);
        if(foundPosition < 0)
        {
            System.out.println(contact.getName() + ", was not found");
            return false;
        }
        this.myContacts.remove(foundPosition);
        System.out.println(contact.getName() + " , was deleted");
        return true;
    }

    //Print all contacts
    //TODO refactor to have toString() in Contact class
    public void printContacts()
    {
        System.out.println("Contact list: ");
        for(Contact contact : this.myContacts)
        {
            System.out.println(contact.getName() + " -> " + contact.getPhoneNumber());
        }
    }

    public String searchContact(Contact contact)
    {
        if(findContact(contact) >=0)
        {
            return contact.getName();
        }
        return null;
    }

    public Contact searchContact(String contactName)
    {
        int position = findContact(contactName);
        if(position >=0)
        {
            return this.myContacts.get(position);
        }
        return null;
    }


    private int findContact(Contact contact)
    {
        return this.myContacts.indexOf(contact);
    }

    private int findContact(String contactName)
    {
        for(int i=0; i < this.myContacts.size(); i++)
        {
            Contact contact = this.myContacts.get(i);
            if(contact.getName().equals(contactName))
            {
                return i;
            }
        }
        return -1;
    }
}
