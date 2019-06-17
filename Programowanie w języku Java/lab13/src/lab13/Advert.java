package lab13;

public class Advert implements AdvertMBean{
	
	private String text;
	private int duration;
	
	Advert(String txt, int dur){
		text = txt;
		duration = dur;
	}
	
	@Override
	public String getText() {
		return text;
	}
	
	@Override
	public void setText(String txt) {
		text = txt;
	}
	
	@Override
	public int getDuration() {
		return duration;
	}
	
	@Override
	public void setDuration(int dur) {
		duration = dur;
	}
}
