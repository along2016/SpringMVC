package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by alion on 2017/1/20.
 */
@Controller
public class CarRaceController {

    @RequestMapping(value = "/main")
    public String mainPage(){
        return "main";
    }
}
