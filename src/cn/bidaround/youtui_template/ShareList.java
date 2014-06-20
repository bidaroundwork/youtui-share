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
	public static String WECHAT = "Wechat";
	/**朋友圈*/
	public static String WECHATMOMENTS = "WechatMoments";
	/**新浪微博*/
	public static String SINAWEIBO = "SinaWeibo";
	/**QQ*/
	public static String QQ = "QQ";
	/**QQ空间*/
	public static String QZONE = "QZone";
	/**腾讯微博*/
	public static String TENCENTWEIBO = "TencentWeibo";
	/**人人网*/
	public static String RENREN = "Renren";
	/**短信*/
	public static String SHORTMESSAGE = "ShortMessage";
	/**邮件*/
	public static String EMAIL = "Email";
	/**更多分享*/
	public static String MORE_SHARE = "More";
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
			
			return res.getIdentifier("wxact", "drawable", packName);
		}else if(WECHATMOMENTS.equals(name)){
			
			return res.getIdentifier("pyqact", "drawable", packName);
		}else if(SINAWEIBO.equals(name)){
			
			return res.getIdentifier("xinlangact", "drawable", packName);
		}else if(QQ.equals(name)){
			
			return res.getIdentifier("qqact", "drawable", packName);
		}else if(QZONE.equals(name)){
			
			return res.getIdentifier("qqkjact", "drawable", packName);
		}else if(TENCENTWEIBO.equals(name)){
			
			return res.getIdentifier("tengxunact", "drawable", packName);
		}else if(RENREN.equals(name)){
			
			return res.getIdentifier("renrenact", "drawable", packName);
		}else if(SHORTMESSAGE.equals(name)){
			
			return res.getIdentifier("messact", "drawable", packName);
		}else if(EMAIL.equals(name)){
			
			return res.getIdentifier("mailact", "drawable", packName);
		}else if(MORE_SHARE.equals(name)){
			
			return res.getIdentifier("more", "drawable", packName);
		}
		return -1;
	}
	/**
	 * 获取分享平台的名字
	 * @param name
	 * @return
	 */
	public static String getTitle(String name) {
		if(WECHAT.equals(name)){
			return "微信";
		}else if(WECHATMOMENTS.equals(name)){
			return "微信朋友圈";
		}else if(SINAWEIBO.equals(name)){
			return "新浪微博";
		}else if(QQ.equals(name)){
			return "QQ";
		}else if(QZONE.equals(name)){
			return "QQ空间";
		}else if(TENCENTWEIBO.equals(name)){
			return "腾讯微博";
		}else if(RENREN.equals(name)){
			return "人人网";
		}else if(SHORTMESSAGE.equals(name)){
			return "短信";
		}else if(EMAIL.equals(name)){
			return "邮件";
		}else if(MORE_SHARE.equals(name)){
			return "更多";
		}
		return "";
	}
}
