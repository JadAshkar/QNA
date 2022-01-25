package bootcamp.qna.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import bootcamp.qna.dto.EventDto;
import bootcamp.qna.dto.QuizDto;
import bootcamp.qna.dto.QuizWithQuestionsDto;
import bootcamp.qna.service.EventService;
import bootcamp.qna.service.QuizService;

@Controller
public class AdminController {
	private QuizService quizService;
	private EventService eventService;
	
	public AdminController(QuizService quizService, EventService eventService) {
		this.quizService = quizService;
		this.eventService = eventService;
	}
	
	@RequestMapping(path="/admin", method=RequestMethod.GET)
	public String home(Model model) {
		return "admin/home";
	}
	
	@RequestMapping(path="/admin/quizes", method=RequestMethod.GET)
	public String quizes(Model model) {
		List<QuizDto> quizes = quizService.getAllQuizes();
		model.addAttribute("quizes", quizes);
		return "admin/quizes";
	}
	
	@RequestMapping(path="/admin/quizes/quiz", method=RequestMethod.GET)
	public String quiz(Model model, 
			@RequestParam(name="id", required=false) Optional<Long> id) {
		if(id.isPresent()) {
			QuizWithQuestionsDto quizDto = quizService.getQuizById(id.get());
			model.addAttribute("quiz", quizDto);
		}
		return "admin/quiz";
	}
	@RequestMapping(path="/admin/quizes/quiz", method=RequestMethod.POST)
	public String quiz(@Valid QuizDto quizDto, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("validationResults", bindingResult);
			model.addAttribute("quiz", quizDto);
			return "admin/quiz";
		}
		quizService.save(quizDto);
		return "redirect:/admin/quizes";
	}
	
	@RequestMapping(path="/admin/events", method=RequestMethod.GET)
	public String events(Model model) {
		List<EventDto> events = eventService.getAllEvents();
		model.addAttribute("events", events);
		return "admin/events";
	}
	
	@RequestMapping(path="/admin/events/event", method=RequestMethod.GET)
	public String event(Model model,
			@RequestParam(name="id", required=false) Optional<Long> id) {
		if(id.isPresent()) {
			EventDto eventDto = eventService.getEventById(id.get());
			model.addAttribute("event", eventDto);
		}
		List<QuizDto> quizes = quizService.getAllQuizes();
		model.addAttribute("quizes", quizes);
		return "admin/event";
	}
	
	@RequestMapping(path="/admin/events/event", method=RequestMethod.POST)
	public String event(@Valid EventDto eventDto, BindingResult bindingResult, Model model) {
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("event", eventDto);
			model.addAttribute("validationResults", bindingResult);
			List<QuizDto> quizes = quizService.getAllQuizes();
			model.addAttribute("quizes", quizes);
			return "admin/event";
		}
		
		eventService.save(eventDto);
		return "redirect:/admin/events";
	}
	
	@RequestMapping(path="/admin/events/event/del", method=RequestMethod.GET)
	public String deleteEvent(long id) {
		eventService.delete(id);
		return "redirect:/admin/events";
	}
}
