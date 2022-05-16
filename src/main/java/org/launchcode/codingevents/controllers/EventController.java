package org.launchcode.codingevents.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("events")
public class EventController {

    //private static List<String> events = new ArrayList<>();
    //events is now a HashMap ch 13 exercise
    private static HashMap<String,String> events = new HashMap<>();


    @GetMapping
    public String displayAllEvents(Model model){
//        List<String> events = new ArrayList<>();
//        events.add("Code With Pride");
//        events.add("Strange Loop");
//        events.add("Apple WWDC");
//        events.add("SpringOne Platform");
//        model.addAttribute("events", events);

        model.addAttribute("events", events);
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
    public String createEvent(@RequestParam String eventName, @RequestParam String eventDesc) {
        //eventName is name of the text input given in create.html

        events.put(eventName,eventDesc);

        //return a redirect reponse that instructs the browser to go to a different page
        //redirect: means redirect to root path for this controller
        return  "redirect:";

    }
}
