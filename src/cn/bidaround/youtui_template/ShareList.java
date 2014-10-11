package cn.bidaround.youtui_template;

import android.content.Context;
import android.content.res.Resources;

/**
 * 获取分享平台的logo和名字
 * @author youtui 
 * @since 2014/3/25
 */

public class ShareList {
	/**微信*/
	public static final String WECHAT = "Wechat";
	/**朋友圈*/
	public static final String WECHATMOMENTS = "WechatMoments";
	/**新浪微博*/
	public static final String SINAWEIBO = "SinaWeibo";
	/**QQ*/
	public static final String QQ = "QQ";
	/**QQ空间*/
	public static final String QZONE = "QZone";
	/**腾讯微博*/
	public static final String TENCENTWEIBO = "TencentWeibo";
	/**人人网*/
	public static final String RENREN = "Renren";
	/**短信*/
	public static final String SHORTMESSAGE = "ShortMessage";
	/**邮件*/
	public static final String EMAIL = "Email";
	/**更多分享*/
	public static final String MORE_SHARE = "More";
	/**复制链接*/
	public static final String COPYLINK = "CopyLink";
	/**截屏*/
	public static final String SCREENCAP = "ScreenCap";
	/***/
	public static final String QRCODE = "QRCode";
	/**
	 * 获取分享平台的lolo
	 * @param name
	 * @param context
	 * @return
	 */
	public static int getLogo(String name,Context context){
		String packName = context.getPackageName();
		Resources res = context.getResources();
		if(WECHAT.equals(name)){
			
			return res.getIdentifier("yt_wxact", "drawable", packName);
		}else if(WECHATMOMENTS.equals(name)){
			
			return res.getIdentifier("yt_pyqact", "drawable", packName);
		}else if(SINAWEIBO.equals(name)){
			
			return res.getIdentifier("yt_xinlangact", "drawable", packName);
		}else if(QQ.equals(name)){
			
			return res.getIdentifier("yt_qqact", "drawable", packName);
		}else if(QZONE.equals(name)){
			
			return res.getIdentifier("yt_qqkjact", "drawable", packName);
		}else if(TENCENTWEIBO.equals(name)){
			
			return res.getIdentifier("yt_tengxunact", "drawable", packName);
		}else if(RENREN.equals(name)){
			
			return res.getIdentifier("yt_renrenact", "drawable", packName);
		}else if(SHORTMESSAGE.equals(name)){
			
			return res.getIdentifier("yt_messact", "drawable", packName);
		}else if(EMAIL.equals(name)){
			
			return res.getIdentifier("yt_mailact", "drawable", packName);
		}else if(MORE_SHARE.equals(name)){
			
			return res.getIdentifier("yt_more", "drawable", packName);
		}else if(COPYLINK.equals(name)){
			return res.getIdentifier("yt_lianjieact", "drawable", packName);
		}else if(SCREENCAP.equals(name)){
			return res.getIdentifier("yt_loadfail", "drawable", packName);
		}else if(QRCODE.equals(name)){
			return res.getIdentifier("yt_erweimaact", "drawable", packName);
		}
		return -1;
	}
	/**
	 * 获取分享平台的名字
	 * @param name
	 * @return
	 */
	public static String getTitle(String name,Context context) {
		String packName = context.getPackageName();
		Resources res = context.getResources();
		if(WECHAT.equals(name)){
			return res.getString(res.getIdentifier("yt_wechat", "string", packName));
		}else if(WECHATMOMENTS.equals(name)){
			return res.getString(res.getIdentifier("yt_wechatmoments", "string", packName));
		}else if(SINAWEIBO.equals(name)){
			return res.getString(res.getIdentifier("yt_sinaweibo", "string", packName));
		}else if(QQ.equals(name)){
			return "QQ";
		}else if(QZONE.equals(name)){
			return res.getString(res.getIdentifier("yt_qzone", "string", packName));
		}else if(TENCENTWEIBO.equals(name)){
			return res.getString(res.getIdentifier("yt_tencentweibo", "string", packName));
		}else if(RENREN.equals(name)){
			return res.getString(res.getIdentifier("yt_renn", "string", packName));
		}else if(SHORTMESSAGE.equals(name)){
			return res.getString(res.getIdentifier("yt_shortmessage", "string", packName));
		}else if(EMAIL.equals(name)){
			return res.getString(res.getIdentifier("yt_email", "string", packName));
		}else if(MORE_SHARE.equals(name)){
			return res.getString(res.getIdentifier("yt_more", "string", packName));
		}else if(COPYLINK.equals(name)){
			return res.getString(res.getIdentifier("yt_copylink", "string", packName));
		}else if(SCREENCAP.equals(name)){
			return res.getString(res.getIdentifier("yt_screencap", "string", packName));
		}else if(QRCODE.equals(name)){
			return res.getString(res.getIdentifier("yt_qrcode", "string", packName));
		}
		return "";
	}
}
