package ru.koleslena.studen.orm.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author koleslena
 *
 */
@Entity
@Table(name = "ST_GROUP", schema = "studen")
public class Group {
	
	private Long groupId;
	private String groupName;
	private String curator;
	private String speciality;
	
	@Column(name = "GROUP_CURATOR")
    public String getCurator() {
        return curator;
    }
 
    public void setCurator(String curator) {
        this.curator = curator;
    }
 
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "GROUP_ID", nullable = false)
    public Long getGroupId() {
        return groupId;
    }
 
    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
 
    @Column(name = "GROUP_NAME")
    public String getGroupName() {
        return groupName;
    }
 
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
 
    @Column(name = "GROUP_SPECIALITY")
    public String getSpeciality() {
        return speciality;
    }
 
    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }
 
    public String toString() {
		return new StringBuilder("Group:").append(" id=").append(getGroupId())
				.append(", name=").append(getGroupName())
				.append(", speciality=").append(getSpeciality())
				.append(", curator=").append(getCurator()).toString();
    }
}
