package androidExamples;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.GeoPoint;
import android.graphics.drawable.Drawable;
import com.google.android.maps.OverlayItem;
//ID = 26362

public class Android03 extends ItemizedOverlay<OverlayItem>{

	public Android03(Drawable defaultMarker) 
	{
		super(defaultMarker);
		populate();
	}


	@Override
	protected OverlayItem createItem(int index) {
		Double lat = (index+37.422006)*1E6;
		Double lng = -122.084095*1E6;
		GeoPoint point = new GeoPoint(lat.intValue(), lng.intValue());

		OverlayItem oi = new OverlayItem(point, "Marker", "Marker Text");
		return oi;
	}

	@Override
	public int size() {
		return 5;
	} }