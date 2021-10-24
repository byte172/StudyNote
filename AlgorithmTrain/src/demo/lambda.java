package demo;

public class lambda {

	static class ILike2 implements Like {

		@Override
		public int lambda(int a) {
			System.out.println("I like lambda2.."+a);
			return a;
		}
		
	}

	public static void main(String[] args) {
		Like like = new ILike();


		Like like2 = new ILike2();
		like2.lambda(12);
		
		like = new Like() {
			
			@Override
			public int lambda(int a) {
				System.out.println("I like lambda3.."+a);
				return a;
			}
		};
		like.lambda(23);
		//lambda
		like=(a)-> {
			System.out.println("I like lambda4.." + a);
			return a;
		};
		like.lambda(43);
	}
		
}

interface Like{
	public int lambda(int a);
}

class ILike implements Like {

	@Override
	public int lambda(int a) {
		System.out.println("I like lambda..");
		return a;
	}

}
