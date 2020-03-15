package com.gem.front.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gem.back.entity.Film;
import com.gem.back.service.IFilmService;
import org.apache.tomcat.jni.Global;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Auther: jzhang
 * @Date: 2018/11/2 13:45
 * @Description: 前台影片控制器
 */
@RequestMapping("/f/film")
@Controller
public class FrontFilmController {
    @Autowired
    private IFilmService filmService;

    @ModelAttribute("film")
    public Film get(Integer id) {
        if (id != null){
            return filmService.getById(id);
        }else{
            return new Film();
        }
    }

    @RequestMapping("/index")
    public String index(Model model, Integer pageNo) {
        int current = 1;
        int pageSize = 10;
        if(pageNo != null) {
            current = pageNo;
        }
        QueryWrapper<Film> queryWrapper = new QueryWrapper<>();
        IPage<Film> page = filmService.page(new Page<>(current,pageSize),queryWrapper);
        model.addAttribute("page",page);
        return "front/index";
    }

    @RequestMapping("/details")
    public String details(Model model, Film film) {
        model.addAttribute("film",film);
        return "front/filmDetails";
    }
}
