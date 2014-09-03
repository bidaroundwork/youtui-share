package cn.bidaround.youtui_template;

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import cn.bidaround.point.ChannelId;
import cn.bidaround.point.YtPoint;
import cn.bidaround.ytcore.YtCore;
/**
 * 白色列表适配器
 * @author youtui
 */
public class ListPopupAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<String> list;

	public ListPopupAdapter(Context context, ArrayList<String> list) {
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup arg2) {
		ViewHoder hoder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(YtCore.res.getIdentifier("yt_sharelist_item", "layout", YtCore.packName), null);
			hoder = new ViewHoder();
			hoder.imageView = (ImageView) convertView.findViewById(YtCore.res.getIdentifier("sharelistitem_logo_image", "id", YtCore.packName));
			hoder.pointText = (TextView) convertView.findViewById(YtCore.res.getIdentifier("sharelistitem_point_text", "id", YtCore.packName));
			hoder.textView = (TextView) convertView.findViewById(YtCore.res.getIdentifier("sharelistitem_platform_text", "id", YtCore.packName));
			convertView.setTag(hoder);
		} else {
			hoder = (ViewHoder) convertView.getTag();
		}
		
		fillView(hoder, position);

		return convertView;
	}

	private class ViewHoder {
		private ImageView imageView;
		private TextView textView;
		private TextView pointText;
	}
	/**
	 * 填充子项
	 * @param hoder
	 * @param name
	 */
	private void fillView(ViewHoder hoder, int position) {
		hoder.imageView.setImageResource(ShareList.getLogo(list.get(position), context));
		hoder.textView.setText(ShareList.getTitle(list.get(position), context));
		// 显示积分
		if (ShareList.SINAWEIBO.equals(list.get(position))) {
			showPoint(hoder.pointText, ChannelId.SINAWEIBO);
		} else if (ShareList.EMAIL.equals(list.get(position))) {
			showPoint(hoder.pointText, ChannelId.EMAIL);
		} else if (ShareList.QQ.equals(list.get(position))) {
			showPoint(hoder.pointText, ChannelId.QQ);
		} else if (ShareList.QZONE.equals(list.get(position))) {
			showPoint(hoder.pointText, ChannelId.QZONE);
		} else if (ShareList.RENREN.equals(list.get(position))) {
			showPoint(hoder.pointText, ChannelId.RENN);
		} else if (ShareList.SHORTMESSAGE.equals(list.get(position))) {
			showPoint(hoder.pointText, ChannelId.MESSAGE);
		} else if (ShareList.TENCENTWEIBO.equals(list.get(position))) {
			showPoint(hoder.pointText, ChannelId.TENCENTWEIBO);
		} else if (ShareList.WECHAT.equals(list.get(position))) {
			showPoint(hoder.pointText, ChannelId.WECHAT);
		} else if (ShareList.WECHATMOMENTS.equals(list.get(position))) {
			showPoint(hoder.pointText, ChannelId.WECHATFRIEND);
		} else{
			hoder.pointText.setVisibility(View.INVISIBLE);
		}
	}
	
	/**
	 * 显示积分
	 * @param pointText
	 * @param channelId
	 */
	private void showPoint(TextView pointText, int channelId) {
		//积分为0时不显示
		if (YtPoint.pointArr[channelId] == 0) {
			pointText.setVisibility(View.INVISIBLE);
		} else {
			pointText.setVisibility(View.VISIBLE);
			pointText.setText("+" + YtPoint.pointArr[channelId]);
		}

	}

}
