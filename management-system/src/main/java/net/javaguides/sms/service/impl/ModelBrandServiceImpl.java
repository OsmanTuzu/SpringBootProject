package net.javaguides.sms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.javaguides.sms.entity.ModelBrand;
import net.javaguides.sms.repository.ModelBrandRepository;
import net.javaguides.sms.service.ModelBrandService;

@Service
public class ModelBrandServiceImpl implements ModelBrandService {

    private final ModelBrandRepository modelBrandRepository;

    @Autowired
    public ModelBrandServiceImpl(ModelBrandRepository modelBrandRepository) {
        this.modelBrandRepository = modelBrandRepository;
    }

    @Override
    @Transactional
    public List<ModelBrand> getAllModels() {
        return modelBrandRepository.findAll();
    }

    @Override
    @Transactional
    public void saveModel(ModelBrand modelBrand) {
        modelBrandRepository.save(modelBrand);
    }

    @Override
    @Transactional
    public ModelBrand getModelById(Long id) {
        return modelBrandRepository.findById(id).orElse(null);
    }
}

