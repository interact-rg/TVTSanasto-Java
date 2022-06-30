package fi.oulu.tol.model;

import org.json.JSONException;
import org.json.JSONObject;

import fi.oulu.tol.Settings;

public class Term {
	String id;
	String english;
	String finnish;
	String englishLink;
	String finnishLink;
	String definition;

	public static Term from(JSONObject jsonObject) throws JSONException {
		Term term = new Term();
		term.id = jsonObject.getString("id");
		term.english = jsonObject.getString("english");
		term.finnish = jsonObject.getString("finnish");
		term.englishLink = jsonObject.getString("englishLink");
		term.finnishLink = jsonObject.getString("finnishLink");
		term.definition = jsonObject.getString("definition");
		return term;
	}

	@Override
	public String toString() {
		if (Settings.language == Language.FINNISH) {
			return finnish + " (" + english + ")";
		}
		return english + " (" + finnish + ")";
	}
}
