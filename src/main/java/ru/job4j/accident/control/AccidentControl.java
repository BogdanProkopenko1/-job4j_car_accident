package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.repository.AccidentMem;
import ru.job4j.accident.service.AccidentService;

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
        model.addAttribute("types", List.copyOf(service.getTypes()));
        return "accident/create";
        }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, @RequestParam("type.id") int id) {
        accident.setType(service.findTypeById(id));
        service.save(accident);
        return "redirect:/";
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") int id, @RequestParam("type") int tid, Model model) {
        AccidentType type = service.findTypeById(tid);
        List<AccidentType> types = new ArrayList<>(List.copyOf(service.getTypes()));
        types.remove(type);
        model.addAttribute("accident", service.findAccidentById(id));
        model.addAttribute("types", types);
        model.addAttribute("type", type);
        return "accident/update";
    }
}
