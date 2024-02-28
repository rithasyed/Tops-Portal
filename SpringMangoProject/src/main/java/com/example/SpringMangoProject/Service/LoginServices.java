package com.example.SpringMangoProject.Service;

import com.example.SpringMangoProject.Entity.Login;
import com.example.SpringMangoProject.Repo.LoginRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServices {

    @Autowired
    public LoginRepo repo;

    public Login saveorUpdate(Login logins) {
        repo.save(logins);
        return logins;
    }

    public Iterable<Login> listAll() {
        return this.repo.findAll();
    }

    public void deleteLogin(String id) {
        repo.deleteById(id);
    }

    public Login getLoginByID(String loginid) {
        return repo.findById(loginid).get();
    }
}
