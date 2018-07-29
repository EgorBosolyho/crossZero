package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/field")
public class Field {
    @Resource
    Logic logic;

    @RequestMapping("/fieldExample")
    public String fieldMethod(HttpSession session, @RequestParam("inputId") String userAction){
            if(!logic.fieldMap.containsKey(userAction)){
                logic.actionPC(userAction);
                session.setAttribute("checkWin", logic.checkEndGame());
                session.setAttribute("fieldMap", logic.fieldMap);
            }

        return "pages/game";
    }

    @RequestMapping("/fieldReset")
    public String fieldReset(HttpSession session){
        logic.resetField();
        session.setAttribute("checkWin", null);
        session.setAttribute("fieldMap", null);
        return "pages/game";
    }

}
