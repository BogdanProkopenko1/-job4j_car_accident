package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {

    private volatile Map<Integer, Accident> accidents = new HashMap<>();
    private AtomicInteger integer = new AtomicInteger(1);

    public AccidentMem() {
        Accident a1 = new Accident(integer.getAndIncrement(), "Zero Name", "Zero Desc", "Zero address");
        Accident a2 = new Accident(integer.getAndIncrement(), "First Name", "First Desc", "First address");
        Accident a3 = new Accident(integer.getAndIncrement(), "Second Name", "Second Desc", "Second address");
        Accident a4 = new Accident(integer.getAndIncrement(), "Third Name", "Third Desc", "Third address");
        Accident a5 = new Accident(integer.getAndIncrement(), "Fourth Name", "Fourth Desc", "Fourth address");
        accidents.put(a1.getId(), a1);
        accidents.put(a2.getId(), a2);
        accidents.put(a3.getId(), a3);
        accidents.put(a4.getId(), a4);
        accidents.put(a5.getId(), a5);
    }

    public Accident save(Accident accident) {
        if (accident.getId() == 0) {
            return this.add(accident);
        } else {
            return this.update(accident);
        }
    }

    private Accident add(Accident accident) {
        accident.setId(integer.getAndIncrement());
        return accidents.put(accident.getId(), accident);
    }

    private Accident update(Accident accident) {
        return accidents.replace(accident.getId(), accident);
    }

    public Accident findById(int id) {
        return accidents.get(id);
    }

    public Map<Integer, Accident> getAccidents() {
        return accidents;
    }
}
