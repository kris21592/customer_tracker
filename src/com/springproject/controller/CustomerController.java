package com.springproject.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springproject.dao.CustomerDAO;
import com.springproject.entity.Customer;
import com.springproject.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	//injecting the DAO 
//	@Autowired
//	private CustomerDAO customerDAO;
	
	@Autowired
	private CustomerService customerService;
	
	//this will send us to list-customers.jsp page
	//will only respond to get requests
	@GetMapping("/list")
	public String listCustomers(Model model) {
		
		//Get the customers from service
			List<Customer> customers = customerService.getCustomers();
		//add customers to spring mvc model
			model.addAttribute("customers", customers);
		return "list-customers";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {
		
		//Create new model attribute to bind the form data
		Customer customer = new Customer();
		model.addAttribute("customer", customer);
		
		return "customer-form";
	}
	
//	@PostMapping("/saveCustomer")
//	public String saveCustomer(@ModelAttribute(name="customer") Customer customer ) {
//		
//		customerService.saveCustomer(customer);
//		return "redirect:/customer/list";
//	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int id, Model model) {
		
		//get customer from service
		Customer customer = customerService.getCustomer(id);
		//set customer as a model attribute to prepopulate form
		model.addAttribute("customer", customer);
		//send over the form

		return "customer-form";
	}
	
	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int id) {
		
		customerService.deleteCustomer(id);
		return "redirect:/customer/list";
	}
	
	@PostMapping("/search")
	private String searchCustomers(@RequestParam("searchName") String searchName, Model model) {
		List<Customer> customers = customerService.searchCustomers(searchName);
		
		model.addAttribute("customers", customers);
		
		return "list-customers";
	}
	
	
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@Valid @ModelAttribute("customer") Customer customer, 
			BindingResult bindingResult) {
		
		System.out.println("Last Name: |" + customer.getLastName() + "|" + " " + "First Name: |" + customer.getFirstName() + "|");
		if(bindingResult.hasErrors()) {
			return "customer-form";
		}
		else {
			customerService.saveCustomer(customer);
			return "redirect:/customer/list";
		}
	}
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		//remove leading and trailing whitespace, if only whitespace present, trim it to null
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true); 
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	
}
