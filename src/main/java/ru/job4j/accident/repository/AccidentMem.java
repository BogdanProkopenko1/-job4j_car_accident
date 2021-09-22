package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Repository
public class AccidentMem {

    private List<Accident> accidents = new LinkedList<>();

    public AccidentMem() {
        accidents = List.of(
                new Accident(0, "Zero Name", "Zero Desc", "Zero address"),
                new Accident(1, "First Name", "First Desc", "First address"),
                new Accident(2, "Second Name", "Second Desc", "Second address"),
                new Accident(3, "Third Name", "Third Desc", "Third address"),
                new Accident(4, "Fourth Name", "Fourth Desc", "Fourth address")
        );
    }

    public boolean add(Accident accident) {
        return accidents.add(accident);
    }

    public List<Accident> getAccidents() {
        return new ArrayList<>(accidents);
    }
}
