/**
 * 
 */
package cn.dlut.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.FileInfo;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 *  the tools of FastDFS client
 * 
 */
public class FdfsTool {

	private static final Logger logger = LoggerFactory.getLogger(FdfsTool.class);

	private static String configName = "fdfs_client.conf";

	public static void setConfigName(String name) {
		configName = name;
	}

	static {
		try {
			// Configuration file of the FastDFS to initialize
			String classpath = new File(FdfsTool.class.getResource("/").getFile()).getCanonicalPath();
			ClientGlobal.init(classpath + "/" + configName);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Upload File to FastDFS. return the relative url of the file
	 * 
	 * @param inStream     , file input stream
	 * @param uploadFileName   , the name of the file.
	 * @return the file ID in DFS.
	 * @throws IOException
	 */
	public static String uploadFile(InputStream inStream, String uploadFileName)throws Exception {

		byte[] fileBuff = ByteTool.InputStreamToByte(inStream);
		return uploadFile(fileBuff, uploadFileName);
	}
	
	public static String uploadFile(byte[] fileBuff, String uploadFileName)throws Exception {

		String relativeUrl = "";
		String fileExtName = "";
		if (uploadFileName.contains(".")) {
			fileExtName = uploadFileName.substring(uploadFileName.lastIndexOf(".") + 1);
		} else {
			logger.error("Fail to upload file, because the format of filename is illegal.");
			return null;
		}

		TrackerServer trackerServer = null;

		try {
			TrackerClient tracker = new TrackerClient();
			trackerServer = tracker.getConnection();
			StorageServer storageServer = null;
			StorageClient1 client = new StorageClient1(trackerServer,storageServer);

			NameValuePair[] metaList = new NameValuePair[3];
			metaList[0] = new NameValuePair("fileName", uploadFileName);
			metaList[1] = new NameValuePair("fileExtName", fileExtName);
			metaList[2] = new NameValuePair("fileLength",String.valueOf(fileBuff.length));
			// results[0]: groupName, results[1]: remoteFilename.
			String results[] = client.upload_file(fileBuff, fileExtName,metaList);

			if (results != null) {
				relativeUrl = results[1];
			}
		} catch (Exception e) {
			logger.error("Upload file {} fails. \n exception : {}.",uploadFileName, e);
			throw e;
		} finally {
			if (trackerServer != null) {
				trackerServer.close();
			}
		}

		return relativeUrl;
	}
	

}