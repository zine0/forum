package com.zineyu.forum.backend.service.impl.user.account;

import com.zineyu.forum.backend.pojo.User;
import com.zineyu.forum.backend.service.impl.utils.UserDetailsImpl;
import com.zineyu.forum.backend.service.user.account.InfoService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class InfoServiceImpl implements InfoService {
    @Override
    public Map<String, String> getInfo() {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) token.getPrincipal();
        User user = userDetails.getUser();
        Map<String, String> info = new HashMap<>();
        info.put("message", "success");
        info.put("id", user.getId().toString());
        info.put("username", user.getUsername());
        info.put("avatar", user.getAvatar());
        info.put("permission", user.getPermission().toString());
        return info;
    }
}
