package springbootTest.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;

import springbootTest.entity.User;
//import springbootTest.repository.UserRepository;
import springbootTest.service.UserService;

/*
 @RestController
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
    @RequestMapping("/getUser")
    @Cacheable(value="user-key")
    public User getUser() {
    	User user=userRepository.findByUserName("aa");
    	System.out.println("若下面没出现“无缓存的时候调用”字样且能打印出数据表示测试成功");  
        return user;
    }
    
    @RequestMapping("/getUsers")
    @Cacheable(value="key-Users")
    public List<User> getUsers() {
    	List<User> users=userRepository.findAll();
    	System.out.println("若下面没出现“无缓存的时候调用”字样且能打印出数据表示测试成功");  
        return users;
    }
}
 * */

@Controller
public class UserController {
    @Resource
    UserService userService;

	@RequestMapping({ "/", "" })
	@RequiresPermissions("user:list") // 权限管理;
	public String list() {
		return "redirect:/list";
	}

    @RequestMapping("/list")
	@RequiresPermissions("user:list") // 权限管理;
    public String list(Model model) {
        List<User> users=userService.getUserList();
        model.addAttribute("users", users);
        return "user/list";
    }

	@RequestMapping("/touser")
	@RequiresPermissions("user:view") // 权限管理;
	public String touser(Model model, long id) {
		User user = userService.findUserById(id);		
		model.addAttribute("user", user);
        return "user/user";
	}

    @RequestMapping("/toAdd")
	@RequiresPermissions("user:add") // 权限管理;
    public String toAdd() {
		return "user/userAdd";
    }

    @RequestMapping("/add")
	public String add(User user) {
		String hashAlgorithmName = "MD5";
		int hashIterations = 2;
		ByteSource credentialsSalt = ByteSource.Util.bytes(user.username);
		Object passObj = new SimpleHash(hashAlgorithmName, user.pass_word, credentialsSalt, hashIterations);
		user.pass_word = passObj.toString();
		user.setState(1);
        userService.save(user);
        return "redirect:/list";
	}

    @RequestMapping("/toEdit")
	@RequiresPermissions("user:edit") // 权限管理;
	public String toEdit(Model model, long id) {
		User user = userService.findUserById(id);
        model.addAttribute("user", user);
		return "user/userEdit";
    }

    @RequestMapping("/edit")
    public String edit(User user) {
        userService.edit(user);
        return "redirect:/list";
    }

    @RequestMapping("/delete")
	@RequiresPermissions("user:del") // 权限管理;
	public String delete(long id) {
        userService.delete(id);
        return "redirect:/list";
    }    
}