import java.util.HashMap;

public class Main{
    // preenche a tabela de programação dinâmica
    public static int[][] FillTable(String word) { 
	int len = word.length();
 
	int[][] dp = new int[len][len];
        for(int i = 0; i < len; i++) {
            for(int j = 0; j < len; j++) {
                if(word.charAt(i) == word.charAt(j)) {
                    try { 
                        dp[i][j] = 1 + dp[i-1][j-1];
                    } catch(Exception e) {
                        dp[i][j] = 1;
                    }
                    
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        
        return dp;
    }
       
    // preenche o hashmap com tamanho e quantidade de repetições de cada substring
    public static HashMap<Integer, Integer>[] FillArray(HashMap<Integer, Integer> vet[], int[][] mat) {
        int tam = mat.length;
        // inicializa o vetor de HashMaps
        vet = (HashMap<Integer, Integer>[]) new HashMap<?,?>[tam];

        for(int c = 0; c < tam; c++) {
            // inicializa cada um dos HashMaps do vetor
            vet[c] = new HashMap<>();

            for(int l = c + 1; l < tam; l++) {
                if(mat[l][c] != 0) {
                    if(vet[c].containsKey(mat[l][c])) {
                        vet[c].put(mat[l][c], vet[c].get(mat[l][c]) + 1);
                        l += mat[l][c];
                    } else {
                        vet[c].put(mat[l][c], 1);
                        l++;
                    }
                }
            }
        }
        
        for(int i = 0; i < tam; i++) {
            for(int key : vet[i].keySet()) {
                vet[i].put(key, vet[i].get(key) + 1);
            }
        }
        
        for(int i = 0; i < tam; i++){
            System.out.println(i);
            for(int key : vet[i].keySet()) {
                System.out.println(key + ": " + vet[i].get(key));
            }
        }
        return vet;
    }
    
    // calcula os produtos quantidade de repetições * tamanho para cada substring e retorna a substring desejada
    public static String GetLCS(HashMap<Integer, Integer> vet[], String word) {
        int num_maior = 0, col_maior = 0, prod_maior = 0;
        int tam = vet.length;
        String aux = "";
        
        for(int i = 0; i < tam; i++) {
            for(int key : vet[i].keySet()) {
                if(key * vet[i].get(key) > prod_maior) {
                    prod_maior = key * vet[i].get(key);
                    num_maior = key;
                    col_maior = i;
                }
            }
        }
        
        for(int i = (col_maior - (num_maior - 1)); i <= col_maior; i++) {
            aux += word.charAt(i);
        }
        
        
        
        
        return aux;
    }

   
    public static void main (String[] args) {
        String str = "abcbc";
        int[][] mat = FillTable(str);
        HashMap<Integer, Integer>[] vet = null;
        vet = FillArray(vet, mat);
        
        String maior = GetLCS(vet, str);
        
        System.out.print(maior);
    }

}
