package com.abc.person.transformer.request;

import com.abc.core.domain.PersonInfo;
import com.abc.core.transformer.RequestResponseTransformer;
import com.abc.person.entity.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonRequestTransformer implements RequestResponseTransformer<PersonInfo, Person> {


    @Override
    public Person transform(final PersonInfo personInfo) {
        Person person = new Person();
        person.setId(personInfo.getId());
        person.setFirstName(personInfo.getFirstName());
        person.setLastName(personInfo.getLastName());
        person.setFavouriteColour(personInfo.getFavouriteColour());
        person.setAge(personInfo.getAge());
        person.setHobbies(personInfo.getHobbies());
        return person;
    }
}
