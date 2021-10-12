package com.dkit.gd2.johnloane;

import java.util.Scanner;

/**
 * Create a program that models a simple mobile phone with the following functions
 * Able to store, modify, remove and query contacts
 * You need to create a separate class for Contacts (name and phone number)
 * Create a class MobilePhone that has an arraylist of contacts
 * The MobilePhone class has the functionality listed above
 * Add a menu of options that are available
 * Options, quit, print list of contacts, add new contact, update existing contact, remove a contact, and search
 * When adding or updating be sure to check if the contact already exists (use name)
 * Be sure not to expose the inner workings of the ArrayList of MobilePhone class e.g no .get(i)
 * MobilePhone should do everything with Contact objects only
 */
public class App 
{
    private static Scanner keyboard = new Scanner(System.in);
    private static MobilePhone myPhone = new MobilePhone("089 7773334");
    public static void main( String[] args )
    {
        System.out.println("Welcome to the amazing mobile phone app");

        boolean quit = false;
        startPhone();
        printOptions();

        while(!quit)
        {
            System.out.print("\n Enter action: (0 to show available options) > ");
            int action = keyboard.nextInt();
            keyboard.nextLine();
            //TODO refactor to use enum instead of 0, 1, 2, etc
            switch(action)
            {
                case 0:
                    printOptions();
                    break;
                case 1:
                    myPhone.printContacts();
                    break;
                case 2:
                    addNewContact();
                    break;
                case 3:
                    updateContact();
                    break;
                case 4:
                    removeContact();
                    break;
                case 5:
                    searchContact();
                    break;
                case 6:
                    System.out.println("Shutting down the system....");
                    quit = true;
                    break;
            }
        }
    }

    private static void startPhone()
    {
        System.out.println("Starting the phone....");
    }

    private static void printOptions()
    {
        System.out.println("\n Available options: ");
        System.out.println("0 - to print all options \n"+
                            "1 - to print all contacts \n" +
                            "2 - to add a new contact\n" +
                            "3 - to update and existing contact \n" +
                            "4 - to remove an existing contact \n" +
                            "5 - search for a contact \n" +
                            "6 - to quit");
    }

    private static void addNewContact()
    {
        System.out.print("Enter new contact name > ");
        String name = keyboard.nextLine();
        System.out.print("Enter new contact phone number > ");
        String phoneNumber = keyboard.nextLine();
        Contact newContact = Contact.createContact(name, phoneNumber);
        if(myPhone.addNewContact(newContact))
        {
            System.out.println("New contact added: name = " + name + ", phone = " + phoneNumber);
        }
        else
        {
            System.out.println("Cannot add, " + name + "already in contacts");
        }
    }

    //Try to write this method
    //TODO take the repeated code asking for name and phoneNumber
    //and add to separate method to avoid repetition
    private static void updateContact()
    {
        System.out.print("Enter existing contact name > ");
        String name = keyboard.nextLine();
        Contact existingContactRecord = myPhone.searchContact(name);
        if(existingContactRecord == null)
        {
            System.out.println("Contact not found");
            return;
        }
        System.out.print("Enter new contact name > ");
        String newName = keyboard.nextLine();
        System.out.print("Enter new contact phone number > ");
        String newPhoneNumber = keyboard.nextLine();
        Contact newContact = Contact.createContact(newName, newPhoneNumber);
        if(myPhone.updateContact(existingContactRecord, newContact))
        {
            System.out.println("Successfully updated");
        }
        else
        {
            System.out.println("Could not update record");
        }
    }

    private static void removeContact()
    {
        System.out.print("Enter the name of the contact to remove > ");
        String name = keyboard.nextLine();
        Contact existingContactRecord = myPhone.searchContact(name);
        if(existingContactRecord == null)
        {
            System.out.println("Contact not found");
            return;
        }
        myPhone.removeContact(existingContactRecord);

    }
    
    private static void searchContact()
    {
        System.out.print("Enter the name of the contact to search > ");
        String name = keyboard.nextLine();
        Contact existingContactRecord = myPhone.searchContact(name);
        if(existingContactRecord == null)
        {
            System.out.println("Contact not found");
            return;
        }
        System.out.println("Name: " + existingContactRecord.getName() + " phone number is " + existingContactRecord.getPhoneNumber());
    }
}
