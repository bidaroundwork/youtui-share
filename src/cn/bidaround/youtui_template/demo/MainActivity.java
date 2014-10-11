package cn.bidaround.youtui_template.demo;

import java.util.Timer;
import java.util.TimerTask;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import cn.bidaround.point.ChannelId;
import cn.bidaround.point.PointActivity;
import cn.bidaround.point.YtLog;
import cn.bidaround.point.YtPoint;
import cn.bidaround.youtui.R;
import cn.bidaround.youtui_template.YouTuiViewType;
import cn.bidaround.youtui_template.YtTemplate;
import cn.bidaround.youtui_template.YtToast;
import cn.bidaround.ytcore.ErrorInfo;
import cn.bidaround.ytcore.YtCore;
import cn.bidaround.ytcore.YtShareListener;
import cn.bidaround.ytcore.data.KeyInfo;
import cn.bidaround.ytcore.data.ShareData;
import cn.bidaround.ytcore.data.YtPlatform;
import cn.bidaround.ytcore.login.AuthListener;
import cn.bidaround.ytcore.login.AuthLogin;
import cn.bidaround.ytcore.login.AuthUserInfo;
import cn.bidaround.ytcore.util.Util;

/**
 * 用于演示
 * 
 * @author youtui
 * @since 14/6/19
 * 
 */
public class MainActivity extends Activity implements OnClickListener {
	private Button popupBt, listBt, testBt;
	private View main_sina_imageview, main_qq_imageview, main_tencentwb_imageview, main_point_tv1, main_point_tv2;
	private View main_item_framelayout;
	private View main_whiteviewpager_bt;
	private ImageView main_sinashare_imageview;
	private TextView main_point_tv;
	private Button main_addpoint_bt;
	private final int REFRESH_POINT = 0;
	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case REFRESH_POINT:
				if (main_point_tv != null) {
					if (YtPoint.getPlatformPoint(ChannelId.SINAWEIBO) != 0) {
						main_point_tv.setVisibility(View.VISIBLE);
						main_point_tv.setText("+" + YtPoint.getPlatformPoint(ChannelId.SINAWEIBO));
					} else {
						main_point_tv.setVisibility(View.INVISIBLE);
					}

				}
				break;

			default:
				break;
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.yt_activity_main);
		initView();
		YtTemplate.init(this, "123");
	}

	private void initView() {
		// 模拟开发者传递数据
		popupBt = (Button) findViewById(R.id.popup_bt);
		popupBt.setOnClickListener(this);

		listBt = (Button) findViewById(R.id.list_bt);
		listBt.setOnClickListener(this);

		testBt = (Button) findViewById(R.id.main_test_bt);
		testBt.setOnClickListener(this);

		main_point_tv1 = findViewById(R.id.main_point_tv1);
		main_point_tv2 = findViewById(R.id.main_point_tv2);

		main_sina_imageview = findViewById(R.id.main_sina_imageview);
		main_sina_imageview.setOnClickListener(this);
		main_qq_imageview = findViewById(R.id.main_qq_imageview);
		main_qq_imageview.setOnClickListener(this);
		main_tencentwb_imageview = findViewById(R.id.main_tencentwb_imageview);
		main_tencentwb_imageview.setOnClickListener(this);

		main_sinashare_imageview = (ImageView) findViewById(R.id.main_sinashare_imageview);
		main_sinashare_imageview.setOnClickListener(this);
		main_point_tv = (TextView) findViewById(R.id.main_point_tv);
		main_point_tv.setVisibility(View.INVISIBLE);

		findViewById(R.id.main_givepoint_bt).setOnClickListener(this);

		main_whiteviewpager_bt = findViewById(R.id.main_whiteviewpager_bt);
		main_whiteviewpager_bt.setOnClickListener(this);

		main_addpoint_bt = (Button) findViewById(R.id.main_addpoint_bt);
		main_addpoint_bt.setOnClickListener(this);

		TimerTask timerTask = new TimerTask() {

			@Override
			public void run() {
				mHandler.sendEmptyMessage(REFRESH_POINT);
			}
		};
		Timer timer = new Timer();
		timer.schedule(timerTask, 100, 1000);

	}

	@Override
	protected void onPause() {
		Util.dismissDialog();
		super.onPause();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.popup_bt:
			// ShareData使用应用分享类型
			ShareData shareData = new ShareData();
			shareData.isAppShare = false;

			shareData.setDescription("友推积分组件");
			shareData.setTitle("友推分享");
			shareData.setText("通过友推积分组件，开发者几行代码就可以为应用添加分享送积分功能，并提供详尽的后台统计数据，除了本身具备的分享功能外，开发者也可将积分功能单独集成在已有分享组件的app上，快来试试吧 ");
			shareData.setTarget_url("http://youtui.mobi/");
			shareData.setImageUrl("http://cdnup.b0.upaiyun.com/media/image/default.png");

			YtTemplate blackTemplate = new YtTemplate(this, YouTuiViewType.BLACK_POPUP, true);
			blackTemplate.setShareData(shareData);
			blackTemplate.setScreencapVisible(false);
			YtShareListener listener = new YtShareListener() {

				@Override
				public void onSuccess(ErrorInfo error) {
					YtLog.e("onSuccess", error.getErrorMessage());
				}

				@Override
				public void onPreShare() {
					YtLog.e("onPreShare", "onPreShare");
				}

				@Override
				public void onError(ErrorInfo error) {
					YtLog.e("onError", error.getErrorMessage());
				}

				@Override
				public void onCancel() {
					YtLog.e("onCancel", "onCancel");
				}
			};
			/** 添加监听事件，非必需 */
			blackTemplate.addListener(YtPlatform.PLATFORM_QQ, listener);
			blackTemplate.addListener(YtPlatform.PLATFORM_QZONE, listener);
			blackTemplate.addListener(YtPlatform.PLATFORM_RENN, listener);
			blackTemplate.addListener(YtPlatform.PLATFORM_SINAWEIBO, listener);
			blackTemplate.addListener(YtPlatform.PLATFORM_TENCENTWEIBO, listener);
			blackTemplate.addListener(YtPlatform.PLATFORM_WECHAT, listener);
			blackTemplate.addListener(YtPlatform.PLATFORM_WECHATMOMENTS, listener);
			blackTemplate.setPopwindowHeight(700);
			blackTemplate.show();
			break;

		case R.id.list_bt:
			// ShareData使用内容分享类型分享类型
			ShareData contentshareData = new ShareData();
			contentshareData.isAppShare = false;
			contentshareData.setDescription("友推积分组件");
			contentshareData.setShareType(ShareData.SHARETYPE_IMAGEANDTEXT);
			contentshareData.setTitle("友推分享");
			contentshareData.setText("通过友推积分组件，开发者几行代码就可以为应用添加分享送积分功能，并提供详尽的后台统计数据，除了本身具备的分享功能外，开发者也可将积分功能单独集成在已有分享组件的app上，快来试试吧 ");
			contentshareData.setTarget_url("http://youtui.mobi/");
			contentshareData.setVideoUrl("http://video.sina.com.cn/p/sports/cba/v/2013-10-22/144463050817.html");
			//contentshareData.setVideoUrl("http://www.iqiyi.com/v_19rrmjqjpg.html?share_sTime=0-share_eTime=269");
			//contentshareData.setMusicUrl("http://music.sina.com.cn/yueku/i/2850305.html");
			contentshareData.setMusicUrl("http://staff2.ustc.edu.cn/~wdw/softdown/index.asp/0042515_05.ANDY.mp3");
			contentshareData.setImageUrl("http://cdnup.b0.upaiyun.com/media/image/default.png");
			// contentshareData.setImageUrl("http://media.czfw.cn/get.php?id=6C14086E9ECAA425B601CF941578B09F.jpg");
			// contentshareData.setImagePath("/storage/sdcard0/123.png");
			//contentshareData.setImage(ShareData.IMAGETYPE_APPRESOURE, R.drawable.yt_pyqact + "");
			YtTemplate whiteTemplate = new YtTemplate(this, YouTuiViewType.WHITE_LIST, false);
			whiteTemplate.setShareData(contentshareData);

			whiteTemplate.addData(YtPlatform.PLATFORM_MORE_SHARE, contentshareData);
			YtShareListener listener2 = new YtShareListener() {

				@Override
				public void onSuccess(ErrorInfo error) {
					YtLog.e("----", error.getErrorMessage());
				}

				@Override
				public void onPreShare() {
					YtLog.e("----", "onPreShare");
				}

				@Override
				public void onError(ErrorInfo error) {
					YtLog.e("----", error.getErrorMessage());
				}

				@Override
				public void onCancel() {
					YtLog.e("----", "onCancel");
				}
			};
			/** 添加监听事件，非必需 */
			whiteTemplate.addListener(YtPlatform.PLATFORM_QQ, listener2);
			whiteTemplate.addListener(YtPlatform.PLATFORM_QZONE, listener2);
			whiteTemplate.addListener(YtPlatform.PLATFORM_RENN, listener2);
			whiteTemplate.addListener(YtPlatform.PLATFORM_SINAWEIBO, listener2);
			whiteTemplate.addListener(YtPlatform.PLATFORM_TENCENTWEIBO, listener2);
			whiteTemplate.addListener(YtPlatform.PLATFORM_WECHAT, listener2);
			whiteTemplate.addListener(YtPlatform.PLATFORM_WECHATMOMENTS, listener2);
			whiteTemplate.show();
			whiteTemplate.setToastVisible(false);
			break;
		case R.id.main_whiteviewpager_bt:
			// ShareData使用内容分享类型分享类型
			ShareData whiteViewShareData = new ShareData();
			// whiteViewShareData.isAppShare = false;
			// whiteViewShareData.setDescription("友推积分组件");
			// whiteViewShareData.setTitle("友推分享");
			// whiteViewShareData.setText("通过友推积分组件，开发者几行代码就可以为应用添加分享送积分功能，并提供详尽的后台统计数据，除了本身具备的分享功能外，开发者也可将积分功能单独集成在已有分享组件的app上，快来试试吧 ");
			// whiteViewShareData.setTarget_url("http://youtui.mobi/");
			// //whiteViewShareData.setImageUrl("http://cdnup.b0.upaiyun.com/media/image/default.png");
			// whiteViewShareData.setImageUrl("http://gj.dr-wine.cn/index/images/new_gj/logo.png");
			// //whiteViewShareData.setImageUrl("http://a.hiphotos.baidu.com/image/w%3D310/sign=3bddd5b0d358ccbf1bbcb33b29d8bcd4/8694a4c27d1ed21b86f752e7af6eddc451da3ff1.jpg");
			YtTemplate whiteGridTemplate = new YtTemplate(this, YouTuiViewType.WHITE_GRID, true);
			whiteGridTemplate.setShareData(whiteViewShareData);
			whiteGridTemplate.setCapData(whiteViewShareData);
			whiteViewShareData.isAppShare = false;
			whiteViewShareData.setShareType(ShareData.SHARETYPE_IMAGEANDTEXT);
			whiteViewShareData.setDescription("古井贡酒官方商城");// 待分享内容的描述
			whiteViewShareData.setTitle("古井贡酒官方商城"); // 待分享的标题
			whiteViewShareData.setText("新发现一款非常好的购酒APP--#古井贡酒官方商城#,它不仅有牛A喝的酒，还有牛C喝的酒。还等什么，马上下载吧!");// 待分享的文字
			whiteViewShareData.setTarget_url("http://www.gj519.com/url/?action=download");// 待分享内容的跳转链接
			whiteViewShareData.setImage(ShareData.IMAGETYPE_APPRESOURE,String.valueOf(R.drawable.yt_erweimaact));
			
			// whiteViewShareData.setImageUrl("http://www.gj519.com/url/url_download.png");
			YtShareListener whiteViewListener = new YtShareListener() {
				@Override
				public void onSuccess(ErrorInfo error) {
					// YtToast.showS(MainActivity.this, "回调:分享成功");
					YtLog.e("mainActivity onSuccess", error.getErrorMessage());
				}

				@Override
				public void onPreShare() {

				}

				@Override
				public void onError(ErrorInfo error) {
					// YtToast.showS(MainActivity.this, "回调:分享失败");
					YtLog.e("mainActivity onError", error.getErrorMessage());
				}

				@Override
				public void onCancel() {

				}
			};

			whiteGridTemplate.addData(YtPlatform.PLATFORM_QQ, whiteViewShareData);
			whiteGridTemplate.addData(YtPlatform.PLATFORM_QZONE, whiteViewShareData);
			whiteGridTemplate.addData(YtPlatform.PLATFORM_RENN, whiteViewShareData);
			whiteGridTemplate.addData(YtPlatform.PLATFORM_SINAWEIBO, whiteViewShareData);
			whiteGridTemplate.addData(YtPlatform.PLATFORM_TENCENTWEIBO, whiteViewShareData);
			whiteGridTemplate.addData(YtPlatform.PLATFORM_WECHAT, whiteViewShareData);
			whiteGridTemplate.addData(YtPlatform.PLATFORM_WECHATMOMENTS, whiteViewShareData);

			/** 添加分享结果监听,如果开发者不需要处理回调事件则不必设置 */
			whiteGridTemplate.addListener(YtPlatform.PLATFORM_QQ, whiteViewListener);
			whiteGridTemplate.addListener(YtPlatform.PLATFORM_QZONE, whiteViewListener);
			whiteGridTemplate.addListener(YtPlatform.PLATFORM_RENN, whiteViewListener);
			whiteGridTemplate.addListener(YtPlatform.PLATFORM_SINAWEIBO, whiteViewListener);
			whiteGridTemplate.addListener(YtPlatform.PLATFORM_TENCENTWEIBO, whiteViewListener);
			whiteGridTemplate.addListener(YtPlatform.PLATFORM_WECHAT, whiteViewListener);
			whiteGridTemplate.addListener(YtPlatform.PLATFORM_WECHATMOMENTS, whiteViewListener);
			whiteGridTemplate.show();
			// whiteGridTemplate.showScreenCap();
			break;
		case R.id.main_sina_imageview:
			/** 新浪第三方登录 */
			AuthLogin sinaLogin = new AuthLogin();
			// 添加授权监听,userInfo中携带用户信息
			AuthListener listener3 = new AuthListener() {
				@Override
				public void onAuthSucess(Activity act, AuthUserInfo userInfo) {
					Log.i("sinaGender:", userInfo.getSinaGender());
					Log.i("sinaName:", userInfo.getSinaName());
					Log.i("sinaProfileImageUrl:", userInfo.getSinaProfileImageUrl());
					Log.i("sinaScreenname:", userInfo.getSinaScreenname());
					Log.i("sinaUid:", userInfo.getSinaUid());
				}

				@Override
				public void onAuthFail(Activity act) {
				}

				@Override
				public void onAuthCancel(Activity act) {
				}
			};
			sinaLogin.sinaAuth(this, listener3);
			break;

		case R.id.main_qq_imageview:
			/** qq第三方登录 */
			AuthLogin qqLogin = new AuthLogin();

			AuthListener qqListener = new AuthListener() {
				@Override
				public void onAuthSucess(Activity act, AuthUserInfo userInfo) {
					Log.i("qqGender:", userInfo.getQqGender());
					Log.i("qqImageUrl:", userInfo.getQqImageUrl());
					Log.i("qqNickName:", userInfo.getQqNickName());
					Log.i("qqOpenid:", userInfo.getQqOpenid());
				}

				@Override
				public void onAuthFail(Activity act) {

				}

				@Override
				public void onAuthCancel(Activity act) {

				}
			};

			qqLogin.qqAuth(this, qqListener);
			break;
		case R.id.main_tencentwb_imageview:
			/** 腾讯微博第三方登录 */
			AuthLogin tencentWbLogin = new AuthLogin();
			AuthListener tencentWbListener = new AuthListener() {
				@Override
				public void onAuthSucess(Activity act, AuthUserInfo userInfo) {

					Log.i("tencentWbName:", userInfo.getTencentWbName());
					Log.i("tencentWbNick:", userInfo.getTencentWbNick());
					Log.i("tencentWbOpenid:", userInfo.getTencentWbOpenid());
					Log.i("tencentWbHead:", userInfo.getTencentWbHead());
				}

				@Override
				public void onAuthFail(Activity act) {

				}

				@Override
				public void onAuthCancel(Activity act) {

				}
			};

			tencentWbLogin.tencentWbAuth(this, tencentWbListener);
			break;
		case R.id.main_test_bt:
			Intent it = new Intent(this, PointActivity.class);
			startActivity(it);
			break;
		case R.id.main_sinashare_imageview:
			ShareData sinashareData = new ShareData();
			sinashareData.isAppShare = false;
			sinashareData.setDescription("友推积分组件");
			sinashareData.setTitle("友推分享");
			sinashareData.setText("通过友推积分组件，开发者几行代码就可以为应用添加分享送积分功能，并提供详尽的后台统计数据，除了本身具备的分享功能外，开发者也可将积分功能单独集成在已有分享组件的app上，快来试试吧 ");
			sinashareData.setTarget_url("http://youtui.mobi/");
			sinashareData.setImageUrl("http://cdnup.b0.upaiyun.com/media/image/default.png");
			YtShareListener sinaListener = new YtShareListener() {

				@Override
				public void onSuccess(ErrorInfo error) {

				}

				@Override
				public void onPreShare() {

				}

				@Override
				public void onError(ErrorInfo error) {

				}

				@Override
				public void onCancel() {

				}
			};
			YtCore.getInstance().share(this, YtPlatform.PLATFORM_SINAWEIBO, sinaListener, sinashareData);
			break;
		case R.id.main_addpoint_bt:
			new Thread() {
				public void run() {
					String str = YtPoint.customPoint("发帖子");
					if (str != null) {
						YtLog.e("main_addpoint_bt", str);
					}
				};
			}.start();
			break;
		case R.id.main_givepoint_bt:
			new Thread() {
				public void run() {
					String str = YtPoint.givePoint(50, "345");
					if (str != null) {
						YtLog.e("main_addpoint_bt", str);
					}
				};
			}.start();
			break;

		default:

			break;
		}

	}

	@Override
	protected void onDestroy() {
		YtTemplate.release(this);
		super.onDestroy();
	}

}
