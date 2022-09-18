package com.example.humanressapp.service.concretes;

import com.example.humanressapp.model.HR;
import com.example.humanressapp.repository.HRRepository;
import com.example.humanressapp.service.abstracts.HRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HRManager  {
    /*

    private  final HRRepository hrRepository;

    @Autowired
    public HRManager(HRRepository hrRepository) {
        this.hrRepository = hrRepository;
    }

    @Override
    public void add(HR hr) {
        hrRepository.save(hr);

    }

    @Override
    public void update(HR hr) {

      hrRepository.save(hr);

    }

    @Override
    public void delete(Long id) {
        HR hr =hrRepository.getById(id);
        if (hr!=null){
            hrRepository.delete(hr);

        }
    }
    @Override
    public List<HR> getAll() {
        return hrRepository.findAll();
    }
    @Override
    public HR getById(Long id) {
        return hrRepository.getById(id);
    }
     */
}
