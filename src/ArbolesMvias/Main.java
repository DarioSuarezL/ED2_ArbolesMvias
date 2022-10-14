package ArbolesMvias;

public class Main {
    private static final int V[] = {100,300,20,90,10,25,60,92,96,110,190,340,400,320,330,350,360,440,490};

    private static void cargarDatos(ArbolM A){
        for (int i=0; i < V.length; i++)
            A.add(V[i]);
    }
    public static void main(String[] args) {
        ArbolM T = new ArbolM();
        System.out.println(T.getCantNodos());
        T.add(100);
        T.add(300);
        T.add(20);
        T.add(90);
        T.add(110);
        T.add(190);
        T.add(340);
        T.add(400);
        T.add(10);
        T.add(25);
        T.add(60);
        T.add(92);
        T.add(96);
        T.add(102);
        T.add(108);
        T.add(120);
        T.add(200);
        T.add(320);
        T.add(330);
        T.add(350);
        T.add(360);
        T.add(440);
        T.add(490);
       
        //T.niveles();
        //System.out.println(T.Existe(152));
        //System.out.println(T.hnoCercano(120,260));
        //T.podar();
        //T.inorden();
        //T.delHoja(530);
        //T.niveles();
        //System.out.println(T.NodoData(86));
        //System.out.println(T.lastParent(110, 108));
        //T.delAlone(120);
        //T.delAlone(200);
        //T.niveles();
        //T.niveles();
        //System.out.println(T.superParent(190, 118));
        
        ArbolM H = new ArbolM();
        cargarDatos(H);
        H.niveles();
        System.out.println(H.balanced());
        
    }
}
