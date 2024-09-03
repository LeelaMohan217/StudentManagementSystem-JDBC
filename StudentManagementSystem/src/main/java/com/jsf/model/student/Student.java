package com.jsf.model.student;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;
import org.primefaces.event.RowEditEvent;

import com.jsf.db_connection.DB_Connection;

@ManagedBean(name = "student")
@ViewScoped
public class Student {

	private int student_id;
	private String student_name;
	private String college;
	private Date dob;
	private String gender;
	private String branch;
	private String skills;
	private String marks;
	private String email;
	private long mobile;
	private String country;
	private String state;
	private String city;
	private String landmark;
	private int pincode;

	private Map<String, Map<String, String>> data = new HashMap<String, Map<String, String>>();
	private Map<String, String> allbranchs;
	private Map<String, String> allskills;
	private Map<String, String> allcountries;
	private Map<String, String> allstates;
	private Map<String, String> allcities;

	private ArrayList<Student> listOfStudents;
	private Student selectedStudent;

	public Student getSelectedStudent() {
		return selectedStudent;
	}

	public void setSelectedStudent(Student selectedStudent) {
		this.selectedStudent = selectedStudent;
	}

	public ArrayList<Student> getListOfStudents() {
		return listOfStudents;
	}

	public void setListOfStudents(ArrayList<Student> listOfStudents) {
		this.listOfStudents = listOfStudents;
	}

	public int getStudent_id() {
		return student_id;
	}

	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}

	public String getStudent_name() {
		return student_name;
	}

	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public String getMarks() {
		return marks;
	}

	public void setMarks(String marks) {
		this.marks = marks;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public long getMobile() {
		return mobile;
	}

	public void setMobile(long mobile) {
		this.mobile = mobile;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public Map<String, Map<String, String>> getData() {
		return data;
	}

	public void setData(Map<String, Map<String, String>> data) {
		this.data = data;
	}

	public Map<String, String> getAllbranchs() {
		return allbranchs;
	}

	public void setAllbranchs(Map<String, String> allbranchs) {
		this.allbranchs = allbranchs;
	}

	public Map<String, String> getAllskills() {
		return allskills;
	}

	public void setAllskills(Map<String, String> allskills) {
		this.allskills = allskills;
	}

	public Map<String, String> getAllcountries() {
		return allcountries;
	}

	public void setAllcountries(Map<String, String> allcountries) {
		this.allcountries = allcountries;
	}

	public Map<String, String> getAllstates() {
		return allstates;
	}

	public void setAllstates(Map<String, String> allstates) {
		this.allstates = allstates;
	}

	public Map<String, String> getAllcities() {
		return allcities;
	}

	public void setAllcities(Map<String, String> allcities) {
		this.allcities = allcities;
	}

	@SuppressWarnings("restriction")
	@PostConstruct
	public void init() {
		getAllStudents();

		allbranchs = new HashMap<String, String>();
		allbranchs.put("CSE", "CSE");
		allbranchs.put("ECE", "ECE");
		allbranchs.put("MECH", "MECH");
		allbranchs.put("CHEM", "CHEM");
		allbranchs.put("CIVIL", "CIVIL");
		allbranchs.put("EEE", "EEE");

		Map<String, String> map = new HashMap<String, String>();
		map.put("Java", "Java");
		map.put("Python", "Python");
		map.put("C", "C");
		map.put("C++", "C++");
		map.put("HTML", "HTML");
		map.put("CSS", "CSS");
		map.put("JavaScript", "JavaScript");
		data.put("CSE", map);

		map = new HashMap<String, String>();
		map.put("VHDL", "VHDL");
		map.put("C", "C");
		map.put("MATLAB", "MATLAB");
		data.put("ECE", map);

		map = new HashMap<String, String>();
		map.put("AutoCAD", "AutoCAD");
		map.put("SolidWorks", "SolidWorks");
		map.put("ANSYS", "ANSYS");
		data.put("MECH", map);

		map = new HashMap<String, String>();
		map.put("ChemCAD", "ChemCAD");
		map.put("ASPEN", "ASPEN");
		map.put("HYSYS", "HYSYS");
		data.put("CHEM", map);

		map = new HashMap<String, String>();
		map.put("AutoCAD", "AutoCAD");
		map.put("STAAD Pro", "STAAD Pro");
		map.put("Revit", "Revit");
		data.put("CIVIL", map);

		map = new HashMap<String, String>();
		map.put("Power Systems", "Power Systems");
		map.put("Control Systems", "Control Systems");
		map.put("C", "C");
		data.put("EEE", map);

		allcountries = new HashMap<String, String>();
		allcountries.put("USA", "USA");
		allcountries.put("IND", "IND");
		allcountries.put("UK", "UK");

		Map<String, String> map1 = new HashMap<String, String>();
		map1.put("California", "California");
		map1.put("Texas", "Texas");
		map1.put("New York", "New York");
		data.put("USA", map1);

		map1 = new HashMap<String, String>();
		map1.put("Los Angeles", "Los Angeles");
		map1.put("San Francisco", "San Francisco");
		map1.put("San Diego", "San Diego");
		data.put("California", map1);

		map1 = new HashMap<String, String>();
		map1.put("Houston", "Houston");
		map1.put("Dallas", "Dallas");
		map1.put("Austin", "Austin");
		data.put("Texas", map1);

		map1 = new HashMap<String, String>();
		map1.put("New York City", "New York City");
		map1.put("Buffalo", "Buffalo");
		map1.put("Rochester", "Rochester");
		data.put("New York", map1);

		map1 = new HashMap<String, String>();
		map1.put("Maharashtra", "Maharashtra");
		map1.put("Karnataka", "Karnataka");
		map1.put("Tamil Nadu", "Tamil Nadu");
		map1.put("Andhra Pradesh", "Andhra Pradesh");
		data.put("IND", map1);

		map1 = new HashMap<String, String>();
		map1.put("Mumbai", "Mumbai");
		map1.put("Pune", "Pune");
		map1.put("Nagpur", "Nagpur");
		data.put("Maharashtra", map1);

		map1 = new HashMap<String, String>();
		map1.put("Bangalore", "Bangalore");
		map1.put("Mysore", "Mysore");
		map1.put("Mangalore", "Mangalore");
		data.put("Karnataka", map1);

		map1 = new HashMap<String, String>();
		map1.put("Chennai", "Chennai");
		map1.put("Coimbatore", "Coimbatore");
		map1.put("Madurai", "Madurai");
		data.put("Tamil Nadu", map1);

		map1 = new HashMap<String, String>();
		map1.put("Vishakapatnam", "Vishakapatnam");
		map1.put("Srikakulam", "Srikakulam");
		map1.put("East Godavari", "East Godavari");
		data.put("Andhra Pradesh", map1);

		map1 = new HashMap<String, String>();
		map1.put("England", "England");
		map1.put("Scotland", "Scotland");
		map1.put("Wales", "Wales");
		data.put("UK", map1);

		map1 = new HashMap<String, String>();
		map1.put("London", "London");
		map1.put("Manchester", "Manchester");
		map1.put("Liverpool", "Liverpool");
		data.put("England", map1);

		map1 = new HashMap<String, String>();
		map1.put("Edinburgh", "Edinburgh");
		map1.put("Glasgow", "Glasgow");
		map1.put("Aberdeen", "Aberdeen");
		data.put("Scotland", map1);

		map1 = new HashMap<String, String>();
		map1.put("Cardiff", "Cardiff");
		map1.put("Swansea", "Swansea");
		map1.put("Newport", "Newport");
		data.put("Wales", map1);

	}

	public void onBranchChange() {
		if (branch != null && !branch.equals("")) {
			allskills = data.get(branch);
		} else {
			allskills = new HashMap<String, String>();
		}
	}

	public void onCountryChange() {
		if (country != null && !country.equals("")) {
			allstates = data.get(country);
			allcities = new HashMap<String, String>();
		} else {
			allstates = new HashMap<String, String>();
			allcities = new HashMap<String, String>();
		}
	}

	public void onStateChange() {
		if (state != null && !state.equals("")) {
			allcities = data.get(state);
		} else {
			allcities = new HashMap<String, String>();
		}
	}

	// Method to handle gender selection
	public void selectGender(String selectedGender) {
		gender = selectedGender;
	}

	// Retrieve all students
	public ArrayList<Student> getAllStudents() {

		listOfStudents = new ArrayList<Student>();
		try {
			Connection connection = null;
			DB_Connection dbcon = new DB_Connection();
			connection = dbcon.getConnection();

			PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM public.students");
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Student obj = new Student();
				obj.setStudent_id(rs.getInt("student_id"));
				obj.setStudent_name(rs.getString("student_name"));
				obj.setCollege(rs.getString("college"));
				obj.setDob(rs.getDate("dob"));
				obj.setGender(rs.getString("gender"));
				obj.setBranch(rs.getString("branch"));
				obj.setSkills(rs.getString("skills"));
				obj.setMarks(rs.getString("marks"));
				obj.setEmail(rs.getString("email"));
				obj.setMobile(rs.getLong("mobile"));
				obj.setCountry(rs.getString("country"));
				obj.setState(rs.getString("state"));
				obj.setCity(rs.getString("city"));
				obj.setLandmark(rs.getString("landmark"));
				obj.setPincode(rs.getInt("pincode"));

				listOfStudents.add(obj);
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		return listOfStudents;
	}

	// Save student
	public String addStudent() {
		try {
			Connection connection = null;
			DB_Connection dbcon = new DB_Connection();
			connection = dbcon.getConnection();

			String sql = "INSERT INTO public.students (student_name, college, dob, gender, skills, branch, marks, email, mobile, country, state, city, landmark, pincode) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, student_name);
			pstmt.setString(2, college);
			pstmt.setDate(3, new java.sql.Date(dob.getTime()));
			pstmt.setString(4, gender);
			pstmt.setString(5, skills);
			pstmt.setString(6, branch);
			pstmt.setString(7, marks);
			pstmt.setString(8, email);
			pstmt.setLong(9, mobile);
			pstmt.setString(10, country);
			pstmt.setString(11, state);
			pstmt.setString(12, city);
			pstmt.setString(13, landmark);
			pstmt.setInt(14, pincode);

			pstmt.executeUpdate();

			getAllStudents();
			System.out.println("Student Added successfully.");
			return "student";

		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	public void deleteStudent(Student student) {
		if (student == null) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
					"No student selected for deletion.");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
		}

		try {
			DB_Connection obj_DB_Connection = new DB_Connection();
			Connection connection = obj_DB_Connection.getConnection();

			PreparedStatement pstmt = connection.prepareStatement("DELETE FROM public.students WHERE student_id = ?");
			pstmt.setInt(1, student.getStudent_id());

			pstmt.executeUpdate();
			getAllStudents(); 

			FacesMessage msg = new FacesMessage("Student Deleted", String.valueOf(student.getStudent_id()));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			PrimeFaces.current().ajax().update("studentTable");
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error deleting student.");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			System.out.println(e);
		}
	}

	// Edit student
	public void onRowEdit(RowEditEvent<Student> event) {
		try {
			Student editedStudent = (Student) event.getObject();

			Connection connection = null;
			DB_Connection dbcon = new DB_Connection();
			connection = dbcon.getConnection();

			String sql = "UPDATE public.students SET student_name=?, college=?, dob=?, gender=?, skills=?, branch=?, marks=?, email=?, mobile=?, country=?, state=?, city=?, landmark=?, pincode=? WHERE student_id=?";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, editedStudent.getStudent_name());
			pstmt.setString(2, editedStudent.getCollege());
			pstmt.setDate(3, new java.sql.Date(editedStudent.getDob().getTime()));
			pstmt.setString(4, editedStudent.getGender());
			pstmt.setString(5, editedStudent.getSkills());
			pstmt.setString(6, editedStudent.getBranch());
			pstmt.setString(7, editedStudent.getMarks());
			pstmt.setString(8, editedStudent.getEmail());
			pstmt.setLong(9, editedStudent.getMobile());
			pstmt.setString(10, editedStudent.getCountry());
			pstmt.setString(11, editedStudent.getState());
			pstmt.setString(12, editedStudent.getCity());
			pstmt.setString(13, editedStudent.getLandmark());
			pstmt.setInt(14, editedStudent.getPincode());
			pstmt.setInt(15, editedStudent.getStudent_id());

			pstmt.executeUpdate();
			getAllStudents();

			FacesMessage msg = new FacesMessage("Student Edited", String.valueOf(editedStudent.getStudent_id()));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			PrimeFaces.current().ajax().update(":mainPageForm:studentTable");

		} catch (Exception e) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error editing student.");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			System.out.println(e);
		}
	}

	// Cancel edit
	public void onRowCancel(RowEditEvent<Student> event) {
		FacesMessage msg = new FacesMessage("Edit Cancelled",
				String.valueOf(((Student) event.getObject()).getStudent_id()));
		FacesContext.getCurrentInstance().addMessage(null, msg);
		PrimeFaces.current().ajax().update("studentTable");
	}

	// search student
	private int searchStudentId;
	private ArrayList<Student> searchedStudents;

	public int getSearchStudentId() {
		return searchStudentId;
	}

	public void setSearchStudentId(int searchStudentId) {
		this.searchStudentId = searchStudentId;
	}

	public ArrayList<Student> getSearchedStudents() {
		return searchedStudents;
	}

	public void setSearchedStudents(ArrayList<Student> searchedStudents) {
		this.searchedStudents = searchedStudents;
	}

	public void searchStudent() {
		try {
			Connection connection = null;
			DB_Connection dbcon = new DB_Connection();
			connection = dbcon.getConnection();

			String sql = "SELECT * FROM public.students WHERE student_id=?";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, searchStudentId);

			ResultSet rs = pstmt.executeQuery();

			listOfStudents = new ArrayList<Student>();

			while (rs.next()) {
				Student student = new Student();
				student.setStudent_id(rs.getInt("student_id"));
				student.setStudent_name(rs.getString("student_name"));
				student.setCollege(rs.getString("college"));
				student.setDob(rs.getDate("dob"));
				student.setGender(rs.getString("gender"));
				student.setBranch(rs.getString("branch"));
				student.setSkills(rs.getString("skills"));
				student.setMarks(rs.getString("marks"));
				student.setEmail(rs.getString("email"));
				student.setMobile(rs.getLong("mobile"));
				student.setCountry(rs.getString("country"));
				student.setState(rs.getString("state"));
				student.setCity(rs.getString("city"));
				student.setLandmark(rs.getString("landmark"));
				student.setPincode(rs.getInt("pincode"));

				listOfStudents.add(student);

			}

			if (searchedStudents.isEmpty()) {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "No Results",
						"No student found with ID: " + searchStudentId);
				FacesContext.getCurrentInstance().addMessage(null, msg);

			}

		} catch (Exception e) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error searching student.");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			System.out.println(e);

		}
	}

	// Reset search results
	public void resetSearch() {
		searchedStudents = null;
		searchStudentId = 0;
		System.out.println("reset button clicked");
		getAllStudents();
	}

}
