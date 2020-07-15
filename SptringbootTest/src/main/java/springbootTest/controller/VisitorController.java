package springbootTest.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springbootTest.common.propertyTest;
import springbootTest.entity.Visitor;
import springbootTest.repository.VisitorRepository;

@RestController    //类中的方法都会以json的格式返回
public class VisitorController {

    @Autowired
    private VisitorRepository repository;
    @Autowired
    private propertyTest pt;
    
    @RequestMapping("/index")
    public String index(HttpServletRequest request) {
        String ip=request.getRemoteAddr();
        Visitor visitor=repository.findByIp(ip);
        if(visitor==null){
            visitor=new Visitor();
            visitor.setIp(ip);
            visitor.setTimes(1);
        }else {
            visitor.setTimes(visitor.getTimes()+1);
        }
        repository.save(visitor);
        return "I have been seen ip "+visitor.getIp()+" "+visitor.getTimes()+" times. title is " + pt.getTitle() + " ,description is " + pt.getDescription();
    }
}