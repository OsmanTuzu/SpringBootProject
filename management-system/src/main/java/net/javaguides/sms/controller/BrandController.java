package net.javaguides.sms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import net.javaguides.sms.entity.Brand;
import net.javaguides.sms.entity.City;
import net.javaguides.sms.service.BrandService;

@Controller
public class BrandController {

    private BrandService brandService;

    //@Autowired
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/add-brand")
    public String showAddBrandForm(Model model) {
        model.addAttribute("brand", new Brand());
        List<Brand> brands = brandService.getAllBrands();
        model.addAttribute("brands", brands);
        return "add_brand"; 
    }


    @PostMapping("/add")
    public String addBrand(@ModelAttribute Brand brand) {
        brandService.addBrand(brand);
        return "redirect:/add-brand";
    }

     
}

