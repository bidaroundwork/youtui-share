package cn.bidaround.youtui_template;



import java.util.ArrayList;
import cn.bidaround.point.ChannelId;
import cn.bidaround.point.YtPoint;
import cn.bidaround.ytcore.YtCore;
import cn.bidaround.ytcore.util.Util;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * GridView适配器，用于ViewPagerPopup中的GridView
 * @author youtui
 * @since 2014/3/25
 */
public class ShareGridAdapter extends BaseAdapter {
	private Activity act;
	private ArrayList<String> list;
	private int showStyle;

	public ShareGridAdapter(Activity act, ArrayList<String> list, int showStyle) {
		this.act = act;
		this.list = list;
		this.showStyle = showStyle;
	}
	/**
	 * GridView的网格数
	 */
	@Override
	public int getCount() {
		return list.size();
	}
	/**
	 * 父类需要重写的方法
	 */
	@Override
	public Object getItem(int arg0) {
		return null;
	}
	/**
	 * 父类需要重写的方法
	 */
	@Override
	public long getItemId(int arg0) {
		return 0;
	}
	/**
	 * 定义GridView显示的内容
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup arg2) {	
		//使用convertView优化listview
		if (convertView == null) {
			View view = null;
			if(showStyle==YouTuiViewType.BLACK_POPUP){
				view = LayoutInflater.from(act).inflate(YtCore.res.getIdentifier("yt_pagergrid_item", "layout", YtCore.packName), null);			
			}else if(showStyle==YouTuiViewType.WHITE_LIST){
				view = LayoutInflater.from(act).inflate(YtCore.res.getIdentifier("yt_sharelist_item", "layout", YtCore.packName), null);
			}else if(showStyle==YouTuiViewType.WHITE_GRID){
				view = LayoutInflater.from(act).inflate(YtCore.res.getIdentifier("yt_whiteviewpager_grid_item", "layout", YtCore.packName), null);
			}
			convertView = view;
		}
		
		TextView pointText = null;
		if(showStyle==YouTuiViewType.BLACK_POPUP){
			ImageView imageView = (ImageView) convertView.findViewById(YtCore.res.getIdentifier("logo_imageview", "id", YtCore.packName));
			TextView textView = (TextView) convertView.findViewById(YtCore.res.getIdentifier("logo_textview", "id", YtCore.packName));
			// 设置社交平台logo 
			imageView.setImageResource(ShareList.getLogo(list.get(position), act));
			// 积分textview
			textView.setText(ShareList.getTitle(list.get(position)));
			pointText = (TextView) convertView.findViewById(YtCore.res.getIdentifier("griditem_point_tv", "id", YtCore.packName));
		}else if(showStyle==YouTuiViewType.WHITE_LIST){
			ImageView imageView = (ImageView) convertView.findViewById(YtCore.res.getIdentifier("sharelistitem_logo_image", "id", YtCore.packName));
			TextView textView = (TextView) convertView.findViewById(YtCore.res.getIdentifier("sharelistitem_platform_text", "id", YtCore.packName));
			// 设置社交平台logo
			imageView.setImageResource(ShareList.getLogo(list.get(position), act));
			// 积分textview
			textView.setText(ShareList.getTitle(list.get(position)));
			pointText = (TextView) convertView.findViewById(YtCore.res.getIdentifier("sharelistitem_point_text", "id", YtCore.packName));
		}else if(showStyle==YouTuiViewType.WHITE_GRID){
			ImageView imageView = (ImageView) convertView.findViewById(YtCore.res.getIdentifier("whitepagergrid_logo_imageview", "id", YtCore.packName));
			TextView textView = (TextView) convertView.findViewById(YtCore.res.getIdentifier("whitepagergrid_logo_textview", "id", YtCore.packName));
			// 设置社交平台logo 
			imageView.setImageResource(ShareList.getLogo(list.get(position), act));
			// 积分textview
			textView.setText(ShareList.getTitle(list.get(position)));
			textView.setTextColor(0xffa8a8a8);
			pointText = (TextView) convertView.findViewById(YtCore.res.getIdentifier("whitepagergrid_point_tv", "id", YtCore.packName));
		}

		// 显示积分
		if (ShareList.SINAWEIBO.equals(list.get(position))) {
			showPoint(pointText, ChannelId.SINAWEIBO);
		} else if (ShareList.EMAIL.equals(list.get(position))) {
			showPoint(pointText, ChannelId.EMAIL);
		} else if (ShareList.QQ.equals(list.get(position))) {
			showPoint(pointText, ChannelId.QQ);
		} else if (ShareList.QZONE.equals(list.get(position))) {
			showPoint(pointText, ChannelId.QZONE);
		} else if (ShareList.RENREN.equals(list.get(position))) {
			showPoint(pointText, ChannelId.RENN);
		} else if (ShareList.SHORTMESSAGE.equals(list.get(position))) {
			showPoint(pointText, ChannelId.MESSAGE);
		} else if (ShareList.TENCENTWEIBO.equals(list.get(position))) {
			showPoint(pointText, ChannelId.TENCENTWEIBO);
		} else if (ShareList.WECHAT.equals(list.get(position))) {
			showPoint(pointText, ChannelId.WECHAT);
		} else if (ShareList.WECHATMOMENTS.equals(list.get(position))) {
			showPoint(pointText, ChannelId.WECHATFRIEND);
		} else{
			pointText.setVisibility(View.INVISIBLE);
		}
		return convertView;
	}

	/**
	 * 显示积分
	 * 
	 * @param pointText
	 * @param channelId
	 */
	private void showPoint(TextView pointText, int channelId) {
		//黑色樣式下積分大于0時，要將積分TextView加寬才能顯示完全
		if(showStyle==YouTuiViewType.BLACK_POPUP){
			if (YtPoint.pointArr[channelId] >= 10) {
				pointText.getLayoutParams().width = Util.dip2px(act, 40);
			}
		}
		//积分为0时不显示
		if (YtPoint.pointArr[channelId] == 0) {
			pointText.setVisibility(View.INVISIBLE);
		} else {
			pointText.setVisibility(View.VISIBLE);
			pointText.setText("+" + YtPoint.pointArr[channelId]);
		}

	}

}
