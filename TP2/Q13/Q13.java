import java.time.*;
import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;

class Pokemon{
	
	private int id;
	private int generation;
	private String name;
	private String description;
	private ArrayList<String> types;
	private ArrayList<String> abilities;
	private double weight;
	private double height;
	private int captureRate;
	private boolean legendary;
	private Date captureDate;

	Pokemon(int id, int generation, String name, String description, ArrayList<String>  types, ArrayList<String>  abilities, double weight, double height, int captureRate, boolean legendary, Date captureDate){
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
		this.weight = 0.0;
		this.height = 0.0;
		this.captureRate = 0;
		this.legendary = false;
		this.captureDate = new Date();
	}


	public Pokemon clon(){
		return new Pokemon(id, generation, name, description, types, abilities, weight, height, captureRate, legendary, captureDate);
	}

	public static Pokemon make(String s) throws Exception {
		Pokemon result = new Pokemon();
		result.read(s);
		return result;
	}

	public static Pokemon[] readFile(String path) throws Exception {
		Pokemon[] result = new Pokemon[801];
		File fil = new File(path);
		Scanner csv = new Scanner(fil);
		String line = csv.nextLine();
		int i = 0;
		while(csv.hasNext()){
			line = csv.nextLine();
			result[i] = make(line);
			i++;
		}
		csv.close();
		return result;
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
	public ArrayList<String> getTypes(){
		return types;
	}
	public ArrayList<String> getAbilities(){
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
	public void setTypes (ArrayList<String> types){
		this.types = types;
	}
	public void setAbilities (ArrayList<String> abilities){
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
		System.out.print("[#" + id + " -> " + name + ": " + description + " - [");

		int reps = types.size();
		for(int i=0; i<reps-1; i++){
			System.out.print("'" + types.get(i) + "', ");
		}
		System.out.print("'" + types.get(reps-1) + "'] - [");

		reps = abilities.size();
		for(int i=0; i<reps-1; i++){
			//System.out.print("'" + abilities.get(i) + "', ");
			System.out.print(abilities.get(i) + ", ");
		}
		System.out.print(abilities.get(reps-1) + "] - ");

		System.out.print(weight + "kg - " + height + "m - " + captureRate + "% - ");

		if(isLegendary()) System.out.print("true ");
		else System.out.print("false ");

		System.out.print("- " + generation + " gen] - ");

		SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println(formater.format(captureDate));
	}

	public void read(String s) throws Exception {
		String format = "";
		boolean control = true;
		for(int i=0; i<s.length(); i++){
			char x = s.charAt(i);
			if(x == '"') control = !control;
			else if(x == ',' && control) format += ';';
			else if(x!='[' && x!=']') format += x;
		}
		String[] aux = format.split(";");
		//System.out.println(format);

		setId(Integer.parseInt(aux[0]));
		setGeneration(Integer.parseInt(aux[1]));
		setName(aux[2]);
		setDescription(aux[3]);

		String type1 = aux[4];
		String type2 = aux[5];
		ArrayList<String> tipos = new ArrayList<String>();
		tipos.add(type1);
		if(!type2.equals("")) tipos.add(type2);
		setTypes(tipos);
	

		boolean hasComma = false;
		ArrayList<String> abis = new ArrayList<String>();
		for(int i=0; i<aux[6].length() && !hasComma; i++)
			if(aux[6].charAt(i)==',') hasComma = true;
		if(!hasComma)
			abis.add(aux[6]);
		else{
			String[] abs = aux[6].split(", ");
			for(int i=0; i<abs.length; i++) abis.add(abs[i]);
		}
		setAbilities(abis);

		if(!aux[7].equals("")) setWeight(Double.parseDouble(aux[7]));
		if(!aux[8].equals("")) setHeight(Double.parseDouble(aux[8]));
		if(!aux[9].equals("")) setCaptureRate(Integer.parseInt(aux[9]));
		
		if(aux[10].equals("1")) setLegendary(true);

		SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
		setCaptureDate(formater.parse(aux[11]));
	}

}


//_____________________________________________________________________________________________________________________________________________________
//=====================================================================================================================================================
//_____________________________________________________________________________________________________________________________________________________


class TP{

	private Instant start;
	private Instant end;
	private int comparisons;
	private int movements;

	TP(){
		this.start = Instant.now();
		this.comparisons = 0;
		this.movements = 0;
	}

	public void comp(){
		this.comparisons++;
	}

	public void comp(int x){
		this.comparisons += x;
	}

	public void move(){
		this.movements++;
	}

	public void move(int x){
		this.movements += x;
	}

	void end(){
		this.end = Instant.now();
	}

	double diff(){
		return Duration.between(this.start, this.end).getNano() / 1000000000.0;
	}

	void print(String fileName) throws Exception{
		PrintWriter write = new PrintWriter(new FileWriter(fileName));
		write.printf("Matrícula: 855947\t");
		write.printf("Tempo de execução: " + diff() + "\t");
		write.printf("Comparações: " + comparisons + "\t");
		write.printf("Movimentações: " + movements);
		write.close();
	}

	void printSearch(String fileName) throws Exception{
		PrintWriter write = new PrintWriter(new FileWriter(fileName));
		write.printf("Matrícula: 855947\t");
		write.printf("Tempo de execução: " + diff() + "\t");
		write.printf("Comparações: " + comparisons);
		write.close();
	}
	
}

//_____________________________________________________________________________________________________________________________________________________
//=====================================================================================================================================================
//_____________________________________________________________________________________________________________________________________________________


public class Q13{

	static void intercalar(Pokemon[] arr, int esq, int meio, int dir, TP tp){
		int nEsq = (meio+1)-esq;
		int nDir = dir-meio;
		Pokemon[] arrayEsq = new Pokemon[nEsq+1];
		Pokemon[] arrayDir = new Pokemon[nDir+1];

		// Criar sentinela
		Pokemon sent = new Pokemon();
		ArrayList<String> t = new ArrayList<String>();
		t.add("\uFFFF");
		sent.setTypes(t);

		arrayEsq[nEsq] = arrayDir[nDir] = sent;
		tp.move(2);

		int iEsq, iDir, i;

		for(iEsq=0; iEsq<nEsq; iEsq++){
			arrayEsq[iEsq] = arr[esq+iEsq];
			tp.move();
		}

		for(iDir=0; iDir<nDir; iDir++){
			arrayDir[iDir] = arr[meio+1+iDir];
			tp.move();
		}
		
		for(iEsq=iDir=0, i=esq; i<=dir; i++){
			arr[i] = 0>=arrayEsq[iEsq].getTypes().get(0).compareTo(arrayDir[iDir].getTypes().get(0)) ? arrayEsq[iEsq++] : arrayDir[iDir++];
			tp.comp();
			tp.move();
		}
	}

	static void merge(Pokemon[] arr, int esq, int dir, TP tp){
		if(esq<dir){
			int meio = (esq+dir) / 2;
			merge(arr, esq, meio, tp);
			merge(arr, meio+1, dir, tp);
			intercalar(arr, esq, meio, dir, tp);
		}
	}

	static void swapPoke(Pokemon[] arr, int a, int b){
		Pokemon tmp = arr[a];
		arr[a] = arr[b];
		arr[b] = tmp;
	}

	static void insertName(Pokemon[] arr, TP tp){
		int n = arr.length;
		for(int i=1; i<n; i++){
			Pokemon tmp = arr[i];
			int j = i-1;
			while(j>=0 && 0==arr[j].getTypes().get(0).compareTo(tmp.getTypes().get(0)) && 0<arr[j].getName().compareTo(tmp.getName())){
				tp.comp(2);
				arr[j+1] = arr[j];
				tp.move();
				j--;
			}
			arr[j+1] = tmp;
			tp.move();
		}
	}

	static void sort(Pokemon[] pokes, TP tp){
		merge(pokes, 0, pokes.length-1, tp);
		insertName(pokes, tp);
	}
		

	public static void main(String[] args){
		try{

			Pokemon[] pokes = Pokemon.readFile("/tmp/pokemon.csv");
			Scanner sc = new Scanner(System.in);

			int[] usingIds = new  int[100];
			int x = 0;
			String input = sc.nextLine();
			while(!input.equals("FIM")){
				usingIds[x] = Integer.parseInt(input);
				x++;
				input = sc.nextLine();
			}

			Pokemon[] using = new Pokemon[x];
			for(int i=0; i<x; i++){
				using[i] = pokes[usingIds[i]-1];
			}
			
			TP tp = new TP();
			sort(using, tp);
			tp.end();
			for(int i=0; i<using.length; using[i++].print());
			tp.print("855947_mergesort.txt");

		} catch(FileNotFoundException e){
			System.out.println(e);
		} catch(Exception e){
			e.printStackTrace();
		}
	}
}
