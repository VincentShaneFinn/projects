package se_hw1;

import java.util.Collections;
import java.util.List;

public class Alphabetizer {
	public List<String> sortByAlphabeticalOrder(List<String> arrayList) {
		Collections.sort(arrayList, String.CASE_INSENSITIVE_ORDER);
		return arrayList;
	}
}
