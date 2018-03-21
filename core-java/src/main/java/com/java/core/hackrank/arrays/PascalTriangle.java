public class PascalTriangle {
	
	public static void main(String[] args) {
          buildPascalTriangle();	
        }

	private static void buildPascalTriangle() {
		System.out.println("Pascal's Triangle");
		for (int row = 0; row < 10; row++) {
			for (int col = row; col >= 0; col--) {
				System.out.print(pascal(col, row) + " ");
			}
			System.out.println();
		}
	}

	private static Integer pascal(Integer c, Integer r) {
		if (c == 0 || c == r)
			return 1;
		else {
			return pascal(c - 1, r - 1) + pascal(c, r - 1);
		}
	}


}
