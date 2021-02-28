package WebApplication.Controller;

import ROOT.VO.Chabak.BestAndCount;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/")
public class HomeController {

    @RequestMapping("/")
    public String home(){
        return "index";
    }
}
