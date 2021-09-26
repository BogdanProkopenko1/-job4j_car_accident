package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentRepository;
import ru.job4j.accident.repository.RuleRepository;
import ru.job4j.accident.repository.TypeRepository;

@Service
@Transactional
public class AccidentService {

    private final AccidentRepository accidentStorage;
    private final RuleRepository ruleStorage;
    private final TypeRepository typeStorage;

    public AccidentService(AccidentRepository accidentMem, RuleRepository ruleStorage, TypeRepository typeStorage) {
        this.accidentStorage = accidentMem;
        this.ruleStorage = ruleStorage;
        this.typeStorage = typeStorage;
    }

    public Iterable<Accident> getAccidents() {
        return accidentStorage.findAll();
    }

    public Iterable<AccidentType> getTypes() {
        return typeStorage.findAll();
    }

    public AccidentType findTypeById(int id) {
        return typeStorage.findById(id).get();
    }

    public Accident findAccidentById(int id) {
        return accidentStorage.findById(id).get();
    }

    public void save(Accident accident, String[] ids) {
        for (String rId : ids) {
            accident.addRule(findRuleById(Integer.parseInt(rId)));
        }
        accidentStorage.save(accident);
    }

    public Iterable<Rule> getRules() {
        return ruleStorage.findAll();
    }

    public Rule findRuleById(int id) {
        return ruleStorage.findById(id).get();
    }
}
