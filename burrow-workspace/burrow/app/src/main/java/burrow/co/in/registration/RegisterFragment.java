package burrow.co.in.registration;

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
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import burrow.co.in.R;


public class RegisterFragment extends Fragment {
	
    Activity activity;
    LinearLayout layoutTop, layoutBottom;
    
    EditText date, firstName, lastName, email, phone;
    TextInputLayout til_firstName, til_lastName, til_email, til_phone;
    
    EditText address, city, state, country;
    TextInputLayout til_address, til_city, til_state, til_country;
    TextView addressCount;
    
    private DatePickerDialog toDatePickerDialog;
    private SimpleDateFormat dateFormatter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_reg, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activity = getActivity();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        layoutTop = (LinearLayout) activity.findViewById(R.id.layout_top);
        layoutBottom = (LinearLayout) activity.findViewById(R.id.layout_bottom);
        
        firstName = (EditText) activity.findViewById(R.id.editText_firstName);
        lastName = (EditText) activity.findViewById(R.id.editText_lastName);
        email = (EditText) activity.findViewById(R.id.editText_email);
        phone = (EditText) activity.findViewById(R.id.editText_phone);
        
        til_firstName = (TextInputLayout)activity.findViewById(R.id.textInputLayout_firstName);
        til_lastName = (TextInputLayout)activity.findViewById(R.id.textInputLayout_lastName);
        til_email = (TextInputLayout)activity.findViewById(R.id.textInputLayout_email);
        til_phone = (TextInputLayout)activity.findViewById(R.id.textInputLayout_phone);
        date = (EditText) activity.findViewById(R.id.date_of_birth);
        
        Button next = (Button) activity.findViewById(R.id.next);
        Button btcontinue = (Button) activity.findViewById(R.id.btContinue);
        
        address = (EditText) activity.findViewById(R.id.editText_address);
        addressCount = (TextView) activity.findViewById(R.id.textView_address_count);
        city = (EditText) activity.findViewById(R.id.editText_city);
        state = (EditText) activity.findViewById(R.id.editText_state);
        country = (EditText) activity.findViewById(R.id.editText_country);
        
        til_address = (TextInputLayout) activity.findViewById(R.id.textInputLayout_address);
        til_city = (TextInputLayout) activity.findViewById(R.id.textInputLayout_city);
        til_state = (TextInputLayout) activity.findViewById(R.id.textInputLayout_state);
        til_country = (TextInputLayout) activity.findViewById(R.id.textInputLayout_country);
                
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        setDateTimeField();
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	toDatePickerDialog.show();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	
            	int errorFlag = 0;
            	
            	String fName = firstName.getText().toString().trim();
            	String lName = lastName.getText().toString().trim();
            	String eEmail = email.getText().toString().trim();
            	String pPhone = phone.getText().toString().trim();
            	
           	
            	if (fName.length() < 1) {
            		errorFlag = 1;
                    til_firstName.setErrorEnabled(true);
                    SpannableStringBuilder ssb = new SpannableStringBuilder("Error message");
                    BitmapDrawable bmpDrawableText = getBitmapDrawable(activity, "Error message");
                    ssb.setSpan(new ImageSpan(bmpDrawableText), 0, 13, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    til_firstName.setError(ssb);
				}else {
					til_firstName.setError(null);
					til_firstName.setErrorEnabled(false);
				}
            	
            	if (lName.length() < 1) {
            		errorFlag = 1;
                    til_lastName.setErrorEnabled(true);
                    SpannableStringBuilder ssb = new SpannableStringBuilder("Error message");
                    BitmapDrawable bmpDrawableText = getBitmapDrawable(activity, "Error message");
                    ssb.setSpan(new ImageSpan(bmpDrawableText), 0, 13, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    til_lastName.setError(ssb);
				}else{
					til_lastName.setError(null);
					til_lastName.setErrorEnabled(false);
				}
            	
            	if (eEmail.length() < 1) {
            		errorFlag = 1;
                    til_email.setErrorEnabled(true);
                    SpannableStringBuilder ssb = new SpannableStringBuilder("Error message");
                    BitmapDrawable bmpDrawableText = getBitmapDrawable(activity, "Error message");
                    ssb.setSpan(new ImageSpan(bmpDrawableText), 0, 13, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    til_email.setError(ssb);
				}else {
					til_email.setError(null);
					til_email.setErrorEnabled(false);
				}
            	
            	if (pPhone.length() < 1) {
            		errorFlag = 1;
                    til_phone.setErrorEnabled(true);
                    SpannableStringBuilder ssb = new SpannableStringBuilder("Error message");
                    BitmapDrawable bmpDrawableText = getBitmapDrawable(activity, "Error message");
                    ssb.setSpan(new ImageSpan(bmpDrawableText), 0, 13, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    til_phone.setError(ssb);
				}else {
					til_phone.setError(null);
					til_phone.setErrorEnabled(false);
				}
            	
            	
            	if (errorFlag == 0) {
                	AnimationSet as = new AnimationSet(true);
                	as.setFillEnabled(true);
                	as.setInterpolator(new LinearInterpolator());
                	
                	Animation animSR = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.slide_out_right);
                	Animation animPD = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.push_down_out);

                	as.addAnimation(animPD);
                	as.addAnimation(animSR);
                	
                	layoutTop.setAnimation(as);
                	layoutTop.getAnimation().start();
                	Animation animZoom = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.shake);
                	layoutBottom.setAnimation(animZoom);
                	layoutBottom.getAnimation().start();
                	
                	ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) layoutBottom.getLayoutParams();
                	layoutParams.setMargins(16, 48, 16, 16);
                	layoutBottom.setLayoutParams(layoutParams);
                	layoutTop.setVisibility(View.GONE);
				}
            }
        });
        layoutTop.bringToFront();
        layoutTop.invalidate();
        btcontinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = ((AppCompatActivity) activity).getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_container, new OTPFragment(), "otp_fragment");
                ft.commit();
            }
        });
       
        setAddressTextWatcher();
    }
    
    private void setAddressTextWatcher() {
    	
		TextWatcher address_TextWatcher = new TextWatcher() {

	        @Override
	        public void onTextChanged(CharSequence s, int start, int before, int count) {
	            // TODO Auto-generated method stub
	        }

	        @Override
	        public void beforeTextChanged(CharSequence s, int start, int count,
	                int after) {
	            // TODO Auto-generated method stub
	        }

	        @Override
	        public void afterTextChanged(Editable s) {
	            // TODO Auto-generated method stub	        	
	        	addressCount.setText(String.valueOf(s.length()+"/120"));
	        }
	    };
	    address.addTextChangedListener(address_TextWatcher);
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
        
        toDatePickerDialog = new DatePickerDialog(activity, new OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                date.setText(dateFormatter.format(newDate.getTime()));
            }
 
        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }
}