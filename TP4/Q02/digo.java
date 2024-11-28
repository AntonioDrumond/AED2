import java.io.*;
import java.util.*;

class Comparacoes {
    public int total = 0;

    public void addcomp() {
        total++;
    }

    public int gettotal() {
        return total;
    }
}

class pokemon {

    private int id;
    private int generation;
    private String name;
    private String description;
    private List<String> types = new ArrayList<>();
    private List<String> abilities;
    private double weight;
    private double height;
    private int captureRate;
    private boolean isLegendary;
    private String captureDate;

    public int getid() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getcaptureRate() {
        return captureRate;
    }

    public pokemon(int id, int generation, String name, String description, String type1, String type2,
            String[] ability, double weight, double height, int captureRate, boolean isLegendary,
            String captureDate) {
        this.id = id;
        this.generation = generation;
        this.name = name;
        this.description = description;
        this.types.add(type1);
        this.types.add(type2);
        this.abilities = new ArrayList<>(Arrays.asList(ability));

        this.weight = weight;
        this.height = height;
        this.captureRate = captureRate;
        this.isLegendary = isLegendary;
        this.captureDate = captureDate;
    }

    public pokemon() {
        this.id = 0;
        this.generation = 0;
        this.name = null;
        this.description = null;
        this.weight = 0;
        this.height = 0;
        this.captureRate = 0;
        this.isLegendary = false;
        this.captureDate = "";
        // this.captureDate = new Date(0);
    }

    public static boolean isinside(String ability) {
        for (int i = 0; i < ability.length(); i++) {
            char ch = ability.charAt(i);
            if (ch == ']') {
                return true;
            }

        }

        return false;
    }

    public String toString() {
        String str = "[#" + id + " " + "->" + " " + name + ":" + " " + description + " " + "-" + " " + "['";

        str += types.get(0);
        // imprime os tipos///////////////////////////////////////
        if (!types.get(1).equals("0")) {
            str += "', '" + types.get(1) + "'] ";
        }

        else {
            str += "'] ";
        }
        /////////////////////////////////////////////////////////

        // imprime as habilidades////////////////////////////////
        str += "- ";

        for (int j = 0; j < abilities.size(); j++) {
            str += abilities.get(j);

        }
        //////////////////////////////////////////////////////////
        str += " - " + weight + "kg" + " - " + height + "m" + " - " + captureRate + "%" + " - " + isLegendary + " - "
                + generation + " gen] " + "- " + captureDate;

        return str;
    }
}

// ARVORE SECUNDARIA
// /=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=
class No_secundario {
    public String elemento;
    public No_secundario esq;
    public No_secundario dir;

    public No_secundario(String x) {
        this.elemento = x;
        this.esq = null;
        this.dir = null;
    }
}

class Arvore_secundaria {

    public No_secundario raiz;

    public Comparacoes comps1 = new Comparacoes();

    public Arvore_secundaria() {
        this.raiz = null;
    }

    public int Get_comparacoes() {
        return comps1.gettotal();
    }

    // ADICIONAR----------------------------------------------------------------------
    public void adicionar(String x) {
        this.raiz = adicionar(x, this.raiz); // sobrescrita
    }

    public No_secundario adicionar(String x, No_secundario i) { // a passagem de valores no java e por valor

        if (i == null) {
            comps1.addcomp();
            i = new No_secundario(x);
        }

        // else if (x < i.elemento) {
        else if (x.compareToIgnoreCase(i.elemento) < 0) {
            comps1.addcomp();
            i.esq = adicionar(x, i.esq);
        }

        // else if (x > i.elemento) {
        else if (x.compareToIgnoreCase(i.elemento) > 0) {
            comps1.addcomp();
            i.dir = adicionar(x, i.dir);
        }

        else {
            System.out.println("esse elemento ja foi inserido previamente na aravore");
        }

        return i;
    }

    public boolean pesquisar(String x) {

        return pesquisar(x, this.raiz);
    }

    public boolean pesquisar(String x, No_secundario i) {

        while (i != null)
        {
            if(i.elemento.equals(x))
            {
                return true;
            }
            else if (x.compareToIgnoreCase(i.elemento) > 0)
            {
                System.out.print("dir ");
                i = i.dir;
            }
            else
            {
                System.out.print("esq ");
                i = i.esq;
            }
        }

        return false;
    }

}
// =/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=

// ARVORE/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=

class No {
    public int elemento;
    public Arvore_secundaria arvore_sec;
    public No esq;
    public No dir;

    public No(int x) {
        this.arvore_sec = new Arvore_secundaria();
        this.elemento = x;
        this.esq = null;
        this.dir = null;
    }
}

class Arvore {
    public No raiz;
    public Comparacoes comps = new Comparacoes();

    public Arvore() {
        this.raiz = null;
    }

    public int Get_comparacoes() {
        return comps.gettotal();
    }

    // pegarComparacoes-----------------------------------------------------------------------
    public void pegarComparacoes() {
        pegarComparacoes(this.raiz);
    }

    public void pegarComparacoes(No i) {
        if (i != null) {
            comps.total += i.arvore_sec.Get_comparacoes();
            pegarComparacoes(i.esq);
            pegarComparacoes(i.dir);
        }
    }

    //CAMINHAR---------------------------------------------------------------------------------------

    public boolean  caminhar(String str) {
        // -------------------------------------------
        System.out.println("=> " + str);
        System.out.print("raiz ");
        boolean[] found = new boolean[1];
        found[0] = false;
        caminhar(this.raiz, str, found);
        return found[0];
    }

    public void caminhar(No i, String str, boolean[] found)
    {
        if (i != null)
        {
            found[0] = i.arvore_sec.pesquisar(str);

            if (found[0] != true)
            {
                System.out.print("ESQ ");
                caminhar(i.esq, str, found);
            }

            if (found[0] != true)
            {
                System.out.print("DIR ");
                caminhar(i.dir, str, found);
            }
        }

    }
    
    // --------------------------------------------------------------------------------

    // ADICIONAR----------------------------------------------------------------------
    public void adicionar_inicial(int x) {
        this.raiz = adicionar_inicial(x, this.raiz);
    }

    public No adicionar_inicial(int x, No i) {

        if (i == null) {
            comps.addcomp();
            i = new No(x);
        }

        else if (x < i.elemento) {
            comps.addcomp();
            i.esq = adicionar_inicial(x, i.esq);
        }

        else if (x > i.elemento) {
            comps.addcomp();
            i.dir = adicionar_inicial(x, i.dir);
        }

        return i;

    }

    // procurar o valor do mod para adicionar na arvore secundaria

    public void adicionar(int x, String name) { // recebe o capture rate mod 15 e o nome do pokemon
        this.raiz = adicionar(x, this.raiz, name); // sobrecarga
    }

    public No adicionar(int x, No i, String name) { // a passagem de valores no java e por valor

        if (i == null) {
            comps.addcomp();
            i = new No(x);
            i.arvore_sec.adicionar(name);
        }

        else if (x < i.elemento) {
            comps.addcomp();
            i.esq = adicionar(x, i.esq, name);
        }

        else if (x > i.elemento) {
            comps.addcomp();
            i.dir = adicionar(x, i.dir, name);
        }

        else {
            i.arvore_sec.adicionar(name);
        }

        return i;
    }


// =/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=

public class Q2 {

    public static void main(String[] args) {

        try {
            long start = System.currentTimeMillis();
            Arvore arvore = new Arvore();
            arvore.adicionar_inicial(7);
            arvore.adicionar_inicial(3);
            arvore.adicionar_inicial(11);
            arvore.adicionar_inicial(1);
            arvore.adicionar_inicial(5);
            arvore.adicionar_inicial(9);
            arvore.adicionar_inicial(13);
            arvore.adicionar_inicial(0);
            arvore.adicionar_inicial(2);
            arvore.adicionar_inicial(4);
            arvore.adicionar_inicial(6);
            arvore.adicionar_inicial(8);
            arvore.adicionar_inicial(10);
            arvore.adicionar_inicial(12);
            arvore.adicionar_inicial(14);

            List<pokemon> pokarray = new ArrayList<>();
            int i; // I aqui
            //File tabela = new File("pokemon.csv");
            File tabela = new File("/tmp/pokemon.csv");

            Scanner reader = new Scanner(tabela);
            reader.nextLine(); // ignora o cabecalho

            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                line = line.replace(",,,", ",0,0,");
                line = line.replace(",,", ",0,");
                line = line.replace("\"[", "[");
                line = line.replace("]\"", "]");
                // System.out.println(line);

                String[] data = line.split(",");

                int id = Integer.parseInt(data[0]);
                int generation = Integer.parseInt(data[1]);
                String name = data[2];
                String description = data[3];
                String type1 = data[4];
                String type2 = data.length > 5 ? data[5] : ""; // Caso tenha um segundo tipo
                List<String> ability = new ArrayList<>();
                i = 6;
                while (i < data.length && !pokemon.isinside(data[i])) {
                    ability.add(data[i] + ",");
                    i++;

                }

                ability.add(data[i++]);

                double weight = Double.parseDouble(data[i++]);
                double height = Double.parseDouble(data[i++]);
                int captureRate = Integer.parseInt(data[i++]);
                boolean isLegendary;
                if (data[i++].equals("1")) {
                    isLegendary = true;
                } else {
                    isLegendary = false;
                }

                // boolean isLegendary = Boolean.parseBoolean(data[i++]);
                String captureDate = data[i++];

                String[] abilitiesArray = ability.toArray(new String[0]);
                pokarray.add(
                        new pokemon(id, generation, name, description, type1, type2, abilitiesArray, weight, height,
                                captureRate, isLegendary, captureDate));// colocar todos os parametros aqui

            }

            reader.close();

            Scanner sc = new Scanner(System.in);

            boolean x = true;

            while (x) {

                String string = sc.nextLine();
                // String string = MyIO.readLine();

                if (string.equals("FIM")) {
                    x = false;
                }

                else {

                    int n = Integer.valueOf(string);
                    arvore.adicionar(pokarray.get(n - 1).getcaptureRate() % 15, pokarray.get(n - 1).getName());

                }

            }

            x = true;

            while (x) {
                boolean found = false;
                String string = sc.nextLine();

                if (string.equals("FIM")) {
                    x = false;
                }

                else {
                   
                        
                    found = arvore.caminhar(string);
                    if (found == true){
                        System.out.print("SIM\n");
                    }
                    else{
                        System.out.print("NAO\n");
                    }   
                

                }

            }

            sc.close();

            FileWriter writer = new FileWriter("matricula_arvoreArvore.txt");
            arvore.pegarComparacoes(arvore.raiz);
            int comparacoes = arvore.Get_comparacoes();
            // int movimentacoes = movs.gettotal();

            Long finish = System.currentTimeMillis();

            Long exectime = finish - start;

            writer.write("848351\t" + comparacoes + "\t" + exectime);
            writer.close();

        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
