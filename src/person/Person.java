package person;


import dataStructures.MyLinkedList;
import node.Node;

public class Person implements Comparable<Person> {

    private int personID;                      //Primary key used in database
    private String firstName;                   //First name of person
    private String lastName;                    //Last name of person
    private String emailID;                     //EmailId of person
    private MyLinkedList<String> phoneNumbers;  //List of phone numbers

    //Constructor for person class
    public Person() {
        phoneNumbers = new MyLinkedList<>();
    }

    //Getter for personID
    public int getPersonID() {
        return personID;
    }

    //Setter for personID
    public void setPersonID(int personID) {
        this.personID = personID;
    }

    //Getter for first name
    public String getFirstName() {
        return firstName;
    }

    //Setter for first name
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    //Getter for last name
    public String getLastName() {
        return lastName;
    }

    //Setter for last name
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    //Getter for Email ID
    public String getEmailID() {
        return emailID;
    }

    //Setter for EmailID
    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    //Getter for fetching all phone numbers
    public String getPhoneNumber() {
        Node<String> phoneNumber = phoneNumbers.getObject();
        if (phoneNumber == null)
            return null;
        return phoneNumber.getData();
    }

    //Setter for inserting new phone numbers
    public void setPhoneNumbers(String phoneNumber) {
        Node<String> node = new Node<>();
        node.setData(phoneNumber);
        this.phoneNumbers.insert(node);
    }

    //toString method to show all phone numbers of person
    @Override
    public String toString() {
        StringBuffer allPhoneNumbers = new StringBuffer();
        String phoneNumber = getPhoneNumber();
        while (phoneNumber != null) {
            allPhoneNumbers.append(phoneNumber);
            phoneNumber = getPhoneNumber();
            if (phoneNumber != null)
                allPhoneNumbers.append(", ");
        }

        if (getEmailID() == null)
            return "-------- * -------- * -------- * --------\n" +
                    "First Name: " + this.getFirstName() + "\n" +
                    "Last Name: " + this.getLastName() + "\n" +
                    "Contact Number(s): " + allPhoneNumbers + "\n" +
                    "-------- * -------- * -------- * --------";
        else

            return "-------- * -------- * -------- * --------\n" +
                    "First Name: " + this.getFirstName() + "\n" +
                    "Last Name: " + this.getLastName() + "\n" +
                    "Contact Number(s): " + allPhoneNumbers + "\n" +
                    "Email address: " + this.getEmailID() + "\n" +
                    "-------- * -------- * -------- * --------";
    }

    //compareTo method for comparing objects of person class
    @Override
    public int compareTo(Person newPerson) {
        return this.getFirstName().compareTo(newPerson.getFirstName());
    }
}