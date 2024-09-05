package com.example.test;


import java.util.List;


public class ApartmentListVo {
	//公寓的Id
	private Integer apartmentId;
	//公寓名称
	private String apartmentNam;
	//公寓电话
	private String apartmentPhone;
	//公寓所在城市
	private String city;
	//所在区级行政区
	private String district;
	//所在商圈
	private String block;
	//附近地铁站
	private String subway;
	//地址
	private String apartmentAddress;
	//经度坐标
	private String longitude;
	//纬度坐标
	private String latitude;
	//公寓图片
	private String imgUrl;
	//门店信息
	private String storeNam;
	//房间Id
	private Integer roomId;
	//面积
	private Double space;
	//朝向
	private String toward;
	//楼层
	private Short floorNum;
	//总楼层
	private Short totalFloorNum;
	//房间数(户型)
	private Short roomNum;
	//房屋配置
	private List<String> apartmentConfig;
	//房屋标签
	private List<String> tags;
	//公寓服务
	private List<String> apartmentService;
	//餐厅
	private Integer livingroom;
	//卫生间
	private Integer washroom;
	//租金
	private Double price;


	public Integer getApartmentId() {
		return apartmentId;
	}

	public void setApartmentId(Integer apartmentId) {
		this.apartmentId = apartmentId;
	}

	public String getApartmentNam() {
		return apartmentNam;
	}

	public void setApartmentNam(String apartmentNam) {
		this.apartmentNam = apartmentNam;
	}

	public String getApartmentPhone() {
		return apartmentPhone;
	}

	public void setApartmentPhone(String apartmentPhone) {
		this.apartmentPhone = apartmentPhone;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getBlock() {
		return block;
	}

	public void setBlock(String block) {
		this.block = block;
	}

	public String getSubway() {
		return subway;
	}

	public void setSubway(String subway) {
		this.subway = subway;
	}

	public String getApartmentAddress() {
		return apartmentAddress;
	}

	public void setApartmentAddress(String apartmentAddress) {
		this.apartmentAddress = apartmentAddress;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getStoreNam() {
		return storeNam;
	}

	public void setStoreNam(String storeNam) {
		this.storeNam = storeNam;
	}

	public Integer getRoomId() {
		return roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

	public Double getSpace() {
		return space;
	}

	public void setSpace(Double space) {
		this.space = space;
	}

	public String getToward() {
		return toward;
	}

	public void setToward(String toward) {
		this.toward = toward;
	}

	public Short getFloorNum() {
		return floorNum;
	}

	public void setFloorNum(Short floorNum) {
		this.floorNum = floorNum;
	}

	public Short getTotalFloorNum() {
		return totalFloorNum;
	}

	public void setTotalFloorNum(Short totalFloorNum) {
		this.totalFloorNum = totalFloorNum;
	}

	public Short getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(Short roomNum) {
		this.roomNum = roomNum;
	}

	public List<String> getApartmentConfig() {
		return apartmentConfig;
	}

	public void setApartmentConfig(List<String> apartmentConfig) {
		this.apartmentConfig = apartmentConfig;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public List<String> getApartmentService() {
		return apartmentService;
	}

	public void setApartmentService(List<String> apartmentService) {
		this.apartmentService = apartmentService;
	}

	public Integer getLivingroom() {
		return livingroom;
	}

	public void setLivingroom(Integer livingroom) {
		this.livingroom = livingroom;
	}

	public Integer getWashroom() {
		return washroom;
	}

	public void setWashroom(Integer washroom) {
		this.washroom = washroom;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
}
