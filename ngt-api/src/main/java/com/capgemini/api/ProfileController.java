package com.capgemini.api;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import java.io.File;
import java.io.FileInputStream;

import com.capgemini.api.model.Profile;
import com.capgemini.api.data.ProfileRepository;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.omg.CORBA.portable.IndirectionException;


@RestController
public class ProfileController {
	
//	private ProfileRepository profileRepository;
	private ProfileRepository profileRepo;

    public ProfileController(ProfileRepository profileRepo) {
        this.profileRepo = profileRepo;
    }
    
	@GetMapping(value = "/test")
	public void testStuff() {
		/*
		 * Return page that lists profiles from excel sheet
		 */
		Profile p1 = new Profile("adam");
		return;
	}
	
	@GetMapping(value = "/test2")
	public Profile testStuff2() {
		/*
		 * Return page that lists profiles from excel sheet
		 */
		Profile p1 = new Profile("adam");
		this.profileRepo.save(new Profile());
		return new Profile();
	}
	
	@GetMapping(value = "/employees")
	public List<Profile> getEmployees() {
		/*
		 * Return page that lists profiles from excel sheet
		 */
//		List<Post> list = this.postRepository.getAllPosts();
//		map.put("posts", list);
		return this.profileRepo.findAll();
	}
	
	@PostMapping(value="/employees")
	public String addEmployee(@PathVariable Long id, ModelMap map) {
		/*
		 * Create new profile
		 */
		Profile prof1 = new Profile();
		return "";
	}
	
	@GetMapping(value="/employees/{id}")
	public Optional<Profile> getEmployee(@PathVariable Long id) {
		/*
		 * Return page for individual profile
		 */
		Optional<Profile> prof = this.profileRepo.findById(id);
		return prof;
	}
	
	@PutMapping(value="/employees/")
	public void PutEmployee(@RequestParam String name, @RequestParam String phoneNumber, @RequestParam String email,
			@RequestParam String city, @RequestParam String state, @RequestParam String track,
			@RequestParam String account, @RequestParam int projectCode, @RequestParam String startDate) {
		/*
		 * Return page for individual profile
		 */
//		Post post = this.postRepository.findById(id);
//		map.put("post", post);
		this.profileRepo.save(new Profile(name, email, phoneNumber, city, state, track, account, projectCode, startDate));
		return;
	}
	
	@DeleteMapping(value="/employees/{id}")
	public void deleteEmployee(@PathVariable Long id) {
		/*
		 * Return page for individual profile
		 */
//		Post post = this.postRepository.findById(id);
//		map.put("post", post);
		this.profileRepo.deleteById(id);
		return;
	}
	
	
	
	@PostMapping(value="/files/")
	public void importExcel(@RequestParam MultipartFile excelFile) throws IOException{
		/*
		 * import excel sheet
		 */
		
		//FileInputStream fis = new FileInputStream(new File("C:\\Users\\ryachu\\OneDrive - Capgemini\\testsheet.xlsx"));
//		XSSFWorkbook workbook= new XSSFWorkbook(fis);
		XSSFWorkbook workbook= new XSSFWorkbook(excelFile.getInputStream());

		XSSFSheet worksheet = workbook.getSheetAt(0);
		
		for (int i=1; i<worksheet.getPhysicalNumberOfRows(); i++) {
			XSSFRow row = worksheet.getRow(i);
			
			String name = row.getCell(0).getStringCellValue();
			String email = row.getCell(1).getStringCellValue();
			String phoneNumber = row.getCell(2).getStringCellValue();
			String city = row.getCell(3).getStringCellValue();
			String state = row.getCell(4).getStringCellValue();
			String track = row.getCell(5).getStringCellValue();
			String account = row.getCell(6).getStringCellValue();
			int projectCode = (int) (row.getCell(7).getNumericCellValue());
			String date = row.getCell(8).getStringCellValue();
			
			this.profileRepo.save(new Profile(name, email, phoneNumber, city, state, track, account, projectCode, date));
		}
		
		workbook.close();
	}
	
	@GetMapping(value="/files/")
	public void exportExcel(@PathVariable Long id, ModelMap map) {
		/*
		 * add a profile
		 */
	}
	
	
}
