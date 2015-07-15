package com.lf.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.Environment;

/**
 * 文件帮助类
 * 
 * @author WZG
 * 
 */
public class FileUtils {
	/**
	 * sd卡的根目录
	 */
	private static String mSdRootPath = Environment
			.getExternalStorageDirectory().getPath();
	/**
	 * 手机的缓存根目录
	 */
	private static String mDataRootPath = null;
	/**
	 * 保存Image的目录名
	 */
	private final static String FOLDER_NAME = "/TESTS";

	public FileUtils(Context context) {
		try {
			mDataRootPath = context.getCacheDir().getPath();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取储存Image的目录
	 * 
	 * @return
	 */
	private String getStorageDirectory() {
		return Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED) ? mSdRootPath + FOLDER_NAME
				: mDataRootPath + FOLDER_NAME;
	}

	/**
	 * 得到给定图片名的地址
	 * 
	 * @param name
	 * @return
	 */
	public String getImgPath(String name) {
		return getStorageDirectory() + "/" + name;
	}

	/**
	 * 保存Image的方法，有sd卡存储到sd卡，没有就存储到手机目录
	 * 
	 * @param fileName
	 * @param bitmap
	 * @throws IOException
	 */
	public void savaBitmap(String fileName, Bitmap bitmap) throws IOException {
		if (bitmap == null) {
			return;
		}
		String path = getStorageDirectory();
		File folderFile = new File(path);
		if (!folderFile.exists()) {
			folderFile.mkdir();
		}
		File file = new File(path + File.separator + fileName);
		file.createNewFile();
		FileOutputStream fos = new FileOutputStream(file);
		bitmap.compress(CompressFormat.JPEG, 100, fos);
		fos.flush();
		fos.close();
	}

	/**
	 * 从手机或者sd卡获取Bitmap
	 * 
	 * @param fileName
	 * @return
	 */
	public Bitmap getBitmap(String fileName) {
		return BitmapFactory.decodeFile(getStorageDirectory() + File.separator
				+ fileName);
	}

	/**
	 * 判断文件是否存在
	 * 
	 * @param fileName
	 * @return
	 */
	public boolean isFileExists(String fileName) {
		return new File(getStorageDirectory() + File.separator + fileName)
				.exists();
	}

	/**
	 * 获取文件的大小
	 * 
	 * @param fileName
	 * @return
	 */
	public long getFileSize(String fileName) {
		return new File(getStorageDirectory() + File.separator + fileName)
				.length();
	}

	/**
	 * 删除SD卡或者手机的缓存图片和目录
	 */
	public void deleteFile() {
		File dirFile = new File(getStorageDirectory());
		if (!dirFile.exists()) {
			return;
		}
		if (dirFile.isDirectory()) {
			String[] children = dirFile.list();
			for (int i = 0; i < children.length; i++) {
				new File(dirFile, children[i]).delete();
			}
		}

		dirFile.delete();
	}

	/**
	 * 写入字符串到制定的文件
	 * 
	 * @param log
	 * @throws IOException
	 */
	public void savaLog(String log) throws IOException {
		try {
			File fp = new File(Environment.getExternalStorageDirectory()
					+ "/TESTS/a.txt");
			if (!fp.exists()) {
				fp.createNewFile();
			}
			FileWriter fileWriter = new FileWriter(fp, true);
			fileWriter.write(log);
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 以行为单位读取文件，读取给定路径的文件内容
	 * 
	 * @param fileName
	 * @return
	 */
	public String readFileByLines(String fileName) {
		File file = new File(fileName);
		BufferedReader reader = null;
		try {
			System.out.println("以行为单位读取文件内容，一次读一整行：");
			reader = new BufferedReader(new FileReader(file));
			StringBuilder msg = new StringBuilder();
			String content;
			// 一次读入一行，直到读入null为文件结束
			while ((content = reader.readLine()) != null) {
				msg.append(content);
			}
			reader.close();
			return msg.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		return "";
	}

	/**
	 * 文本的读取操作
	 * 
	 * @param path
	 *            文件路径,一定要加上文件名字<br>
	 *            例如：../a/a.txt
	 * @return
	 */
	public static String read(InputStream is) {
		BufferedReader bufr = null;
		try {
			bufr = new BufferedReader(new InputStreamReader(is));
			StringBuffer sb = new StringBuffer();
			String str = null;
			while ((str = bufr.readLine()) != null) {
				sb.append(str);
			}
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bufr != null) {
				try {
					bufr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
}
