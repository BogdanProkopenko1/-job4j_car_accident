package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.repository.AccidentMem;

import java.util.Collection;

@Service
public class AccidentService {

    private final AccidentMem accidentMem;

    public AccidentService(AccidentMem accidentMem) {
        this.accidentMem = accidentMem;
    }

    public Collection<Accident> getAccidents() {
        return accidentMem.getAccidents();
    }

    public Collection<AccidentType> getTypes() {
        return accidentMem.getTypes();
    }

    public AccidentType findTypeById(int id) {
        return accidentMem.findTypeById(id);
    }

    public Accident findAccidentById(int id) {
        return accidentMem.findAccidentById(id);
    }

    public Accident save(Accident accident) {
        if (accident.getId() == 0) {
            return accidentMem.add(accident);
        } else {
            return accidentMem.update(accident);
        }
    }
}
