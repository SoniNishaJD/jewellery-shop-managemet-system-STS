package com.springboot.jewellerysystem.controller;

import com.springboot.jewellerysystem.entity.State; 
import com.springboot.jewellerysystem.service.StateService; 
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RequestParam; 
import java.util.List; 
@Controller 
@RequestMapping(value = "state") 
public class StateController { 
 private StateService stateService; 
    public StateController(StateService stateService) { 
        this.stateService = stateService; 
    }
 
    @GetMapping(value = "/index") 
    public String states(Model model, @RequestParam(name = "keyword", defaultValue = "") String keyword) { 
        List<State> states = stateService.getAllState(); 
        model.addAttribute("listStates", states); 
        model.addAttribute("keyword", keyword); 
        return "admin/list/states_list"; 
    }
 
  @GetMapping(value = "/create") 
    public String formStates(Model model) { 
        model.addAttribute("state", new State()); 
        return "admin/entry/state_entry"; 
    } 
    @GetMapping(value = "/delete") 
    public String deleteState(Integer id, String keyword) { 
        stateService.removeState(id); 
        return "redirect:/states/index?keyword=" + keyword; 
    }
 
    @GetMapping(value = "/update") 
    public String updateState(Model model, Integer id) { 
        State state = stateService.loadStateById(id); 
        model.addAttribute("State", state); 
        return "admin/edit/State_update"; 
    }
 
    @PostMapping(value = "/save") 
    public String save(State state) { 
        stateService.createOrUpdateState(state); 
        return "redirect:/states/index"; 
    }
 
} 
