package com.zineyu.forum.backend.controller.user.account;

import com.zineyu.forum.backend.service.user.account.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class InfoController {

    @Autowired
    private InfoService infoService;

    @GetMapping("/user/info")
    public Map<String, String> info() {
        return infoService.getInfo();
    }
}
