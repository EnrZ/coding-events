package org.launchcode.codingevents.models;

import org.springframework.boot.convert.DataSizeUnit;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

//POJO Events plain old java objects
@Entity
public class Event extends AbstractEntity {

    //each unique event object we create has own integer

    //one-to-one relationship between event and EventDtails. Only a one way relationship, EventDetails doesnt know about Event
    @OneToOne(cascade = CascadeType.ALL)//Tells hibernate to cascade every operation of an object to its subject(event to EventDetails) eg. whenever an Event is saved, also save the associated EventDetails object.
    @Valid
    @NotNull
    private EventDetails eventDetails;

    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters.")
    @NotBlank(message = "Name is required")
    private String name;


    //going to be a many-to-one relationships, many events in one category. jpa annotations needed to specify this
    @ManyToOne
    @NotNull(message = "Category is required")
    private EventCategory eventCategory;

    //we want an event to be able to have multiple tags so its a collection type(many to many relationship)
    @ManyToMany
    private final List<Tag> tags = new ArrayList<>();

    public Event(String name, EventCategory eventCategory) {
        this.name = name;
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

    public EventCategory getEventCategory() {
        return eventCategory;
    }

    public void setEventCategory(EventCategory eventCategory) {
        this.eventCategory = eventCategory;
    }

    public EventDetails getEventDetails() {
        return eventDetails;
    }

    public void setEventDetails(EventDetails eventDetails) {
        this.eventDetails = eventDetails;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void addTag(Tag tag){
        this.tags.add(tag);
    }

    @Override
    public String toString() {
        return name;
    }
}
