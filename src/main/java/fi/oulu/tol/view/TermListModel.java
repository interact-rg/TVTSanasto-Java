package fi.oulu.tol.view;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

import org.json.JSONException;

import fi.oulu.tol.model.Term;
import fi.oulu.tol.model.TermProvider;
import fi.oulu.tol.model.TermProviderObserver;

public class TermListModel implements ListModel<Term>, TermProviderObserver {

	private TermProvider provider;
	private List<Term> terms;
	
	public TermListModel(TermProvider provider) throws JSONException, SQLException, IOException {
		this.provider = provider;
		provider.addObserver(this);
		this.terms = provider.getSelectedCategoryTerms();
	}

	@Override
	public int getSize() {
		if (null != terms) {
			return terms.size();
		}
		return 0;
	}

	@Override
	public Term getElementAt(int index) {
		if (null != terms && index >= 0 && index < terms.size()) {
			return terms.get(index);
		}
		return null;
	}

	@Override
	public void addListDataListener(ListDataListener l) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeListDataListener(ListDataListener l) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changeEvent(Topic topic) {
		if (topic == Topic.SELECTED_CATEGORY_CHANGED) {
			try {
				this.terms = provider.getSelectedCategoryTerms();
			} catch (JSONException | SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
}
