package burrow.co.in.owner.beanclass;

/**
 * Created by amma on 9/15/2015.
 */
public class BuildingData {

    private String building_id;
    private String building_name;
    private String building_address;
    private String building_pincode;
    private String building_type;
    private String building_url;
    private String totalFlats;
    private String  vacantFlats;
    
    

	public BuildingData() {
		this.building_id = "";
		this.building_name = "";
		this.building_address = "";
		this.building_pincode = "";
		this.building_type = "";
		this.building_url = "";
		this.totalFlats = "";
		this.vacantFlats = "";
	}
	public String getBuilding_id(){
        return building_id;
    }
    public String getBuilding_name(){
        return building_name;
    }
    public String getBuilding_address(){
        return building_address;
    }
    public String getBuilding_pincode(){
        return building_pincode;
    }
    public String getBuilding_type(){
        return building_type;
    }
    public String getBuilding_url(){
        return building_url;
    }

    public void setBuilding_id(String building_id){
        this.building_id= building_id;
    }

    public void setBuilding_name(String building_name){
        this.building_name=building_name;
    }
    public void setBuilding_address(String building_address){
        this.building_address=building_address;
    }
    public void setBuilding_pincode(String building_pincode){
        this.building_pincode=building_pincode;
    }
    public void setBuilding_type(String building_type){
        this.building_type=building_type;
    }
    public void setBuilding_url(String building_url){
        this.building_url=building_url;
    }
    public String getTotalFlats() {
		return totalFlats;
	}
	public void setTotalFlats(String totalFlats) {
		this.totalFlats = totalFlats;
	}
	public String getVacantFlats() {
		return vacantFlats;
	}
	public void setVacantFlats(String vacantFlats) {
		this.vacantFlats = vacantFlats;
	}
}