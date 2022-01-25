package bootcamp.qna.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import bootcamp.qna.dto.EventDto;
import bootcamp.qna.service.EventService;

@Controller
public class PresenterController {
	private EventService eventService;

	public PresenterController(EventService eventService) {
		this.eventService = eventService;
	}
	
	@RequestMapping(path="/presenter", method = RequestMethod.GET)
	public String access() {
		return "presenter/access";
	}
	
	@RequestMapping(path="/presenter", method = RequestMethod.POST)
	public String access(Model model, 
			@RequestParam(value="accessCode", required=true) String accessCode,
			@RequestParam(value="secretCode", required=true) String secretCode) {
		
		Optional<EventDto> oEventDto = eventService.getEventByAccessAndSecretCodes(accessCode, secretCode);
		if(!oEventDto.isPresent()) {
			model.addAttribute("status", "failed");
			model.addAttribute("message", "No event found with the access and secret code specified");
			return "presenter/access";
		}
		model.addAttribute("accessCode", accessCode);
		return "presenter/home";
	}
}
