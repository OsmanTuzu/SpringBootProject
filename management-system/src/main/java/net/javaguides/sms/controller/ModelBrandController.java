package net.javaguides.sms.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.javaguides.sms.entity.ModelBrand;
import net.javaguides.sms.service.BrandService;
import net.javaguides.sms.service.ModelBrandService;

@Controller
public class ModelBrandController {

    private final ModelBrandService modelBrandService;
    private final BrandService brandService;

    @Autowired
    public ModelBrandController(ModelBrandService modelBrandService, BrandService brandService) {
        this.modelBrandService = modelBrandService;
        this.brandService = brandService;
    }

    

    @GetMapping("/add-model")
    public String showAddModelForm(Model model) {
        ModelBrand newModel = new ModelBrand();
        model.addAttribute("model", newModel);
        model.addAttribute("brands", brandService.getAllBrands());
        model.addAttribute("models", modelBrandService.getAllModels());
        return "add_model";
    }
    
    
    @PostMapping("/add1")
    public String addModel(@ModelAttribute ModelBrand model) {
        modelBrandService.saveModel(model);
        return "redirect:/add-model";
    }



    
    
    

}

