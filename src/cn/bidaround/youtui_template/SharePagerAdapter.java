package cn.bidaround.youtui_template;

import java.util.ArrayList;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
/**
 * viewpager适配,用于ViewPagerPopup
 * @author youtui
 * @since 14/5/4
 * 
 */
public class SharePagerAdapter extends PagerAdapter {
	private ArrayList<View> list;

	public SharePagerAdapter(ArrayList<View> list) {
		this.list = list;
	}

	@Override
	/**ViewPager的页数*/
	public int getCount() {
		return list.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		container.addView( list.get(position));
		return list.get(position);
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView(list.get(position));
	}

}
