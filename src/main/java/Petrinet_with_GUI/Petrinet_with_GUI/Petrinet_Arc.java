package Petrinet_with_GUI.Petrinet_with_GUI;

//import petrinet.Arc.Direction;

public class Petrinet_Arc extends PetrinetObject {

	Petrinet_Place place;
	Petrinet_Transition transition;
	Direction direction;
	int weight = 1;

	enum Direction {

		/**
		 * The two directions, which may have such an edge
		 */

		PLACE_TO_TRANSITION {
			/*
			 * fire arc p-->t: 1.Fire condition: place.tokens>=1 2.If can fire,
			 * remove <weight> tokens from place
			 */
			@Override
			public boolean canFire(Petrinet_Place p, int weight) {
				return p.hasAtLeastTokens(weight);// weight=1
			}

			@Override
			public void fire(Petrinet_Place p, int weight) {
				p.removeTokens(weight);
			}
		},

		TRANSITION_TO_PLACE {
			/*
			 * fire arc t-->p: 
			 * 1.Fire condition: 
			 * 2.If can fire, add <weight> to place
			 */
			@Override
			public boolean canFire(Petrinet_Place p, int weight) {
//				return !p.maxTokensReached(weight);// weight == 1
				return true;
			}

			@Override
			public void fire(Petrinet_Place p, int weight) {
				p.addTokens(weight);
			}

		};

		public abstract boolean canFire(Petrinet_Place p, int weight);

		public abstract void fire(Petrinet_Place p, int weight);
	}

	protected Petrinet_Arc(String name, Direction d, Petrinet_Place p, Petrinet_Transition t) {
		super(name);
		this.direction = d;
		this.place = p;
		this.transition = t;
	}
	
	protected Petrinet_Arc(String name, Petrinet_Place p, Petrinet_Transition t) {
		this(name, Direction.PLACE_TO_TRANSITION, p, t);
		t.addIncoming(this);
	}

	protected Petrinet_Arc(String name, Petrinet_Transition t, Petrinet_Place p) {
		this(name, Direction.TRANSITION_TO_PLACE, p, t);
		t.addOutgoing(this);
	}

	public boolean canFire() {
		return direction.canFire(place, weight);
	}

	public void fire() {
		this.direction.fire(place, this.weight);
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getWeight() {
		return weight;
	}
}
