package cn.dlut.entity;

import java.io.File;
import java.io.Serializable;

import cn.dlut.util.ByteFileUtil;

public class ZoneFile implements Serializable{

	private static final long serialVersionUID = 2958780848090178561L;
	
	private byte[] file;

	private String filePath;
	
	public ZoneFile(String filePath) {
		this.filePath = filePath;
		File file = new File(filePath);
		if (file.exists() && file.isFile()) {
			this.file = ByteFileUtil.getBytesFromFile(file);
		}
	}
	
	public byte[] getFile() {
		return file;
	}
	
	public void setFile(byte[] file) {
		this.file = file;
	}

	public String getFilePath() {
		return filePath;
	}

}
