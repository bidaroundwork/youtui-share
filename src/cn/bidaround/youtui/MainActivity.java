package cn.bidaround.youtui;

import java.io.File;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import cn.bidaround.youtui_template.YouTuiViewType;
import cn.bidaround.youtui_template.YtTemplate;
import cn.bidaround.ytcore.YtShareListener;
import cn.bidaround.ytcore.data.ShareData;
import cn.bidaround.ytcore.data.YtPlatform;
import cn.bidaround.ytcore.login.AuthListener;
import cn.bidaround.ytcore.login.AuthLogin;
import cn.bidaround.ytcore.login.AuthUserInfo;
import cn.bidaround.ytcore.util.Util;
import cn.bidaround.ytcore.util.YtToast;

/**
 * demo,用于演示集成分享
 * @author youtui
 */
public class MainActivity extends Activity {
	/** 分享的网络图片地址 */
	private static final String IMAGEURL = "http://cdnup.b0.upaiyun.com/media/image/default.png";
	
	/** 是否使用积分系统 	*/
	private boolean usePointSys = true;
	
	/** 点击社交平台后，是否隐藏分享界面, 默认是显示的*/
	private boolean isShowSharePop = true;
	
	/** 分享的数据对象	*/
	private ShareData shareData;
	
	/** 分享的界面平台	*/
	private YtTemplate template;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		// 友推分享平台初始化
		YtTemplate.init(this);
		
		// 开启集成检测机制，请在检测完成后关闭，默认不检测
		//YtTemplate.checkConfig(false);
		
		initShareData();
		initTemplate();
	}
	
	/**
	 * 初始化数据配置
	 * 说明：
	 * 1、如果有设置链接TargetUrl，就必须设置参数：Title、Description、PublishTime、TargetId、ImageUrl，否则无法分享；目的是在友推后台可以统计分享链接子页面
	 * 	1）Title分享内容的标题
	 *  2）Description 分享内容的简单描述信息
	 *  3）PublishTime 分享信息的发布时间, 会显示在子页面统计的发布时间栏上，格式为2014-02-17
	 *  4）TargetId 	  分享内容的在用户系统的中主键id，用来区分子页面，如果的TargetId会显示多栏，如果相同，会增加该子页面的分享量
	 *  5）ImageUrl 分享图片的地址，可以通过setImage(ShareData.IMAGETYPE_INTERNET, IMAGEURL)方式设置
	 */
	private void initShareData(){
		shareData = new ShareData();
		shareData.setAppShare(false); // 是否为应用分享，如果为true，分享的数据需在友推后台设置
		shareData.setDescription("友推积分组件");// 待分享内容的描述
		shareData.setTitle("友推分享"); // 待分享的标题
		shareData.setText("通过友推积分组件，开发者几行代码就可以为应用添加分享送积分功能，并提供详尽的后台统计数据，" +
				"除了本身具备的分享功能外，开发者也可将积分功能单独集成在已有分享组件的app上，快来试试吧!");// 待分享的文字
		shareData.setImage(ShareData.IMAGETYPE_INTERNET, IMAGEURL);// 设置网络分享地址
		shareData.setPublishTime("2013-02-05");
		shareData.setTargetId(String.valueOf(100)); 
		shareData.setTargetUrl("http://www.baidu.com/");// 待分享内容的跳转链接
	}
	
	/**
	 * 初始化分享界面
	 */
	private void initTemplate(){
		template = new YtTemplate(this, YouTuiViewType.WHITE_GRID, usePointSys);
		template.setShareData(shareData);
		template.addListeners(listener);
	}
	
	public void toggle(View view){
		Button bt = (Button) view;
		String text = bt.getText().toString();
		
		// 是否为应用分享，默认为否，应用分享的数据在友推后台设置
		if(text.equals(getString(R.string.appshare_no))){
			bt.setText(getString(R.string.appshare_yes));
			shareData.setAppShare(true);
		}
		
		// 是否为应用分享，默认为否，应用分享的数据在友推后台设置
		else if(text.equals(getString(R.string.point_off))){
			bt.setText(getString(R.string.point_on));
			shareData.setAppShare(false);
		}
		
		// 关闭积分系统
		else if(text.equals(getString(R.string.point_on))){
			bt.setText(getString(R.string.point_off));
			usePointSys = false;
		}
		
		// 开启积分系统
		else if(text.equals(getString(R.string.point_off))){
			bt.setText(getString(R.string.point_on));
			usePointSys = true;
		}
		
		// 开启集成检测机制（默认是关闭的）
		else if(text.equals(getString(R.string.check_off))){
			bt.setText(getString(R.string.check_on));
			YtTemplate.checkConfig(true);
		}
		
		// 关闭集成检测机制（默认是关闭的）
		else if(text.equals(getString(R.string.check_on))){
			bt.setText(getString(R.string.check_off));
			YtTemplate.checkConfig(false);
		}
		
		// 设置网络图片分享
		else if(text.equals(getString(R.string.picture_app))){
			bt.setText(getString(R.string.picture_http));
			shareData.setImage(ShareData.IMAGETYPE_INTERNET, IMAGEURL);
		}
		
		// 设置本地图片分享
		else if(text.equals(getString(R.string.picture_http))){
			bt.setText(getString(R.string.picture_local));
			shareData.setImage(ShareData.IMAGETYPE_SDCARD, getSdcardImagePath());
		}
		
		// 设置应用内的图片分享
		else if(text.equals(getString(R.string.picture_local))){
			bt.setText(getString(R.string.picture_app));
			shareData.setImage(ShareData.IMAGETYPE_APPRESOURE, String.valueOf(R.drawable.test));
		}
		
		// 显示右上角截屏按钮
		else if(text.equals(getString(R.string.cap_hide))){
			bt.setText(getString(R.string.cap_show));
			template.setScreencapVisible(true);
		}
		
		// 隐藏右上角截屏按钮
		else if(text.equals(getString(R.string.cap_show))){
			bt.setText(getString(R.string.cap_hide));
			template.setScreencapVisible(false);
		}
		
		// 分享界面默认的高度
		else if(text.equals(getString(R.string.popheight_cus))){
			bt.setText(getString(R.string.popheight_default));
			template.setPopwindowHeight(0);
		}
		
		// 分享界面自定义高度
		else if(text.equals(getString(R.string.popheight_default))){
			bt.setText(getString(R.string.popheight_cus));
			template.setPopwindowHeight(800);
		}
		
		// 点击社交平台后，显示分享界面, 默认是显示的
		else if(text.equals(getString(R.string.pop_call_hide))){
			bt.setText(getString(R.string.pop_call_show));
			isShowSharePop = true;
		}
		
		// 分享界面自定义高度
		else if(text.equals(getString(R.string.pop_call_show))){
			bt.setText(getString(R.string.pop_call_hide));
			isShowSharePop = false;
		}
	}
	
	private String getSdcardImagePath(){
		String path = Util.getSDCardPath() + "/youtui";
		String picturePath = path + "/test.png";
		File file = new File(picturePath);
		if(!file.exists()){
			Bitmap bit = BitmapFactory.decodeResource(getResources(), Integer.valueOf(R.drawable.demo));
			Util.savePicToSd(bit, "test", shareData, "res");
		}
		return picturePath;
	}
	
	/**
	 * 授权登录监听器
	 */
	AuthListener authListener = new AuthListener() {
		
		@Override
		public void onAuthSucess(AuthUserInfo userInfo) {
			
			YtToast.showS(MainActivity.this, "onAuthSucess");
			
			// 授权平台为QQ
			if(userInfo.isQqPlatform()){
				// 授权完成后返回的QQ用户信息
				Log.w("QQ", userInfo.getQqUserInfoResponse());
				
				Log.w("QQ", userInfo.getQqOpenid());
				Log.w("QQ", userInfo.getQqGender());
				Log.w("QQ", userInfo.getQqImageUrl());
				Log.w("QQ", userInfo.getQqNickName());
			}
			
			// 授权平台为新浪
			else if(userInfo.isSinaPlatform()){
				// 授权完成后返回的新浪用户信息
				Log.w("Sina", userInfo.getSinaUserInfoResponse());
				
				Log.w("Sina", userInfo.getSinaUid());
				Log.w("Sina", userInfo.getSinaGender());
				Log.w("Sina", userInfo.getSinaName());
				Log.w("Sina", userInfo.getSinaProfileImageUrl());
				Log.w("Sina", userInfo.getSinaScreenname());
				Log.w("Sina", userInfo.getSinaAccessToken());
				
			}
			
			// 授权平台为腾讯微博
			else if(userInfo.isTencentWbPlatform()){
				// 授权完成后返回的腾讯微博用户信息
				Log.w("TencentWb", userInfo.getTencentUserInfoResponse());
				
				Log.w("TencentWb", userInfo.getTencentWbOpenid());
				Log.w("TencentWb", userInfo.getTencentWbName());
				Log.w("TencentWb", userInfo.getTencentWbNick());
				Log.w("TencentWb", userInfo.getTencentWbBirthday());
				Log.w("TencentWb", userInfo.getTencentWbHead());
				Log.w("TencentWb", userInfo.getTencentWbGender());
			}
			
			// 授权平台为微信
			else if(userInfo.isWechatPlatform()){
				// 授权完成后返回的微信用户信息
				Log.w("Wechat", userInfo.getWeChatUserInfoResponse());
				
				Log.w("Wechat", userInfo.getWechatOpenId());
				Log.w("Wechat", userInfo.getWechatCountry());
				Log.w("Wechat", userInfo.getWechatImageUrl());
				Log.w("Wechat", userInfo.getWechatLanguage());
				Log.w("Wechat", userInfo.getWechatNickName());
				Log.w("Wechat", userInfo.getWechatProvince());
				Log.w("Wechat", userInfo.getWechatSex());
			}
		}
		
		@Override
		public void onAuthFail() {
			YtToast.showS(MainActivity.this, "onAuthFail");
		}
		
		@Override
		public void onAuthCancel() {
			YtToast.showS(MainActivity.this, "onAuthCancel");
		}
	};

	/**
	 * 显示分享界面
	 */
	private void showTemplate(int type){
		template.setTemplateType(type);
		template.setHasAct(usePointSys);
		template.show();
	}
	
	public void onClick(View v) {
		// 显示白色网格模板
		if (v.getId() == R.id.ytmain_whitegrid) 
			showTemplate(YouTuiViewType.WHITE_GRID);
		
		// 显示黑色网格模板
		else if (v.getId() == R.id.ytmain_blackgrid) 
			showTemplate(YouTuiViewType.BLACK_POPUP);
		
		// 显示白色列表模板
		else if (v.getId() == R.id.ytmain_whitelist) 
			showTemplate(YouTuiViewType.WHITE_LIST);
		
		// 显示白色网格居中模板
		else if (v.getId() == R.id.ytmain_whitegridcenter) 
			showTemplate(YouTuiViewType.WHITE_GRID_CENTER);
		
		// 新浪第三方登录 
		else if (v.getId() == R.id.ytmain_sina_auth)
			new AuthLogin().sinaAuth(this, authListener);
		
		// QQ第三方登录 
		else if (v.getId() == R.id.ytmain_qq_auth)	
			new AuthLogin().qqAuth(this, authListener);
		
		// 腾讯微博第三方登录 
		else if (v.getId() == R.id.ytmain_tencentwb_auth) 
			new AuthLogin().tencentWbAuth(this, authListener);
		
		// 微信第三方登录
		else if (v.getId() == R.id.ytmain_wechat_auth)
			new AuthLogin().wechatAuth(this, authListener);
	}
	
	/**
	 * 设置分享监听器
	 */
	
	YtShareListener listener = new YtShareListener() {
		
		/** 分享成功后回调方法*/
		@Override
		public void onSuccess(YtPlatform platform, String result) {
			YtToast.showS(MainActivity.this, "onSuccess");
			
			Log.w("YouTui", platform.getName());
		}
		
		/** 分享之前调用方法*/
		@Override
		public void onPreShare(YtPlatform platform) {
			if (!isShowSharePop) 
				YtTemplate.dismiss();
			
			YtToast.showS(MainActivity.this, "onPreShare");
		
			Log.w("YouTui", platform.getName());
		}
		
		/** 分享失败回调方法*/
		@Override
		public void onError(YtPlatform platform, String error) {
			YtToast.showS(MainActivity.this, "onError");
			
			Log.w("YouTui", platform.getName());
			Log.w("YouTui", error);
		}
		
		/** 分享取消回调方法*/
		@Override
		public void onCancel(YtPlatform platform) {
			YtToast.showS(MainActivity.this, "onCancel");
			
			Log.w("YouTui", platform.getName());
		}
	};
	
	@Override
	protected void onDestroy() {
		// 释放资源
		YtTemplate.release(this);
		super.onDestroy();
	}
}
