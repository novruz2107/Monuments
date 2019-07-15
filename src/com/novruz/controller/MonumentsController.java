package com.novruz.controller;

import com.mysql.cj.jdbc.Blob;
import com.novruz.entity.Monument;
import com.novruz.entity.MonumentTypes;
import com.novruz.helper.ImagePath;
import com.novruz.service.MonumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.PathParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/main")
public class MonumentsController {
	
	@Autowired
	MonumentService monumentService;
		
	@RequestMapping("/list")
	public String showMainPage(@RequestParam(value = "searchTerm", required = false) String searchTerm
			, @PathParam(value="city") String city
			, @PathParam(value="type") String type
			, @PathParam(value="century") String century
			, HttpServletRequest request
			, HttpServletResponse response, Model model) {
		if(searchTerm == null) {
			model.addAttribute("monuments", monumentService.getMonumentByCityTypeCenturyName(city, type, century, null));
		}else {		
			model.addAttribute("searchTerm", searchTerm);
			List<Monument> monuments = monumentService.getMonumentByName(searchTerm);
			model.addAttribute("monuments", monuments);
			
		}
		List<MonumentTypes> allTypes = new ArrayList<>();
		allTypes = monumentService.getMonumentTypes();
		List<ImagePath> images = new ArrayList<>();
		for (int i=0; i<allTypes.size(); i++) {
			String b64 = javax.xml.bind.DatatypeConverter.printBase64Binary(allTypes.get(i).getPhoto());
			images.add(new ImagePath(allTypes.get(i).getId(), b64, allTypes.get(i).getName()));
			
		}
		model.addAttribute("typeImages", images);
		model.addAttribute("allMonuments", monumentService.getMonuments());
		model.addAttribute("types", monumentService.getMonumentTypes());
		model.addAttribute("cities", monumentService.getCities());
		model.addAttribute("centuries", monumentService.getCenturies());
		
		return "welcome-page";
	}
	
	@RequestMapping(value="/listByType", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<Monument> searchByType(@RequestParam(value="type", required=false) String type,
			@RequestParam(value="city", required=false) String city,
			@RequestParam(value="century", required=false) String century,
			@RequestParam(value="typedWord", required=false) String name){
		List<Monument> monuments = monumentService.getMonumentByCityTypeCenturyName(city, type, century, name);
				
		return monuments;
	}

}
