package com.lf.common;

import java.io.IOException;
import java.lang.ref.SoftReference;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.util.LruCache;
import android.widget.ImageView;

import com.bysj.zzx.R;

/**
 * 异步加载网络图片，实现硬软引用本地缓存机制。
 * 
 * @author WZG
 * 
 */
@SuppressLint("NewApi")
public class AsyncImageLoader {
	// 软引用缓存队列
	private Map<String, SoftReference<Bitmap>> imageCache;
	// 上下文
	private Context context;
	// 文件操作类
	private FileUtils fileUtils;
	// 下载Image的线程池
	private ExecutorService mImageThreadPool = null;
	// 缓存Image的类，当存储Image的大小大于LruCache设定的值，系统自动释放内存
	private LruCache<String, Bitmap> mMemoryCache;
	// 當前操作類
	private static AsyncImageLoader loader;

	/**
	 * 单例模式
	 * 
	 * @param context
	 * @return
	 */
	public static AsyncImageLoader getAsyncImageLoader(Context context) {
		if (loader == null) {
			synchronized (AsyncImageLoader.class) {
				loader = new AsyncImageLoader(context);
			}
		}
		return loader;
	}

	private AsyncImageLoader(Context context) {
		imageCache = new HashMap<String, SoftReference<Bitmap>>();
		this.context = context;
		fileUtils = new FileUtils(context);
		initLruCache();
	}

	/**
	 * 初始化硬缓存
	 */
	private void initLruCache() {
		// 获取系统分配给每个应用程序的最大内存，每个应用系统分配32M
		int maxMemory = (int) Runtime.getRuntime().maxMemory();
		int mCacheSize = maxMemory / 8;
		// 给LruCache分配1/8 4M
		mMemoryCache = new LruCache<String, Bitmap>(mCacheSize) {
			// 必须重写此方法，来测量Bitmap的大小
			@Override
			protected int sizeOf(String key, Bitmap value) {
				return value.getRowBytes() * value.getHeight();
			}

			// 硬引用缓存区满，将一个最不经常使用的oldvalue推入到软引用缓存区
			@Override
			protected void entryRemoved(boolean evicted, String key,
					Bitmap oldValue, Bitmap newValue) {
				Log.v("tag", "硬缓冲区满，放入到软缓存中");
				imageCache.put(key, new SoftReference<Bitmap>(oldValue));
			}
		};
	}

	/**
	 * 给imgview添加图片
	 * 
	 * @param imgEntity
	 * @param imageView
	 */
	public void loadDrawable(String url, final ImageView imageView) {
		if (url == null || "".equals(url)) {
			return;
		}
		ImgEntity imgEntity = new ImgEntity();
		imgEntity.setUrl(url);
		imgEntity.setSaveState(2);
		Bitmap bitmap = getLocalDrawable(imgEntity);
		if (bitmap != null) {
			imageView.setImageBitmap(bitmap);
		} else {
			// 添加等待图片
//			imageView.setImageBitmap(new BitmapFactory().decodeResource(
//					context.getResources(), R.drawable.bg_welcom));
			setImageDownlaod(imageView, imgEntity);
			Log.e("tag", "网络获取图片");
		}
	}
	
	/**
	 * 给imgview添加图片
	 * 
	 * @param imgEntity
	 * @param imageView
	 */
	public void loadDrawable(String url, final ImageView imageView,Bitmap bitmapTemp) {
		if (url == null || "".equals(url)) {
			return;
		}
		ImgEntity imgEntity = new ImgEntity();
		imgEntity.setUrl(url);
		imgEntity.setSaveState(2);
		Bitmap bitmap = getLocalDrawable(imgEntity);
		bitmapTemp=bitmap;
		if (bitmap != null) {
			imageView.setImageBitmap(bitmap);
		} else {
			// 添加等待图片
//			imageView.setImageBitmap(new BitmapFactory().decodeResource(
//					context.getResources(), R.drawable.bg_welcom));
			setImageDownlaod(imageView, imgEntity);
			Log.e("tag", "网络获取图片");
		}
	}

	/**
	 * 取消正在下载的任务
	 */
	public synchronized void cancelTask() {
		if (mImageThreadPool != null) {
			mImageThreadPool.shutdownNow();
			mImageThreadPool = null;
		}
	}

	/**
	 * 缓存或者本地是否有图片资源
	 * 
	 * @param imgEntity
	 * @return
	 */
	private Bitmap getLocalDrawable(ImgEntity imgEntity) {
		Bitmap bitmap = null;
		if (mMemoryCache.get(imgEntity.getUrl()) != null) {
			Log.e("tag", "硬缓存获取图片");
			bitmap = mMemoryCache.get(imgEntity.getUrl());
			return bitmap;
		}

		if (imageCache.containsKey(imgEntity.getUrl())) {
			SoftReference<Bitmap> softReference = imageCache.get(imgEntity
					.getUrl());
			if (softReference.get() != null) {
				Log.e("tag", "软缓存获取图片");
				bitmap = softReference.get();
				return bitmap;
			}
		}

		if (fileUtils.isFileExists(imgEntity.getName())) {
			Log.e("tag", "sd本地获取");
			bitmap = fileUtils.getBitmap(imgEntity.getName());
			save(bitmap, imgEntity);
			return bitmap;
		}
		return bitmap;
	}

	/**
	 * 给控件设置网路下载的图片
	 * 
	 * @param imageView
	 * @param imgEntity
	 */
	private void setImageDownlaod(final ImageView imageView,
			final ImgEntity imgEntity) {
		/**
		 * 下载完成以后处理
		 */
		final Handler handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				imageView.setImageBitmap((Bitmap) msg.obj);
			}
		};

		getThreadPool().execute(new Runnable() {
			@Override
			public void run() {
				Bitmap bitmap = loadImageFromUrl(imgEntity);
				handler.sendMessage(handler.obtainMessage(0, bitmap));
			}
		});
	}

	/**
	 * 获取线程池的方法，因为涉及到并发的问题，我们加上同步锁
	 * 
	 * @return
	 */
	private ExecutorService getThreadPool() {
		if (mImageThreadPool == null) {
			if (mImageThreadPool == null) {
				synchronized (ExecutorService.class) {
					// 为了下载图片更加的流畅，我们用了2个线程来下载图片
					mImageThreadPool = Executors.newFixedThreadPool(2);
				}
			}
		}
		return mImageThreadPool;
	}

	/**
	 * 下载图片，保存到本地文件中
	 * 
	 * @param imgEntity
	 * @return
	 */
	protected Bitmap loadImageFromUrl(ImgEntity imgEntity) {
		try {
			Bitmap bitmap = BitmapFactory.decodeStream(new URL(imgEntity
					.getUrl()).openStream());
			save(bitmap, imgEntity);
			return bitmap;
		} catch (Exception e) {
			Log.e("tag", "((((((((((((((((((((下载失败" + e.toString());
		}
		return null;
	}

	/**
	 * 更具设置保存图片保存图片
	 * 
	 * @param bitmap
	 */
	private void save(Bitmap bitmap, ImgEntity imgEntity) {
		// 得到的图片放入到硬缓存队列里面
		if (imgEntity.getSaveState() == 1 || imgEntity.getSaveState() == 4) {
			mMemoryCache.put(imgEntity.getUrl(), bitmap);
		}
		// 得到的图片放入到软缓存队列里面
		if (imgEntity.getSaveState() == 2 || imgEntity.getSaveState() == 5) {
			imageCache.put(imgEntity.getUrl(),
					new SoftReference<Bitmap>(bitmap));
		}
		// 得到的图片放入本地
		if (imgEntity.getSaveState() == 3 || imgEntity.getSaveState() == 4
				|| imgEntity.getSaveState() == 5) {
			try {
				fileUtils.savaBitmap(imgEntity.getName(), bitmap);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// 使用方法
	// imageView = (ImageView) findViewById(R.id.imageView1);
	// fileUtils= new FileUtils(this);
	// ImgEntity entity = new ImgEntity();
	// entity.setUrl("http://img0.bdstatic.com/img/image/shouye/lyj/ssmv.jpg");
	// entity.setName("ssmv1111.jpg");
	// entity.setSaveState(2);
	// AsyncImageLoader.getAsyncImageLoader(this).loadDrawable(entity,
	// imageView);

	// 调用系统自带视频播放器播放网络音频
	// Uri uri =
	// Uri.parse("http://192.168.1.140:8080/EduExamCenter/file/media/1415243991765.wmv");
	// Intent intent = new Intent(Intent.ACTION_VIEW,uri);
	// intent.setType("video/*");
	// intent.setDataAndType(uri , "video/*");
	// startActivity(intent);

	/**
	 * 下载数据实体类
	 * 
	 * @author WZG
	 * 
	 */
	public static class ImgEntity {
		// 地址
		private String url;
		// 文件名
		private String name;
		// 详细描述
		private String intreduce;
		// 如何保持图片资源（1.保持到硬引用；2软引用；3本地；4本地和硬引用；5软引用本地；）
		private int saveState;

		public int getSaveState() {
			return saveState;
		}

		public void setSaveState(int saveState) {
			this.saveState = saveState;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getIntreduce() {
			return intreduce;
		}

		public void setIntreduce(String intreduce) {
			this.intreduce = intreduce;
		}
	}
}
