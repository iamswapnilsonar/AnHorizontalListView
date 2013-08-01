package co.ardor.anhorizontallistview;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.Gallery;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class AnHorizontalListViewActivity extends Activity {
	Gallery myHorizontalListView;
	MyAdapter myAdapter;
	FrameLayout linearLayout;
	// private View lastview;

	Toast toast;

	/** Called when the activity is first created. */
	@SuppressLint("ShowToast")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		linearLayout = (FrameLayout) findViewById(R.id.linear_layout);
		myHorizontalListView = (Gallery) findViewById(R.id.horizontallistview);
//		myHorizontalListView.setBackgroundResource(R.drawable.gal_overlay);

		// myHorizontalListView.setBackgroundColor(Color.WHITE);

		myAdapter = new MyAdapter(this);
		myHorizontalListView.setAdapter(myAdapter);

		toast = Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT);

		myHorizontalListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View arg1,
					int position, long id) {
				// if (lastview != null)
				// lastview.setBackgroundColor(0xFFFFFFFF);
				// lastview = arg1;
				// arg1.setBackgroundColor(0xFFFF0000);

				toast.setText(parent.getItemAtPosition(position).toString()
						+ " Clicked");
				toast.show();

			}
		});

		/***
		 * This following code snippet is for shifting the center to left. CR is
		 * gallery view starts from left side...
		 */

		/*
		 * DisplayMetrics metrics = new DisplayMetrics();
		 * getWindowManager().getDefaultDisplay().getMetrics(metrics);
		 * 
		 * // Gallery gallery = (Gallery) findViewById(R.id.gallery);
		 * 
		 * MarginLayoutParams mlp = (MarginLayoutParams)
		 * myHorizontalListView.getLayoutParams();
		 * 
		 * int temp=(-(metrics.widthPixels/2+200));
		 * 
		 * mlp.setMargins(temp, mlp.topMargin, mlp.rightMargin, mlp.bottomMargin
		 * );
		 */

	}

	@Override
	protected void onDestroy() {
		toast.cancel();
		super.onDestroy();

	}

	/**
	 * Align the first gallery item to the left.
	 * 
	 * @param parentView
	 *            The view containing the gallery widget (we assume the gallery
	 *            width is set to match_parent)
	 * @param gallery
	 *            The gallery we have to change
	 */
	private void alignGalleryToLeft(Context context, View parentView,
			Gallery gallery) {
		int galleryWidth = parentView.getWidth();

		// We are taking the item widths and spacing from a dimension resource
		// because:
		// 1. No way to get spacing at runtime (no accessor in the Gallery
		// class)
		// 2. There might not yet be any item view created when we are calling
		// this
		// function
		int itemWidth = context.getResources().getDimensionPixelSize(
				R.dimen.gallery_item_width);
		int spacing = context.getResources().getDimensionPixelSize(
				R.dimen.gallery_spacing);

		// The offset is how much we will pull the gallery to the left in order
		// to simulate left alignment of the first item
		final int offset;
		if (galleryWidth <= itemWidth) {
			offset = galleryWidth / 2 - itemWidth / 2 - spacing;
		} else {
			offset = galleryWidth - itemWidth - 2 * spacing;
		}

		// Now update the layout parameters of the gallery in order to set the
		// left margin
		MarginLayoutParams mlp = (MarginLayoutParams) gallery.getLayoutParams();
		mlp.setMargins(-offset, mlp.topMargin, mlp.rightMargin,
				mlp.bottomMargin);
	}

	// /////////////////////////////////////////////////////

	// public static void show(Context ctx, String message, boolean notify)
	// {
	//
	// toast = Toast.makeText(ctx, message,Toast.LENGTH_SHORT);
	//
	// ShapeDrawable roundrect = new ShapeDrawable(new RoundRectShape(new
	// float[]{10,10,10,10,10,10,10,10}, null,null));
	// roundrect.setIntrinsicHeight(50);
	// roundrect.setIntrinsicWidth(200);
	// roundrect.getPaint().setColor(Color.rgb(0,191,255));
	// // if the message is a notify, modify the Toast view
	//
	// if (notify)
	// {
	// int offset = 50;
	//
	// // get the layout, center it, and change the background
	// LinearLayout layout = (LinearLayout) toast.getView();
	// layout.setLayoutParams(new
	// LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
	// layout.setGravity(Gravity.CENTER);
	//
	// layout.setPadding(20, 0, 20, 0);
	// layout.setBackgroundDrawable(roundrect);
	//
	// // get the text view, change the color, and make it narrower
	// TextView tv = (TextView) layout.getChildAt(0);
	// tv.setTextColor(Color.WHITE);
	// tv.setGravity(Gravity.CENTER);
	// tv.setMaxWidth(ctx.getResources().getDisplayMetrics().widthPixels -
	// offset);
	// }
	// toast.show();
	// }

	// ///////////////////////////////////////////////////

	public class MyAdapter extends BaseAdapter {

		Context context;

		// String[] itemsArray = {
		// "SUN","MON", "TUS", "WED", "THU", "FRI", "SAT"};

		String[] itemsArray = { "1", "2", "3", "4", "5", "6", "7", "8", "9",
				"10", "11", "12", "13", "14", "15", "16", "17", "18", "19",
				"20" };

		private int itemBackground;

		MyAdapter(Context c) {
			context = c;
			// TypedArray a = c.obtainStyledAttributes(R.styleable.Gallery1);
			// itemBackground =
			// a.getResourceId(R.styleable.Gallery1_android_galleryItemBackground,0);
			// a.recycle();
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return itemsArray.length;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return itemsArray[position];
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub

			View rowView = LayoutInflater.from(parent.getContext()).inflate(
					R.layout.row, null);
			TextView listTextView = (TextView) rowView
					.findViewById(R.id.itemtext);
			listTextView.setText(itemsArray[position]);

			return rowView;
		}

	}
}