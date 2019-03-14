package se_hw2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import PipeAndFilter.Filter;
import PipeAndFilter.Pipe;

public class Alphabetizer extends Filter {
	
	public Alphabetizer(Pipe input_, Pipe output_) {
		super(input_, output_);
		
	}
	
	protected void transform() {
		LineStorage lines;
		try {
			lines = (LineStorage) input_.get();
			List<String> list = new ArrayList<String>();
			for (int i = 0; i < lines.getSize(); i++) {
				list.add(lines.getLineAsString(i));
			}
			list = sortByAlphabeticalOrder(list);
			LineStorage newLines = new LineStorage(list);
			output_.put(newLines);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<String> sortByAlphabeticalOrder(List<String> arrayList) {
		Collections.sort(arrayList, String.CASE_INSENSITIVE_ORDER);
		return arrayList;
	}
	
}
