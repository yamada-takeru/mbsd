package dto;
public class ScheduleDto {
	private Integer scheduleId;
	private Integer sequentialNumber;
	private Integer transportationId;
	private String schedule;

	public Integer getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(Integer scheduleId) {
		this.scheduleId = scheduleId;
	}

	public Integer getSequentialNumber() {
		return sequentialNumber;
	}

	public void setSequentialNumber(Integer sequentialNumber) {
		this.sequentialNumber = sequentialNumber;
	}

	public Integer getTransportationId() {
		return transportationId;
	}

	public void setTransportationId(Integer transportationId) {
		this.transportationId = transportationId;
	}

	public String getSchedule() {
		return schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}
}
