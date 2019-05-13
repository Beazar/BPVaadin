package com.example;

import com.vaadin.data.Binder;
import com.vaadin.server.ExternalResource;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

public class ContactLayout extends HorizontalLayout {
    private final TextField firstName;
    private final TextField lastName;
    private final ExternalResource resource;
    private final TextField phone;
    private final CheckBox delete;

    public ContactLayout(Contact contact, ContactChangeListener changeListener) {
        setWidth("100%");
        setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);

        delete = new CheckBox();
        firstName = new TextField();
        lastName = new TextField();
        resource = new ExternalResource(contact.getImage());
        Image image = new Image(null, resource);
        image.setHeight(100, Unit.PIXELS);

        phone = new TextField();
        firstName.addStyleName(ValoTheme.TEXTFIELD_BORDERLESS);
        firstName.setValueChangeMode(ValueChangeMode.BLUR);

        lastName.addStyleName(ValoTheme.TEXTFIELD_BORDERLESS);
        lastName.setValueChangeMode(ValueChangeMode.BLUR);

 /*       image.addStyleName(ValoTheme.TEXTFIELD_BORDERLESS);
        image.setValueChangeMode(ValueChangeMode.BLUR);*/

        phone.addStyleName(ValoTheme.TEXTFIELD_BORDERLESS);
        phone.setValueChangeMode(ValueChangeMode.BLUR);

        Binder<Contact> binder = new Binder<>(Contact.class);
        //Binds fields in this class to those in Contact based on their names
        binder.bindInstanceFields(this);
        // The following does the same more explicitly
        // binder.bind(text, Todo::getText, Todo::setText);
        // binder.bind(done, Todo::isDone, Todo::setDone);

        binder.setBean(contact);

        addComponent(delete);
        addComponent(image);
        addComponent(firstName);
        addComponent(lastName);
        addComponent(phone);

      //  addComponentsAndExpand(text);

        binder.addValueChangeListener(event -> changeListener.contactChanged(contact));
    }
}