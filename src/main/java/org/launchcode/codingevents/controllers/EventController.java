package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.data.EventData;
import org.launchcode.codingevents.models.Event;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("events")
public class EventController {

    //not needed after adding data layer, EventData
    //private static List<Event> events = new ArrayList<>();

    @GetMapping
    public String displayAllEvents(Model model){
//        List<String> events = new ArrayList<>();
//        events.add("Code With Pride");
//        events.add("Strange Loop");
//        events.add("Apple WWDC");
//        events.add("SpringOne Platform");
//        model.addAttribute("events", events);

        model.addAttribute("events", EventData.getAll());
        return "events/index";
    }

    //lives at /events/create since that is the prefix(request mapping)
    @GetMapping("create")
    public String renderCreateEventForm(){
        return "events/create";
    }

    //lives at /events/create(its okay for this and last method to have the same path because one
    //uses GET and this is POST
    @PostMapping("create")
    public String createEvent(@ModelAttribute Event newEvent) {
        //ModelAttribute is the key piece of model binding, have to back and change form names so Spring can find name and description and binding can work

        //eventName is name of the text input given in create.html

        EventData.addNew(newEvent);

        //return a redirect reponse that instructs the browser to go to a different page
        //redirect: means redirect to root path for this controller
        return  "redirect:";

    }

    @GetMapping("delete")
    public String displayDeleteEventForm(Model model){
        //the model parameter is a different model than the EventData class
        model.addAttribute("title","Delete Events");
        model.addAttribute("events", EventData.getAll());
        return "events/delete";
    }

    @PostMapping("delete")
    public String processDeleteEventForm(@RequestParam(required = false) int[] eventIds) {
        //delete.html has the checkbox eventIds which will be submitted as an
        //int array so the request param will be an array(and the name has to match)

        //required = false allows method to be called without any eventIds, now we have to account for null
        if (eventIds != null) {
            for (int id : eventIds) {
                EventData.remove(id);
            }

        }
        return "redirect:";
    }

    @GetMapping("edit/{eventId}")
    public String displayEditEventForm(Model model, @PathVariable int eventId){
        Event eventToEdit = EventData.getById(eventId);
        model.addAttribute("event", eventToEdit);
        String title = "Edit Event " + eventToEdit.getName() + " (id=" + eventToEdit.getId() + ")";
        model.addAttribute("title", title);
        return "events/edit";
    }

    @PostMapping("edit")
    public String processEditEventForm(int eventId, String name, String description) {
        Event eventToEdit = EventData.getById(eventId);
        eventToEdit.setName(name);
        eventToEdit.setDescription(description);
        return "redirect:";
    }
}
