package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.data.EventRepository;
import org.launchcode.codingevents.models.Event;
import org.launchcode.codingevents.models.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("events")
public class EventController {

    //sprint boot dependency injection feature
    @Autowired
    private EventRepository eventRepository;

    //findALl,save, FindById(all methods are part of EventRepository interface)

    @GetMapping
    public String displayAllEvents(Model model){
//        List<String> events = new ArrayList<>();
//        events.add("Code With Pride");
//        events.add("Strange Loop");
//        events.add("Apple WWDC");
//        events.add("SpringOne Platform");
//        model.addAttribute("events", events);

        model.addAttribute("events", eventRepository.findAll());
        return "events/index";
    }

    //lives at /events/create since that is the prefix(request mapping)
    @GetMapping("create")
    public String renderCreateEventForm(Model model){

        //the empty event object just made will have information about the fields
        model.addAttribute("event" , new Event());

        //returns an array of the 4 values in enum, addAttribute always needs label/attribute name
        model.addAttribute("types",EventType.values());

        return "events/create";

    }

    //lives at /events/create(its okay for this and last method to have the same path because one
    //uses GET and this is POST
    @PostMapping("create")
    public String createEvent(@ModelAttribute @Valid Event newEvent, Errors errors, Model model) {
        //for validation, the controller checks the criteria set in the model like @Size min max etc.

        //ModelAttribute is the key piece of model binding, have to back and change form names so Spring can find name and description and binding can work

        //eventName is name of the text input given in create.html

        if(errors.hasErrors()){
            model.addAttribute("title", "Create Event");
            //changes made to create template now that error attribute added
            return "events/create";
        }

       eventRepository.save(newEvent);


        //return a redirect reponse that instructs the browser to go to a different page
        //redirect: means redirect to root path for this controller
        return  "redirect:";

    }

    @GetMapping("delete")
    public String displayDeleteEventForm(Model model){
        //the model parameter is a different model than the EventData class
        model.addAttribute("title","Delete Events");
        model.addAttribute("events", eventRepository.findAll());
        return "events/delete";
    }

    @PostMapping("delete")
    public String processDeleteEventForm(@RequestParam(required = false) int[] eventIds) {
        //delete.html has the checkbox eventIds which will be submitted as an
        //int array so the request param will be an array(and the name has to match)

        //required = false allows method to be called without any eventIds, now we have to account for null
        if (eventIds != null) {
            for (int id : eventIds) {
                eventRepository.deleteById(id);
            }

        }
        return "redirect:";
    }

}
