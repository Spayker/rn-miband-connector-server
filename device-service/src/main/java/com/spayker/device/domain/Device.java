package com.spayker.device.domain;

import lombok.Builder;
import lombok.Data;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "devices")
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
public class Device {

	@Id
	private String deviceId;

	private String userId;

	private Date date;

	private String hrData;

}
