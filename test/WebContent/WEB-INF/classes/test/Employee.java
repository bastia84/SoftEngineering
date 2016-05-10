package test;

import java.util.Arrays;

public class Employee {

	public String name, department;
	public String[] skills;
	public float hoursWorked;
	public int count;
	
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

	public Employee(String nameIn, String deptIn, Object[] array) {
		name = nameIn;
		department = deptIn;
		skills = Arrays.copyOf(array, array.length, String[].class);
			}

	public Employee(String emp1, String dept1, float hoursWorked2, int cnt) {
		name = emp1;
		department = dept1;
		hoursWorked = hoursWorked2;
		count = cnt;
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
	
	public int getCount(){
		return count;
	}
}
