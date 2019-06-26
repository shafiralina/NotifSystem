package com.siemo.notif.system.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "data_pegawai")
public class DataPegawai {
	
	@Id
	private String id;
	
	@Column
	private String kendaraan;
	
	@Column
	private String nama;
	
	@Column
	private String email;
	
	public DataPegawai(String kendaraan, String nama, String email) {
		this.kendaraan = kendaraan;
		this.nama = nama;
		this.email = email;
	}
	
	@Id
	@GenericGenerator(strategy = "uuid", name = "system-uuid")
	@GeneratedValue(generator = "system-uuid")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getKendaraan() {
		return kendaraan;
	}
	public void setKendaraan(String kendaraan) {
		this.kendaraan = kendaraan;
	}
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	

}
