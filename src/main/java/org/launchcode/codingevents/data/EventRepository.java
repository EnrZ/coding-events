package org.launchcode.codingevents.data;

import org.launchcode.codingevents.models.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//17.2 (primary key is primitive int but here its Integer (java will autobox)
@Repository
public interface EventRepository extends CrudRepository<Event, Integer> {
}
