import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

class Fone{
    public String label;
    public String number;
    
    public static boolean validate(String number){
        int quant = 0;
        String validar = "1234567890";
        String val[] = new String[10];
        String num[] = new String[number.length()];
        val = validar.split("");
        num = number.split("");
        for (int i = 0; i < num.length; i++) {
            for (int j = 0; j < val.length; j++) {
                if(num[i].equals(val[j])){
                    quant++;
                }
            }
        }
        if(quant < number.length()){
            System.out.println("Numero invalido");
            return false;
        }else{
            return true;
        }
    }

    public Fone(String label, String number) {
        if(validate(number)){
            this.label = label;
            this.number = number;
        }
    }
    
    public Fone(int serial){
        
    }
    
    public String toString(){
        return label+":"+number;
    }
}

class Contato{
    private String name;
    private ArrayList<Fone> fone;
    
    public void procurarNull(){
        for (int i = 0; i < fone.size(); i++) {
            if(fone.get(i).label == null || fone.get(i).number == null){
                rmFone(i);
            }
        }
    }
    
    public void addFone(String label, String number){
        fone.add(new Fone(label, number));
        procurarNull();
    }
    
    public void rmFone(int index){
        fone.remove(index);
    }

    public Contato(String name, String label, String number) {
        fone = new ArrayList<Fone>();
        fone.add(new Fone(label, number));
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public ArrayList<Fone> getFone() {
        return fone;
    }

    public void setFone(ArrayList<Fone> fone) {
        this.fone = fone;
    }
    
    public String toString(){
        return "Nome:"+name+", Fone:"+fone;
    }
}


public class Agenda{
    private List<Contato> contatos;
    private List<Contato> bookmarks;
    
    public int findContact(String name){
        for (int i = 0; i < contatos.size(); i++) {
            if(contatos.get(i).getName() == name){
                return i;
            }
        }
        return -1;
    }
    
    public void addContact(String name, Fone fone){
        if(findContact(name) != -1){
            System.out.println("Ja existe esse nome");
        }else{
            contatos.add(new Contato(name, fone.label, fone.number));   
        }
    }
    
    public boolean rmContact(String name){
        int pos = findContact(name);
        if(pos == -1){
            System.out.println("Não existe esse contato");
            return false;
        }else{
            contatos.remove(pos);
            return true;
        }
    }
    
    public List<Contato> search(String pattern){
        List<Contato> aux = new ArrayList<>();
        for (int i = 0; i < contatos.size(); i++) {
            if (contatos.get(i).toString().contains(pattern)) {
                aux.add(i, contatos.get(i));
            }
        }
        return aux;
    }

    public List<Contato> getContacts(String name) {
        return contatos;
    }
    
    void bookmark(String id){
        String aux;
        if(perBook(id)){
            aux = "@ " + id;
            for (int i = 0; i < contatos.size(); i++) {
                if(contatos.get(i).getName().equals(id)){
                    contatos.get(i).setName(aux);
                    bookmarks.add(contatos.get(i));
                }
            }
        }
    }
    
    void unBookmark(String id){
        if(perBook(id)){
            System.out.println("Não existe esse contato");
        }else{
            String aux = "@ " + id;
            for (int i = 0; i < bookmarks.size(); i++) {
                if(bookmarks.get(i).getName().equals(aux)){
                    bookmarks.remove(i);
                }
            }
            for (int i = 0; i < contatos.size(); i++) {
                if(contatos.get(i).getName().equals(aux)){
                    contatos.get(i).setName(id);
                }
            }
        }
    }

    boolean perBook(String id){
        String aux;
        aux = "@ " + id;
        for (int i = 0; i < bookmarks.size(); i++) {
            if(bookmarks.get(i).getName().equals(aux)){
                return false;
            }
        }
        return true;
    }
    
    public List<Contato> getBookmarks() {
        return bookmarks;
    }

    public Agenda(){
        contatos = new ArrayList<Contato>();
        bookmarks = new ArrayList<Contato>();
    }

    public String toString(){
        return "Contatos: "+ contatos;
    }
    
    public String starred(){
        return "Favoritos: "+ bookmarks;
    }
    
    public static void main(String[] args) {
        Agenda agenda = new Agenda();
        while(true){
                Scanner tcl = new Scanner(System.in);
                String[] ui = tcl.nextLine().split(" ");
                if(ui[0].equals("add")){
                    agenda.addContact(ui[1],new Fone(ui[2], ui[3]));
                }else if(ui[0].equals("remove")){
                    agenda.rmContact(ui[1]);
                }else if(ui[0].equals("search")){
                    System.out.println(agenda.search(ui[1]));
                }else if(ui[0].equals("star")){
                    agenda.bookmark(ui[1]);
                }else if(ui[0].equals("unstar")){
                    agenda.unBookmark(ui[1]);
                }else if(ui[0].equals("show")){
                    System.out.println(agenda);
                }else if(ui[0].equals("starred")){
                    System.out.println(agenda.starred());
                }else if(ui[0].equals("stop")){
                    break;
                }
            }
        
    }
    
}
