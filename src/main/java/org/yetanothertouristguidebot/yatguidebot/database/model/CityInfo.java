package org.yetanothertouristguidebot.yatguidebot.database.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@DynamicUpdate
@NoArgsConstructor
@Table(name = "CITY_INFO")
public class CityInfo implements Serializable {

	private static final long serialVersionUID = 5286423045295035837L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "NAME")
	private String name;
	@Column(name = "COUNTRY")
	private String country;
	@Column(name = "INFO")
	private String info;
}





