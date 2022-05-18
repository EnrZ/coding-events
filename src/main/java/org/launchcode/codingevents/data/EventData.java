package org.launchcode.codingevents.data;

//create data layer, this class responsible for storing event objects

import org.launchcode.codingevents.models.Event;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

//
public class EventData {

    //need a place to put events

    //Map is an interface and we went to code to interfaces whenever possible so
    //making it a map is preferred to HashMap
    private static final Map<Integer, Event> events = new HashMap<>();

    //get all events
    public static Collection<Event> getAll(){
        //collection interface

        //values method
        return events.values();
    }

    //get a single event
    public static Event getById(int id){
        return events.get(id);
    }

    //add an event
    public static void addNew(Event event){
        events.put(event.getId(),event);
    }

    // remove an event
    public static void remove(int id){
        events.remove(id);
    }
}
