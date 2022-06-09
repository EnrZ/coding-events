package org.launchcode.codingevents.data;

import org.launchcode.codingevents.models.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//17.2 (primary key is primitive int but here its Integer (java will autobox)
@Repository
public interface EventRepository extends CrudRepository<Event, Integer> {
}

//the reason a class like "public class MyRepository implements EventRepos... isnt needed
// (remember interfaces are just a blueprint) is that spring boot sees this interface and creates it on its own