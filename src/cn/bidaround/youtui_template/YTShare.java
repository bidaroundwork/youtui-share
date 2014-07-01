package cn.bidaround.youtui_template;

import android.app.Activity;
import cn.bidaround.ytcore.YtCore;
import cn.bidaround.ytcore.data.KeyInfo;
import cn.bidaround.ytcore.data.ShareData;
import cn.bidaround.ytcore.data.YtPlatform;


/**
 * 具体的分享内容类
 * @author youtui 
 * @since 14/6/19
 */
public class YTShare {
	private Activity act;
	public YTShare(Activity act){
		this.act = act;
	}
	/**
	 * 分享到各个平台 判断平台所处的页面和在页面的位置设置点击分享事件 该方法用于黑色网格样式中
	 * 
	 * @param position
	 *            点击事件所在gridview上位置
	 * @param pageIndex
	 *            点击事件所在viewpager页面indext
	 * @param ShareData.shareData
	 */
	public void doGridShare(int position, int pageIndex,YtTemplate template,ShareData shareData,int itemAmount) {
		// 新浪微博，
		if (position == KeyInfo.sinaWeiboIndex % itemAmount && KeyInfo.sinaWeiboIndex / itemAmount == pageIndex) {
			if(template.getData(YtPlatform.PLATFORM_SINAWEIBO)!=null){
				YtCore.getInstance().share(act, YtPlatform.PLATFORM_SINAWEIBO, template.getListener(YtPlatform.PLATFORM_SINAWEIBO),template.getData(YtPlatform.PLATFORM_SINAWEIBO));	
			}else{
				YtCore.getInstance().share(act, YtPlatform.PLATFORM_SINAWEIBO, template.getListener(YtPlatform.PLATFORM_SINAWEIBO),shareData);
			}
			// 微信
		} else if (position == KeyInfo.weChatIndex% itemAmount && KeyInfo.weChatIndex / itemAmount == pageIndex) {
			if(template.getData(YtPlatform.PLATFORM_WECHAT)!=null){
				YtCore.getInstance().share(act, YtPlatform.PLATFORM_WECHAT, template.getListener(YtPlatform.PLATFORM_WECHAT),template.getData(YtPlatform.PLATFORM_WECHAT));
			}else{
				YtCore.getInstance().share(act, YtPlatform.PLATFORM_WECHAT, template.getListener(YtPlatform.PLATFORM_WECHAT),shareData);
			}
			
			// QQ
		} else if (position == KeyInfo.qQIndex% itemAmount && KeyInfo.qQIndex / itemAmount == pageIndex) {
			if(template.getData(YtPlatform.PLATFORM_QQ)!=null){
				YtCore.getInstance().share(act, YtPlatform.PLATFORM_QQ, template.getListener(YtPlatform.PLATFORM_QQ),template.getData(YtPlatform.PLATFORM_QQ));
			}else{
				YtCore.getInstance().share(act, YtPlatform.PLATFORM_QQ, template.getListener(YtPlatform.PLATFORM_QQ),shareData);
			}
			
			// QQ空间
		} else if (position == KeyInfo.qZoneIndex% itemAmount && KeyInfo.qZoneIndex / itemAmount == pageIndex) {
			if(template.getData(YtPlatform.PLATFORM_QZONE)!=null){
				YtCore.getInstance().share(act, YtPlatform.PLATFORM_QZONE, template.getListener(YtPlatform.PLATFORM_QZONE),template.getData(YtPlatform.PLATFORM_QZONE));
			}else{
				YtCore.getInstance().share(act, YtPlatform.PLATFORM_QZONE, template.getListener(YtPlatform.PLATFORM_QZONE),shareData);
			}
			
			// 微信朋友圈
		} else if (position == KeyInfo.wechatMomentsIndex% itemAmount && KeyInfo.wechatMomentsIndex / itemAmount == pageIndex) {
			if(template.getData(YtPlatform.PLATFORM_WECHATMOMENTS)!=null){
				YtCore.getInstance().share(act, YtPlatform.PLATFORM_WECHATMOMENTS, template.getListener(YtPlatform.PLATFORM_WECHATMOMENTS),template.getData(YtPlatform.PLATFORM_WECHATMOMENTS));
			}else{
				YtCore.getInstance().share(act, YtPlatform.PLATFORM_WECHATMOMENTS, template.getListener(YtPlatform.PLATFORM_WECHATMOMENTS),shareData);
			}
			
			// 腾讯微博
		} else if (position == KeyInfo.tencentWeiboIndex% itemAmount && KeyInfo.tencentWeiboIndex / itemAmount == pageIndex) {
			if(template.getData(YtPlatform.PLATFORM_TENCENTWEIBO)!=null){
				YtCore.getInstance().share(act, YtPlatform.PLATFORM_TENCENTWEIBO, template.getListener(YtPlatform.PLATFORM_TENCENTWEIBO),template.getData(YtPlatform.PLATFORM_TENCENTWEIBO));
			}else{
				YtCore.getInstance().share(act, YtPlatform.PLATFORM_TENCENTWEIBO, template.getListener(YtPlatform.PLATFORM_TENCENTWEIBO),shareData);
			}
			
			// 人人网
		} else if (position == KeyInfo.renrenIndex % itemAmount && KeyInfo.renrenIndex / itemAmount == pageIndex) {
			if(template.getData(YtPlatform.PLATFORM_RENN)!=null){
				YtCore.getInstance().share(act, YtPlatform.PLATFORM_RENN, template.getListener(YtPlatform.PLATFORM_RENN),template.getData(YtPlatform.PLATFORM_RENN));
			}else{
				YtCore.getInstance().share(act, YtPlatform.PLATFORM_RENN, template.getListener(YtPlatform.PLATFORM_RENN),shareData);
			}
			
			// 短信
		} else if (position == KeyInfo.shortMessageIndex % itemAmount && KeyInfo.shortMessageIndex / itemAmount == pageIndex) {
			if(template.getData(YtPlatform.PLATFORM_MESSAGE)!=null){
				YtCore.getInstance().share(act, YtPlatform.PLATFORM_MESSAGE, template.getListener(YtPlatform.PLATFORM_MESSAGE),template.getData(YtPlatform.PLATFORM_MESSAGE));
			}else{
				YtCore.getInstance().share(act, YtPlatform.PLATFORM_MESSAGE, template.getListener(YtPlatform.PLATFORM_MESSAGE),shareData);
			}
			
			
			// 邮件
		} else if (position == KeyInfo.emailIndex % itemAmount && KeyInfo.emailIndex / itemAmount == pageIndex) {
			if(template.getData(YtPlatform.PLATFORM_EMAIL)!=null){
				YtCore.getInstance().share(act, YtPlatform.PLATFORM_EMAIL, template.getListener(YtPlatform.PLATFORM_EMAIL),template.getData(YtPlatform.PLATFORM_EMAIL));
			}else{
				YtCore.getInstance().share(act, YtPlatform.PLATFORM_EMAIL, template.getListener(YtPlatform.PLATFORM_EMAIL),shareData);
			}
			
		} else if(position == KeyInfo.moreIndex % itemAmount && KeyInfo.moreIndex / itemAmount == pageIndex){
			if(template.getData(YtPlatform.PLATFORM_MORE_SHARE)!=null){
				YtCore.getInstance().share(act, YtPlatform.PLATFORM_MORE_SHARE, template.getListener(YtPlatform.PLATFORM_MORE_SHARE),template.getData(YtPlatform.PLATFORM_MORE_SHARE));
			}else{
				YtCore.getInstance().share(act, YtPlatform.PLATFORM_MORE_SHARE, template.getListener(YtPlatform.PLATFORM_MORE_SHARE),shareData);
			}
			
		}else if(position == KeyInfo.copyLinkIndex % itemAmount && KeyInfo.copyLinkIndex / itemAmount == pageIndex){
			if(template.getData(YtPlatform.PLATFORM_COPYLINK)!=null){
				YtCore.getInstance().share(act, YtPlatform.PLATFORM_COPYLINK, template.getListener(YtPlatform.PLATFORM_COPYLINK),template.getData(YtPlatform.PLATFORM_COPYLINK));
			}else{
				YtCore.getInstance().share(act, YtPlatform.PLATFORM_COPYLINK, template.getListener(YtPlatform.PLATFORM_COPYLINK),shareData);
			}
		}
	}
	/**
	 * 分享到各个平台, 用于白色列表样式的listview
	 * 
	 * @param position
	 */
	public void doListShare(int position,YtTemplate template,ShareData shareData){
		if (position == KeyInfo.sinaWeiboIndex) {
			if(template.getData(YtPlatform.PLATFORM_SINAWEIBO)!=null){
				YtCore.getInstance().share(act, YtPlatform.PLATFORM_SINAWEIBO, template.getListener(YtPlatform.PLATFORM_SINAWEIBO),template.getData(YtPlatform.PLATFORM_SINAWEIBO));
			}else{
				YtCore.getInstance().share(act, YtPlatform.PLATFORM_SINAWEIBO, template.getListener(YtPlatform.PLATFORM_SINAWEIBO),shareData);
			}
			
		} else if (position == KeyInfo.weChatIndex) {
			if(template.getData(YtPlatform.PLATFORM_WECHAT)!=null){
				YtCore.getInstance().share(act, YtPlatform.PLATFORM_WECHAT, template.getListener(YtPlatform.PLATFORM_WECHAT),template.getData(YtPlatform.PLATFORM_WECHAT));
			}else{
				YtCore.getInstance().share(act, YtPlatform.PLATFORM_WECHAT, template.getListener(YtPlatform.PLATFORM_WECHAT),shareData);
			}
			
		} else if (position == KeyInfo.wechatMomentsIndex) {
			if(template.getData(YtPlatform.PLATFORM_WECHATMOMENTS)!=null){
				YtCore.getInstance().share(act, YtPlatform.PLATFORM_WECHATMOMENTS, template.getListener(YtPlatform.PLATFORM_WECHATMOMENTS),template.getData(YtPlatform.PLATFORM_WECHATMOMENTS));
			}else{
				YtCore.getInstance().share(act, YtPlatform.PLATFORM_WECHATMOMENTS, template.getListener(YtPlatform.PLATFORM_WECHATMOMENTS),shareData);
			}
			
		} else if (position == KeyInfo.qQIndex) {
			if(template.getData(YtPlatform.PLATFORM_QQ)!=null){
				YtCore.getInstance().share(act, YtPlatform.PLATFORM_QQ, template.getListener(YtPlatform.PLATFORM_QQ),template.getData(YtPlatform.PLATFORM_QQ));
			}else{
				YtCore.getInstance().share(act, YtPlatform.PLATFORM_QQ, template.getListener(YtPlatform.PLATFORM_QQ),shareData);
			}
			
		} else if (position == KeyInfo.qZoneIndex) {
			if(template.getData(YtPlatform.PLATFORM_QZONE)!=null){
				YtCore.getInstance().share(act, YtPlatform.PLATFORM_QZONE, template.getListener(YtPlatform.PLATFORM_QZONE),template.getData(YtPlatform.PLATFORM_QZONE));
			}else{
				YtCore.getInstance().share(act, YtPlatform.PLATFORM_QZONE, template.getListener(YtPlatform.PLATFORM_QZONE),shareData);
			}
			
		} else if (position == KeyInfo.tencentWeiboIndex) {
			if(template.getData(YtPlatform.PLATFORM_TENCENTWEIBO)!=null){
				YtCore.getInstance().share(act, YtPlatform.PLATFORM_TENCENTWEIBO, template.getListener(YtPlatform.PLATFORM_TENCENTWEIBO),template.getData(YtPlatform.PLATFORM_TENCENTWEIBO));
			}else{
				YtCore.getInstance().share(act, YtPlatform.PLATFORM_TENCENTWEIBO, template.getListener(YtPlatform.PLATFORM_TENCENTWEIBO),shareData);
			}
			
		} else if (position == KeyInfo.renrenIndex) {
			if(template.getData(YtPlatform.PLATFORM_RENN)!=null){
				YtCore.getInstance().share(act, YtPlatform.PLATFORM_RENN, template.getListener(YtPlatform.PLATFORM_RENN),template.getData(YtPlatform.PLATFORM_RENN));
			}else{
				YtCore.getInstance().share(act, YtPlatform.PLATFORM_RENN, template.getListener(YtPlatform.PLATFORM_RENN),shareData);
			}
			
		} else if (position == KeyInfo.shortMessageIndex) {
			if(template.getData(YtPlatform.PLATFORM_MESSAGE)!=null){
				YtCore.getInstance().share(act, YtPlatform.PLATFORM_MESSAGE, template.getListener(YtPlatform.PLATFORM_MESSAGE), template.getData(YtPlatform.PLATFORM_MESSAGE));
			}else{
				YtCore.getInstance().share(act, YtPlatform.PLATFORM_MESSAGE, template.getListener(YtPlatform.PLATFORM_MESSAGE),shareData);
			}
			
		} else if (position == KeyInfo.emailIndex) {
			if(template.getData(YtPlatform.PLATFORM_EMAIL)!=null){
				YtCore.getInstance().share(act, YtPlatform.PLATFORM_EMAIL, template.getListener(YtPlatform.PLATFORM_EMAIL),template.getData(YtPlatform.PLATFORM_EMAIL));
			}else{
				YtCore.getInstance().share(act, YtPlatform.PLATFORM_EMAIL, template.getListener(YtPlatform.PLATFORM_EMAIL),shareData);
			}
			
		} else if(position == KeyInfo.moreIndex){
			if(template.getData(YtPlatform.PLATFORM_MORE_SHARE)!=null){
				YtCore.getInstance().share(act, YtPlatform.PLATFORM_MORE_SHARE, template.getListener(YtPlatform.PLATFORM_MORE_SHARE),template.getData(YtPlatform.PLATFORM_MORE_SHARE));
			}else{
				YtCore.getInstance().share(act, YtPlatform.PLATFORM_MORE_SHARE, template.getListener(YtPlatform.PLATFORM_MORE_SHARE),shareData);
			}
			
		}else if(position == KeyInfo.copyLinkIndex){
			if(template.getData(YtPlatform.PLATFORM_COPYLINK)!=null){
				YtCore.getInstance().share(act, YtPlatform.PLATFORM_COPYLINK, template.getListener(YtPlatform.PLATFORM_COPYLINK),template.getData(YtPlatform.PLATFORM_COPYLINK));
			}else{
				YtCore.getInstance().share(act, YtPlatform.PLATFORM_COPYLINK, template.getListener(YtPlatform.PLATFORM_COPYLINK),shareData);
			}
		}
	}
}
