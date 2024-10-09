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


public class Q09{

	static void swapPoke(Pokemon[] arr, int a, int b){
		Pokemon tmp = arr[a];
		arr[a] = arr[b];
		arr[b] = tmp;
	}

	static void heapSort(Pokemon[] arr, TP tp){
		int n = arr.length;
		int endIndex = n-1;
		for(int max=1; max<n; max++){
			makeHeap(arr, max, tp);
		}
		while(endIndex>0){
			swapPoke(arr, 0, endIndex);
			tp.move(3);
			endIndex--;
			redoHeap(arr, endIndex, tp);
		}
	}

	static void makeHeap(Pokemon[] arr, int max, TP tp){
		for(int i=max; max>0 && arr[i].getHeight()>arr[(i-1)/2].getHeight(); i=(i-1)/2){
			swapPoke(arr, i, (i-1)/2);
			tp.move(3);
			tp.comp();
		}
	}

	static boolean hasSon(int aux, int endIndex){
		boolean ver = false;
		if(((aux*2)+1) <= endIndex) ver = true;
		return ver;
	}

	static int getBiggerSon(Pokemon[] arr, int aux, int endIndex, TP tp){
		int son = 0;
		if(((2*aux)+1) == endIndex)
			son = endIndex;
		else if(arr[(2*aux)+1].getHeight() > arr[(2*aux)+2].getHeight()){
			son = ((2*aux)+1);
			tp.comp();
		}
		else son = ((2*aux)+2);
		return son;
	}

	static void redoHeap(Pokemon[] arr, int endIndex, TP tp){
		int aux = 0;
		boolean ctrl = true;
		while(hasSon(aux, endIndex) && ctrl){
			int son = getBiggerSon(arr, aux, endIndex, tp);
			if(arr[aux].getHeight() < arr[son].getHeight()){
				swapPoke(arr, aux, son);
				tp.comp();
				tp.move(3);
				aux = son;
			}
			else ctrl = false;
		}
	}

	static boolean shouldSwap(String a, String b, TP tp){
		boolean result = false;
		int i = -1;
		do{
			i++;
			if(a.charAt(i) > b.charAt(i)) result = true;
		}while(a.charAt(i)==b.charAt(i));
		tp.comp();
		return result;
	}

	static void insertion(Pokemon[] arr, TP tp){
		int n = arr.length;
		for(int i=1; i<n; i++){
			Pokemon tmp = arr[i];
			int j = i-1;
			while(j>=0 && arr[j].getHeight()==tmp.getHeight() && shouldSwap(arr[j].getName(), tmp.getName(), tp)){
				tp.comp();
				arr[j+1] = arr[j];
				j--;
				tp.move();
			}
			arr[j+1] = tmp;
			tp.move();
		}
	}

	static void sort(Pokemon[] pokes, TP tp){
		heapSort(pokes, tp);
		insertion(pokes, tp);
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
			tp.print("855947_heapsort.txt");

		} catch(FileNotFoundException e){
			System.out.println(e);
		} catch(Exception e){
			System.out.println(e);
		}
	}
}
