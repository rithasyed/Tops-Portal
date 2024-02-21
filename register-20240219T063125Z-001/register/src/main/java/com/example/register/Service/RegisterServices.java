package com.example.register.Service;

import com.example.register.Entity.Register;
import com.example.register.Repo.RegisterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterServices {

    @Autowired
    private RegisterRepo repo;

    public void saveorUpdate(Register registers) {

        repo.save(registers);

    }

    public Iterable<Register> listAll() {


        return this.repo.findAll();
    }
}
