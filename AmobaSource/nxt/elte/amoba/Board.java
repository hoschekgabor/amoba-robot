package nxt.elte.amoba;

public class Board {
	private ListOfSteps listOfSteps = new ListOfSteps();
	
	public void setStep(Step step) {
		listOfSteps.addStep(step);
	}

	public ListOfSteps getSteps() {
		return listOfSteps;
	}

	public void setListOfSteps(ListOfSteps listOfSteps) {
		this.listOfSteps = listOfSteps;
	}

	public int compare(ListOfSteps userSteps) {
		int difference = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (!listOfSteps.getStep(i, j).equals(userSteps.getStep(i, j))) {
					difference++;
				}
			}
		}
		return difference;
	}

}
