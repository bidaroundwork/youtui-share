package cn.bidaround.youtui_template;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.PopupWindow;
import android.widget.Toast;
import cn.bidaround.point.YtPoint;
import cn.bidaround.ytcore.data.KeyInfo;

/**
 * 友推基础分享框，其他的分享popupwindow继承于此类
 * @author youtui
 * @since 14/5/4 
 */
public abstract class YTBasePopupWindow extends PopupWindow implements OnItemClickListener {
	private static final String TAG = "at YTBasePopupWindow:";
	/** 主分享界面实例 */
	protected static YTBasePopupWindow instance;
	/** 主分享界面样式 */
	protected int showStyle = -1;
	/**获取积分信息*/
	public static final int GET_POINT = 0;
	/**分享后获得积分成功*/
	public static final int SHARED_HAS_POINT = 1;
	/**分享后获取积分失败*/
	public static final int SHARE_POINT_FAIL = 2;
	/**传入的activity*/
	protected static Activity act;
	/**是否有积分活动*/
	protected static boolean hasAct;
	/**监听分享完后的回调，刷新积分显示和告诉用户获得积分*/
	public static Handler mHandler = null;

	public YTBasePopupWindow(Context context, boolean hasAct) {
		super(context);	
		act = (Activity) context;
		YTBasePopupWindow.hasAct = hasAct;
		initData();
	}

	/**
	 * 刷新积分，将网络图片下载到本地SD卡
	 */
	protected void initData() {
		if (mHandler == null) {
			mHandler = new Handler() {
				public void handleMessage(android.os.Message msg) {
					switch (msg.what) {
					// 刷新积分显示
					case GET_POINT:
						if (instance != null) {
							/** 没有活动的话就不刷新积分 */
							if (hasAct) {
								instance.refresh();
							}
						}
						break;
					case SHARED_HAS_POINT:
						if (hasAct && instance != null) {
							instance.refresh();
						}
						if ((Integer) msg.obj != 0) {
							Toast.makeText(act, "分享成功,积分+" + msg.obj, Toast.LENGTH_SHORT).show();
						} else {
							Toast.makeText(act, "分享成功", Toast.LENGTH_SHORT).show();
						}
						break;
					case SHARE_POINT_FAIL:
						Toast.makeText(act, "分享成功", Toast.LENGTH_SHORT).show();
						break;
					default:
						break;
					}
				};
			};
		}
		// 没有活动则将
		if (hasAct) {
			new Thread() {
				@Override
				public void run() {
					YtPoint.refresh(act, KeyInfo.youTui_AppKey);
				}
			}.start();
		} else {
			for (int i = 0; i < YtPoint.pointArr.length; i++) {
				YtPoint.pointArr[i] = 0;
			}
		}
	}

	/**
	 * 刷新积分，在子类实现
	 * 
	 * @param arr
	 */
	public abstract void refresh();

	/** 释放instance */
	@Override
	public void dismiss() {
		instance = null;
		super.dismiss();
	}

}
