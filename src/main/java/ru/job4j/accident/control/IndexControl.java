package ru.job4j.accident.control;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.service.AccidentService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexControl {

    private AccidentService service;

    public IndexControl(AccidentService service) {
        this.service = service;
    }
    @GetMapping("/")
    public String index(Model model) {
        List<Accident> accidents = new ArrayList<>();
        service.getAccidents().forEach(accidents::add);
        model.addAttribute("accidents", accidents);
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return "index";
    }
}
