package jp.co.sakusaku.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="mst_prefectures")
public class MstPrefectures {
	
	@Id
	private String id;
	
	private String prefecturesCd;
	
	private String prefecturesName;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPrefecturesCd() {
		return prefecturesCd;
	}
	public void setPrefecturesCd(String prefecturesCd) {
		this.prefecturesCd = prefecturesCd;
	}
	public String getPrefecturesName() {
		return prefecturesName;
	}
	public void setPrefecturesName(String prefecturesName) {
		this.prefecturesName = prefecturesName;
	}
	
	

}
