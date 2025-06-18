package com.alvaroeb.backend.application.util;

import com.alvaroeb.backend.domain.model.Person;
import com.alvaroeb.backend.domain.model.Planet;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Sort;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

class PersonComparatorsTest {

    private Person person(
            String name, Integer height, Integer mass, String hairColor, String skinColor,
            String eyeColor, String birthYear, String gender, String homeworld,
            LocalDateTime created, LocalDateTime edited) {
        Person p = new Person();
        p.setName(name);
        p.setHeight(height);
        p.setMass(mass);
        p.setHairColor(hairColor);
        p.setSkinColor(skinColor);
        p.setEyeColor(eyeColor);
        p.setBirthYear(birthYear);
        p.setGender(gender);
        if (homeworld != null) {
            Planet planet = new Planet();
            planet.setName(homeworld);
            p.setHomeworld(planet);
        }
        p.setCreated(created);
        p.setEdited(edited);
        return p;
    }

    @Test
    void testSortByNameAscending() {
        Person a = person("Luke", 172, 77, "Blond", "Fair", "Blue", "19BBY", "Male", "Tatooine", LocalDateTime.now(),
                LocalDateTime.now());
        Person b = person("Leia", 150, 49, "Brown", "Light", "Brown", "19BBY", "Female", "Alderaan",
                LocalDateTime.now(), LocalDateTime.now());
        List<Person> people = Arrays.asList(a, b);
        people.sort(PersonComparators.getComparator("name", Sort.Direction.ASC));
        Assertions.assertThat(people.get(0).getName()).isEqualTo("Leia");
        Assertions.assertThat(people.get(1).getName()).isEqualTo("Luke");
    }

    @Test
    void testSortByHeightDescending() {
        Person a = person("Luke", 172, 77, "Blond", "Fair", "Blue", "19BBY", "Male", "Tatooine", LocalDateTime.now(),
                LocalDateTime.now());
        Person b = person("Leia", 150, 49, "Brown", "Light", "Brown", "19BBY", "Female", "Alderaan",
                LocalDateTime.now(), LocalDateTime.now());
        List<Person> people = Arrays.asList(a, b);
        people.sort(PersonComparators.getComparator("height", Sort.Direction.DESC));
        Assertions.assertThat(people.get(0).getName()).isEqualTo("Luke");
        Assertions.assertThat(people.get(1).getName()).isEqualTo("Leia");
    }

    @Test
    void testSortByMassWithNulls() {
        Person a = person("Luke", 172, null, "Blond", "Fair", "Blue", "19BBY", "Male", "Tatooine", LocalDateTime.now(),
                LocalDateTime.now());
        Person b = person("Leia", 150, 49, "Brown", "Light", "Brown", "19BBY", "Female", "Alderaan",
                LocalDateTime.now(), LocalDateTime.now());
        List<Person> people = Arrays.asList(a, b);
        people.sort(PersonComparators.getComparator("mass", Sort.Direction.ASC));
        Assertions.assertThat(people.get(0).getName()).isEqualTo("Leia");
        Assertions.assertThat(people.get(1).getName()).isEqualTo("Luke");
    }

    @Test
    void testSortByHomeworldAscending() {
        Person a = person("Luke", 172, 77, "Blond", "Fair", "Blue", "19BBY", "Male", "Tatooine", LocalDateTime.now(),
                LocalDateTime.now());
        Person b = person("Leia", 150, 49, "Brown", "Light", "Brown", "19BBY", "Female", "Alderaan",
                LocalDateTime.now(), LocalDateTime.now());
        List<Person> people = Arrays.asList(a, b);
        people.sort(PersonComparators.getComparator("homeworld", Sort.Direction.ASC));
        Assertions.assertThat(people.get(0).getName()).isEqualTo("Leia");
        Assertions.assertThat(people.get(1).getName()).isEqualTo("Luke");
    }

    @Test
    void testSortByCreatedDescending() {
        LocalDateTime now = LocalDateTime.now();
        Person a = person("Luke", 172, 77, "Blond", "Fair", "Blue", "19BBY", "Male", "Tatooine", now.minusDays(1), now);
        Person b = person("Leia", 150, 49, "Brown", "Light", "Brown", "19BBY", "Female", "Alderaan", now, now);
        List<Person> people = Arrays.asList(a, b);
        people.sort(PersonComparators.getComparator("created", Sort.Direction.DESC));
        Assertions.assertThat(people.get(0).getName()).isEqualTo("Leia");
        Assertions.assertThat(people.get(1).getName()).isEqualTo("Luke");
    }

    @Test
    void testSortByUnknownFieldDefaultsToName() {
        Person a = person("Luke", 172, 77, "Blond", "Fair", "Blue", "19BBY", "Male", "Tatooine", LocalDateTime.now(),
                LocalDateTime.now());
        Person b = person("Leia", 150, 49, "Brown", "Light", "Brown", "19BBY", "Female", "Alderaan",
                LocalDateTime.now(), LocalDateTime.now());
        List<Person> people = Arrays.asList(a, b);
        people.sort(PersonComparators.getComparator("unknown", Sort.Direction.ASC));
        Assertions.assertThat(people.get(0).getName()).isEqualTo("Leia");
        Assertions.assertThat(people.get(1).getName()).isEqualTo("Luke");
    }

    @Test
    void testSortByNullSortByDefaultsToName() {
        Person a = person("Luke", 172, 77, "Blond", "Fair", "Blue", "19BBY", "Male", "Tatooine", LocalDateTime.now(),
                LocalDateTime.now());
        Person b = person("Leia", 150, 49, "Brown", "Light", "Brown", "19BBY", "Female", "Alderaan",
                LocalDateTime.now(), LocalDateTime.now());
        List<Person> people = Arrays.asList(a, b);
        people.sort(PersonComparators.getComparator(null, Sort.Direction.ASC));
        Assertions.assertThat(people.get(0).getName()).isEqualTo("Leia");
        Assertions.assertThat(people.get(1).getName()).isEqualTo("Luke");
    }
}