package cn.bidaround.youtui_template;

import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.os.Message;
import cn.bidaround.point.YtPoint;
import cn.bidaround.point.YtPointListener;
import cn.bidaround.ytcore.YtCore;
import cn.bidaround.ytcore.YtShareListener;
import cn.bidaround.ytcore.data.ShareData;
import cn.bidaround.ytcore.data.YtPlatform;
/**
 * 友推模板,用于使用友推分享界面的开发者
 * @author youtui
 * @since 14/6/19
 *
 */
public class YtTemplate {
	private Activity act;
	private static int youTuiViewType;
	private boolean needPoint;
	private HashMap<YtPlatform, YtShareListener> listenerMap = new HashMap<YtPlatform, YtShareListener>();
	private HashMap<YtPlatform, ShareData> shareDataMap = new HashMap<YtPlatform, ShareData>();
	private ShareData shareData;
	
	public YtTemplate(Activity act,int youTuiViewType,boolean needPoint){
		this.act = act;
		YtTemplate.youTuiViewType = youTuiViewType;
		this.needPoint = needPoint;
	}

	/**
	 * 为单独的平台添加分享数据
	 * @param platform
	 * @param shareData
	 */
	public void addData(YtPlatform platform, ShareData shareData) {
		shareDataMap.put(platform, shareData);
	}
	/**
	 * 获取指定平台的分享信息
	 * @param platform
	 * @return 指定平台的分享信息
	 */
	public ShareData getData(YtPlatform platform){
		return shareDataMap.get(platform);
	}
	/**
	 * 添加分享监听
	 * @param platform
	 * @param listener
	 */
	public void addListener(YtPlatform platform, YtShareListener listener) {
		listenerMap.put(platform, listener);
	}
	/**
	 * 获得监听事件
	 * @param platform
	 * @return 监听事件
	 */
	public YtShareListener getListener(YtPlatform platform) {
		return listenerMap.get(platform);
	}
	/**调出分享界面*/
	public  void show(){
		if(youTuiViewType == YouTuiViewType.BLACK_POPUP){
			new ViewPagerPopup(act, youTuiViewType, needPoint,this,shareData).show();
		}else if(youTuiViewType == YouTuiViewType.WHITE_LIST){
			new ListPopup(act, youTuiViewType, needPoint, this,shareData).show();
		}
	}
	/**
	 * 关闭主分享界面
	 */
	public static void dismiss(){
		if(youTuiViewType == YouTuiViewType.BLACK_POPUP){
			ViewPagerPopup.close();
		}else if(youTuiViewType == YouTuiViewType.WHITE_LIST){
			ListPopup.close();
		}
	}
	/**
	 * 主分享界面是否正在显示
	 * @return 主分享界面是否正在显示
	 */
	public boolean isShowing(){
		if(youTuiViewType == YouTuiViewType.BLACK_POPUP){
			return ViewPagerPopup.isShowing;
		}else if(youTuiViewType == YouTuiViewType.WHITE_LIST){
			return ListPopup.isShowing;
		}
		return true;
	}
	/**
	 * YtTemplate初始化,开发者应该在程序的入口调用,初始化后后续操作才能正常进行
	 * @param act
	 */
	public static void init(final Activity act){		
		YtCore.init(act);
		YtPointListener listener = new YtPointListener() {			
			@Override
			public void onSuccess(int arg0) {
				//YtLog.e("at YtTemplat:", "onSuccess");
				if(YTBasePopupWindow.mHandler!=null){
					Message msg = Message.obtain(YTBasePopupWindow.mHandler, YTBasePopupWindow.SHARED_HAS_POINT, arg0);
					YTBasePopupWindow.mHandler.sendMessage(msg);
				}
			}
			
			@Override
			public void onFail() {
				YTBasePopupWindow.mHandler.sendEmptyMessage(YTBasePopupWindow.SHARE_POINT_FAIL);
			}
		};
		YtPoint.setListener(listener);
	}
	/**
	 * 在应用出口调用，释放内存
	 */
	public static void release(Context context){
		YtCore.release(context);
		YTBasePopupWindow.mHandler = null;
	}
	
	
	/**
	 * 该方法用于设置所有平台的待分享数据,如果开发者没有使用addData(YtPlatform platform, ShareData shareData)方法为特定平台设置待分享数据,则平台分享的内容为此处设置的内容
	 * @param shareData
	 */
	public void setShareData(ShareData shareData){
		this.shareData = shareData;
	}

}
