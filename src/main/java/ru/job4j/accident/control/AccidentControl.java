package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.service.AccidentService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AccidentControl {

    private final AccidentService service;

    public AccidentControl(AccidentService service) {
        this.service = service;
    }

    @GetMapping("/create")
    public String create(Model model) {
        List<Rule> rules = new ArrayList<>();
        service.getRules().forEach(rules::add);
        List<AccidentType> types = new ArrayList<>();
        service.getTypes().forEach(types::add);
        model.addAttribute("types", types);
        model.addAttribute("rules", rules);
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, @RequestParam("type.id") int id, HttpServletRequest req) {
        accident.setType(service.findTypeById(id));
        service.save(accident, req.getParameterValues("rIds"));
        return "redirect:/";
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") int id, Model model) {
        List<Rule> rules = new ArrayList<>();
        service.getRules().forEach(rules::add);
        List<AccidentType> types = new ArrayList<>();
        service.getTypes().forEach(types::add);
        Accident accident = service.findAccidentById(id);
        List<Rule> rulesSelected = new ArrayList<>(accident.getRules());
        rules.removeAll(rulesSelected);
        types.remove(accident.getType());
        model.addAttribute("accident", service.findAccidentById(id));
        model.addAttribute("types", types);
        model.addAttribute("type", accident.getType());
        model.addAttribute("rulesSelected", rulesSelected);
        model.addAttribute("rules", rules);
        return "accident/update";
    }
}
