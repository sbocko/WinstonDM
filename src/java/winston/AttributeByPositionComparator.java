package winston;

import java.util.Comparator;

public class AttributeByPositionComparator implements Comparator<Attribute> {

	@Override
	public int compare(Attribute a1, Attribute a2) {
		return a1.getPositionInDataFile()-a2.getPositionInDataFile();
	}

}
