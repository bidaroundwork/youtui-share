package cn.bidaround.youtui_template;

import android.content.Context;
import android.widget.Toast;

/**
 * 取代系统Toast
 * @author youtui
 * @since 14/7/25
 */
public class YtToast {
	public static boolean visible = true;
	
	public static void showS(Context context,String text){
		if(visible){
			Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
		}
	}
	
	public static void showL(Context context,String text){
		if(visible){
			Toast.makeText(context, text, Toast.LENGTH_LONG).show();
		}
	}

}
