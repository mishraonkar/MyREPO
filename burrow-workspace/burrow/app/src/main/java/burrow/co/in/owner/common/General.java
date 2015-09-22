package burrow.co.in.owner.common;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Bitmap.Config;
import android.graphics.PorterDuff.Mode;

public class General {
	
	public static Bitmap getCircularimage(Bitmap bitmap) {
		
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        float imageRadius;
        if (bitmap.getWidth() > bitmap.getHeight()) {
        	imageRadius = bitmap.getHeight();
		}else{
			imageRadius = bitmap.getWidth();
		}
        canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2, imageRadius / 2, paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }

}
