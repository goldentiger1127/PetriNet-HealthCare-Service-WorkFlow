package Petrinet_with_GUI.Petrinet_with_GUI;
import Petrinet_with_GUI.Petrinet_with_GUI.PetrinetObject;

public class Petrinet_Place extends PetrinetObject {

	// it's a magic number....
	public static final int UNLIMITED = -1;

	private int  tokens = 0;
	private int maxTokens = UNLIMITED;

	protected Petrinet_Place(String name) {
		super(name);
	}

	protected Petrinet_Place(String name, int initial) {
		this(name);
		this.tokens = initial;
	}

	/**
	 * Does the site have at least as many tokens?
	 */
	public boolean hasAtLeastTokens(int threshold) {
		if (tokens >= 1) {
			return true;
		} else {
			return false;
		}
		// return (tokens >= threshold);
	}

	public boolean maxTokensReached(int newTokens) {
		if (hasUnlimitedMaxTokens()) {
			return false;
		}
		return (tokens + newTokens > maxTokens);
	}

	private boolean hasUnlimitedMaxTokens() {
		return maxTokens == UNLIMITED;
	}

	public int getTokens() {
		return tokens;
	}

	public void setTokens(int tokens) {
		this.tokens = tokens;
	}

	public void setMaxTokens(int max) {
		this.maxTokens = max;
	}

	public void addTokens(int weight) {
		this.tokens += weight;
	}

	public void removeTokens(int weight) {
		this.tokens -= weight;
	}

	@Override
	public String toString() {
		return super.toString() + " Tokens=" + this.tokens;
		// return super.toString() + " Tokens=" + this.tokens + " max="
		// + (hasUnlimitedMaxTokens() ? "unlimited" : this.maxTokens);
	}
}

