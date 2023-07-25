package com.perscholas.capstone.controller;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.perscholas.capstone.entity.*;
import com.perscholas.capstone.repository.FormSubmissionRepository;
import com.perscholas.capstone.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@Controller
public class FormSubmissionController {
    private final FormSubmissionRepository formSubmissionRepository;

    public FormSubmissionController(FormSubmissionRepository formSubmissionRepository) {
        this.formSubmissionRepository = formSubmissionRepository;
    }

    @GetMapping("/form_submission")
    public String showForm(Model model) {
        model.addAttribute("submission", new FormSubmission());
        return "form_submission";
    }

    @PostMapping("/form_submission")
    public String submitForm(@ModelAttribute FormSubmission submission, RedirectAttributes redirectAttributes) {
        // Save the details to my database
        formSubmissionRepository.save(submission);
        redirectAttributes.addFlashAttribute("successMessage", "Form submitted successfully!");
        return  "redirect:/form_submission"; // Redirect back to form ( it should be refreshed?)
    }
}
