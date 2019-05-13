package com.example;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;

@UIScope
@SpringComponent
class ContactList extends VerticalLayout implements ContactChangeListener {

    @Autowired
    ContactRepository repository;
    private List<Contact> contacts;

    @PostConstruct
    void init() {
        setWidth("80%");

        update();
    }

    private void update() {
        setContacts(repository.findAll());
    }

    private void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
        removeAllComponents();
        contacts.forEach(contact -> addComponent(new ContactLayout(contact, this)));
    }

    void addContact(Contact contact) {
        repository.save(contact);
        update();
    }

    @Override
    public void contactChanged(Contact contact) {
        addContact(contact);
    }


    public void deleteCompleted() {
      //  repository.deleteByDone(true);
        update();
    }
}
