package clone.demo;

public class Mark implements Cloneable {
	private int chinese;
	private int math;
	public Mark(int chinese,int math) {
		this.chinese=chinese;
		this.math=math;
	}
	public void setChinese(int chinese) {
		this.chinese = chinese;
	}
	public void setMath(int math) {
		this.math = math;
	}
	@Override
	public String toString() {
		return "Mark [chinese=" + chinese + ", math=" + math + "]";
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}

