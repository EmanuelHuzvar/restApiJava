package sk.kasv.huzvare.rest;

import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {

    @RequestMapping(method = RequestMethod.GET, value="/hello")
    public String hello(){
        return "Hello world! I am your new RESTful app";
    }

    @PostMapping(value="/hello/{name}")
    public String sayHello(@PathVariable("name") String username){
        return "Hello "+username;
    }

    @GetMapping(value="/bmi/{name}")
    public String bmi(@PathVariable String name, @RequestParam("m") int weight,
                      @RequestParam("h") int height){
        double bmiCoeficient = weight/(height*height/10000.0);
        return "Your name is "+name+ " and your bmi coeficient is :"+bmiCoeficient;


    }
}

