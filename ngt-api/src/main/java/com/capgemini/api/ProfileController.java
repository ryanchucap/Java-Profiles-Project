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

import org.springframework.http.ResponseEntity;
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
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CreationHelper;
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
	@GetMapping(value = "/employees")
	public ResponseEntity<List<Profile>> getEmployees() {
		/*
		 * Return page that lists profiles from excel sheet
		 */
//		List<Post> list = this.postRepository.getAllPosts();
//		map.put("posts", list);
//		return this.profileRepo.findAll();
		return ResponseEntity.ok(this.profileRepo.findAll());
	}
	

	
	@CrossOrigin
	@GetMapping(value="/employees/{id}")
	public ResponseEntity<Profile> getEmployee(@PathVariable Long id) {
		/*
		 * Return page for individual profile
		 */
		Optional<Profile> op = this.profileRepo.findById(id);
		Profile p;
		if (op.isPresent()) {
			p = op.get();
		}
		else {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(p);
	}
	
	@CrossOrigin
	@PutMapping(value="/employees/{id}")
	public ResponseEntity<Void> updateEmployee(@RequestBody Map<String, String> payload) {
		Optional<Profile> op = this.profileRepo.findById(Long.parseLong(payload.get("id")));
		Profile p;
		if (op.isPresent()) {
			p = op.get();
		}
		else {
			return ResponseEntity.notFound().build();
		}
		p.setName(payload.get("name"));
		p.setEmail(payload.get("email"));
		p.setPhone_number(Long.parseLong(payload.get("phone_number")));
		p.setCity(payload.get("city"));
		p.setState(payload.get("state"));
		p.setTrack(payload.get("track"));
		p.setAccount(payload.get("account"));
		p.setProject_code(Integer.parseInt(payload.get("project_code")));
		this.profileRepo.save(p);
		
		return ResponseEntity.ok().build();
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
	public ResponseEntity<Profile> PutEmployee(@RequestBody Profile p1) {
		/*
		 * Return page for individual profile
		 */
//		Post post = this.postRepository.findById(id);
//		map.put("post", post);
//		this.profileRepo.save(new Profile(name, email, phoneNumber, city, state, track, account, projectCode, startDate));
//		return;
		this.profileRepo.save(p1);
		return ResponseEntity.ok(p1);
	}
	
	@CrossOrigin
	@DeleteMapping(value="/employees/{id}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
		/*
		 * Return page for individual profile
		 */
//		Post post = this.postRepository.findById(id);
//		map.put("post", post);
		this.profileRepo.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
	
	@CrossOrigin
	@PostMapping(value="/files/")
	public void importExcel(@RequestParam("file") MultipartFile excelFile) throws IOException{
		/*
		 * import excel sheet
		 */
		
		//FileInputStream fis = new FileInputStream(new File("C:\\Users\\ryachu\\OneDrive - Capgemini\\testsheet.xlsx"));
//		XSSFWorkbook workbook= new XSSFWorkbook(fis);
		this.profileRepo.deleteAll();
		XSSFWorkbook workbook= new XSSFWorkbook(excelFile.getInputStream());

		XSSFSheet worksheet = workbook.getSheetAt(0);
		
		for (int i=1; i<worksheet.getPhysicalNumberOfRows(); i++) {
			XSSFRow row = worksheet.getRow(i);
			
			String name = row.getCell(0).getStringCellValue();
			String email = row.getCell(1).getStringCellValue();
			long phoneNumber;
			Cell c = row.getCell(2);
			if (c == null || c.getCellType() == CellType.BLANK) {
				phoneNumber = -1;
			}
			else {
				phoneNumber = (long) c.getNumericCellValue();
			}
//			long phoneNumber = (long) row.getCell(2).getNumericCellValue();
			String city = row.getCell(3).getStringCellValue();
			String state = row.getCell(4).getStringCellValue();
			String track = row.getCell(5).getStringCellValue();
			String account = row.getCell(6).getStringCellValue();
			int projectCode;
			Cell project_cell = row.getCell(7);
			if (project_cell == null || project_cell.getCellType() == CellType.BLANK) { 
				projectCode = -1;
			}
			else {
				projectCode = (int) project_cell.getNumericCellValue();
			}
			// int projectCode = (int) (row.getCell(7).getNumericCellValue());
			Date date = row.getCell(8).getDateCellValue();
			
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
			long phone_number = profiles.get(i).getPhone_number();
			String city = profiles.get(i).getCity();
			String state = profiles.get(i).getState();
			String track = profiles.get(i).getTrack();
			String account = profiles.get(i).getAccount();
			int project_code = profiles.get(i).getProject_code();
			Date start_date = profiles.get(i).getStart_date();
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
				
				if (obj instanceof String) {
					cell.setCellValue((String) obj);
				}
				else if (obj instanceof Integer) {
					cell.setCellValue((Integer) obj);
				}
				else if (obj instanceof Long) {
					cell.setCellValue((Long) obj);
				}
				else if (obj instanceof Date) {
					cell.setCellValue((Date) obj);
					CellStyle cellStyle = workbook.createCellStyle();
					CreationHelper createHelper = workbook.getCreationHelper();
					cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("m/d/yy"));
					
					
					cell.setCellStyle(cellStyle);
				}
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
