package com.example;

import com.vaadin.annotations.Theme;
import com.vaadin.event.ShortcutAction;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

@SpringUI
@Theme("valo")
public class ContactUI extends UI {

    private ContactRepository repository;
    Grid<Contact> grid;
    private VerticalLayout layout;

    @Autowired
    ContactList contactList;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        this.grid = new Grid<>(Contact.class);
        setupLayout();
        addHeader();
        addForm();
        addContactList();
        addActionButtons();
    }

    private void setupLayout() {
        layout = new VerticalLayout();
        layout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        setContent(layout);
    }

    private void addHeader() {
        Label header = new Label("Contacts");
        header.addStyleName(ValoTheme.LABEL_H1);
        layout.addComponent(header);
    }

    private void addForm() {
        HorizontalLayout formLayoutH = new HorizontalLayout();
        VerticalLayout formLayoutVLabels = new VerticalLayout();
        VerticalLayout formLayoutVTextFields= new VerticalLayout();
        formLayoutH.setWidth("65%");
        formLayoutH.setHeight("40%");

        TextField firstNameField = new TextField();
        TextField lastNameField = new TextField();
        TextField imageField = new TextField();
        TextField phoneField = new TextField();
        firstNameField.setHeight("75px");
        lastNameField.setHeight("75px");
        imageField.setHeight("75px");
        phoneField.setHeight("75px");

        firstNameField.focus();
        Button addButton = new Button("");

        Label firstNameLabel = new Label("Voornaam");
        Label lastNameLabel = new Label("Achternaam");
        Label imageLabel = new Label("URL van de afbeelding");
        Label phoneLabel = new Label("Telefoonnummer");
        firstNameLabel.setHeight("45px");
        lastNameLabel.setHeight("45px");
        imageLabel.setHeight("45px");
        phoneLabel.setHeight("45px");
        formLayoutVLabels.setWidth("25%");
        formLayoutVLabels.addComponents(firstNameLabel,lastNameLabel,imageLabel,phoneLabel);
        formLayoutVTextFields.addComponentsAndExpand(firstNameField);
        formLayoutVTextFields.addComponentsAndExpand(lastNameField);
        formLayoutVTextFields.addComponentsAndExpand(imageField);
        formLayoutVTextFields.addComponentsAndExpand(phoneField);
        formLayoutVTextFields.addComponent(addButton);
        formLayoutH.addComponents(formLayoutVLabels,formLayoutVTextFields);
        layout.addComponent(formLayoutH);

        addButton.addStyleName(ValoTheme.BUTTON_PRIMARY);
        addButton.setIcon(VaadinIcons.PLUS);

        addButton.addClickListener(click -> {
            contactList.addContact(new Contact(firstNameField.getValue(),lastNameField.getValue(),imageField.getValue(),phoneField.getValue()));
            firstNameField.setValue("");
            lastNameField.setValue("");
            imageField.setValue("");
            phoneField.setValue("");
            firstNameField.focus();
        });
        addButton.setClickShortcut(ShortcutAction.KeyCode.ENTER);
    }

    private void addContactList() {
        layout.addComponent(contactList);
    }

    private void addActionButtons() {
        Button deleteButton = new Button("Delete Contact");

       // deleteButton.addClickListener(click->contactList.deleteCompleted());

       // layout.addComponent(deleteButton);

    }
}
