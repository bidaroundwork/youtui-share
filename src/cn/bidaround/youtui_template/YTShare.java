package cn.bidaround.youtui_template;

import android.app.Activity;
import android.content.Intent;
import cn.bidaround.ytcore.YtCore;
import cn.bidaround.ytcore.data.ShareData;
import cn.bidaround.ytcore.data.YtPlatform;

/**
 * 具体的分享内容类
 * 
 * @author youtui
 * @since 14/6/19
 */
public class YTShare {
	private String picPath = TemplateUtil.getSDCardPath() + "/youtui/yt_screen.png";
	private Activity act;

	public YTShare(Activity act) {
		this.act = act;
	}

	/**
	 * 分享到各个平台 判断平台所处的页面和在页面的位置设置点击分享事件 该方法用于黑色网格样式中
	 * 
	 * @param position
	 *            点击事件所在gridview上位置
	 * @param pageIndex
	 *            点击事件所在viewpager页面indext
	 * @param ShareData
	 *            .shareData
	 */
	public void doGridShare(int position, int pageIndex, YtTemplate template, ShareData shareData, int itemAmount, YTBasePopupWindow instance) {
		// 新浪微博，
		if (position == template.getIndex(YtPlatform.PLATFORM_SINAWEIBO) % itemAmount && template.getIndex(YtPlatform.PLATFORM_SINAWEIBO) / itemAmount == pageIndex) {
			if (template.getData(YtPlatform.PLATFORM_SINAWEIBO) != null) {
				YtCore.getInstance().share(act, YtPlatform.PLATFORM_SINAWEIBO, template.getListener(YtPlatform.PLATFORM_SINAWEIBO), template.getData(YtPlatform.PLATFORM_SINAWEIBO));
			} else {
				YtCore.getInstance().share(act, YtPlatform.PLATFORM_SINAWEIBO, template.getListener(YtPlatform.PLATFORM_SINAWEIBO), shareData);
			}
			// 微信
		} else if (position == template.getIndex(YtPlatform.PLATFORM_WECHAT) % itemAmount && template.getIndex(YtPlatform.PLATFORM_WECHAT) / itemAmount == pageIndex) {
			if (template.getData(YtPlatform.PLATFORM_WECHAT) != null) {
				YtCore.getInstance().share(act, YtPlatform.PLATFORM_WECHAT, template.getListener(YtPlatform.PLATFORM_WECHAT), template.getData(YtPlatform.PLATFORM_WECHAT));
			} else {
				YtCore.getInstance().share(act, YtPlatform.PLATFORM_WECHAT, template.getListener(YtPlatform.PLATFORM_WECHAT), shareData);
			}

			// QQ
		} else if (position == template.getIndex(YtPlatform.PLATFORM_QQ) % itemAmount && template.getIndex(YtPlatform.PLATFORM_QQ) / itemAmount == pageIndex) {
			if (template.getData(YtPlatform.PLATFORM_QQ) != null) {
				YtCore.getInstance().share(act, YtPlatform.PLATFORM_QQ, template.getListener(YtPlatform.PLATFORM_QQ), template.getData(YtPlatform.PLATFORM_QQ));
			} else {
				YtCore.getInstance().share(act, YtPlatform.PLATFORM_QQ, template.getListener(YtPlatform.PLATFORM_QQ), shareData);
			}

			// QQ空间
		} else if (position == template.getIndex(YtPlatform.PLATFORM_QZONE) % itemAmount && template.getIndex(YtPlatform.PLATFORM_QZONE) / itemAmount == pageIndex) {
			if (template.getData(YtPlatform.PLATFORM_QZONE) != null) {
				YtCore.getInstance().share(act, YtPlatform.PLATFORM_QZONE, template.getListener(YtPlatform.PLATFORM_QZONE), template.getData(YtPlatform.PLATFORM_QZONE));
			} else {
				YtCore.getInstance().share(act, YtPlatform.PLATFORM_QZONE, template.getListener(YtPlatform.PLATFORM_QZONE), shareData);
			}

			// 微信朋友圈
		} else if (position == template.getIndex(YtPlatform.PLATFORM_WECHATMOMENTS) % itemAmount && template.getIndex(YtPlatform.PLATFORM_WECHATMOMENTS) / itemAmount == pageIndex) {
			if (template.getData(YtPlatform.PLATFORM_WECHATMOMENTS) != null) {
				YtCore.getInstance().share(act, YtPlatform.PLATFORM_WECHATMOMENTS, template.getListener(YtPlatform.PLATFORM_WECHATMOMENTS), template.getData(YtPlatform.PLATFORM_WECHATMOMENTS));
			} else {
				YtCore.getInstance().share(act, YtPlatform.PLATFORM_WECHATMOMENTS, template.getListener(YtPlatform.PLATFORM_WECHATMOMENTS), shareData);
			}

			// 腾讯微博
		} else if (position == template.getIndex(YtPlatform.PLATFORM_TENCENTWEIBO) % itemAmount && template.getIndex(YtPlatform.PLATFORM_TENCENTWEIBO) / itemAmount == pageIndex) {
			if (template.getData(YtPlatform.PLATFORM_TENCENTWEIBO) != null) {
				YtCore.getInstance().share(act, YtPlatform.PLATFORM_TENCENTWEIBO, template.getListener(YtPlatform.PLATFORM_TENCENTWEIBO), template.getData(YtPlatform.PLATFORM_TENCENTWEIBO));
			} else {
				YtCore.getInstance().share(act, YtPlatform.PLATFORM_TENCENTWEIBO, template.getListener(YtPlatform.PLATFORM_TENCENTWEIBO), shareData);
			}

			// 人人网
		} else if (position == template.getIndex(YtPlatform.PLATFORM_RENN) % itemAmount && template.getIndex(YtPlatform.PLATFORM_RENN) / itemAmount == pageIndex) {
			if (template.getData(YtPlatform.PLATFORM_RENN) != null) {
				YtCore.getInstance().share(act, YtPlatform.PLATFORM_RENN, template.getListener(YtPlatform.PLATFORM_RENN), template.getData(YtPlatform.PLATFORM_RENN));
			} else {
				YtCore.getInstance().share(act, YtPlatform.PLATFORM_RENN, template.getListener(YtPlatform.PLATFORM_RENN), shareData);
			}

			// 短信
		} else if (position == template.getIndex(YtPlatform.PLATFORM_MESSAGE) % itemAmount && template.getIndex(YtPlatform.PLATFORM_MESSAGE) / itemAmount == pageIndex) {
			if (template.getData(YtPlatform.PLATFORM_MESSAGE) != null) {
				YtCore.getInstance().share(act, YtPlatform.PLATFORM_MESSAGE, template.getListener(YtPlatform.PLATFORM_MESSAGE), template.getData(YtPlatform.PLATFORM_MESSAGE));
			} else {
				YtCore.getInstance().share(act, YtPlatform.PLATFORM_MESSAGE, template.getListener(YtPlatform.PLATFORM_MESSAGE), shareData);
			}
			// 邮件
		} else if (position == template.getIndex(YtPlatform.PLATFORM_EMAIL) % itemAmount && template.getIndex(YtPlatform.PLATFORM_EMAIL) / itemAmount == pageIndex) {
			if (template.getData(YtPlatform.PLATFORM_EMAIL) != null) {
				YtCore.getInstance().share(act, YtPlatform.PLATFORM_EMAIL, template.getListener(YtPlatform.PLATFORM_EMAIL), template.getData(YtPlatform.PLATFORM_EMAIL));
			} else {
				YtCore.getInstance().share(act, YtPlatform.PLATFORM_EMAIL, template.getListener(YtPlatform.PLATFORM_EMAIL), shareData);
			}

		} else if (position == template.getIndex(YtPlatform.PLATFORM_MORE_SHARE) % itemAmount && template.getIndex(YtPlatform.PLATFORM_MORE_SHARE) / itemAmount == pageIndex) {
			if (template.getData(YtPlatform.PLATFORM_MORE_SHARE) != null) {
				YtCore.getInstance().share(act, YtPlatform.PLATFORM_MORE_SHARE, template.getListener(YtPlatform.PLATFORM_MORE_SHARE), template.getData(YtPlatform.PLATFORM_MORE_SHARE));
			} else {
				YtCore.getInstance().share(act, YtPlatform.PLATFORM_MORE_SHARE, template.getListener(YtPlatform.PLATFORM_MORE_SHARE), shareData);
			}

		} else if (position == template.getIndex(YtPlatform.PLATFORM_COPYLINK) % itemAmount && template.getIndex(YtPlatform.PLATFORM_COPYLINK) / itemAmount == pageIndex) {
			if (template.getData(YtPlatform.PLATFORM_COPYLINK) != null) {
				YtCore.getInstance().share(act, YtPlatform.PLATFORM_COPYLINK, template.getListener(YtPlatform.PLATFORM_COPYLINK), template.getData(YtPlatform.PLATFORM_COPYLINK));
			} else {
				YtCore.getInstance().share(act, YtPlatform.PLATFORM_COPYLINK, template.getListener(YtPlatform.PLATFORM_COPYLINK), shareData);
			}
		} else if (position == template.getIndex(YtPlatform.PLATFORM_SCREENCAP) % itemAmount && template.getIndex(YtPlatform.PLATFORM_SCREENCAP) / itemAmount == pageIndex) {
			if (instance != null) {
				instance.dismiss();
			}
			if (template.getData(YtPlatform.PLATFORM_SCREENCAP) != null) {
				TemplateUtil.GetandSaveCurrentImage(act,true);
				Intent it = new Intent(act, ScreenCapEditActivity.class);
				it.putExtra("viewType", template.getViewType());
				it.putExtra("target_url", template.getData(YtPlatform.PLATFORM_SCREENCAP).getTarget_url());
				act.startActivity(it);
			} else {
				TemplateUtil.GetandSaveCurrentImage(act,true);
				Intent it = new Intent(act, ScreenCapEditActivity.class);
				it.putExtra("viewType", template.getViewType());
				it.putExtra("target_url", shareData.getTarget_url());
				act.startActivity(it);
			}
		}
	}

	/**
	 * 分享到各个平台, 用于白色列表样式的listview
	 * 
	 * @param position
	 */
	public void doListShare(int position, YtTemplate template, ShareData shareData, YTBasePopupWindow instance) {
		if (position == template.getIndex(YtPlatform.PLATFORM_SINAWEIBO)) {
			if (template.getData(YtPlatform.PLATFORM_SINAWEIBO) != null) {
				YtCore.getInstance().share(act, YtPlatform.PLATFORM_SINAWEIBO, template.getListener(YtPlatform.PLATFORM_SINAWEIBO), template.getData(YtPlatform.PLATFORM_SINAWEIBO));
			} else {
				YtCore.getInstance().share(act, YtPlatform.PLATFORM_SINAWEIBO, template.getListener(YtPlatform.PLATFORM_SINAWEIBO), shareData);
			}

		} else if (position == template.getIndex(YtPlatform.PLATFORM_WECHAT)) {
			if (template.getData(YtPlatform.PLATFORM_WECHAT) != null) {
				YtCore.getInstance().share(act, YtPlatform.PLATFORM_WECHAT, template.getListener(YtPlatform.PLATFORM_WECHAT), template.getData(YtPlatform.PLATFORM_WECHAT));
			} else {
				YtCore.getInstance().share(act, YtPlatform.PLATFORM_WECHAT, template.getListener(YtPlatform.PLATFORM_WECHAT), shareData);
			}

		} else if (position == template.getIndex(YtPlatform.PLATFORM_WECHATMOMENTS)) {
			if (template.getData(YtPlatform.PLATFORM_WECHATMOMENTS) != null) {
				YtCore.getInstance().share(act, YtPlatform.PLATFORM_WECHATMOMENTS, template.getListener(YtPlatform.PLATFORM_WECHATMOMENTS), template.getData(YtPlatform.PLATFORM_WECHATMOMENTS));
			} else {
				YtCore.getInstance().share(act, YtPlatform.PLATFORM_WECHATMOMENTS, template.getListener(YtPlatform.PLATFORM_WECHATMOMENTS), shareData);
			}

		} else if (position == template.getIndex(YtPlatform.PLATFORM_QQ)) {
			if (template.getData(YtPlatform.PLATFORM_QQ) != null) {
				YtCore.getInstance().share(act, YtPlatform.PLATFORM_QQ, template.getListener(YtPlatform.PLATFORM_QQ), template.getData(YtPlatform.PLATFORM_QQ));
			} else {
				YtCore.getInstance().share(act, YtPlatform.PLATFORM_QQ, template.getListener(YtPlatform.PLATFORM_QQ), shareData);
			}

		} else if (position == template.getIndex(YtPlatform.PLATFORM_QZONE)) {
			if (template.getData(YtPlatform.PLATFORM_QZONE) != null) {
				YtCore.getInstance().share(act, YtPlatform.PLATFORM_QZONE, template.getListener(YtPlatform.PLATFORM_QZONE), template.getData(YtPlatform.PLATFORM_QZONE));
			} else {
				YtCore.getInstance().share(act, YtPlatform.PLATFORM_QZONE, template.getListener(YtPlatform.PLATFORM_QZONE), shareData);
			}

		} else if (position == template.getIndex(YtPlatform.PLATFORM_TENCENTWEIBO)) {
			if (template.getData(YtPlatform.PLATFORM_TENCENTWEIBO) != null) {
				YtCore.getInstance().share(act, YtPlatform.PLATFORM_TENCENTWEIBO, template.getListener(YtPlatform.PLATFORM_TENCENTWEIBO), template.getData(YtPlatform.PLATFORM_TENCENTWEIBO));
			} else {
				YtCore.getInstance().share(act, YtPlatform.PLATFORM_TENCENTWEIBO, template.getListener(YtPlatform.PLATFORM_TENCENTWEIBO), shareData);
			}

		} else if (position == template.getIndex(YtPlatform.PLATFORM_RENN)) {
			if (template.getData(YtPlatform.PLATFORM_RENN) != null) {
				YtCore.getInstance().share(act, YtPlatform.PLATFORM_RENN, template.getListener(YtPlatform.PLATFORM_RENN), template.getData(YtPlatform.PLATFORM_RENN));
			} else {
				YtCore.getInstance().share(act, YtPlatform.PLATFORM_RENN, template.getListener(YtPlatform.PLATFORM_RENN), shareData);
			}

		} else if (position == template.getIndex(YtPlatform.PLATFORM_MESSAGE)) {
			if (template.getData(YtPlatform.PLATFORM_MESSAGE) != null) {
				YtCore.getInstance().share(act, YtPlatform.PLATFORM_MESSAGE, template.getListener(YtPlatform.PLATFORM_MESSAGE), template.getData(YtPlatform.PLATFORM_MESSAGE));
			} else {
				YtCore.getInstance().share(act, YtPlatform.PLATFORM_MESSAGE, template.getListener(YtPlatform.PLATFORM_MESSAGE), shareData);
			}

		} else if (position == template.getIndex(YtPlatform.PLATFORM_EMAIL)) {
			if (template.getData(YtPlatform.PLATFORM_EMAIL) != null) {
				YtCore.getInstance().share(act, YtPlatform.PLATFORM_EMAIL, template.getListener(YtPlatform.PLATFORM_EMAIL), template.getData(YtPlatform.PLATFORM_EMAIL));
			} else {
				YtCore.getInstance().share(act, YtPlatform.PLATFORM_EMAIL, template.getListener(YtPlatform.PLATFORM_EMAIL), shareData);
			}

		} else if (position == template.getIndex(YtPlatform.PLATFORM_MORE_SHARE)) {
			if (template.getData(YtPlatform.PLATFORM_MORE_SHARE) != null) {
				YtCore.getInstance().share(act, YtPlatform.PLATFORM_MORE_SHARE, template.getListener(YtPlatform.PLATFORM_MORE_SHARE), template.getData(YtPlatform.PLATFORM_MORE_SHARE));
			} else {
				YtCore.getInstance().share(act, YtPlatform.PLATFORM_MORE_SHARE, template.getListener(YtPlatform.PLATFORM_MORE_SHARE), shareData);
			}

		} else if (position == template.getIndex(YtPlatform.PLATFORM_COPYLINK)) {
			if (template.getData(YtPlatform.PLATFORM_COPYLINK) != null) {
				YtCore.getInstance().share(act, YtPlatform.PLATFORM_COPYLINK, template.getListener(YtPlatform.PLATFORM_COPYLINK), template.getData(YtPlatform.PLATFORM_COPYLINK));
			} else {
				YtCore.getInstance().share(act, YtPlatform.PLATFORM_COPYLINK, template.getListener(YtPlatform.PLATFORM_COPYLINK), shareData);
			}
		} else if (position == template.getIndex(YtPlatform.PLATFORM_SCREENCAP)) {
			if (instance != null) {
				instance.dismiss();
			}
			if (template.getData(YtPlatform.PLATFORM_SCREENCAP) != null) {
				TemplateUtil.GetandSaveCurrentImage(act,true);
				Intent it = new Intent(act, ScreenCapEditActivity.class);
				it.putExtra("viewType", template.getViewType());
				it.putExtra("target_url", template.getData(YtPlatform.PLATFORM_SCREENCAP).getTarget_url());
				act.startActivity(it);
			} else {
				TemplateUtil.GetandSaveCurrentImage(act,true);
				Intent it = new Intent(act, ScreenCapEditActivity.class);
				it.putExtra("viewType", template.getViewType());
				it.putExtra("target_url", shareData.getTarget_url());
				act.startActivity(it);
			}

		}
	}
}
