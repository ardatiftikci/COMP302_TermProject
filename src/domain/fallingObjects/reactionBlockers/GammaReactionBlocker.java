package domain.fallingObjects.reactionBlockers;

public class GammaReactionBlocker extends ReactionBlocker{

	public GammaReactionBlocker(int unitLength) {
		super("gamma", unitLength);
		imageAddress = "src/images/gammaRB.png";
	}
	
}
