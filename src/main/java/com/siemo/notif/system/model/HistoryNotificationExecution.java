package com.siemo.notif.system.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "history_notification_execution")
public class HistoryNotificationExecution {
		@Id
		@GeneratedValue(strategy = GenerationType.SEQUENCE)
		private String id;
		
		@Column
		@NotNull
		private String action;
		
		@Column
		@NotNull
		private Date executionDate;
		
		
		@ManyToOne
		protected MasterData masterData;
		
		@Lob
		@Column
		@NotNull
		private String request;
		
		@Lob
		@Column
		@NotNull
		private String response;


		public HistoryNotificationExecution() {
			
		}
		
		public HistoryNotificationExecution(String action, Date executionDate, MasterData masterData, String request, String response) {
			this.action=action;
			this.executionDate=executionDate;
			this.masterData=masterData;
			this.request=request;
			this.response=response;
		}
		
		
		@JoinColumn(name="masterDataId")
		@ManyToOne
		public MasterData getMasterData() {
			return masterData;
		}

		public void setMasterData(MasterData masterData) {
			this.masterData = masterData;
		}
		
		
		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getAction() {
			return action;
		}

		public void setAction(String action) {
			this.action = action;
		}

		public Date getExecutionDate() {
			return executionDate;
		}

		public void setExecutionDate(Date executionDate) {
			this.executionDate = executionDate;
		}

		
		public String getRequest() {
			return request;
		}

		public void setRequest(String request) {
			this.request = request;
		}

		public String getResponse() {
			return response;
		}

		public void setResponse(String response) {
			this.response = response;
		}



}
