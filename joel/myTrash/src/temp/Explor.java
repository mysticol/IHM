package temp;

import java.net.URI;



public class Explor {

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
    
        URI uri= new URI("file:///www.home/manou/truc/?va=cc;tr=ponay#chaussette");
        
        System.out.println(uri.getAuthority());
        
        
        
        

    }

}
