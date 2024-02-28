package com.example.SpringMangoProject.Controller;

import com.example.SpringMangoProject.Entity.Login;
import com.example.SpringMangoProject.Service.LoginServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/v1/login")
public class LoginController {

    @Autowired
    private LoginServices loginServices;
    // Added constructor

            @PostMapping(value = "/save")
        private  String saveLogin(@RequestBody Login logins)
        {
            loginServices.saveorUpdate(logins);
            return logins.get_id();
        }

    @GetMapping(value = "/getAll")
    private Iterable<Login>getLogins()
    {

        return loginServices.listAll();

    }
    @PutMapping(value = "/edit/{id}")
    private Login update(@RequestBody Login logins,@PathVariable(name = "id")String _id)
    {
        logins.set_id(_id);
        loginServices.saveorUpdate(logins);
        return logins;

    }

    @DeleteMapping("/delete/{id}")
    private void deleteLogin(@PathVariable("id")String _id)
    {
        loginServices.deleteLogin(_id);

    }

    @RequestMapping("/search/{id}")
    private Login getStudent(@PathVariable(name="id")String loginid)
    {
        return loginServices.getLoginByID(loginid);
    }
}
