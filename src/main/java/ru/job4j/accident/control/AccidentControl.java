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

import java.util.ArrayList;
import java.util.List;

@Controller
public class AccidentControl {

    private final AccidentMem storage;

    public AccidentControl(AccidentMem storage) {
        this.storage = storage;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("types", List.copyOf(storage.getTypes().values()));
        return "accident/create";
        }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, @RequestParam("type.id") int id) {
        accident.setType(storage.findType(id));
        storage.save(accident);
        return "redirect:/";
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") int id, @RequestParam("type") int tid, Model model) {
        AccidentType type = storage.findType(tid);
        List<AccidentType> types = new ArrayList<>(List.copyOf(storage.getTypes().values()));
        types.remove(type);
        model.addAttribute("accident", storage.findById(id));
        model.addAttribute("types", types);
        model.addAttribute("type", type);
        return "accident/update";
    }
}
