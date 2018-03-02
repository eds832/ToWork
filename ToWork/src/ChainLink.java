enum Side {
	NONE, LEFT, RIGHT
}

public class ChainLink {
	private ChainLink left, right;

	public void append(ChainLink rightPart) {
		if (this.right != null)
			throw new IllegalStateException("Link is already connected.");

		this.right = rightPart;
		rightPart.left = this;
	}

	public Side longerSide() {
		Side side = Side.NONE;
		int leftSideLength = 0;
		int rightSideLength = 0;
		boolean isLoop = false;
		if (this != null) {
			ChainLink leftLink = this.left;
			ChainLink rightLink = this.right;
			while (leftLink != null) {
				if (leftLink == this) {
					isLoop = true;
					break;
				}
				leftLink = leftLink.left;
				leftSideLength++;
			}
			if (!isLoop) {
				while (rightLink != null) {
					rightLink = rightLink.right;
					rightSideLength++;
				}
				if (leftSideLength > rightSideLength) {
					side = Side.LEFT;
				}
				if (rightSideLength > leftSideLength) {
					side = Side.RIGHT;
				}
			}
		}
		return side;
		// int leftLength = 0;
		// ChainLink leftPart = this.left;
		// int rightLength = 0;
		// ChainLink rightPart = this.right;
		// while (leftPart != null){
		// if(leftPart == rightPart) {
		// return Side.NONE;
		// }
		// leftLength++;
		// leftPart = leftPart.left;
		// }
		//
		// while (rightPart != null){
		// rightLength++;
		// rightPart = rightPart.right;
		// }
		//
		// if (rightLength > leftLength){
		// return Side.RIGHT;
		// }
		// if (leftLength > rightLength){
		// return Side.LEFT;
		// }
		// return Side.NONE;
	}

	public static void main(String[] args) {
		ChainLink left = new ChainLink();
		ChainLink middle = new ChainLink();
		ChainLink right = new ChainLink();
		left.append(middle);
		middle.append(right);
		System.out.println(left.longerSide());
	}
}
