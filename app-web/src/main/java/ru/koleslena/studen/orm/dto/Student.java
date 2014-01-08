package ru.koleslena.studen.orm.dto;

import java.text.Collator;
import java.util.Date;
import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author koleslena
 *
 */
@Entity
@Table(name = "ST_STUDENT", schema = "studen")
public class Student implements Comparable<Student> {

    private Long studentId;
    private String firstName;
    private String surName;
    private String patronymic;
    private Date birthDate;
    private String sex;
    private Group group;
    private Integer educationYear;
 
    @Column(name = "STUDENT_BIRTH_DATE")
    public Date getBirthDate() {
        return birthDate;
    }
 
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
 
    @Column(name = "STUDENT_EDUCATION_YEAR")
    public Integer getEducationYear() {
        return educationYear;
    }
 
    public void setEducationYear(Integer educationYear) {
        this.educationYear = educationYear;
    }
 
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "STUDENT_GROUP")
    public Group getGroup() {
        return group;
    }
 
    public void setGroup(Group group) {
        this.group = group;
    }
 
    @Id
 	@GeneratedValue(strategy = GenerationType.IDENTITY)
 	@Column(name = "STUDENT_ID", nullable = false)
    public Long getStudentId() {
        return studentId;
    }
 
    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
 
    @Column(name = "STUDENT_FIRST_NAME")
    public String getFirstName() {
        return firstName;
    }
 
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
 
    @Column(name = "STUDENT_PATRONYMIC")
    public String getPatronymic() {
        return patronymic;
    }
 
    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }
 
    @Column(name = "STUDENT_SUR_NAME")
    public String getSurName() {
        return surName;
    }
 
    public void setSurName(String surName) {
        this.surName = surName;
    }
 
    @Column(name = "STUDENT_SEX")
    public String getSex() {
        return sex;
    }
 
    public void setSex(String sex) {
        this.sex = sex;
    }
 
    public String toString() {
    	return new StringBuilder("Student:").append(" id=").append(getStudentId())
				.append(", first name=").append(getFirstName())
				.append(", sur name=").append(getSurName())
				.append(", birth date=").append(getBirthDate())
				.append(", group name=").append(getGroup().getGroupName())
				.append(", education year=").append(getEducationYear()).toString();
    }
 
    @Override
    public int compareTo(Student o) {
    	Collator c = Collator.getInstance(new Locale("ru"));
        c.setStrength(Collator.PRIMARY);
        return c.compare(this.toString(), o.toString());
    }
}
