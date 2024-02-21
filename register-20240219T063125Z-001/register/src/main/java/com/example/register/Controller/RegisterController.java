package com.example.register.Controller;
import com.example.register.Entity.Register;
import com.example.register.Service.RegisterServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/register")
public class RegisterController {

    @Autowired
    private RegisterServices registerServices;
    @PostMapping("/save")
    public String saveRegister(@RequestBody Register registers)
    {

        registerServices.saveorUpdate(registers);
        return registers.get_id();
    }

    @GetMapping("/getAll")
    public Iterable<Register>getRegister()
    {

        return registerServices.listAll();
    }
}
