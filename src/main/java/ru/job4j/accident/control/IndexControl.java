package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.model.Accident;

import java.util.List;

@Controller
public class IndexControl {
    @GetMapping("/")
    public String index(Model model) {
        List<Accident> accidents = List.of(
                new Accident(0, "Zero Name", "Zero Desc", "Zero address"),
                new Accident(1, "First Name", "First Desc", "First address"),
                new Accident(2, "Second Name", "Second Desc", "Second address"),
                new Accident(3, "Third Name", "Third Desc", "Third address"),
                new Accident(4, "Fourth Name", "Fourth Desc", "Fourth address")
        );
        model.addAttribute("accidents", accidents);
        return "index";
    }
}
