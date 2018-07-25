package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;

@Controller
@RequestMapping("/field")
public class Field {
    @Resource
    Logic logic;


    @RequestMapping("/fieldExample")
    public String fieldMethod(HttpServletRequest req, HttpSession session){
        String userAction = req.getParameter("inputId");

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
