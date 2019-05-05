package com.sky.spider.advance.anno2;
import org.springframework.stereotype.Controller; 
import org.springframework.ui.ModelMap; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.ResponseBody; 
import javax.servlet.http.HttpServletRequest; 
@Controller 
public class URLController { 
  @RequestLimit(count=10,time=5000) 
  @RequestMapping("/urltest") 
  @ResponseBody 
  public String test(HttpServletRequest request, ModelMap modelMap) { 
    return "aaa"; 
  } 
} 
