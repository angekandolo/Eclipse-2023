package com.perscholas.capstone.controller;

import  com.perscholas.capstone.repository.*;
import  com.perscholas.capstone.service.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.PathVariable;
import com.perscholas.capstone.service.EmailService;
import com.perscholas.capstone.repository.FormSubmissionRepository;
import com.perscholas.capstone.entity.FormSubmission;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import  com.perscholas.capstone.entity.*;

@Controller
public class AdminController {

    @Autowired
    private final FormSubmissionRepository formSubmissionRepository;
    private final EmailService emailService;

    public AdminController(FormSubmissionRepository formSubmissionRepository, EmailService emailService) {
        this.formSubmissionRepository = formSubmissionRepository;
        this.emailService = emailService;
    }

   // @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/submissions")
    public String showSubmissions(Model model) {
        List<FormSubmission> submissions = formSubmissionRepository.findAll();
        model.addAttribute("submissions", submissions);
        return "submissions";
    }
    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/review/{id}/complete")
    public String markAsComplete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        FormSubmission submission = formSubmissionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid submission ID"));
        
        submission.setStatus("Completed");
        formSubmissionRepository.save(submission);

        // Send email to the user
        String emailContent = "Hello, <br><br> "  
        		+ "Your submission:' <br><br> "   
        		+ submission.getDetails() + "<br><br>"
        		+ " ' has been completed!" + "<br><br>"
        		+ " - from the top dawgs  at Love Anime";
        emailService.sendEmail(submission.getEmail(), "Love Anime Completed Request", emailContent);
        formSubmissionRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("success", "Request Completed ");

        return "redirect:/submissions";
    }
   // @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/review/{id}/reject")
    public String markAsRejected(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        FormSubmission submission = formSubmissionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid submission ID"));
        
        submission.setStatus("Rejected");
        formSubmissionRepository.save(submission);

        // Send email to the user
        String emailContent = "Hello, <br><br> "  
        		+ "Your submission:' <br><br> "   
        		+ submission.getDetails() + "<br><br>"
        		+ " ' has been Rejected" + "<br><br>"
        		+ " - from the top dawgs  at Love Anime";
        emailService.sendEmail(submission.getEmail(), "Love Anime Rejected Request", emailContent);
        formSubmissionRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("error", "Request Rejected ");

        return "redirect:/submissions";
    }


}
