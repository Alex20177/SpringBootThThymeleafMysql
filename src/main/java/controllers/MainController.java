package controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import entities.User;
import entities.UserRepository;

@Controller
public class MainController {

	private static final Logger log = LoggerFactory.getLogger(MainController.class);

	/*My controller class*/
	
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping("/")
	public String indexPage() {
		log.info("Entering on index page");
		return "index";
	}//Close indexPage
	
    @RequestMapping(value = "/users/add/{firstName}/{lastName}", method = RequestMethod.GET)
    public @ResponseBody String newUser(@PathVariable String firstName, @PathVariable String lastName){
        User user = new User(firstName, lastName);
        userRepository.save(user);
        return String.format("User ('%s %s') saved!", firstName, lastName);
    }//Close newUser	
    
    @RequestMapping(value = "/users/all", method = RequestMethod.GET)
    public @ResponseBody Iterable<User> getAllUsers(){
        return userRepository.findAll();
    }//Close getAllUser    
	
}//Close MainController class.

/*Description:

Field userRepository in controllers.MainController required a bean of type 'entities.UserRepository' that could not be found.


Action:

Consider defining a bean of type 'entities.UserRepository' in your configuration.*/
