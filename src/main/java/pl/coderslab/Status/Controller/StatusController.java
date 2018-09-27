package pl.coderslab.Status.Controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.coderslab.Status.Service.StatusService;
import pl.coderslab.Status.dto.StatusDto;

@Controller
@RequestMapping("/status")
public class StatusController {

	private final StatusService statusService;

	public StatusController(StatusService statusService) {
		this.statusService = statusService;
	}

	@RequestMapping("/all")
	public String getAllStatuses(Model model) {

		model.addAttribute("statuses", statusService.getAll());

		return "status/allStatuses";
	}

	@GetMapping("/add")
	public String getNewStatus(Model model) {

		model.addAttribute("status", new StatusDto());

		return "/status/statusForm";
	}

	@PostMapping("/add")
	public String processNewStatus(
			@Valid @ModelAttribute("status") StatusDto dto,
			BindingResult result) {
		if (result.hasErrors()) {
			return "/status/statusForm";
		}

		statusService.save(dto);

		return "redirect:/status/all";
	}

	@GetMapping("/edit/{id}")
	public String editStatus(@PathVariable("id") Long id, Model model) {

		model.addAttribute("status", statusService.findById(id));

		return "/status/statusForm";
	}

	@PostMapping("/edit/**")
	public String processEditStatus(
			@Valid @ModelAttribute("status") StatusDto dto,
			BindingResult result) {
		if (result.hasErrors()) {
			return "/status/statusForm";
		}

		statusService.save(dto);

		return "redirect:/status/all";
	}

	@GetMapping("/delete/{id}")
	public String deleteStatus(@PathVariable("id") Long id) {

		statusService.deleteFromDb(id);

		return "redirect:/status/all";
	}

}
