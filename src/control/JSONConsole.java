package control;

import java.lang.reflect.Field;
import interfaces.TextField;
import processing.data.JSONArray;
import processing.data.JSONObject;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PFont;

// allows class fields to be modified using a text buffer + JSON file as intermediary
// currently, only deals with int, float, boolean and String -> what to do about float[]? or Object??
// manual: load + send PApplet, saveFieldsForClasses(editables), (optional) addMetadata, send keys to input, (poll) syncFieldsToJSON

public class JSONConsole extends TextField {
	JSONArray json;
	PApplet papp;
	String filename;
	PFont font;

	public JSONConsole(PApplet p) {
		super();
		json = new JSONArray();
		papp = p;
		filename = "src/data/console.json";
		font = papp.createFont("Courier New", 20);
		papp.textFont(font);
	}
	
	public JSONArray getJSON() {
		return json;
	}
	
	public void input(char key, int keyCode) {
		if(keyCode == PConstants.ENTER) {
			parse();
			save();
		}
		else if(keyCode == PConstants.BACKSPACE) {
			removeChar();
		}
		else if(keyCode == PConstants.SHIFT) {
			//ignore
		}
		else if(keyCode == PConstants.ALT) {
			clear();
		}
		else {
			addChar(key);
		}
	}
	
	public void parse() {
		//break apart String buffer, but don't clear
		String[] s = get().split(" ");
		if(s.length != 3) return;
		
		for(int i = 0; i < json.size(); i++) {
			if(json.getJSONObject(i).getString("nickname").equals(s[0])) {
				if(s[2].equals("true") || s[2].equals("false")) {
					json.getJSONObject(i).setBoolean(s[1], Boolean.parseBoolean(s[2]));
					return;
				}
				else if(s[2].contains(".")) {
					json.getJSONObject(i).setFloat(s[1], Float.parseFloat(s[2]));
					return;
				}
				try {
					int in = Integer.parseInt(s[2]);
					json.getJSONObject(i).setInt(s[1], in);
				} catch (Exception e) {
					System.out.println("not an int, float or bool...");
					try {
						json.getJSONObject(i).setString(s[1], s[2]);
					} catch (Exception ee) {
						System.out.println("whaa???????????????");
					}
				}
				
			}
		}
	}
	
	public void addMetadata(String key, String[] s) {
		if(s.length != json.size()) return;
		for(int i = 0; i < json.size(); i++) json.getJSONObject(i).setString(key, s[i]);
		save();
	}
	
	public void syncFieldsToJSON(Object[] obj) {
		JSONObject j;
		for(Object o : obj) {
			for(int i = 0; i < json.size(); i++) {
				j = json.getJSONObject(i);
				if(o.getClass().getSimpleName().equals(j.getString("class"))) {
					Field[] fields = o.getClass().getDeclaredFields();
					for(Field f : fields) {
						String n = f.getName();
						String t = f.getType().getName();
						f.setAccessible(true);
						try {
						switch(t) {
						case "int":
							if(f.getInt(o) != j.getInt(n)) f.setInt(o, j.getInt(n));
							break;
						case "float":
							if(f.getFloat(o) != j.getFloat(n)) f.setFloat(o, j.getFloat(n));
							break;
						case "boolean":
							if(f.getBoolean(o) != j.getBoolean(n)) f.setBoolean(o, j.getBoolean(n));
							break;
						case "java.lang.String":
							//if(f.get(o).equals(obj)) ; //????
							break;
						}
						} catch (IllegalAccessException e) {
							System.out.println("can't do that m8");
						}
						f.setAccessible(false);
					}
				}
			}
			
		}
	}
	
	public void saveFieldsForClasses(Object[] obj) {
		for(int i = 0; i < obj.length; i++) json.append(getFieldsForClass(obj[i]));
		save();
	}
	
	public static JSONObject getFieldsForClass(Object obj) {
		JSONObject j = new JSONObject();
		j.setString("class", obj.getClass().getSimpleName());
		try {
			Field[] fields = Class.forName(obj.getClass().getName()).getDeclaredFields();
			for(Field f : fields) {
				String n = f.getName();
				String t = f.getType().getName();
				//System.out.println(s);
				f.setAccessible(true);
				Object val = f.get(obj);
				//System.out.println(val);
				f.setAccessible(false);
				switch(t) {
				case "int":
					j.setInt(n, (Integer)(val));
					break;
				case "float":
					j.setFloat(n, (Float)(val));
					break;
				case "boolean":
					j.setBoolean(n, (Boolean)(val));
					break;
				case "java.lang.String":
					j.setString(n, (String)val);
					break;
				}
			}
		} catch (SecurityException e) {
			System.out.println("not authorised m8");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("no such class m9");
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			System.out.println("can't do that m8");
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			System.out.println("wrong arguments m10");
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return j;
	}
	
	public void save() {
		papp.saveJSONArray(json, filename);
	}
	
	public void filename(String filename) {
		this.filename = filename;
	}
	
	public void render(int x, int y) {
		papp.noStroke();
		papp.fill(0, 70);
		papp.rect(15, 10, get().length() * 15, 30);
		papp.fill(255);
		papp.text(get(), x, y);
	}
}
