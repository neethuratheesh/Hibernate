package com.h2kInfosys.hibernate.dto;



public class TeacherDTO {
	private int teacherID;
	private String firstName;
	private String lastName;
	private String skill;
	
	
	public int getTeacherID() {
		return teacherID;
	}
	public void setTeacherID(int teacherID) {
		this.teacherID = teacherID;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getSkill() {
		return skill;
	}
	public void setSkill(String skill) {
		this.skill = skill;
	}
	
	public void validTeacher(String firstName,String lastName) throws InvalidTeacherException {
		for (int i=0;i<firstName.length();i++) {
			char c =firstName.charAt(i);
			if (!Character.isLetter(c)) {
				InvalidTeacherException e = new InvalidTeacherException();
				throw e;
			}
		}
		for (int i=0;i<lastName.length();i++) {
			char c =lastName.charAt(i);
			if (!Character.isLetter(c)) {
				InvalidTeacherException e = new InvalidTeacherException();
				throw e;
			}
		}
	}

}
