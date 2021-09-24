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
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Controller
public class AccidentControl {

    private final AccidentService service;

    public AccidentControl(AccidentService service) {
        this.service = service;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("types", service.getTypes());
        model.addAttribute("rules", service.getRules());
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, @RequestParam("type.id") int id, HttpServletRequest req) {
        accident.setType(service.findTypeById(id));
        String[] ids = req.getParameterValues("rIds");
        for (String rId : ids) {
            accident.addRule(service.findRuleById(Integer.parseInt(rId)));
        }
        service.save(accident);
        return "redirect:/";
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") int id, Model model) {
        Accident accident = service.findAccidentById(id);
        List<Rule> rulesSelected = new ArrayList<>(accident.getRules());
        List<Rule> rules = new ArrayList<>(service.getRules());
        rules.removeAll(rulesSelected);
        List<AccidentType> types = new ArrayList<>(service.getTypes());
        types.remove(accident.getType());
        model.addAttribute("accident", service.findAccidentById(id));
        model.addAttribute("types", types);
        model.addAttribute("type", accident.getType());
        model.addAttribute("rulesSelected", rulesSelected);
        model.addAttribute("rules", rules);
        return "accident/update";
    }
}
