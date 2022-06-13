package org.launchcode.codingevents.models;

import org.springframework.boot.convert.DataSizeUnit;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

//POJO Events plain old java objects
@Entity
public class Event extends AbstractEntity {

    //each unique event object we create has own integer

    //counter no longer needed, generaedvalue lets database generate it

    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters.")
    @NotBlank(message = "Name is required")
    private String name;
    @Size(max = 500, message = "Description too long!")
    private String description;


    @Email(message = "Invalid email. Try again.")
    @NotBlank(message = "Email is required")
    private String contactEmail;

    //going to be a many-to-one relationships, many events in one category. jpa annotations needed to specify this
    @ManyToOne
    @NotNull(message = "Category is required")
    private EventCategory eventCategory;
    public Event(String name, String description, String contactEmail, EventCategory eventCategory) {
        this.name = name;
        this.description = description;
        this.contactEmail = contactEmail;
        this.eventCategory = eventCategory;
    }

    public Event(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }



    public void setDescription(String description) {
        this.description = description;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }


    public EventCategory getEventCategory() {
        return eventCategory;
    }

    public void setEventCategory(EventCategory eventCategory) {
        this.eventCategory = eventCategory;
    }

    @Override
    public String toString() {
        return name;
    }
}
