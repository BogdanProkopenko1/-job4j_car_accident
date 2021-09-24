package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentMem;

import java.util.Collection;

@Service
public class AccidentService {

    private final AccidentMem storage;

    public AccidentService(AccidentMem accidentMem) {
        this.storage = accidentMem;
    }

    public Collection<Accident> getAccidents() {
        return storage.getAccidents();
    }

    public Collection<AccidentType> getTypes() {
        return storage.getTypes();
    }

    public AccidentType findTypeById(int id) {
        return storage.findTypeById(id);
    }

    public Accident findAccidentById(int id) {
        return storage.findAccidentById(id);
    }

    public Accident save(Accident accident) {
        if (accident.getId() == 0) {
            return storage.add(accident);
        } else {
            return storage.update(accident);
        }
    }

    public Collection<Rule> getRules() {
        return storage.getRules();
    }

    public Rule findRuleById(int id) {
        return storage.findRuleById(id);
    }
}
