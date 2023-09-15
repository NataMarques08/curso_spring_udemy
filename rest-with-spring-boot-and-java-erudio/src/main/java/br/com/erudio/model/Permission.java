package br.com.erudio.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "permission")
public class Permission implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
}
