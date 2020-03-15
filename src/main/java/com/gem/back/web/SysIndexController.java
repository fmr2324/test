package com.gem.back.web;

import com.gem.back.entity.SysMenu;
import com.gem.back.service.ISysMenuService;
import com.gem.back.service.ISysRoleService;
import com.gem.back.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Author: jzhang
 * @WX: 15250420158
 * @Date: 2020/2/18 16:23
 * @Description: 后台主页面控制器
 */
@Controller
@RequestMapping("/a")
public class SysIndexController {
    @Autowired
    private ISysMenuService sysMenuService;
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private ISysRoleService sysRoleService;

    @RequestMapping("/index")
    public String index(Model model) {
        //读取当前登录成功用户的角色，并根据角色加载菜单
        //获得spring security用户
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        //读取当前用户管理的菜单
        List<SysMenu> menuList = sysMenuService.findListByName(userDetails.getUsername());

        //获取当前登录的系统用户
        model.addAttribute("menus",menuList);
        return "sys/index";
    }

    /**
     * 读取控制台数据
     */
    @RequestMapping("/console")
    public String console(Model model) {
        //用户数
        Integer userCount = sysUserService.count();

        //角色数
        Integer roleCount = sysRoleService.count();

        //菜单数
        Integer menuCount = sysMenuService.count();
        //........................
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        //读取当前用户管理的菜单
        List<SysMenu> menuList = sysMenuService.findListByName(userDetails.getUsername());

        //获取当前登录的系统用户
        model.addAttribute("menus",menuList);
        //............
        model.addAttribute("userCount",userCount);
        model.addAttribute("roleCount",roleCount);
        model.addAttribute("menuCount",menuCount);
        return "sys/console";
    }

}
