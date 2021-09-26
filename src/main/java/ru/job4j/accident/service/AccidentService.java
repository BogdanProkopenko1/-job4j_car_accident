package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentJdbcTemplate;

import java.util.Collection;

@Service
public class AccidentService {

    private final AccidentJdbcTemplate storage;

    public AccidentService(AccidentJdbcTemplate accidentMem) {
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

    public void save(Accident accident, String[] ids) {
        for (String rId : ids) {
            accident.addRule(findRuleById(Integer.parseInt(rId)));
        }
        if (accident.getId() == 0) {
            storage.add(accident);
        } else {
            storage.update(accident);
        }
    }

    public Collection<Rule> getRules() {
        return storage.getRules();
    }

    public Rule findRuleById(int id) {
        return storage.findRuleById(id);
    }
}
