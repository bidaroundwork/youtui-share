package cn.bidaround.youtui_template;

import java.util.ArrayList;
import java.util.HashMap;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Message;
import cn.bidaround.point.YtPoint;
import cn.bidaround.point.YtPointListener;
import cn.bidaround.ytcore.YtCore;
import cn.bidaround.ytcore.YtShareListener;
import cn.bidaround.ytcore.data.KeyInfo;
import cn.bidaround.ytcore.data.ShareData;
import cn.bidaround.ytcore.data.YtPlatform;
/**
 * 友推模板,用于使用友推分享界面的开发者
 * @author youtui
 * @since 14/6/19
 *
 */
public class YtTemplate{
	private Activity act;
	private int youTuiViewType;
	private boolean needPoint;
	private HashMap<YtPlatform, YtShareListener> listenerMap = new HashMap<YtPlatform, YtShareListener>();
	private HashMap<YtPlatform, ShareData> shareDataMap = new HashMap<YtPlatform, ShareData>();
	private ShareData shareData;
	private ShareData capData;
	private ArrayList<String> enList = new ArrayList<String>();
	private boolean screencapVisible = true;
	private boolean hasAct;
	private int popwindowHeight;
	
	public YtTemplate(Activity act,int youTuiViewType,boolean needPoint){
		this.act = act;
		this.youTuiViewType = youTuiViewType;
		this.needPoint = needPoint;	
		hasAct = needPoint;
		enList.addAll(KeyInfo.enList);
	}
	
	/**
	 * 为单独的平台添加分享数据
	 * @param platform
	 * @param shareData
	 */
	public void addData(YtPlatform platform, ShareData shareData) {
		shareDataMap.put(platform, shareData);
	}
	
	public int getIndex(YtPlatform platform){
		return enList.indexOf(YtPlatform.getPlatfornName(platform));
	}	
	/**
	 * 获取指定平台的分享信息
	 * @param platform
	 * @return 指定平台的分享信息
	 */
	public ShareData getData(YtPlatform platform){
		return shareDataMap.get(platform);
	}
	/**移除平台*/
	public void removePlatform(YtPlatform platform){
		enList.remove(YtPlatform.getPlatfornName(platform));
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
			new ViewPagerPopup(act, youTuiViewType, needPoint,this,shareData,enList).show();
		}else if(youTuiViewType == YouTuiViewType.WHITE_LIST){
			new ListPopup(act, youTuiViewType, needPoint, this,shareData,enList).show();
		}else if(youTuiViewType == YouTuiViewType.WHITE_GRID){
			new WhiteViewPagerTemplate(act, youTuiViewType, needPoint, this, shareData,enList).show();
		}
	}
	
	/**调出截屏分享界面*/
	public void showScreenCap(){
		TemplateUtil.GetandSaveCurrentImage(act, false);
		
		Intent it = new Intent(act, ScreenCapEditActivity.class);
		it.putExtra("viewType", getViewType());
		if(shareData.isAppShare){
			it.putExtra("target_url", YtCore.getTargetUrl());
		}else{
			it.putExtra("target_url", shareData.getTarget_url());	
		}
		it.putExtra("capdata", getCapData());
		act.startActivity(it);
	}
	
	/**调出分享界面,分享的图片为截屏(替换分享内容中的图片)*/
	public void showScreenCapShare(){
		if(youTuiViewType == YouTuiViewType.BLACK_POPUP){	
			new ViewPagerPopup(act, youTuiViewType, needPoint,this,shareData,enList).show();
		}else if(youTuiViewType == YouTuiViewType.WHITE_LIST){
			new ListPopup(act, youTuiViewType, needPoint, this,shareData,enList).show();
		}else if(youTuiViewType == YouTuiViewType.WHITE_GRID){
			new WhiteViewPagerTemplate(act, youTuiViewType, needPoint, this, shareData,enList).show();
		}
	}
	
	public String getScreenCapPath(){
		return TemplateUtil.getSDCardPath()+"/youtui/yt_screen.png";
	}
	/**
	 * 获取分享模板的类型
	 * @return
	 */
	public int getViewType(){
		return youTuiViewType;
	}
	/**
	 * 关闭主分享界面
	 */
	public void dismiss(){
		if(youTuiViewType == YouTuiViewType.BLACK_POPUP){
			ViewPagerPopup.close();
		}else if(youTuiViewType == YouTuiViewType.WHITE_LIST){
			ListPopup.close();
		}else if(youTuiViewType==YouTuiViewType.WHITE_GRID){
			WhiteViewPagerTemplate.close();
		}
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
	 * YtTemplate初始化,传入用户id,开发者应该在程序的入口调用,初始化后后续操作才能正常进行
	 * @param act
	 */
	public static void init(final Activity act,String appUserId){		
		YtCore.init(act,appUserId);
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
	 * 设置是否显示分享成功，分享失败，分享取消的提示
	 * @param visible
	 */
	public void setToastVisible(boolean visible){
		YtToast.visible = visible;
	}
	
	
	/**的待分享数据,如果开发者没有使用addData(YtPlatform platform, ShareData shareData)方法为特定平台设置待分享数据,则平台分享的内容为此处设置的内容
	 * @param shareD
	 * 该方法用于设置所有平台ata
	 */
	public void setShareData(ShareData shareData){
		this.shareData = shareData;
	}

	public ShareData getCapData() {
		return capData;
	}

	public void setCapData(ShareData capData) {
		this.capData = capData;
	}
	
	public boolean isScreencapVisible() {
		return screencapVisible;
	}
	/**
	 * 设置截屏按钮是否可见
	 * @param screencapVisible
	 */
	public void setScreencapVisible(boolean screencapVisible) {
		this.screencapVisible = screencapVisible;
	}

	public boolean isHasAct() {
		return hasAct;
	}

	public void setHasAct(boolean hasAct) {
		this.hasAct = hasAct;
	}

	public int getPopwindowHeight() {
		return popwindowHeight;
	}
	/**
	 * 设置弹出分享框的高度
	 * @param popwindowHeight
	 */
	public void setPopwindowHeight(int popwindowHeight) {
		this.popwindowHeight = popwindowHeight;
	}

}
