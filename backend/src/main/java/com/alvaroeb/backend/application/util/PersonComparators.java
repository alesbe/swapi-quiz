package com.alvaroeb.backend.application.util;

import com.alvaroeb.backend.domain.model.Person;
import org.springframework.data.domain.Sort;

import java.util.Comparator;

public class PersonComparators {

    public static Comparator<Person> getComparator(String sortBy, Sort.Direction direction) {
        Comparator<Person> comparator;

        switch (sortBy != null ? sortBy.toLowerCase() : "") {
            case "height":
                comparator = Comparator.comparing(Person::getHeight, Comparator.nullsLast(Comparator.naturalOrder()));
                break;
            case "mass":
                comparator = Comparator.comparing(Person::getMass, Comparator.nullsLast(Comparator.naturalOrder()));
                break;
            case "haircolor":
                comparator = Comparator.comparing(Person::getHairColor, Comparator.nullsLast(String.CASE_INSENSITIVE_ORDER));
                break;
            case "skincolor":
                comparator = Comparator.comparing(Person::getSkinColor, Comparator.nullsLast(String.CASE_INSENSITIVE_ORDER));
                break;
            case "eyecolor":
                comparator = Comparator.comparing(Person::getEyeColor, Comparator.nullsLast(String.CASE_INSENSITIVE_ORDER));
                break;
            case "birthyear":
                comparator = Comparator.comparing(Person::getBirthYear, Comparator.nullsLast(String.CASE_INSENSITIVE_ORDER));
                break;
            case "gender":
                comparator = Comparator.comparing(Person::getGender, Comparator.nullsLast(String.CASE_INSENSITIVE_ORDER));
                break;
            case "homeworld":
                comparator = Comparator.comparing(
                        person -> person.getHomeworld() != null ? person.getHomeworld().getName() : null,
                        Comparator.nullsLast(String.CASE_INSENSITIVE_ORDER)
                );
                break;
            case "created":
                comparator = Comparator.comparing(Person::getCreated, Comparator.nullsLast(Comparator.naturalOrder()));
                break;
            case "edited":
                comparator = Comparator.comparing(Person::getEdited, Comparator.nullsLast(Comparator.naturalOrder()));
                break;
            case "name":
            default:
                comparator = Comparator.comparing(Person::getName, Comparator.nullsLast(String.CASE_INSENSITIVE_ORDER));
                break;
        }

        return direction.isAscending() ? comparator : comparator.reversed();
    }
}
