package com.duan.blogos.web.cleanblog.blogger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created on 2018/2/8.
 *
 * @author DuanJiaNing
 */
@Controller
@RequestMapping("register")
public class RegisterController {

    @RequestMapping
    public String registerPage() {
        return "blogger/register";
    }
}
