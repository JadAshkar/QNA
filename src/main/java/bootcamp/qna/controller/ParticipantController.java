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
public class ParticipantController {
	
	private EventService eventService;
	
	public ParticipantController(EventService eventService) {
		this.eventService = eventService;
	}

	@RequestMapping(path="/", method=RequestMethod.GET)
	public String access() {
		return "participant/access";
	}
	
	@RequestMapping(path="/", method=RequestMethod.POST)
	public String access(Model model, 
			@RequestParam(value="accessCode", required=true) String accessCode,
			@RequestParam(value="participantName", required=true) String name) {
		Optional<EventDto> oEventDto = eventService.getEventByAccessCode(accessCode);
		if(!oEventDto.isPresent()) {
			model.addAttribute("status", "failed");
			model.addAttribute("message", "No event found for access code " + accessCode);
			return "participant/access";
		}
		model.addAttribute("accessCode", accessCode);
		model.addAttribute("participantName", name);
		return "participant/home";
	}
}
