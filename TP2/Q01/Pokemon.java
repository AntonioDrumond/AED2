import java.time.*;
import java.util.*;
import java.io.*;

class Pokemon{
	
	private int id;
	private int generation;
	private String name;
	private String description;
	private ArrayList types;
	private ArrayList abilities;
	private double weight;
	private double height;
	private int captureRate;
	private boolean legendary;
	private Date captureDate;

	Pokemon(int id, int generation, String name, String description, ArrayList types, ArrayList abilities, double weight, double height, int captureRate, boolean legendary, Date captureDate){
		this.id = id;
		this.generation = generation;
		this.name = name;
		this.description = description;
		this.types = types;
		this.abilities = abilities;
		this.weight = weight;
		this.height = height;
		this.captureRate = captureRate;
		this.legendary = legendary;
		this.captureDate = captureDate;
	}

	Pokemon(){
		this.id = 0;
		this.generation = 0;
		this.name = null;
		this.description = null;
		this.types = null;
		this.abilities = null;
		this.weight = 0;
		this.height = 0;
		this.captureRate = 0;
		this.legendary = false;
		this.captureDate = null;
	}

	//_____Getters_____
	public int getId(){
		return id;
	}
	public int getGeneration(){
		return generation;
	}
	public String getName(){
		return name;
	}
	public String getDescription(){
		return description;
	}
	public ArrayList getTypes(){
		return types;
	}
	public ArrayList getAbilities(){
		return abilities;
	}
	public double getWeight(){
		return weight;
	}
	public double getHeight(){
		return height;
	}
	public int getCaptureRate(){
		return captureRate;
	}
	public boolean isLegendary(){
		return legendary;
	}
	public Date getCaptureDate(){
		return captureDate;
	}

	//_____Setters_____
	public void setId (int id){
		this.id = id;
	}
	public void setGeneration (int generation){
		this.generation = generation;
	}
	public void setName (String name){
		this.name = name;
	}
	public void setDescription (String description){
		this.description = description;
	}
	public void setTypes (ArrayList types){
		this.types = types;
	}
	public void setAbilities (ArrayList abilities){
		this.abilities = abilities;
	}
	public void setWeight (double weight){
		this.weight = weight;
	}
	public void setHeight (double height){
		this.height = height;
	}
	public void setCaptureRate (int captureRate){
		this.captureRate = captureRate;
	}
	public void setLegendary (boolean legendary){
		this.legendary = legendary;
	}
	public void setCaptureDate (Date captureDate){
		this.captureDate = captureDate;
	}

	//____Print and Read____
	public void print(){
		System.out.println("[#id -> name: description - [types] - [abilities] - weight - height - captureRate - isLegendary - generation] - captureDate");
		System.out.print("[#" + id + " -> " + name + ": " + description + " - [");

		int reps = types.size();
		for(int i=0; i<reps-1; i++){
			System.out.print("'" + types.get(i) + "', ");
		}
		System.out.print("'" + types.get(reps) + "'] - [");

		reps = abilities.size();
		for(int i=0; i<reps-1; i++){
			System.out.print("'" + abilities.get(i) + "', ");
		}
		System.out.print("'" + abilities.get(reps) + "'] - ");

		System.out.print(weight + "kg - " + height + "m - " + captureRate + "% - ");
		if(isLegendary()) 

}
