package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {

    private Map<Integer, Accident> accidents = new HashMap<>();
    private Map<Integer, AccidentType> types = new HashMap<>();
    private AtomicInteger accidentIds = new AtomicInteger(1);

    public AccidentMem() {
        Accident a1 = new Accident(
                accidentIds.getAndIncrement(), "Zero Name",
                "Zero Desc", "Zero address");
        Accident a2 = new Accident(
                accidentIds.getAndIncrement(), "First Name",
                "First Desc", "First address");
        Accident a3 = new Accident(
                accidentIds.getAndIncrement(), "Second Name",
                "Second Desc", "Second address");
        Accident a4 = new Accident(
                accidentIds.getAndIncrement(), "Third Name",
                "Third Desc", "Third address");
        Accident a5 = new Accident(
                accidentIds.getAndIncrement(), "Fourth Name",
                "Fourth Desc", "Fourth address");
        accidents.put(a1.getId(), a1);
        accidents.put(a2.getId(), a2);
        accidents.put(a3.getId(), a3);
        accidents.put(a4.getId(), a4);
        accidents.put(a5.getId(), a5);
        AccidentType t1 = AccidentType.of(1, "Две машины");
        AccidentType t2 = AccidentType.of(2, "Машина и человек");
        AccidentType t3 = AccidentType.of(3, "Машина и велосипед");
        types.put(t1.getId(), t1);
        types.put(t2.getId(), t2);
        types.put(t3.getId(), t3);
        a1.setType(t1);
        a2.setType(t2);
        a3.setType(t3);
        a4.setType(t1);
        a5.setType(t2);
    }

    public Accident add(Accident accident) {
        accident.setId(accidentIds.getAndIncrement());
        return accidents.put(accident.getId(), accident);
    }

    public Accident update(Accident accident) {
        return accidents.replace(accident.getId(), accident);
    }

    public Accident findAccidentById(int id) {
        return accidents.get(id);
    }

    public Collection<Accident> getAccidents() {
        return accidents.values();
    }

    public AccidentType findTypeById(int id) {
        return types.get(id);
    }

    public Collection<AccidentType> getTypes() {
        return types.values();
    }
}
