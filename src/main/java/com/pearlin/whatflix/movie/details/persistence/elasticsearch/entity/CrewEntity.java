package com.pearlin.whatflix.movie.details.persistence.elasticsearch.entity;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CrewEntity {

	@Id
	@JsonProperty("id")
	private Long id;

	@JsonProperty("credit_id")
	private String creditId;

	@JsonProperty("department")
	private String department;

	@JsonProperty("gender")
	private String gender;

	@JsonProperty("job")
	private String job;

	@JsonProperty("name")
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCreditId() {
		return creditId;
	}

	public void setCreditId(String creditId) {
		this.creditId = creditId;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "CrewEntity [id=" + id + ", creditId=" + creditId + ", department=" + department + ", gender=" + gender
				+ ", job=" + job + ", name=" + name + "]";
	}

}
