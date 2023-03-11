import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class controller {

    Scanner in = new Scanner(System.in);
    Random rand = new Random();

    // Değişkenleri tanımladım
    int row, column, size;
    private int x, y;
    private int[][] guess;
    int[][] map;
    int[][] board;
    private boolean mayin;

    // Constructor
    controller(int row, int column) {
        this.row = row;
        this.column = column;
        this.size = row * column;
        this.map = new int[row][column];
        this.board = new int[row][column];
    }

    // Oyun kurulumu için kullanıcıdan isteklerin alınması
    public void setup() {
        System.out.println(
                "Mayın Tarlası Oynuna Hoşgeldiniz. Oynun kuralları: 0 mayın hakkında bilgi olmadığı anlamındadır , -1 ise mayının olduğu anlamına gelir. Lütfen oynamak istediğiniz boyutları giriniz.");
        System.out.print("Satır sayısı:");
        row = in.nextInt();
        System.out.println();
        System.out.print("Sütün sayısı:");
        column = in.nextInt();
        size = row * column;
        map = new int[row][column];
        board = new int[row][column];
    }

    public void run() {
        setup();
        prepare(); // Mayınlar oluşturuldu haritada
        printBoard(board); // güncel tahtayı gösteriyor
        for (int i = 0; i < size; i++) {
            askMayin(); // Kullanıcıdan tahmini alınıyor
            returnGuess(x, y);
            checkMayin();
            if (mayin == false) {
                printBoard(board); // güncel tahtayı gösteriyor
                if (i - 1 == size) {
                    System.out.println("Oyunu başarı ile kazandınız.");
                }
            } else {
                System.out.println("Mayına bastınız.");
                System.out.println("Mayın Haritası.");
                printMap(map);
                break;
            }
        }

    }

    public boolean checkMayin() {
        if (guess[x][y] == map[x][y]) {
            mayin = true;
        } else {
            mayin = false;
        }
        return mayin;
    }

    public int[][] returnGuess(int x, int y) {
        guess = new int[row][column];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (i == x && j == y) {
                    guess[i][j] = -1;
                } else {
                    guess[i][j] = 0; // Diğer tüm koordinatları varsayılan olarak 0 olarak işaretle
                }
            }
        }
        return guess;
    }

    public void askMayin() {
        System.out.println();
        System.out.println("Hangi satırın sütünunda mayın yoktur ?");
        System.out.print("Satırı yazınız: ");
        x = in.nextInt();
        System.out.println();
        System.out.print("Sütünü yazınız: ");
        y = in.nextInt();

        if (x > row || y > column) {
            System.out.println("Lütfen geçerli bir satır ve sütün numarası giriniz.");
            in.nextLine(); // buffer temizleme
            askMayin();
        }

    }

    public void prepare() {
        int randRow, randCol;
        for (int i = 0; i <= size / 4; i++) { // En fazla alanın çeyreğinde mayın olucak
            randRow = rand.nextInt(row); // Rasgale bir satır seçiyor
            randCol = rand.nextInt(column); // Rasgale bir sütün seçiyor
            if (map[randRow][randCol] != -1) { // Eğer daha önce ilgili satırın sütünü -1 atanmamış ise
                map[randRow][randCol] = -1; // Buna -1 atıyor ve -1 olanlar mayınlı yerler
            }
        }
    }

    public void printBoard(int[][] arr) {
        // Sütun numaralarını yazdır
        System.out.print("   "); // İlk üç karakteri boş bırakmak için
        for (int j = 0; j < arr[0].length; j++) {
            System.out.printf("%3d", j);
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.println();
            System.out.printf("%3d", i);
            for (int j = 0; j < arr[i].length; j++) {
                System.out.printf("%3d", 0);
            }
        }
    }

    public void printMap(int[][] arr) {
        // Sütun numaralarını yazdır
        System.out.print("   "); // İlk üç karakteri boş bırakmak için
        for (int j = 0; j < arr[0].length; j++) {
            System.out.printf("%3d", j);
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.println();
            System.out.printf("%3d", i);
            for (int j = 0; j < arr[i].length; j++) {
                System.out.printf("%3d", arr[i][j]);
            }
        }
    }

}
