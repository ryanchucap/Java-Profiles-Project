package com.capgemini.api;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.io.ByteArrayOutputStream;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
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
    
    @CrossOrigin
	@GetMapping(value = "/test")
	public void testStuff() {
		/*
		 * Return page that lists profiles from excel sheet
		 */
		Profile p1 = new Profile("adam");
		return;
	}
	
	@CrossOrigin
	@GetMapping(value = "/test2")
	public Profile testStuff2() {
		/*
		 * Return page that lists profiles from excel sheet
		 */
		Profile p1 = new Profile("adam");
		this.profileRepo.save(new Profile());
		return new Profile();
	}
	
	@CrossOrigin
	@GetMapping(value = "/employees")
	public List<Profile> getEmployees() {
		/*
		 * Return page that lists profiles from excel sheet
		 */
//		List<Post> list = this.postRepository.getAllPosts();
//		map.put("posts", list);
		return this.profileRepo.findAll();
	}
	
//	@CrossOrigin
//	@PostMapping(value="/employees")
//	public String addEmployee(@PathVariable Long id, ModelMap map) {
//		/*
//		 * Create new profile
//		 */
//		Profile prof1 = new Profile();
//		return "";
//	}
	
	@CrossOrigin
	@GetMapping(value="/employees/{id}")
	public Profile getEmployee(@PathVariable Long id) {
		/*
		 * Return page for individual profile
		 */
		Profile prof = this.profileRepo.findById(id).get();
		return prof;
	}
	
	@CrossOrigin
	@PutMapping(value="/employees/{id}")
	public void updateEmployee(@RequestBody Map<String, String> payload) {
		Profile p = this.profileRepo.findById(Long.parseLong(payload.get("id"))).get();
		p.setName(payload.get("name"));
		p.setEmail(payload.get("email"));
		p.setPhone_number(payload.get("phone_number"));
		p.setCity(payload.get("city"));
		p.setState(payload.get("state"));
		p.setTrack(payload.get("track"));
		p.setAccount(payload.get("account"));
		p.setProject_code(Integer.parseInt(payload.get("project_code")));
		this.profileRepo.save(p);
	}
	
//	@CrossOrigin
//	@PostMapping(value="/employees/")
//	public void PutEmployee(@RequestParam("name") String name, @RequestParam("phone_number") String phoneNumber, @RequestParam("email") String email,
//			@RequestParam("city") String city, @RequestParam("state") String state, @RequestParam(value="track") String track,
//			@RequestParam("account") String account, @RequestParam("project_code") int projectCode, @RequestParam("start_date") String startDate) {
//		/*
//		 * Return page for individual profile
//		 */
////		Post post = this.postRepository.findById(id);
////		map.put("post", post);
//		this.profileRepo.save(new Profile(name, email, phoneNumber, city, state, track, account, projectCode, startDate));
//		return;
//	}
	
	@CrossOrigin
	@PostMapping(value="/employees/")
	public void PutEmployee(@RequestBody Profile p1) {
		/*
		 * Return page for individual profile
		 */
//		Post post = this.postRepository.findById(id);
//		map.put("post", post);
//		this.profileRepo.save(new Profile(name, email, phoneNumber, city, state, track, account, projectCode, startDate));
//		return;
		this.profileRepo.save(p1);
	}
	
	@CrossOrigin
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
	
	
	@CrossOrigin
	@PostMapping(value="/files/")
	public void importExcel(@RequestParam("file") MultipartFile excelFile) throws IOException{
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
	
	@CrossOrigin
	@GetMapping(value="/files/")
	public byte[] exportExcel() {
		/*
		 * add a profile
		 */
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet spreadsheet = workbook.createSheet("Sheet1");
		XSSFRow row;
		
		int rowid = 2;
		
		Map<String, Object[]> data = new TreeMap<String, Object[]>();
		data.put("1", new Object[] {"name", "email", "phone number", "city", "state", "track", "account", "project code", "start date"});
			
		List<Profile> profiles = this.profileRepo.findAll();
		for (int i = 0; i < profiles.size(); i++) {
			String name = profiles.get(i).getName();
			String email = profiles.get(i).getEmail();
			String phone_number = profiles.get(i).getPhone_number();
			String city = profiles.get(i).getCity();
			String state = profiles.get(i).getState();
			String track = profiles.get(i).getTrack();
			String account = profiles.get(i).getAccount();
			String project_code = String.valueOf(profiles.get(i).getProject_code());
			String start_date = profiles.get(i).getStart_date();
			data.put(String.valueOf(rowid++), new Object[] {name, email, phone_number, city,
					state, track, account, project_code, start_date});
		}
		
		Set<String> keyid = data.keySet();
		rowid = 0;
		
		for(String key : keyid) {
			row = spreadsheet.createRow(rowid++);
			Object[] objectArr = data.get(key);
			int cellid = 0;
			
			for (Object obj : objectArr) {
				Cell cell = row.createCell(cellid++);
				cell.setCellValue((String)obj);
			}
		}
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		//write to output
		try {
			workbook.write(out);
		}
		catch (IOException e) {}
		finally {
			try {
				out.close();
				workbook.close();
			}
			catch (IOException e) {}
		}
		return out.toByteArray();
	}
	
	
}
