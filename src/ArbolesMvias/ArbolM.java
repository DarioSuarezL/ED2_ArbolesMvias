package ArbolesMvias;
import java.util.LinkedList;

public class ArbolM {
    private NodoM raiz;
    private int n;          //n=cantidad de nodos.
    
    public ArbolM(){
        raiz = null;
        n = 0;
    }
    
    public int getCantNodos(){
        return n;
    }
    
    public void add(int x){
        if (raiz == null)
            raiz = new NodoM(x);
        else{
            int i=0;
            NodoM ant = null, p = raiz;
            while (p != null){
                if (!p.isLleno()){  //Hay espacio en el nodo p. Insertar x ahí.
                    p.insDataInOrden(x);
                    return;
                }
                
                i = hijoDesc(p, x);
                if (i == -1)
                    return;     //x está en el nodo p.
                
                ant = p;
                p = p.getHijo(i);
            }
            
                //p llegó a null.  Crear una nueva hoja y engancharla a ant.
            NodoM nuevo = new NodoM(x);
            ant.setHijo(i, nuevo);
        }
        
        n++;
    }
    
    
    private boolean hoja(NodoM T){
        return (T !=null && T.cantHijos()==0);
    }
    
    public void inorden(){
        System.out.print("Inorden:");
        if (raiz == null)
            System.out.println(" (Árbol vacío)");
        else{
            inorden(raiz);
            System.out.println();
        }
            
    }
    
    private void inorden(NodoM T){
        if (T != null){
            int z = T.cantDataUsadas();      //z = índice de la última data usada.
            for (int i = 1; i <= z; i++){
                inorden(T.getHijo(i));
                System.out.print(" "+T.getData(i));
            }
            inorden(T.getHijo(z+1));
        }
    }
    
    private int hijoDesc(NodoM N, int x){   //Corrutina de insertar.
        int i = 1;
        while (i <= N.cantDataUsadas()){
            if (x < N.getData(i))
                return i;
            
            if (x == N.getData(i))
                return -1;
            
            i++;
        }
        return N.cantDataUsadas()+1;
    }
    
    private void print(NodoM N){
       for (int i=1; i<= N.cantDataUsadas(); i++)
          System.out.print(" "+N.getData(i)); 
    }
    
    public void niveles(){
        niveles("");
    }
    
    public void niveles(String nombreVar){
        if (nombreVar != null && nombreVar.length()>0)
            nombreVar = " del Arbol "+nombreVar;
        else
            nombreVar = "";
                    
        System.out.print("Niveles"+nombreVar+": ");
        
        if (raiz == null)
            System.out.println("(Arbol vacío)");
        else
            niveles(raiz);
    }
    
    public boolean Existe(int x){  //existe la data x en el arbol --mascara
        return Existe(x,raiz);
    }
    
    private boolean Existe(int x, NodoM T){  //esclavo
        if(T == null)
            return false;
        else{
            if(T.existe(x))
                return true;
            return Existe(x,T.getHijo(hijoDesc(T,x)));
        }
    }
//----------------------------------------
//------------- PRACTICO -----------------
//----------------------------------------

//--- EJERCICIO 1 ---
    public boolean hnoCercano(int a, int b){  //mascara
        return hnoCercano(a,b,raiz);
    }
    
    private boolean hnoCercano(int a, int b, NodoM T){
        if(T == null || hoja(T) || !(T.cantHijos() > 1))
            return false;
        
        for(int i = 1; i <= raiz.cantDataUsadas(); i++)
            if((T.getHijo(i) != null && T.getHijo(i+1) != null) && (T.getHijo(i).existe(a) && T.getHijo(i+1).existe(b)))
                return true;
        
        if(hijoDesc(T,a) > 0)
            return hnoCercano(a,b,T.getHijo(hijoDesc(T,a)));  
        return false;
    }
    
//--- EJERCICIO 2 ---    
    public void podar(){
        raiz = podar(raiz);
    }
    
    private NodoM podar(NodoM T){
        if(T == null || hoja(T))
            return null;
                
        for(int i = 1; i <= T.cantDataUsadas() + 1; i++)
            T.setHijo(i, podar(T.getHijo(i)));
       
        return T;
    }
    
//--- EJERCICIO 3 ---    
    public void borrarHoja(int x){
        raiz = borrarHoja(x,raiz);
        //raiz = borrarHojaRec(x,raiz);
    }
    
    private NodoM borrarHoja(int x,NodoM T){
        if(T == null || (T.existe(x) && hoja(T)))
            return null;
        
        NodoM p = raiz;
        NodoM ant = null;
        int corte = hijoDesc(p,x);
        while(p != null && hijoDesc(p,x) >= 0){
            ant = p;
            corte = hijoDesc(p,x);
            p = p.getHijo(corte);   
        }
        
        if(p != null && hoja(p) && corte != -1)
            ant.setHijo(corte, null);
        
        return T;
        
    }
    
    // RECURSIVO
    private NodoM borrarHojaRec(int x, NodoM T){
        if((T == null) || (T.existe(x) && hoja(T)))
            return null;
        
        for(int i = 1; i <= T.cantDataUsadas() + 1; i++)
            T.setHijo(i,borrarHojaRec(x,T.getHijo(i)));
        
        return T;
    }
    
//--- EJERCICIO 4 ---
    private String InordenStr(){
        return "";
        // WORK IN PROGRESS...
    }
    
    private String InordenStr(NodoM T){
        if(T != null){
            for(int i = 1; i <= T.cantDataUsadas(); i++){
                return InordenStr(T.getHijo(i));
                //String cad = " " + T.getData(i);
            }
        }
        return InordenStr(T.getHijo(T.cantDataUsadas()+1));
    }
    
    public void distInorden(int a, int b){
        
    }
    
//--- EJERCICIO 5 ---
    public void delAlone(int x){
        raiz = delAlone(x,raiz);
    }
    
    private NodoM delAlone(int x, NodoM T){
        if((T == null) || (T.existe(x) && T.cantDataUsadas() == 1 && hoja(T)))
            return null;
        
        for(int i = 1; i <= T.cantDataUsadas() + 1; i++)
            T.setHijo(i,delAlone(x,T.getHijo(i)));
        
        return T;
    }   

//--- EJERCICIO 6 ---
    public void delHoja(int sum){  //la suma de todas sus datas dan igual a sum*
        raiz = delHoja(sum,raiz);
    }
    
    private NodoM delHoja(int sum, NodoM T){
        
        int tot = 0;
        
        if(hoja(T)){
            
            for(int i = 1; i <= T.cantDataUsadas(); i++){
                tot = tot + T.getData(i);
            }
        }
        
        if((T == null) || (tot == sum && hoja(T)))
            return null;
        
        for(int i = 1; i <= T.cantDataUsadas() + 1; i++)
            T.setHijo(i,delHoja(sum,T.getHijo(i)));

        return T;
    }
    
//--- EJERCICIO 7 ---
    public NodoM NodoData(int x){
        return NodoData(x,raiz);
    }
    
    private NodoM NodoData(int x, NodoM T){
        if(T == null)
            return null;
        
        if(T.existe(x))
            return T;
        
        int i = hijoDesc(T,x);
        if(i == -1)
            return null;
       return NodoData(x, T.getHijo(i)); 
    }    
    
    public boolean lastParent(int p, int h){
        if(NodoData(p) == null || NodoData(h) == null || !hoja(NodoData(h)))
            return false;
        
        for(int i = 1; i <= NodoData(p).cantDataUsadas()+1; i++){
            if(NodoData(p).getHijo(i) == NodoData(h))
                return true;
        }
        return false;
    }

//--- EJERCICIO 8 ---  
    public boolean Desciende(int a, int b){  //Desciende b de a?, siendo a el antecesor y b el descendiente
        return Existe(b, NodoData(a));
    }
    
    public boolean superParent(int x, int z){
        if(NodoData(x) == null || NodoData(z) == null || NodoData(x) == NodoData(z)) //la logica se divide en 2, si es que desciende, 
            return false;                                                               //y si es que la via no supera "el index de la data + 1" para saber que está dentro de su izquierda y derecha
        if(hijoDesc(NodoData(x),z) <= NodoData(x).dataPos(x)+1 && hijoDesc(NodoData(x),z) >= NodoData(x).dataPos(x)-1 && Desciende(x,z))
            return true;
        return false;
    }
    
//--- EJERCICIO 9 ---
    public boolean tio(int t, int s){
        if(!Existe(t) || !Existe(s) || NodoData(s) == raiz || NodoData(t) == raiz || NodoData(s) == NodoData(t))
            return false;
        NodoM p = raiz;
        NodoM ant = null;
        while(p != null){
            if(p.existe(t))
                break;
            int corte = hijoDesc(p,t);
            ant = p;
            p = p.getHijo(corte);
        }
        if(ant != null){
            for(int i = 1; i <= ant.cantDataUsadas()+1; i++){
                if(ant.getHijo(i) != NodoData(t))
                    for(int j = 1; j <= ant.getHijo(i).cantDataUsadas()+1; j++){
                        if(ant.getHijo(i).getHijo(j) == NodoData(s))
                            return true;
                    }
            }
        }
        return false;
    }

//--- EJERCICIO 10 ---
    
    public NodoM nodoPadre(int x){  // te da el padre de la data x y si no existe x, retorna null
        NodoM padre = null;
        NodoM hijo = raiz;
        while(hijo != null || hijo.existe(x)){
            int corte = hijoDesc(hijo,x);
            if (corte == -1)
                break;
            padre = hijo;
            hijo = hijo.getHijo(corte);
        }
        if(hijo != null)
            return padre;
        return null;
    }
    
    public boolean balanced(){
        return balanced(raiz);
    }
    
    private boolean balanced(NodoM T){
        if(T == null)
            return true;
        
        if(hoja(T)){
            NodoM p = nodoPadre(T.getData(1));
            for(int j = 1; j <= p.cantDataUsadas()+1; j++){
                if(!hoja(p.getHijo(j)))
                    return false;
            }
            return true;
        }
        
        if(T.viasOcupadas()){
            boolean a = true;
            for(int i = 1; i <= T.cantDataUsadas()+1; i++)
                a = a && balanced(T.getHijo(i));
            return a;
        }
        return false;
    }
    
//---------- Métodos auxiliares para mostrar el árbol por niveles --------------
    private void niveles(NodoM T){   //Pre: T no es null.
        LinkedList <NodoM> colaNodos   = new LinkedList<>();
        LinkedList<Integer> colaNivel = new LinkedList<>();
        
        int nivelActual = 0;
        String coma="";
        
        colaNodos.addLast(T);
        colaNivel.addLast(1);
        
        do{
            NodoM p = colaNodos.pop();       //Sacar un nodo p de la cola
            int nivelP = colaNivel.pop();   //Sacar el nivel donde se encuentra p.
            
            if (nivelP != nivelActual){ //Se está cambiando de nivel
                System.out.println();
                System.out.print("  Nivel "+nivelP+": ");
                nivelActual = nivelP;
                coma = "";
            }
            
            System.out.print(coma + p);
            coma = ", ";
            
            addHijos(colaNodos, colaNivel, p, nivelP);   
        }while (colaNodos.size() > 0);
        
        System.out.println();
    }
    
    private void addHijos(LinkedList <NodoM> colaNodos, LinkedList<Integer> colaNivel,  NodoM p, int nivelP){
        for (int i=1; i<=NodoM.M; i++){  //Insertar a la cola de nodos los hijos no-nulos de p
            NodoM hijo = p.getHijo(i);
            
            if (hijo != null){
                colaNodos.addLast(hijo);
                colaNivel.addLast(nivelP+1);
            }
        }
    }    
}
