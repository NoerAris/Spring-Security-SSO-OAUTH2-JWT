package com.app.ssoserver.Entity;

import com.app.ssoserver.dto.UserDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "mst_user", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"}), @UniqueConstraint(columnNames = {"email"})})
public class MstUser {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    
    @NotBlank
    @Size(max = 50)
    private String username;
    
    @NotBlank
    @Size(max = 50)
    @JsonIgnore
    private String password;
    
    private long salary;
    
    private int age;
    
    @Email
    @NotBlank
    @Size(max = 50)
    private String email;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
    		joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
    
	public UserDto toUserDto() {
		UserDto userDto = new UserDto();
        userDto.setId(this.id);
        userDto.setEmail(this.email);
        userDto.setUsername(this.username);
        userDto.setRole(this.roles.stream().map(role -> role.getName().toString()).collect(Collectors.toList()));
        return userDto;
	}
}
