package com.jobhub.controller.jobposting;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jobhub.dto.jobposting.Description;
import com.jobhub.dto.jobposting.Job;
import com.jobhub.dto.jobposting.Jobposting;
import com.jobhub.service.jobposting.JobpostingService;

@Controller
public class JobpostingController {

	@Autowired
	JobpostingService jobpostingService;
	
	
	@GetMapping("/jobposting")
	public String jobsget(Model model) {
		
		System.out.println("get 요청");
		
		List<Job> jobList = jobpostingService.findJobList();
		
		model.addAttribute("jobList" , jobList);
				
		return "jobPosting/posting";
		
	}
	
	@PostMapping("/jobposting")
	   public String jobpostingProcess(@ModelAttribute Jobposting jobposting, @ModelAttribute Description description ) {
		
		System.out.println("post 요청");
		System.out.println(jobposting);
	    System.out.println(description); 
	    
	      int result = jobpostingService.saveJobposting(jobposting);
	      int result2 = jobpostingService.saveDescription(description);
	      
	      System.out.println(jobposting);
	      System.out.println(description);
	      //저장
	      
	      if(result > 0 && result2 > 0) { //저장이 성공
	    	  System.out.println("성공");
	         return "redirect:/jobPosting/posting";  //main 요청 경로
	         
	      } else { //저장 실패
	    	  System.out.println("실패");
	         return "jobPosting/posting"; //view 파일경로
	      }
	   }
	
	@PostMapping
	
	
	
	
	/*
	 * @RequestMapping("/jobs") public String jobsList(Model model) {
	 * 
	 * List<Job> cateList = jobpostingService.cateList(); //카테고리 여기 ObjectMapper
	 * objm = new ObjectMapper(); String result = objm.writeValueAsString(cateList);
	 * model.addAttribute("cateList" , cateList);
	 * 
	 * return "/jobPosting/posting";
	 * 
	 * }
	 */
	
	/*
	 * @ResponseBody
	 * 
	 * @RequestMapping("/jobList") public String jobNameList(@RequestBody Job job){
	 * 
	 * 
	 * return jobNameList(job); }
	 */
	
	
	
	@RequestMapping("/jobList")
	public String findJobNameListbyPid(Model model, int jobsId){
		
		/*System.out.println(job);
		
		job.setJobsLevel(2);*/
		
		List<Job> jobNameList = jobpostingService.findJobNameListbyPid(jobsId);
		
		model.addAttribute("jobNameList" , jobNameList);

		return "/jobPosting/posting";
	}
	
	
	
	
	
	
	
	
	

	



}
