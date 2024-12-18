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


class Lista{
	private Pokemon[] arr;
	private int n;

	Lista(int n){
		arr = new Pokemon[n+1];
		this.n = 0;
	}

	Lista(){
		this(50);
	}

	public int size(){
		return this.n;
	}

	public Pokemon get(int x){
		Pokemon ret = null;
		if(x<n && x>=0)
			ret = arr[x];
		return ret;
	}

	public void print(){
		for(int i=0; i<n; i++){
			System.out.print("[" + i + "] ");
			arr[i].print();
		}
	}

	public Pokemon pop(int x){
		Pokemon ret = null;
		if(n>0 && x>=0 && x<=n){
			ret = arr[x];
			int i = x;
			while(i<n){
				arr[i] = arr[i+1];
				i++;
			}
			n--;
		}
		return ret;
	}

	public Pokemon popP(int x){
		Pokemon tmp = this.pop(x);
		System.out.println("(R) " + tmp.getName());
		return tmp;
	}

	public Pokemon popStart(){
		return pop(0);
	}

	public Pokemon popStartP(){
		Pokemon tmp = this.pop(0);
		System.out.println("(R) " + tmp.getName());
		return tmp;
	}

	public Pokemon popEnd(){
		return pop(n-1);
	}

	public Pokemon popEndP(){
		Pokemon tmp = this.pop(n-1);
		System.out.println("(R) " + tmp.getName());
		return tmp;
	}

	public void pushEnd(Pokemon p){
		if(n<arr.length){
			arr[n] = p;
			n++;
		}
	}

	public void push(Pokemon p, int x){
		if(n<arr.length){
			int i = n;
			while(i>=x && i>0){
				arr[i] = arr[i-1];
				i--;
			}
			arr[x] = p;
			n++;
		}
	}

	public void pushStart(Pokemon p){
		push(p, 0);
	}
	
}


//_____________________________________________________________________________________________________________________________________________________
//=====================================================================================================================================================
//_____________________________________________________________________________________________________________________________________________________


public class Q01{

	static void swapPoke(Pokemon[] arr, int a, int b){
		Pokemon tmp = arr[a];
		arr[a] = arr[b];
		arr[b] = tmp;
	}

	public static void main(String[] args){
		try{

			Pokemon[] pokes = Pokemon.readFile("/tmp/pokemon.csv");
			Scanner sc = new Scanner(System.in);

			int x = 0;
			Lista l = new Lista(100);
			String input = sc.nextLine();
			while(!input.equals("FIM")){
				x = Integer.parseInt(input);
				l.pushEnd(pokes[x-1]);
				input = sc.nextLine();
			}

			//l.print();

			/*
			TP tp = new TP();
			sort(using, tp);
			tp.end();
			for(int i=0; i<using.length; using[i++].print());
			tp.print("855947_mergesort.txt");
			*/

			int reps = sc.nextInt();
			int ins = 0;
			int index = 0;
			for(int i=0; i<reps; i++){
				input = sc.next();
				//Operacoes
				if(input.equals("II")){
					ins = sc.nextInt();
					l.pushStart(pokes[ins-1]);
					//System.out.println("Inserindo " + ins);
				}
				else if(input.equals("IF")){
					ins = sc.nextInt();
					l.pushEnd(pokes[ins-1]);
					//System.out.println("Inserindo " + ins);
				}
				else if(input.equals("I*")){
					index = sc.nextInt();
					ins = sc.nextInt();
					l.push(pokes[ins-1], index);
					//System.out.println("Inserindo " + ins);
				}
				else if(input.equals("RI")){
					l.popStartP();
				}
				else if(input.equals("RF")){
					l.popEndP();
				}
				else if(input.equals("R*")){
					index = sc.nextInt();
					l.popP(index);
				}

			}

			l.print();

		} catch(FileNotFoundException e){
			System.out.println(e);
		} catch(Exception e){
			e.printStackTrace();
		}
	}
}
