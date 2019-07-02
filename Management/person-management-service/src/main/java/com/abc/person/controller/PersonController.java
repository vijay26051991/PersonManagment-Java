package com.abc.person.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.abc.core.controller.CoreController;
import com.abc.core.domain.ApiOperationEnum;
import com.abc.core.domain.PersonInfo;
import com.abc.core.exception.NoDataFoundException;
import com.abc.person.entity.Person;
import com.abc.person.repository.PersonRepository;
import com.abc.person.transformer.request.PersonRequestTransformer;
import com.abc.person.transformer.response.PersonResponseTransformer;
import com.google.common.collect.ImmutableMap;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriTemplate;

/**
 * Rest controller for person api that extends PersonRequest.
 *
 * @author vijaykumar.s 01/07/2019
 */
@RestController
public class PersonController extends CoreController<PersonInfo> {

    PersonRepository personRepository;
    PersonRequestTransformer personRequestTransformer;
    PersonResponseTransformer personResponseTransformer;

    public PersonController(PersonRepository personRepository,
                            PersonRequestTransformer personRequestTransformer,
                            PersonResponseTransformer personResponseTransformer) {
        this.personRepository = personRepository;
        this.personRequestTransformer = personRequestTransformer;
        this.personResponseTransformer = personResponseTransformer;
    }

    @Override
    protected String doPostSaveInfo(PersonInfo info) {
        Person person = personRequestTransformer.transform(info);
        Person savedPerson = personRepository.save(person);
        return savedPerson.getId().toString();
    }

    @Override
    protected PersonInfo dogetInfo(Integer id) {
        Person per = personRepository.getOne(id);
        return personResponseTransformer.transform(per);
    }

    @Override
    protected PersonInfo doUpdateInfo(Integer id, PersonInfo info) throws NoDataFoundException {
        Person person = null;
        try {
            Person existingPerson = personRepository.getOne(id);
            if (info.getAge() != null) {
                existingPerson.setAge(info.getAge());
            }
            if (info.getFavouriteColour() != null) {
                existingPerson.setFavouriteColour(info.getFavouriteColour());
            }
            if (info.getFirstName() != null) {
                existingPerson.setFirstName(info.getFirstName());
            }
            if (info.getLastName() != null) {
                existingPerson.setLastName(info.getLastName());
            }
            if (info.getHobbies() != null) {
                existingPerson.setHobbies(info.getHobbies());
            }
            person = personRepository.save(existingPerson);
        } catch (final RuntimeException ex) {
            throw new NoDataFoundException("No person record found", ex);
        }
        return personResponseTransformer.transform(person);
    }

    @Override
    protected void doDeleteInfo(Integer id) throws NoDataFoundException {
        try {
            personRepository.deleteById(id);
        } catch (RuntimeException ex) {
            throw new NoDataFoundException("No person record found", ex);
        }
    }

    @Override
    protected Collection<PersonInfo> doGetAllInfo() throws NoDataFoundException {
        Collection<PersonInfo> personInfos = new ArrayList<>();
        try {
            personRepository.findAll().forEach(person -> {
                personInfos.add(personResponseTransformer.transform(person));
            });
        } catch (RuntimeException ex) {
            throw new NoDataFoundException("No person record found", ex);
        }
        return personInfos;
    }

    @Override
    protected HttpHeaders constructResponseHeaders(ApiOperationEnum operationType, Class<?> t) {
        return constructResponseHeaders("employee", operationType, t);
    }

    public static HttpHeaders constructResponseHeaders(
            final String serviceContextPath,
            final ApiOperationEnum operationType, final Class<?> t){

        final StringBuilder uri=new StringBuilder("/");
        uri.append(serviceContextPath);
        uri.append("/");
        uri.append(operationType);
        uri.append("/");
        uri.append("{primaryKey}");

        final UriTemplate uriTemplate = new UriTemplate(uri.toString());
        final URI responseUri = uriTemplate.expand(ImmutableMap.of("key", t));
        final HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(responseUri);

        return responseHeaders;
    }
}
