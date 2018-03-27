package com.cxd.springsecurity.web;

import com.cxd.springsecurity.domain.Msg;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 * User: cxd
 * Date: 2017/11/25
 * Description:
 */
@Controller
public class HomeController {

    @RequestMapping("/home")
    public String index(Model model){
        Msg msg = new Msg("测试标题","测试内容","额外信息,只对管理员显示");
        model.addAttribute("msg",msg);
        return "home";
    }
}
