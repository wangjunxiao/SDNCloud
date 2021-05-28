package cn.dlut.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ByteTool {
	/**
	* byte to int
	*
	* @param b
	*            byte[]
	* @param offset
	*            byte[]'s start point
	* @return
	*/
	public static int byte2int(byte b[], int offset) {
	   return b[offset + 3] & 0xff | (b[offset + 2] & 0xff) << 8
	     | (b[offset + 1] & 0xff) << 16 | (b[offset] & 0xff) << 24;
	}
	public static short byte2short(byte b[], int offset) {
		   return (short)(b[offset + 1] & 0xff | (b[offset] & 0xff) << 8);
		}

	/**
	* int to byte
	*
	* @param n
	*			 int
	* @param buf
	*            byte[]
	* @param offset
	*            byte[]'s start point
	*/
	public static void int2byte(int n, byte buf[], int offset) {
	   buf[offset] = (byte) (n >> 24);
	   buf[offset + 1] = (byte) (n >> 16);
	   buf[offset + 2] = (byte) (n >> 8);
	   buf[offset + 3] = (byte) n;
	}

	/**
	* @returntype void
	* @param n
	*            short
	* @param buf
	*            byte[]
	* @param offset
	*			 byte[]'s start point
	*/
	public static void short2byte(short n, byte buf[], int offset) {
	   buf[offset] = (byte) (n >> 8);
	   buf[offset + 1] = (byte) n;
	}

	/**
	*
	* @param buf
	* @return
	*/
	public static String byte2Hex(byte[] buf) {
	   StringBuffer sb = new StringBuffer();
	   //sb.append("{");
	   for (byte b : buf) {
	    if (b == 0) {
	     sb.append("00");
	    } else if (b == -1) {
	     sb.append("FF");
	    } else {
	     String str = Integer.toHexString(b).toUpperCase();
	     // sb.append(a);
	     if (str.length() == 8) {
	      str = str.substring(6, 8);
	     } else if (str.length() < 2) {
	      str = "0" + str;
	     }
	     sb.append(str);

	    }
	    //sb.append(" ");
	   }
	   //sb.append("}");
	   return sb.toString();
	}

	public static int unsignedByteToInt(byte b) {
	   return (int) b & 0xFF;
	}

	/**
	* convert signed one byte into a hexadecimal digit
	*
	* @param b
	*            byte
	* @return convert result
	*/
	public static String byteToHex(byte b) {
	   int i = b & 0xFF;
	   return Integer.toHexString(i);
	}

	/**
	* convert signed 4 bytes into a 32-bit integer
	*
	* @param buf
	*            bytes buffer
	* @param pos
	*            beginning <code>byte</code>> for converting
	* @return convert result
	*/
	public static long unsigned4BytesToInt(byte[] buf, int pos) {
	   int firstByte = 0;
	   int secondByte = 0;
	   int thirdByte = 0;
	   int fourthByte = 0;
	   int index = pos;
	   firstByte = (0x000000FF & ((int) buf[index]));
	   secondByte = (0x000000FF & ((int) buf[index + 1]));
	   thirdByte = (0x000000FF & ((int) buf[index + 2]));
	   fourthByte = (0x000000FF & ((int) buf[index + 3]));
	   index = index + 4;
	   return ((long) (firstByte << 24 | secondByte << 16 | thirdByte << 8 | fourthByte)) & 0xFFFFFFFFL;
	}

	public static long bytes2long(byte[] b) {

	   int mask = 0xff;
	   long temp = 0;
	   long res = 0;
	   for (int i = 0; i < 8; i++) {
	    res <<= 8;
	    temp = b[i] & mask;
	    res |= temp;
	   }
	   return res;
	}

	public static byte[] long2bytes(long num) {
	   byte[] b = new byte[8];
	   for (int i = 0; i < 8; i++) {
	    b[i] = (byte) (num >>> (56 - i * 8));
	   }
	   return b;
	}

	public static long getLong(byte[] bb, int index) {
	   return ((((long) bb[index + 0] & 0xff) << 56)
	     | (((long) bb[index + 1] & 0xff) << 48)
	     | (((long) bb[index + 2] & 0xff) << 40)
	     | (((long) bb[index + 3] & 0xff) << 32)
	     | (((long) bb[index + 4] & 0xff) << 24)
	     | (((long) bb[index + 5] & 0xff) << 16)
	     | (((long) bb[index + 6] & 0xff) << 8) | (((long) bb[index + 7] & 0xff) << 0));
	}

	public static void putLong(byte[] bb, long x, int index) {
	   bb[index + 0] = (byte) (x >> 56);
	   bb[index + 1] = (byte) (x >> 48);
	   bb[index + 2] = (byte) (x >> 40);
	   bb[index + 3] = (byte) (x >> 32);
	   bb[index + 4] = (byte) (x >> 24);
	   bb[index + 5] = (byte) (x >> 16);
	   bb[index + 6] = (byte) (x >> 8);
	   bb[index + 7] = (byte) (x >> 0);
	}

	public static void putShort(byte b[], short s, int index) {
	   b[index] = (byte) (s >> 8);
	   b[index + 1] = (byte) (s >> 0);
	}

	public static short getShort(byte[] b, int index) {
	   return (short) (((b[index] << 8) | b[index + 1] & 0xff));
	}
	
	public static byte[] subArray(byte[] srcByte,int nStart, int nLength ){
		
		byte[] ret = new byte[nLength];
		System.arraycopy(srcByte, nStart, ret, 0, nLength);
		return ret;
	}
	
	public static byte[] InputStreamToByte(InputStream is) throws IOException {  
		   ByteArrayOutputStream bytestream = new ByteArrayOutputStream();  
		   int ch;  
		   while ((ch = is.read()) != -1) {  
			   bytestream.write(ch);  
		   }  
		   byte imgdata[] = bytestream.toByteArray();  
		   bytestream.close();  
		   return imgdata;  
	}
	
	public static int getSizeOfInputStream(InputStream is) throws IOException {  
		  int count = 0;
		   while (is.read() != -1) {  
			   count++;
		   }  
		   return count;  
	}
}
