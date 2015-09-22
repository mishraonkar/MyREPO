package burrow.co.in.owner.fragments;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import burrow.co.in.R;

public class AddTenantProfileTabOne extends Fragment {
	
	private DatePickerDialog startDatePickerDialog, endDatePickerDialog, cycleDatePickerDialog;
    private SimpleDateFormat dateFormatter;
    private Activity activity;
    
    private EditText startDate, endDate, cycleDate, rentAmount;
    private TextInputLayout til_startDate, til_endDate, til_cycleDate, til_rentAmount;
    private Button back, next;
	
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.owner_add_tenant_profile_tab_one, container, false);
        startDate = (EditText) v.findViewById(R.id.editText_startDate);
        endDate = (EditText) v.findViewById(R.id.editText_endDate);
        cycleDate = (EditText) v.findViewById(R.id.editText_cycleDate);
        rentAmount = (EditText) v.findViewById(R.id.editText_amount);
        
        til_startDate = (TextInputLayout) v.findViewById(R.id.textInputLayout_startDate);
        til_endDate = (TextInputLayout) v.findViewById(R.id.textInputLayout_endDate);
        til_cycleDate = (TextInputLayout) v.findViewById(R.id.textInputLayout_cycleDate);
        til_rentAmount = (TextInputLayout) v.findViewById(R.id.textInputLayout_amount);
        
        back = (Button) v.findViewById(R.id.button_back);
        next = (Button) v.findViewById(R.id.button_next);
        
        activity = getActivity();
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        setDateTimeField();
        
        startDate.setOnClickListener(dateListner);    
        endDate.setOnClickListener(dateListner);
        cycleDate.setOnClickListener(dateListner);
        
        next.setOnClickListener(buttonListner);        
        back.setOnClickListener(buttonListner);
        
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
    
	private BitmapDrawable getBitmapDrawable(Context context, String str){
    	
    	TextView textView = (TextView) activity.findViewById(R.id.textViewFake);
		textView.setText(str);
		textView.setTextColor(Color.parseColor("#FF3030"));

		int spec = MeasureSpec.makeMeasureSpec(0,MeasureSpec.UNSPECIFIED);
		textView.measure(spec, spec);
		textView.layout(0, 0, textView.getWidth(),textView.getMeasuredHeight());
		Bitmap b = Bitmap.createBitmap(textView.getWidth(),textView.getHeight(), Bitmap.Config.ARGB_8888);
		
		
		Canvas canvas = new Canvas(b);
		canvas.translate(-textView.getScrollX(), -textView.getScrollY());
		textView.draw(canvas);
		textView.setDrawingCacheEnabled(true);
		
		
		Bitmap cacheBmp = textView.getDrawingCache();
		Bitmap viewBmp = cacheBmp.copy(Bitmap.Config.ARGB_8888, true);
		textView.destroyDrawingCache(); 
		
		BitmapDrawable bmpDrawable = new BitmapDrawable(context.getResources(), viewBmp);
		bmpDrawable.setBounds(0, 0, bmpDrawable.getIntrinsicWidth(),bmpDrawable.getIntrinsicHeight());
		return bmpDrawable;
    }
    
    private void setDateTimeField() {

        
        Calendar newCalendar = Calendar.getInstance();
        
        startDatePickerDialog = new DatePickerDialog(activity, new OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                startDate.setText(dateFormatter.format(newDate.getTime()));
            }
 
        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        
        endDatePickerDialog = new DatePickerDialog(activity, new OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                endDate.setText(dateFormatter.format(newDate.getTime()));
            }
 
        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        
        cycleDatePickerDialog = new DatePickerDialog(activity, new OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                cycleDate.setText(dateFormatter.format(newDate.getTime()));
            }
 
        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }
    
    OnClickListener dateListner = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			int id = v.getId();
			
			switch (id) {
			case R.id.editText_startDate:
				startDatePickerDialog.show();
				break;
			case R.id.editText_endDate:
				endDatePickerDialog.show();
				break;
			case R.id.editText_cycleDate:
				cycleDatePickerDialog.show();
				break;
			default:
				break;
			}
		}
	};
	
    OnClickListener buttonListner = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			int id = v.getId();
			
			switch (id) {
			case R.id.button_next:
            	int errorFlag = 0;
            	
            	String sDate = startDate.getText().toString().trim();
            	String eDate = endDate.getText().toString().trim();
            	String cDate = cycleDate.getText().toString().trim();
            	String rAmount = rentAmount.getText().toString().trim();
            	
            	if (sDate.length() < 1) {
            		errorFlag = 1;
            		til_startDate.setErrorEnabled(true);
                    SpannableStringBuilder ssb = new SpannableStringBuilder("Error message");
                    BitmapDrawable bmpDrawableText = getBitmapDrawable(activity, "Error message");
                    ssb.setSpan(new ImageSpan(bmpDrawableText), 0, 13, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    til_startDate.setError(ssb);
				}else {
					til_startDate.setError(null);
					til_startDate.setErrorEnabled(false);
				}
            	
            	if (eDate.length() < 1) {
            		errorFlag = 1;
            		til_endDate.setErrorEnabled(true);
                    SpannableStringBuilder ssb = new SpannableStringBuilder("Error message");
                    BitmapDrawable bmpDrawableText = getBitmapDrawable(activity, "Error message");
                    ssb.setSpan(new ImageSpan(bmpDrawableText), 0, 13, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    til_endDate.setError(ssb);
				}else {
					til_endDate.setError(null);
					til_endDate.setErrorEnabled(false);
				}
            	
            	if (cDate.length() < 1) {
            		errorFlag = 1;
            		til_cycleDate.setErrorEnabled(true);
                    SpannableStringBuilder ssb = new SpannableStringBuilder("Error message");
                    BitmapDrawable bmpDrawableText = getBitmapDrawable(activity, "Error message");
                    ssb.setSpan(new ImageSpan(bmpDrawableText), 0, 13, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    til_cycleDate.setError(ssb);
				}else {
					til_cycleDate.setError(null);
					til_cycleDate.setErrorEnabled(false);
				}
            	
            	if (rAmount.length() < 1) {
            		errorFlag = 1;
            		til_rentAmount.setErrorEnabled(true);
                    SpannableStringBuilder ssb = new SpannableStringBuilder("Error message");
                    BitmapDrawable bmpDrawableText = getBitmapDrawable(activity, "Error message");
                    ssb.setSpan(new ImageSpan(bmpDrawableText), 0, 13, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    til_rentAmount.setError(ssb);
				}else {
					til_rentAmount.setError(null);
					til_rentAmount.setErrorEnabled(false);
				}
            	
            	
            	if (errorFlag == 0) {
            		ViewPager vp =  (ViewPager) activity.findViewById(R.id.pager);
            		vp.setCurrentItem(1);
            	}
				break;
			case R.id.button_back:
				activity.finish();
				break;
			default:
				break;
			}
		}
	};
}