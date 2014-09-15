package cn.bidaround.youtui_template;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Bitmap.Config;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import cn.bidaround.point.PointActivity;
import cn.bidaround.ytcore.YtCore;
import cn.bidaround.ytcore.data.ShareData;
import cn.bidaround.ytcore.util.Util;

public class QRCodePopup extends PopupWindow implements OnClickListener {
	private YtTemplate template;
	private ShareData shareData;
	private Activity act;
	private boolean hasAct;
	private ImageView popup_qrcode_image;
	private int height;

	public QRCodePopup(Activity act, boolean hasAct, YtTemplate template, ShareData shareData,int height) {
		this.act = act;
		this.template = template;
		this.shareData = shareData;
		this.hasAct = hasAct;
		this.height = height;
	}

	/**
	 * 显示分享界面
	 */
	@SuppressWarnings("deprecation")
	public void show() {
		View view = LayoutInflater.from(act).inflate(YtCore.res.getIdentifier("yt_popup_qrcode", "layout", YtCore.packName), null);
		initButton(view);
		// 设置whitepopupwindow的属
		setFocusable(true);
		setOutsideTouchable(true);
		setBackgroundDrawable(YtCore.res.getDrawable(YtCore.res.getIdentifier("yt_side", "drawable", YtCore.packName)));
		setContentView(view);
		setWidth(act.getWindowManager().getDefaultDisplay().getWidth());
		setHeight(height);
		setAnimationStyle(YtCore.res.getIdentifier("YtSharePopupAnim", "style", YtCore.packName));
		showAtLocation(getContentView(), Gravity.BOTTOM, 0, 0);
	}

	/**
	 * 初始化积分按钮
	 * 
	 * @param view
	 */

	private void initButton(View view) {
		TextView cancelBt = (TextView) view.findViewById(YtCore.res.getIdentifier("popup_qrcode_cancel_bt", "id", YtCore.packName));
		if (hasAct) {
			String pointCharge = YtCore.res.getString(YtCore.res.getIdentifier("yt_pointcharge", "string", YtCore.packName));
			cancelBt.setText(pointCharge);
		} else {
			String cancel = YtCore.res.getString(YtCore.res.getIdentifier("yt_cancel", "string", YtCore.packName));
			cancelBt.setText(cancel);
		}
		cancelBt.setOnClickListener(this);
		
		popup_qrcode_image = (ImageView) view.findViewById(YtCore.res.getIdentifier("popup_qrcode_image", "id", YtCore.packName));
		if(shareData.getTarget_url()!=null){
			Bitmap bm = generateQRCode(shareData.getTarget_url());
			popup_qrcode_image.setImageBitmap(bm);
		}
	}
	
	
    private Bitmap generateQRCode(String content) {  
        try {  
            QRCodeWriter writer = new QRCodeWriter();  
            // MultiFormatWriter writer = new MultiFormatWriter();  
            BitMatrix matrix = writer.encode(content, BarcodeFormat.QR_CODE, Util.px2dip(act,300), Util.px2dip(act,300));  
            return bitMatrix2Bitmap(matrix);  
        } catch (WriterException e) {  
            e.printStackTrace();  
        }  
        return null;  
    } 
	
	
    private Bitmap bitMatrix2Bitmap(BitMatrix matrix) {  
        int w = matrix.getWidth();  
        int h = matrix.getHeight();  
        int[] rawData = new int[w * h];  
        for (int i = 0; i < w; i++) {  
            for (int j = 0; j < h; j++) {  
                int color = Color.WHITE;  
                if (matrix.get(i, j)) {  
                    color = YtCore.res.getColor(YtCore.res.getIdentifier("yt_qrcode_blue", "color", YtCore.packName));  
                }  
                rawData[i + (j * w)] = color;  
            }  
        }  
  
        Bitmap bitmap = Bitmap.createBitmap(w, h, Config.RGB_565);  
        bitmap.setPixels(rawData, 0, w, 0, 0, w, h);  
        return bitmap;  
    }

	/**
	 * 活动按钮事件
	 */
	@Override
	public void onClick(View v) {

		if (v.getId() == YtCore.res.getIdentifier("popup_qrcode_cancel_bt", "id", YtCore.packName)) {
			/** 有活动点击显示活动规则 */
			if (hasAct) {
				Intent it = new Intent(act, PointActivity.class);
				act.startActivity(it);
			} else {
				this.dismiss();
			}
		}
	}
}
