package cz.jiripinkas.jba.simple;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/simple")
public class SimpleController {

    @RequestMapping("/empty")
    public String empty() {
        return "empty";
    }
    
}
