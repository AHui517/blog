package com.kong.blog.controller;

import com.kong.blog.domain.Type;
import com.kong.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping("/types")
    public String types(@PageableDefault(size=5,sort ={"id"}) Pageable pageable, Model model){
        Page<Type> types = typeService.listType(pageable);
        model.addAttribute("types",types);
        return "admin/types";
    }

    @GetMapping("/types/input")
    public String typeInput(){
        return "admin/types-input";
    }

    @PostMapping("/types")
    public String saveType(Type type, RedirectAttributes attributes){

        if (type.getId()!=null){
            typeService.updateType(type.getId(),type);
            return "redirect:/admin/types";
        }

        Type tem = typeService.findByName(type.getName());
        if (tem!=null){
            attributes.addFlashAttribute("m","不能重复添加");
            return "redirect:/admin/types/input";
        }
        attributes.addFlashAttribute("msg","操作成功");
        typeService.saveType(type);
        return "redirect:/admin/types";
    }

    @GetMapping("/edit/{id}")
    public String aditType(@PathVariable Long id, Model model){
        Type t = typeService.getType(id);
        model.addAttribute("t",t);
        return "admin/types-edit";
    }

    @GetMapping("/delete/{id}")
    public String deleteType(@PathVariable Long id){
        typeService.deleteType(id);
        return "redirect:/admin/types";
    }
}
