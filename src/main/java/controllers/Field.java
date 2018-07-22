package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/field")
public class Field {

    @RequestMapping("/fieldExample")
    public String fieldMethod(HttpServletRequest req, HttpSession session){
        String cellNumber = req.getParameter("inputId");
        if (session.getAttribute("fieldMap") == null){
            System.out.println("fieldMap = null, создание в сессии fieldMap");
            Map<String, String> fieldMap = new HashMap<String, String>();
            fieldMap.put(cellNumber, "x");
            session.setAttribute("fieldMap", fieldMap);
            Logic.actionPC(fieldMap);
        } else {
            Map<String, String> fieldMap = (Map<String, String>) session.getAttribute("fieldMap");
            if(!fieldMap.containsKey(cellNumber)){
                fieldMap.put(cellNumber, "x");
                session.setAttribute("checkWin", Logic.actionPC(fieldMap));
            }
        }

        System.out.println("fieldMap = "+session.getAttribute("fieldMap"));
        System.out.println("parameter from JS = "+req.getParameter("inputId"));

        return "pages/game";
    }

    @RequestMapping("/fieldReset")
    public String fieldReset(HttpSession session){
        session.setAttribute("fieldMap", null);
        session.setAttribute("checkWin", "");
        return "pages/game";
    }

}
