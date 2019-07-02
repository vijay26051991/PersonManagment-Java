package com.abc.core.domain;

import java.util.Objects;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author vijaykumar.s 12/14/2016
 */
@ApiModel(description = "Person information")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonInfo {

    @ApiModelProperty(value = "Person id", required = false)
    @Valid
    @JsonIgnore
    private final Integer id;

    @ApiModelProperty(value = "Person first name", required = true)
    @NotNull
    @Valid
    private final String firstName;

    @ApiModelProperty(value = "Person last name", required = true)
    @NotNull
    @Valid
    private final String lastName;

    @ApiModelProperty(value = "Person age", required = true)
    @NotNull
    @Valid
    private final Integer age;

    @ApiModelProperty(value = "Person favourite colour", required = true)
    @NotNull
    @Valid
    private final String favouriteColour;

    @ApiModelProperty(value = "Person hobbies", required = true)
    @NotNull
    @Valid
    private final Set<String> hobbies;

    public PersonInfo(
            @JsonProperty("id") final Integer id,
            @JsonProperty("first_name") final String firstName,
            @JsonProperty("last_name") final String lastName,
            @JsonProperty("age") final Integer age,
            @JsonProperty("favourite_colour") final String favouriteColour,
            @JsonProperty("hobby") final Set<String> hobbies) {

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.favouriteColour = favouriteColour;
        this.hobbies = hobbies;
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getAge() {
        return age;
    }

    public String getFavouriteColour() {
        return favouriteColour;
    }

    public Set<String> getHobbies() {
        return hobbies;
    }

    public static class Builder {
        private Integer id;
        private String firstName;
        private String lastName;
        private Integer age;
        private String favouriteColour;
        private Set<String> hobbies;

        private Builder() {
        }

        public Builder withId(final Integer theId) {
            this.id = theId;
            return this;
        }

        public Builder withFirstName(final String theFirstName) {
            this.firstName = theFirstName;
            return this;
        }

        public Builder withLastName(final String theLastName) {
            this.lastName = theLastName;
            return this;
        }

        public Builder withAge(final Integer theAge) {
            this.age = theAge;
            return this;
        }

        public Builder withFavouriteColour(final String theFavouriteColour) {
            this.favouriteColour = theFavouriteColour;
            return this;
        }

        public Builder withHobbies(final Set<String> theHobbies) {
            this.hobbies = theHobbies;
            return this;
        }

        public PersonInfo build() {
            return new PersonInfo(id, firstName, lastName, age, favouriteColour, hobbies);
        }

    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonInfo person = (PersonInfo) o;
        return Objects.equals(id, person.id) &&
                firstName.equals(person.firstName) &&
                lastName.equals(person.lastName) &&
                age.equals(person.age) &&
                favouriteColour.equals(person.favouriteColour) &&
                hobbies.equals(person.hobbies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, age, favouriteColour, hobbies);
    }
}