package com.tigerjoys.data.dragon.persist.model;

import javax.persistence.*;
import java.io.Serializable;
import javax.validation.constraints.Size;

/**
 * @author welon
 */
@Entity
@Table(name = "fingerprint_raw")
public class FingerPrintRaw extends CreateUpdate implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "conversationID", updatable = false, nullable = false)
	@Size(min = 32, max = 36, message = "An uuid must contain 32 or 36 characters")
	private String id;

	@Lob
	@Column(length = 2147483647)
	private String conversationJS;

	@Lob
	@Column(length = 2147483647)
	private String devicedefinitionJS;

	@Lob
	@Column(length = 2147483647)
	private String appfingerJS;

	@Lob
	@Column(length = 2147483647)
	private String batteryfingerJS;

	@Lob
	@Column(length = 2147483647)
	private String bluetoothfingerJS;

	@Lob
	@Column(length = 2147483647)
	private String cpufingerJS;

	@Lob
	@Column(length = 2147483647)
	private String devicefingerJS;

	@Lob
	@Column(length = 2147483647)
	private String headsetfingerJS;

	@Lob
	@Column(length = 2147483647)
	private String memoryfingerJS;

	@Lob
	@Column(length = 2147483647)
	private String netfingerJS;

	@Lob
	@Column(length = 2147483647)
	private String photofingerJS;

	@Lob
	@Column(length = 2147483647)
	private String processfingerJS;

	@Lob
	@Column(length = 2147483647)
	private String screenfingerJS;

	@Lob
	@Column(length = 2147483647)
	private String simfingerJS;

	@Lob
	@Column(length = 2147483647)
	private String systimefingerJS;

	@Lob
	@Column(length = 2147483647)
	private String usbfingerJS;

	public String getId() {
		return this.id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof FingerPrintRaw)) {
			return false;
		}
		FingerPrintRaw other = (FingerPrintRaw) obj;
		if (id != null) {
			if (!id.equals(other.id)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public String getConversationJS() {
		return conversationJS;
	}

	public void setConversationJS(String conversationJS) {
		this.conversationJS = conversationJS;
	}

	public String getDevicedefinitionJS() {
		return devicedefinitionJS;
	}

	public void setDevicedefinitionJS(String devicedefinitionJS) {
		this.devicedefinitionJS = devicedefinitionJS;
	}

	public String getAppfingerJS() {
		return appfingerJS;
	}

	public void setAppfingerJS(String appfingerJS) {
		this.appfingerJS = appfingerJS;
	}

	public String getBatteryfingerJS() {
		return batteryfingerJS;
	}

	public void setBatteryfingerJS(String batteryfingerJS) {
		this.batteryfingerJS = batteryfingerJS;
	}

	public String getBluetoothfingerJS() {
		return bluetoothfingerJS;
	}

	public void setBluetoothfingerJS(String bluetoothfingerJS) {
		this.bluetoothfingerJS = bluetoothfingerJS;
	}

	public String getCpufingerJS() {
		return cpufingerJS;
	}

	public void setCpufingerJS(String cpufingerJS) {
		this.cpufingerJS = cpufingerJS;
	}

	public String getDevicefingerJS() {
		return devicefingerJS;
	}

	public void setDevicefingerJS(String devicefingerJS) {
		this.devicefingerJS = devicefingerJS;
	}

	public String getHeadsetfingerJS() {
		return headsetfingerJS;
	}

	public void setHeadsetfingerJS(String headsetfingerJS) {
		this.headsetfingerJS = headsetfingerJS;
	}

	public String getMemoryfingerJS() {
		return memoryfingerJS;
	}

	public void setMemoryfingerJS(String memoryfingerJS) {
		this.memoryfingerJS = memoryfingerJS;
	}

	public String getNetfingerJS() {
		return netfingerJS;
	}

	public void setNetfingerJS(String netfingerJS) {
		this.netfingerJS = netfingerJS;
	}

	public String getPhotofingerJS() {
		return photofingerJS;
	}

	public void setPhotofingerJS(String photofingerJS) {
		this.photofingerJS = photofingerJS;
	}

	public String getProcessfingerJS() {
		return processfingerJS;
	}

	public void setProcessfingerJS(String processfingerJS) {
		this.processfingerJS = processfingerJS;
	}

	public String getScreenfingerJS() {
		return screenfingerJS;
	}

	public void setScreenfingerJS(String screenfingerJS) {
		this.screenfingerJS = screenfingerJS;
	}

	public String getSimfingerJS() {
		return simfingerJS;
	}

	public void setSimfingerJS(String simfingerJS) {
		this.simfingerJS = simfingerJS;
	}

	public String getSystimefingerJS() {
		return systimefingerJS;
	}

	public void setSystimefingerJS(String systimefingerJS) {
		this.systimefingerJS = systimefingerJS;
	}

	public String getUsbfingerJS() {
		return usbfingerJS;
	}

	public void setUsbfingerJS(String usbfingerJS) {
		this.usbfingerJS = usbfingerJS;
	}

	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (conversationJS != null && !conversationJS.trim().isEmpty())
			result += "conversationJS: " + conversationJS;
		if (devicedefinitionJS != null && !devicedefinitionJS.trim().isEmpty())
			result += ", devicedefinitionJS: " + devicedefinitionJS;
		if (appfingerJS != null && !appfingerJS.trim().isEmpty())
			result += ", appfingerJS: " + appfingerJS;
		if (batteryfingerJS != null && !batteryfingerJS.trim().isEmpty())
			result += ", batteryfingerJS: " + batteryfingerJS;
		if (bluetoothfingerJS != null && !bluetoothfingerJS.trim().isEmpty())
			result += ", bluetoothfingerJS: " + bluetoothfingerJS;
		if (cpufingerJS != null && !cpufingerJS.trim().isEmpty())
			result += ", cpufingerJS: " + cpufingerJS;
		if (devicefingerJS != null && !devicefingerJS.trim().isEmpty())
			result += ", devicefingerJS: " + devicefingerJS;
		if (headsetfingerJS != null && !headsetfingerJS.trim().isEmpty())
			result += ", headsetfingerJS: " + headsetfingerJS;
		if (memoryfingerJS != null && !memoryfingerJS.trim().isEmpty())
			result += ", memoryfingerJS: " + memoryfingerJS;
		if (netfingerJS != null && !netfingerJS.trim().isEmpty())
			result += ", netfingerJS: " + netfingerJS;
		if (photofingerJS != null && !photofingerJS.trim().isEmpty())
			result += ", photofingerJS: " + photofingerJS;
		if (processfingerJS != null && !processfingerJS.trim().isEmpty())
			result += ", processfingerJS: " + processfingerJS;
		if (screenfingerJS != null && !screenfingerJS.trim().isEmpty())
			result += ", screenfingerJS: " + screenfingerJS;
		if (simfingerJS != null && !simfingerJS.trim().isEmpty())
			result += ", simfingerJS: " + simfingerJS;
		if (systimefingerJS != null && !systimefingerJS.trim().isEmpty())
			result += ", systimefingerJS: " + systimefingerJS;
		if (usbfingerJS != null && !usbfingerJS.trim().isEmpty())
			result += ", usbfingerJS: " + usbfingerJS;
		return result;
	}
}