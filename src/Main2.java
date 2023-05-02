import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class Main2 {
    public static void main(String[] args)  throws IOException ,FileNotFoundException{
        char [][]dosya1 = dosyalama.oku("böcek.txt");//böcek dosyasını arraye çevirme.
        char [][]dosya2 = dosyalama.oku("test_arazi.txt");//arazi dosyasını arraye çevirme.
        System.out.println("Böcek deseni sayısı:"+ karsılastırma.karsılastır(dosya1,dosya2));//karsılastırma islemi.
    }

}
class dosyalama{
    static char[][] oku(String dosyaAdi) throws IOException ,FileNotFoundException{

        //İstenilen dosyanın satır sayısını buluyor.
        int satirs=0;
            BufferedReader ok = new BufferedReader(new FileReader(dosyaAdi));
            while (ok.readLine() != null) satirs++;
            ok.close();
        //İstenilen dosyanın sutun sayısını buluyor.
        int sutuns = 0;
        for (int i = 0; i < satirs;i++){
            String satır = Files.readAllLines(Paths.get(dosyaAdi)).get(i);
            if(satır.length()>sutuns) {
                sutuns = satır.length();
            }
        }
        //İstenilen dosyadaki satırları okuyup bir String arrayine kaydediyor.Sonrasında bulduğu tab karakterlerini 4 tane boşluk ile değiştiriyor.
        String satırlar[]= new String[satirs];
        for (int i = 0; i < satirs;i++){
            satırlar[i] = Files.readAllLines(Paths.get(dosyaAdi)).get(i);
        }
        for (int i = 0; i < satirs;i++){
            satırlar[i]=satırlar[i].replaceAll("\t","    ");
        }
        //Dosyayı daha kolay manipüle etmek için 2 boyutlu bir array oluşturup  içine yazıyoruz.
        char dosya[][] = new char[satirs][sutuns];
        for(int i=0;i<satirs;i++){
            int limit = satırlar[i].length();
            for(int j=0;j<limit;j++){
                dosya[i][j] = satırlar[i].charAt(j);
            }
        }
        //null olan karakterleri boşluk ile değiştiriyorum ki satır sonu olan kısımlardaki patternler de okunsun
        for(int i=0;i<satirs;i++){
            for(int j=0;j<sutuns;j++){
                if(dosya[i][j] == 0){
                    dosya[i][j]= ' ';
                }

            }
        }
        //Bulduğumuz sonuç arrayını geri döndürüyoruz.
        return dosya;
    }
}
class karsılastırma{
    static int karsılastır(char[][]desen,char[][]arazi){
        //ilerde kullanacağım için arraylerin x ve y eksenindeki boylarını alıyorum.
        int arazix=0;
        int araziy=0;
        int desenx=0;
        int deseny=0;
        arazix = arazi.length;
        araziy = arazi[0].length;
        desenx=desen.length;
        deseny=desen[0].length;
        //ilk önce arazinin arrayindeki tüm karakterleri içiçe loop ile okutuyorum
        int bocekcount=0;
        int flag=0;
        for(int i =0;i<arazix;i++){
            for(int j=0;j<araziy;j++){
               //sonra desen arrayinin 0,0 karakteriyle uyumluluklarını test ediyorum
                if(arazi[i][j]==desen[0][0]){
                    //uyumlu iseler bu sefer desen arrayinin tüm karakterlerini tek tek okutuyorum
                    for(int k=0;k<desenx;k++){
                        for(int l=0;l<deseny;l++){
                            // desen arrayini x ve y değeri değiştikçe arazi arrayinin x ve y değerini aynı oranda arttırıyorum taşmasın diye limit koyduktan sonra karakter karakter doğrulukları kontrol ediliyor.
                            if(i+k<arazix && j+l<araziy){
                            if(arazi[i+k][j+l]==desen[k][l]){
                                flag=flag+1;
                            }}
                        }
                    }
                    //eğer doğru çıkan karakter sayısı desendeki toplam karakter sayısına eşitse bocek sayısını bir arttırıp kullandığım geçici değişkeni sıfırlıyorum.
                    if(flag==desenx*deseny){
                        bocekcount++;
                    }
                    flag=0;
                }
            }
        }



        //final olarak bocek degerini geri yolluyorum.
        return bocekcount;
    }
}