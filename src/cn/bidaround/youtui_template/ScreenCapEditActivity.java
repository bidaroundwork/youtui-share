package cn.bidaround.youtui_template;

import java.io.File;
import java.io.FileOutputStream;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import cn.bidaround.point.YtLog;
import cn.bidaround.ytcore.data.ShareData;
import cn.bidaround.ytcore.data.YtPlatform;

/**
 * 处理截屏图像
 * @author youtui
 * @since 14/7/10
 * 
 */
public class ScreenCapEditActivity extends Activity implements OnClickListener {
	private ImageView view;
	private Button share_bt;
	LinearLayout back_linelay;
	private RelativeLayout yt_screencap_choose_paintwidth, yt_screencap_choose_color, yt_screen_drawline, yt_screen_drawrect, clear_bt, yt_screencap_save;
	private Bitmap bit;
	private Bitmap drawBit;
	private Bitmap swapBit;
	float downx = 0, downy = 0;
	float rectx = 0, recty = 0;
	private Canvas canvas;
	private Paint paint;
	private int width, height;
	private boolean drawLine = true;
	private boolean drawRect = false;
	private Handler mHandler = new Handler();
	public static  String picPath = getSDCardPath() + "/youtui/yt_screen.png";
	// 默认画细线
	private boolean drawSmall = true;
	private int chooseColor = 0;
	private int count = 0;
	private Resources res;
	private String packName;
	private ImageView yt_screencap_drawrect_image, yt_screencap_drawline_image, yt_screencap_choose_paintwidth_image, yt_screencap_choose_color_image;
	private int viewType;
	private String target_url;

	private final int COLOR_RED = 0;
	private final int COLOR_BLUE = 5;
	private final int COLOR_BLUE_GREEN = 4;
	private final int COLOR_GRAY = 6;
	private final int COLOR_GREEN = 3;
	private final int COLOR_ORANGE = 1;
	private final int COLOR_YELLOW = 2;

	private final int COLOR_SIZE = 7;

	private Path path = new Path();
	
	private ShareData capdata;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		res = getResources();
		packName = getPackageName();
		viewType = getIntent().getExtras().getInt("viewType");
		target_url = getIntent().getExtras().getString("target_url");
		capdata = (ShareData) getIntent().getExtras().getSerializable("capdata");
		setContentView(res.getIdentifier("yt_activity_screencapedit", "layout", packName));
		// 待编辑图像
		view = (ImageView) findViewById(res.getIdentifier("yt_screencap_image", "id", packName));
		view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
			@Override
			public void onGlobalLayout() {
				if (count == 0) {
					count++;
					width = view.getMeasuredWidth();
					height = view.getMeasuredHeight();
					int w = bit.getWidth();
					int h = bit.getHeight();
					Matrix matrix = new Matrix();
					matrix.postScale((float) (width * 1.0 / w), (float) (height * 1.0 / h));
					drawBit = Bitmap.createBitmap(bit, 0, 0, w, h, matrix, true).copy(Bitmap.Config.ARGB_8888, true);
					resetPaint();
					view.setImageBitmap(drawBit);
					// 设置触摸事件进行绘图
					view.setOnTouchListener(new View.OnTouchListener() {
						@Override
						public boolean onTouch(View v, MotionEvent event) {
							float upx = 0, upy = 0;
							switch (event.getAction()) {
							case MotionEvent.ACTION_DOWN:
								// YtLog.e("onTouch", "ACTION_DOWN");
								rectx = event.getX();
								recty = event.getY();
								downx = event.getX();
								downy = event.getY();
								path.moveTo(downx, downy);

								swapBit = Bitmap.createBitmap(drawBit);

								break;
							case MotionEvent.ACTION_MOVE:
								// YtLog.e("onTouch", "ACTION_MOVE");
								upx = event.getX();
								upy = event.getY();
								if (canvas != null && paint != null) {
									if (drawLine) {
										// canvas.drawLine(downx, downy, upx,
										// upy, paint);
										drawBit = Bitmap.createBitmap(swapBit);
										view.setImageBitmap(drawBit);
										resetPaint();

										path.lineTo(upx, upy);
										canvas.drawPath(path, paint);
									} else {
										drawBit = Bitmap.createBitmap(swapBit);
										view.setImageBitmap(drawBit);
										resetPaint();
										canvas.drawLine(rectx, recty, rectx, upy, paint);
										canvas.drawLine(rectx, recty, upx, recty, paint);
										canvas.drawLine(rectx, upy, upx, upy, paint);
										canvas.drawLine(upx, recty, upx, upy, paint);
									}
								}
								downx = upx;
								downy = upy;
								view.invalidate();
								break;
							case MotionEvent.ACTION_UP:
								// YtLog.e("onTouch", "ACTION_UP");
								upx = event.getX();
								upy = event.getY();

								if (drawRect) {
									canvas.drawLine(rectx, recty, rectx, upy, paint);
									canvas.drawLine(rectx, recty, upx, recty, paint);
									canvas.drawLine(rectx, upy, upx, upy, paint);
									canvas.drawLine(upx, recty, upx, upy, paint);
									view.invalidate();
								} else {
									path.reset();
								}
								break;
							default:
								break;
							}
							return true;
						}
					});
				}

			}
		});
		// 画线
		yt_screen_drawline = (RelativeLayout) findViewById(res.getIdentifier("yt_screen_drawline", "id", packName));
		yt_screen_drawline.setOnClickListener(this);
		yt_screencap_drawline_image = (ImageView) findViewById(res.getIdentifier("yt_screencap_drawline_image", "id", packName));
		yt_screencap_drawline_image.setBackgroundResource(res.getIdentifier("yt_screencap_pencil_on", "drawable", packName));

		bit = BitmapFactory.decodeFile(getSDCardPath() + "/youtui/yt_screen.png").copy(Bitmap.Config.ARGB_8888, true);
		BitmapDrawable background = new BitmapDrawable(bit);
		view.setBackgroundDrawable(background);
		// 清空按钮
		clear_bt = (RelativeLayout) findViewById(res.getIdentifier("yt_screencap_clear", "id", packName));
		clear_bt.setOnClickListener(this);
		// 返回按钮
		back_linelay = (LinearLayout) findViewById(res.getIdentifier("yt_screencap_back_linelay", "id", packName));
		back_linelay.setOnClickListener(this);
		// 分享按钮
		share_bt = (Button) findViewById(res.getIdentifier("yt_screencap_share_bt", "id", packName));
		share_bt.setOnClickListener(this);
		// 画矩形
		yt_screen_drawrect = (RelativeLayout) findViewById(res.getIdentifier("yt_screen_drawrect", "id", packName));
		yt_screen_drawrect.setOnClickListener(this);
		yt_screencap_drawrect_image = (ImageView) findViewById(res.getIdentifier("yt_screencap_drawrect_image", "id", packName));
		// 选择线粗细
		yt_screencap_choose_paintwidth = (RelativeLayout) findViewById(res.getIdentifier("yt_screencap_choose_paintwidth", "id", packName));
		yt_screencap_choose_paintwidth.setOnClickListener(this);
		yt_screencap_choose_paintwidth_image = (ImageView) findViewById(res.getIdentifier("yt_screencap_choose_paintwidth_image", "id", packName));
		// 粗线选择按钮
		yt_screencap_choose_color = (RelativeLayout) findViewById(res.getIdentifier("yt_screencap_choose_color", "id", packName));
		yt_screencap_choose_color.setOnClickListener(this);
		yt_screencap_choose_color_image = (ImageView) findViewById(res.getIdentifier("yt_screencap_choose_color_image", "id", packName));
		// 保存图像
		yt_screencap_save = (RelativeLayout) findViewById(res.getIdentifier("yt_screencap_save", "id", packName));
		yt_screencap_save.setOnClickListener(this);
	}

	/**
	 * @return
	 */
	private static String getSDCardPath() {
		File sdcardDir = null;
		boolean sdcardExist = Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
		if (sdcardExist) {
			sdcardDir = Environment.getExternalStorageDirectory();
		}
		return sdcardDir.toString();
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == res.getIdentifier("yt_screencap_back_linelay", "id", packName)) {
			finish();
		} else if (v.getId() == res.getIdentifier("yt_screencap_share_bt", "id", packName)) {
			savePic(false);
			if(capdata == null){
				YtTemplate template = new YtTemplate(this, viewType, false);
				template.removePlatform(YtPlatform.PLATFORM_MESSAGE);
				template.removePlatform(YtPlatform.PLATFORM_EMAIL);
				ShareData shareData = new ShareData();
				shareData.setImagePath(picPath);
				shareData.setTarget_url(target_url);
				String sharePic = res.getString(res.getIdentifier("yt_sharepic", "string", packName));
				shareData.setText(sharePic);
				shareData.setTitle(sharePic);
				shareData.setShareType(ShareData.SHARETYPE_IMAGE);
				template.setShareData(shareData);
				template.show();
			}else{
				YtTemplate template = new YtTemplate(this, viewType, false);
				template.removePlatform(YtPlatform.PLATFORM_MESSAGE);
				template.removePlatform(YtPlatform.PLATFORM_EMAIL);
				ShareData shareData = capdata;
				shareData.setImagePath(picPath);
				template.setCapData(capdata);
				template.setShareData(shareData);
				template.show();
			}
			// 用户快速多次点击可能会调出多个页面，这里在点击后的短时间内设置禁止点击
			share_bt.setClickable(false);
			mHandler.postDelayed(new Runnable() {
				@Override
				public void run() {
					share_bt.setClickable(true);
				}
			}, 500);
		} else if (v.getId() == res.getIdentifier("yt_screen_drawrect", "id", packName)) {
			drawRect = !drawRect;
			resetPaint();
			if (drawRect) {
				// 如果打开画矩形，将画线关闭
				drawLine = false;
				yt_screencap_drawline_image.setBackgroundResource(res.getIdentifier("yt_screencap_pencil_off", "drawable", packName));
				yt_screencap_drawrect_image.setBackgroundResource(res.getIdentifier("yt_screencap_rectangle_on", "drawable", packName));
			} else {
				yt_screencap_drawrect_image.setBackgroundResource(res.getIdentifier("yt_screencap_rectangle_off", "drawable", packName));
			}
		} else if (v.getId() == res.getIdentifier("yt_screencap_clear", "id", packName)) {
			// 清除按钮
			if (view != null && drawBit != null) {
				int w = bit.getWidth();
				int h = bit.getHeight();
				Matrix matrix = new Matrix();
				matrix.postScale((float) (width * 1.0 / w), (float) (height * 1.0 / h));
				drawBit = Bitmap.createBitmap(bit, 0, 0, w, h, matrix, true).copy(Bitmap.Config.ARGB_8888, true);
				view.setImageBitmap(drawBit);
				resetPaint();
				view.invalidate();
			}
		} else if (v.getId() == res.getIdentifier("yt_screen_drawline", "id", packName)) {
			// 画线
			drawLine = !drawLine;
			resetPaint();
			if (drawLine) {
				// 如果打开画线，将画矩形关闭
				drawRect = false;
				yt_screencap_drawrect_image.setBackgroundResource(res.getIdentifier("yt_screencap_rectangle_off", "drawable", packName));
				yt_screencap_drawline_image.setBackgroundResource(res.getIdentifier("yt_screencap_pencil_on", "drawable", packName));

			} else {
				yt_screencap_drawline_image.setBackgroundResource(res.getIdentifier("yt_screencap_pencil_off", "drawable", packName));
			}
		} else if (v.getId() == res.getIdentifier("yt_screencap_choose_paintwidth", "id", packName)) {
			// 选择粗细
			drawSmall = !drawSmall;

			resetPaint();

			if (drawSmall) {
				yt_screencap_choose_paintwidth_image.setBackgroundResource(res.getIdentifier("yt_screencap_circle_small_off", "drawable", packName));
			} else {
				yt_screencap_choose_paintwidth_image.setBackgroundResource(res.getIdentifier("yt_screencap_circle_middle_off", "drawable", packName));
			}

		} else if (v.getId() == res.getIdentifier("yt_screencap_choose_color", "id", packName)) {
			// 选择颜色
			chooseColor++;
			resetPaint();
			switch (chooseColor % COLOR_SIZE) {
			case COLOR_RED:
				yt_screencap_choose_color_image.setBackgroundResource(res.getIdentifier("yt_colorchoose_red", "drawable", packName));
				break;
			case COLOR_BLUE:
				yt_screencap_choose_color_image.setBackgroundResource(res.getIdentifier("yt_colorchoose_blue", "drawable", packName));
				break;
			case COLOR_BLUE_GREEN:
				yt_screencap_choose_color_image.setBackgroundResource(res.getIdentifier("yt_colorchoose_blue_green", "drawable", packName));
				break;
			case COLOR_GRAY:
				yt_screencap_choose_color_image.setBackgroundResource(res.getIdentifier("yt_colorchoose_gray", "drawable", packName));
				break;
			case COLOR_GREEN:
				yt_screencap_choose_color_image.setBackgroundResource(res.getIdentifier("yt_colorchoose_green", "drawable", packName));
				break;
			case COLOR_ORANGE:
				yt_screencap_choose_color_image.setBackgroundResource(res.getIdentifier("yt_colorchoose_orange", "drawable", packName));
				break;
			case COLOR_YELLOW:
				yt_screencap_choose_color_image.setBackgroundResource(res.getIdentifier("yt_colorchoose_yellow", "drawable", packName));
				break;

			default:
				break;
			}
		} else if (v.getId() == res.getIdentifier("yt_screencap_save", "id", packName)) {
			savePic(true);
		}

	}

	/** 重置画笔 */
	private void resetPaint() {
		if (drawRect || drawLine) {
			canvas = new Canvas(drawBit);
			paint = new Paint();
			paint.setStyle(Style.STROKE);
			switch (chooseColor % COLOR_SIZE) {
			case COLOR_RED:
				int red = res.getColor(res.getIdentifier("yt_cap_red", "color", packName));
				paint.setColor(red);
				break;
			case COLOR_BLUE:
				int blue = res.getColor(res.getIdentifier("yt_cap_blue", "color", packName));
				paint.setColor(blue);
				break;
			case COLOR_BLUE_GREEN:
				int blueGreen = res.getColor(res.getIdentifier("yt_cap_blue_green", "color", packName));
				paint.setColor(blueGreen);
				break;
			case COLOR_GRAY:
				int gray = res.getColor(res.getIdentifier("yt_cap_gray", "color", packName));
				paint.setColor(gray);
				break;
			case COLOR_GREEN:
				int green = res.getColor(res.getIdentifier("yt_cap_green", "color", packName));
				paint.setColor(green);
				break;
			case COLOR_ORANGE:
				int orange = res.getColor(res.getIdentifier("yt_cap_orange", "color", packName));
				paint.setColor(orange);
				break;
			case COLOR_YELLOW:
				int yellow = res.getColor(res.getIdentifier("yt_cap_yellow", "color", packName));
				paint.setColor(yellow);
				break;

			default:
				break;
			}
			paint.setAlpha(150);
			// 设置边缘光滑
			paint.setStrokeCap(Paint.Cap.ROUND);
			if (drawSmall) {;
				paint.setStrokeWidth(5);
			} else {
				paint.setStrokeWidth(15);
			}
		} else {
			canvas = null;
			paint = null;
		}
	}

	/** 保存涂鸦图片 */
	private  void savePic(boolean showMsg) {
		YtLog.e("timetamp", System.currentTimeMillis() + "");
		// 图片存储路径
		String SavePath = getSDCardPath() + "/youtui";
		// 保存Bitmap
		try {
			File path = new File(SavePath);
			// 文件
			picPath = SavePath + "/yt_" + System.currentTimeMillis() + ".png";
			File file = new File(picPath);
			if (!path.exists()) {
				path.mkdirs();
			}
			if (!file.exists()) {
				file.createNewFile();
			}
			FileOutputStream fos = null;
			fos = new FileOutputStream(file);
			if (null != fos) {
				drawBit.compress(Bitmap.CompressFormat.PNG, 90, fos);
				fos.flush();
				fos.close();
				if (showMsg) {
					String saveCap = res.getString(res.getIdentifier("yt_savecap", "string", packName));
					Toast.makeText(this, saveCap, Toast.LENGTH_SHORT).show();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
