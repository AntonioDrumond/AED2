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


class Node2{
	public String name;
	public Node2 left;
	public Node2 right;
	
	Node2(){
		name = null;
		left = right = null;
	}

	Node2(Pokemon p){
		name = p.getName();
		left = right = null;
	}

}

class Node1{
	public int capt;
	public Node2 root;
	public Node1 left;
	public Node1 right;

	Node1(int capt){
		this.capt = capt;
		left = null;
		right = null;
		this.root = null;
	}

}

class Tree{
	public Node1 root;
	Tree(){
		root = new Node1(7);
		addCapt(3);
		addCapt(11);
		addCapt(1);
		addCapt(5);
		addCapt(9);
		addCapt(13);
		addCapt(0);
		addCapt(2);
		addCapt(4);
		addCapt(6);
		addCapt(8);
		addCapt(10);
		addCapt(12);
		addCapt(14);
	}

	public void addCapt(int x) {
        if(root == null){
            root = new Node1(x);
        }
        else if(x < root.capt){
            addCapt(x, root.left, root);
        }
        else{
            addCapt(x, root.right, root);
        }
    }

    public void addCapt(int x, Node1 n, Node1 pai) {
        if(n==null){
            if(x < pai.capt){
                pai.left = new Node1(x);
            }
            else pai.right = new Node1(x);
        }
        else if(x < n.capt){
            addCapt(x, n.left, n);
        }
        else{
            addCapt(x, n.right, n);
        }
    }

	public void insert(Pokemon p){
		insert(p, this.root);
	}

	private void insert(Pokemon p, Node1 crr){
		if(crr.capt == p.getCaptureRate()%15) insert(p, crr.root);
		else if(crr.capt < p.getCaptureRate()%15) insert(p, crr.left);
		else if(crr.capt > p.getCaptureRate()%15) insert(p, crr.right);
	}

	public void insert(Pokemon p, Node2 root){
		if(root == null) root = new Node2(p);
		else if(0 > p.getName().compareTo(root.name)) insert(p, root.left, root);
		else insert(p, root.right, root);
	}

	private void insert(Pokemon p, Node2 in, Node2 dad){
		if(in == null){
			if(0 > p.getName().compareTo(dad.name)) dad.left = new Node2(p);
			else dad.right = new Node2(p);
		}
		else if(0 > p.getName().compareTo(dad.name)) insert(p, in.left, in);
		else insert(p, in.right, in);
	}

	public void search(String key, TP tp){
		StringBuilder path = new StringBuilder("=> ").append(key).append("\nraiz ");
		boolean found = searchExternal(this.root, key, path, tp);
		path.append(found ? "SIM" : "NAO");
		System.out.println(path.toString());
	}

	//node1 = external | node2 = internal 
	private boolean searchExternal(Node1 ext, String key, StringBuilder path, TP tp){
		if(searchInternal(ext.root, key, path, tp)) return true;
		else{
			boolean found = false;
			if(ext.left!=null){
				path.append("ESQ ");
				found = searchExternal(ext.left, key, path, tp);
			}
			if(ext.right!=null && !found){
				path.append("DIR ");
				found = searchExternal(ext.right, key, path, tp);
			}
			return found;
		}
	}

	private boolean searchInternal(Node2 crr, String key, StringBuilder path, TP tp){
		if(crr.name.equals(key)) return true;
		else if(0 > crr.name.compareTo(key)){
			tp.comp();
			if(crr.left == null) return false;
			else{
				path.append("esq ");
				return searchInternal(crr.left, key, path, tp);
			}
		}
		else{
			if(crr.right == null) return false;
			else{
				path.append("dir ");
				return searchInternal(crr.right, key, path, tp);
			}
		}
	}

}


//_____________________________________________________________________________________________________________________________________________________
//=====================================================================================================================================================
//_____________________________________________________________________________________________________________________________________________________


public class Q02{

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
			Tree t = new Tree();
			String input = sc.nextLine();
			while(!input.equals("FIM")){
				x = Integer.parseInt(input);
				t.insert(pokes[x-1]);
				input = sc.nextLine();
			}

			//t.printMid();

			TP tp = new TP();
			input = sc.nextLine();
			while(!input.equals("FIM")){
				t.search(input, tp);
				input = sc.nextLine();
			}
			tp.end();
			tp.printSearch("855947_arvoreArvore.txt");

		} catch(FileNotFoundException e){
			System.out.println(e);
		} catch(Exception e){
			e.printStackTrace();
		}
	}
}
