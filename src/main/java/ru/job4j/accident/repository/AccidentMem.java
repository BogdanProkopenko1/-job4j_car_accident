package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {

    private List<Accident> accidents = new ArrayList<>();
    private AtomicInteger integer = new AtomicInteger();

    public AccidentMem() {
        accidents.addAll(List.of(
                new Accident( "Zero Name", "Zero Desc", "Zero address"),
                new Accident("First Name", "First Desc", "First address"),
                new Accident("Second Name", "Second Desc", "Second address"),
                new Accident("Third Name", "Third Desc", "Third address"),
                new Accident("Fourth Name", "Fourth Desc", "Fourth address")

        ));
    }

    public boolean save(Accident accident) {
        accident.setId(integer.getAndIncrement());
        return accidents.add(accident);
    }

    public boolean update(Accident accident) {
        return accidents.set(accident.getId(), accident) != null;
    }

    public List<Accident> getAccidents() {
        return accidents;
    }
}
