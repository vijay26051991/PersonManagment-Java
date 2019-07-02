package com.abc.person.transformer.response;

import com.abc.core.domain.PersonInfo;
import com.abc.core.transformer.RequestResponseTransformer;
import com.abc.person.entity.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonResponseTransformer implements RequestResponseTransformer<Person, PersonInfo> {
    @Override
    public PersonInfo transform(Person person) {
        final PersonInfo personInfo = PersonInfo.builder()
                .withId(person.getId())
                .withFirstName(person.getFirstName())
                .withLastName(person.getLastName())
                .withAge(person.getAge())
                .withFavouriteColour(person.getFavouriteColour())
                .withHobbies(person.getHobbies())
                .build();
        return personInfo;
    }
}
