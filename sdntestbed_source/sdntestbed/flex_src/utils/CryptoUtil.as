import com.hurlant.crypto.symmetric.CBCMode;
import com.hurlant.crypto.symmetric.DESKey;

import flash.utils.ByteArray;

import mx.utils.Base64Decoder;
import mx.utils.Base64Encoder;

/** 
 * Encryption algorithm to encrypt configuration files 
 * */

function encryption(str:String):String {
	if (! str||str.length==0) {
		return "";
	}
	var key:ByteArray= new   ByteArray();
	key.writeUTFBytes("1234567812345678");
	var iv:ByteArray= new   ByteArray();
	iv.writeUTFBytes("1234567812345678");
	var des:DESKey=new DESKey(key);
	var cbc:CBCMode=new CBCMode(des);
	cbc.IV=iv;
	var tmpByteArray:ByteArray=convertStringToByteArray(str);
	cbc.encrypt(tmpByteArray);
	var $base64:Base64Encoder = new Base64Encoder();
	$base64.insertNewLines=false;//true: The result of the output will be changed automatically, default true    
	$base64.encodeBytes(tmpByteArray);//If you want to encrypt Chinese, don't use $base64.encode();      
	return $base64.toString();
}
/** 
 * Decryption of string 
 * */
function decryption(str:String):String {
	if (! str||str.length==0) {
		return "";
	}
	var key:ByteArray= new   ByteArray();
	key.writeUTFBytes("1234567812345678");
	var iv:ByteArray= new   ByteArray();
	iv.writeUTFBytes("1234567812345678");
	var des:DESKey=new DESKey(key);
	var cbc:CBCMode=new CBCMode(des);
	cbc.IV=iv;
	var $base64D:Base64Decoder = new Base64Decoder();
	$base64D.decode(str);//If you want to encrypt Chinese, don't use $base64.encode();  
	var tmpByteArray:ByteArray=$base64D.toByteArray();
	cbc.decrypt(tmpByteArray);
	return convertByteArrayToString(tmpByteArray);
}
function convertStringToByteArray(str:String):ByteArray {
	var bytes:ByteArray;
	if (str) {
		bytes=new ByteArray();
		bytes.writeUTFBytes(str);
	}
	return bytes;
}
function convertByteArrayToString(bytes:ByteArray):String {
	var str:String;
	if (bytes) {
		bytes.position=0;
		str=bytes.readUTFBytes(bytes.length);
	}
	return str;
}

//trace(encryption("test"));
//trace(decryption("SiQu8unOTKY="));
// SiQu8unOTKY=
// test