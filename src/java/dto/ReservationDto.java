package dto;

public class ReservationDto {

	private Integer reservationId;
	private String userId;
    private Integer planId;
    private Integer checkInDay;
    private Integer checkInTime;
    private Integer fixedPrice;
    private Integer accommodationDate;
    private Integer adultGuests;
    private Integer childGuests;
    private Integer carId;
	public Integer getReservationId() {
		return reservationId;
	}
	public void setReservationId(Integer reservationId) {
		this.reservationId = reservationId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Integer getPlanId() {
		return planId;
	}
	public void setPlanId(Integer planId) {
		this.planId = planId;
	}
	public Integer getCheckInDay() {
		return checkInDay;
	}
	public void setCheckInDay(Integer checkInDay) {
		this.checkInDay = checkInDay;
	}
	public Integer getCheckInTime() {
		return checkInTime;
	}
	public void setCheckInTime(Integer checkInTime) {
		this.checkInTime = checkInTime;
	}
	public Integer getFixedPrice() {
		return fixedPrice;
	}
	public void setFixedPrice(Integer fixedPrice) {
		this.fixedPrice = fixedPrice;
	}
	public Integer getAccommodationDate() {
		return accommodationDate;
	}
	public void setAccommodationDate(Integer accommodationDate) {
		this.accommodationDate = accommodationDate;
	}
	public Integer getAdultGuests() {
		return adultGuests;
	}
	public void setAdultGuests(Integer adultGuests) {
		this.adultGuests = adultGuests;
	}
	public Integer getChildGuests() {
		return childGuests;
	}
	public void setChildGuests(Integer childGuests) {
		this.childGuests = childGuests;
	}
	public Integer getCarId() {
		return carId;
	}
	public void setCarId(Integer carId) {
		this.carId = carId;
	}
    
    
}
