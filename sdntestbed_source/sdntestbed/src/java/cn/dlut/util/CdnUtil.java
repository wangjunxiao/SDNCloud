package cn.dlut.util;

import java.io.BufferedReader;
import java.io.FileReader;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;


public class CdnUtil {
	private static Logger log = Logger.getLogger(CdnUtil.class);
	
	//declare the variables
	String valid;//The set of characters that can be used in a CDN
	String other;//The set of characters that can be used in a CDN excepts valid Chinese characters
	int[] charValid;
	int[] tcValid;
	int[] scValid;
	char[] indexChar;
	String[] tcString;
	String[] scString;
	String[] stringArray;
	
	/**
	 * validTxt, cdnTxt and init() method definition. Li Hongtao, 2008-08-01
	 */
	String validTxt;
	String cdnTxt;

	@Required
	public void setValidTxt(String validTxt) {
		log.info("CDN valid txt: " + validTxt);
		this.validTxt = validTxt;
	}
	
	@Required
	public void setCdnTxt(String cdnTxt) {
		log.info("CDN cdn txt: " + cdnTxt);
		this.cdnTxt = cdnTxt;
	}

	//construct the class and initialize the variables
	//Modified by Li Hongtao, in order to be initialized by spring
	public void init() throws Exception {
		//Initialize the variables
		charValid = new int[60000];
		tcValid = new int[60000];
		scValid = new int[60000];
		indexChar = new char[60000];
		tcString = new String[60000];
		scString = new String[60000];
		stringArray = new String[19577];

		//read the valid characters to a string
		FileReader validFile = new FileReader(validTxt);
		BufferedReader validBuffer = new BufferedReader(validFile);
		valid = validBuffer.readLine();
		validFile.close();
		validBuffer.close();

		//read not Chinese characters to a string
		other = "10e-c2ai8ton6439mbs5pdlrugwvkh7yxfjzqEICQABOPNGRMVKHUTSLZYXWJFD";

		//read the main file to memory
		FileReader cdnFile = new FileReader(cdnTxt);
		BufferedReader cdnBuffer = new BufferedReader(cdnFile);

		//The following arithmetic assign the variables above real values
		String aLine;
		while ((aLine = cdnBuffer.readLine()) != null) {
			int tcIndex = 0, scIndex = 0;
			String fan = "";
			String jian = "";

			for (int i = 2; i < aLine.length(); i++) {
				if (aLine.charAt(i) == ';') {
					tcIndex = i;
					break;
				} else if (i % 2 == 0) {
					tcValid[(int) aLine.charAt(i)] = 1;
					fan = fan + aLine.charAt(i);
				}
			}

			for (int i = tcIndex + 1; i < aLine.length(); i++) {
				if (aLine.charAt(i) == ';') {
					scIndex = i;
					break;
				} else if (i % 2 == 0) {
					scValid[(int) aLine.charAt(i)] = 1;
					jian = jian + aLine.charAt(i);
				}
			}

			indexChar[(int) aLine.charAt(0)] = aLine.charAt(scIndex + 1);
			tcString[(int) aLine.charAt(0)] = fan;
			scString[(int) aLine.charAt(0)] = jian;
			int num = valid.indexOf(aLine.charAt(0));
			stringArray[num] = aLine;
		}

		for (int i = 0; i < other.length(); i++) {
			tcValid[(int) other.charAt(i)] = 1;
			scValid[(int) other.charAt(i)] = 1;
			indexChar[(int) other.charAt(i)] = other.charAt(i);
			tcString[(int) other.charAt(i)] = String.valueOf(other.charAt(i));
			scString[(int) other.charAt(i)] = String.valueOf(other.charAt(i));
		}

		for (int i = 0; i < 19577; i++)
			charValid[(int) valid.charAt(i)] = 1;

		cdnFile.close();
		cdnBuffer.close();
	}

	/** 
	 * to check if a CDN is TC form ,use the TC column
	 * as the TC set, if all the chars of the CDN
	 * are in this set,then return true, else return false    
	 *   
	 * @param CDN the CDN name to check  
	 * @return if or not the CDN is a traditional one
	 *
	 */
	public boolean isTC(String CDN) throws Exception {
		boolean bool = true;
		for (int i = 0; i < CDN.length(); i++) {
			if (((int) CDN.charAt(i) >= 60000)
					|| (tcValid[(int) CDN.charAt(i)] < 1)) {
				bool = false;
				break;
			}
		}
		return bool;
	}

	/** 
	 * to check if a CDN is SC form ,use the SC column
	 * as the SC set, if all the chars of the CDN
	 * are in this set,then return true, else return false    
	 *   
	 * @param CDN the CDN name to check  
	 * @return if or not the CDN is a simplified one
	 */
	public boolean isSC(String CDN) throws Exception {
		boolean bool = true;
		for (int i = 0; i < CDN.length(); i++) {
			if (((int) CDN.charAt(i) >= 60000)
					|| (scValid[(int) CDN.charAt(i)] < 1)) {
				bool = false;
				break;
			}
		}
		return bool;
	}

	/** 
	 * to check if a CDN is the TC form of the origial CDN
	 * based on the variant table, for every char
	 * of OriginalCDN (Char A as defined), check
	 * if the corresponding char in TC-CDN is the 
	 * right one of A'TC form, if so, ruturn true,else return false    
	 *   
	 * @param OriginalCDN the original name submited to check 
	 * @param TC_CDN the traditional form submited to check 
	 * @return if or not the TC_CDN is the original's right form
	 */
	public boolean isRightTC(String OriginalCDN, String TC_CDN)
			throws Exception {
		if (OriginalCDN.length() != TC_CDN.length())
			return false;
		else {
			boolean bool = true;
			for (int i = 0; i < OriginalCDN.length(); i++) {
				if (tcString[(int) (OriginalCDN.charAt(i))].indexOf(TC_CDN
						.charAt(i)) < 0) {
					bool = false;
					break;
				}
			}
			return bool;
		}

	}

	/** 
	 * to check if a CDN is the SC form of the origial CDN
	 * based on the variant table, for every char
	 * of OriginalCDN (Char A as defined), check
	 * if the corresponding char in SC-CDN is the 
	 * right one of A'SC form, if so, ruturn true,else return false    
	 *   
	 * @param OriginalCDN the original name submited to check 
	 * @param SC_CDN the simplified form submited to check 
	 * @return if or not the SC_CDN is the original's right form
	 */
	public boolean isRightSC(String OriginalCDN, String SC_CDN)
			throws Exception {
		if (OriginalCDN.length() != SC_CDN.length())
			return false;
		else {
			boolean bool = true;
			for (int i = 0; i < OriginalCDN.length(); i++) {
				if (scString[(int) (OriginalCDN.charAt(i))].indexOf(SC_CDN
						.charAt(i)) < 0) {
					bool = false;
					break;
				}
			}
			return bool;
		}
	}

	/**
	 * to calculate the index form of the original CDN
	 * use the 1st one of the variant column as the
	 * index, replace every char of OrignalCDN with its'index
	 * 
	 * @param OriginalCDN the original CDN name to calculate
	 * @return the original CDN's index form
	 */
	public String indexCDN(String OriginalCDN) throws Exception {
		String cdn = "";
		for (int i = 0; i < OriginalCDN.length(); i++) {
			cdn = cdn + indexChar[(int) OriginalCDN.charAt(i)];
		}
		return cdn;
	}

	/**
	 * to calulate the TC chars of a orginal char
	 *
	 * @param OriginalChar a char to calculate
	 * @return the TC forms of OriginalChar
	 */
	public char[] TC(char OriginalChar) throws Exception {
		if (other.indexOf(OriginalChar) >= 0) {
			char[] ch = (String.valueOf(OriginalChar)).toCharArray();
			return ch;
		} else {
			int pointer = valid.indexOf(OriginalChar);
			String line = stringArray[pointer];
			String tcString = "";
			for (int i = 2; i < line.length(); i++) {
				if (line.charAt(i) == ';')
					break;
				else {
					if (i % 2 == 0)
						tcString = tcString + line.charAt(i);
				}
			}
			char[] ch = tcString.toCharArray();
			return ch;
		}
	}

	/**
	 * to calulate the SC chars of a orginal char
	 *
	 * @param OriginalChar a char to calculate
	 * @return the SC forms of OriginalChar
	 */
	public char[] SC(char OriginalChar) throws Exception {
		if (other.indexOf(OriginalChar) >= 0) {
			char[] ch = (String.valueOf(OriginalChar)).toCharArray();
			return ch;
		} else {
			int pointer = valid.indexOf(OriginalChar);
			String line = stringArray[pointer];
			String scString = "";
			int index = 0;
			for (int i = 2; i < line.length(); i++) {
				if (line.charAt(i) == ';') {
					index = i;
					break;
				}
			}
			for (int i = index + 1; i < line.length(); i++) {
				if (line.charAt(i) == ';')
					break;
				else {
					if (i % 2 == 0) {
						scString = scString + line.charAt(i);
					}
				}
			}
			char[] ch = scString.toCharArray();
			return ch;
		}
	}

	/**
	 * to check if a CDN is a right one
	 * based on the variant table, for every char
	 * of CDN, check it,if it is in the table,
	 * ruturn true, else return false
	 *
	 * @param CDN the CDN to check
	 * @return if or not the CDN is a rigth one
	 */
	public boolean isRightCDN(String CDN) throws Exception {
		boolean bool = true;
		for (int i = 0; i < CDN.length(); i++) {
			if (((int) CDN.charAt(i) >= 60000)
					|| (charValid[(int) CDN.charAt(i)] < 1)) {
				bool = false;
				break;
			}
		}
		return bool;
	}

	/**
	 * to check if a char is a right one
	 * based on the variant table, 
	 * check it,if it is in the table,
	 * ruturn true, else return false
	 *
	 * @param c the char to check
	 * @return if or not the char is a rigth one
	 */
	public boolean isRightChar(char c) throws Exception {
		if (((int) c >= 60000) || (charValid[(int) c] < 1))
			return false;
		else
			return true;
	}

	/**
	 * to calculate a TC form of the original CDN
	 * 
	 * @param OriginalCDN the original CDN name to calculate
	 * @return a original CDN's TC form
	 */
	public String aTCForm(String OriginalCDN) throws Exception {
		String cdn = "";
		for (int i = 0; i < OriginalCDN.length(); i++) {
			cdn = cdn + (tcString[(int) OriginalCDN.charAt(i)]).charAt(0);
		}
		return cdn;
	}

	/**
	 * to calculate a SC form of the original CDN
	 * 
	 * @param OriginalCDN the original CDN name to calculate
	 * @return a original CDN's SC form
	 */
	public String aSCForm(String OriginalCDN) throws Exception {
		String cdn = "";
		for (int i = 0; i < OriginalCDN.length(); i++) {
			cdn = cdn + (scString[(int) OriginalCDN.charAt(i)]).charAt(0);
		}
		return cdn;
	}
}
