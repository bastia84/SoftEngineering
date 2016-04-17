package test;

public class Employee {

	public String name, department;
	public String[] skills;
	public float hoursWorked;
	
	public Employee(String nameIn, String deptIn, String[] skillsIn){
		name = nameIn;
		department = deptIn;
		skills = skillsIn;
	}
	
	public Employee(String nameIn, String deptIn, float hoursWorkedIn) {
		name = nameIn;
		department = deptIn;
		hoursWorked = hoursWorkedIn;
	}

	public String getName(){
		return name;
	}
	
	public String getDepartment(){
		return department;
	}
	
	public String[] getSkills(){
		return skills;
	}
	
	public float getHours(){
		return hoursWorked;
	}
}
