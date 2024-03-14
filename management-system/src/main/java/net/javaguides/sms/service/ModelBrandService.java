package net.javaguides.sms.service;


import java.util.List;

import net.javaguides.sms.entity.ModelBrand;

public interface ModelBrandService {

    List<ModelBrand> getAllModels();

    void saveModel(ModelBrand modelBrand);

    ModelBrand getModelById(Long id);
}

