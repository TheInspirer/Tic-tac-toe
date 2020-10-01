package first;
import java.util.Scanner;

public class Field {
    private Logic logic;
    private int[][] fields;     // Игровое поле для логики
    private char[][] gameField; //
    private int count;          // Счетчик



    public Field(int n, int m) {
        logic = new Logic(n , m);
        fields = new int[n][m];
        gameField = new char[n][n];
        count = 0;
    }


    // Запуск игры
    public void startGame(){

        Scanner scn = new Scanner(System.in);
        int canPlay = 0;

        while (canPlay == 0){
            boolean addX = false;
            boolean addO = false;

            while (!addX){
                int xAdd = addNewElemX(scn.nextInt());

                if (xAdd == 1){
                    count++;
                    addX = true;

                    showField();
                    canPlay = logic.canWePlay(count, fields);

                } else if (xAdd == -1){
                    System.out.println("Ошибка, данное поле уже занято");
                    System.out.println("Попробуй снова");
                } else {
                    System.out.println("Элемент вышел за границы поля, вы указали неверное число");
                    System.out.println("Попробуй снова");
                }

            }

            while (!addO && canPlay == 0){
                int oAdd = addNewElemO(scn.nextInt());

                if (oAdd == 1){
                    count++;
                    addO = true;

                    showField();
                    canPlay = logic.canWePlay(count, fields);

                } else if (oAdd == -1) {
                    System.out.println("Ошибка, данное поле уже занято");
                    System.out.println("Попробуй снова");
                } else {
                    System.out.println("Элемент вышел за границы поля, вы указали неверное число");
                    System.out.println("Попробуй снова");
                }

            }
        }


        if(canPlay == -1){

            System.out.println("Game over, nobody wins!");

        } else if (canPlay == 1){

            System.out.println("First player WIN, Congratulations");

        } else if (canPlay == 2){

            System.out.println("Second player WIN, Congratulations");

        } else{

            System.out.println("Unknown mistake");

        }
    }


    // Метод добавляет элемент для первого игрока в поле. Возвращает:
    // Пока работает только вариант с успешным добавлением
    //
    //
    public int addNewElemX(int inputNumber){

        int checkNumber = logic.checkIndex(inputNumber);

        if (checkNumber == 1) {

            int[] array = logic.getIndex(inputNumber, count);

            fields[array[0]][array[1]] = 1;

            return 1;

        } else if (checkNumber == -1){

            //System.out.println("Ошибка, данное поле уже занято");
            return -1;

        } else {

            //System.out.println("Элемент вышел за границы поля, вы указали неверное число");
            return 0;
        }
    }

    // Метод добавляет элемент для второго игрока в поле. Возвращает:
    // Пока работает только вариант с успешным добавлением
    //
    //
    private int addNewElemO(int inputNumber){

        int checkNumber = logic.checkIndex(inputNumber);

        if (checkNumber == 1) {

            int[] array = logic.getIndex(inputNumber, count);

            fields[array[0]][array[1]] = 2;

            return 1;

        } else if (checkNumber == -1){

            //System.out.println("Ошибка, данное поле уже занято");
            return -1;

        } else {

            //System.out.println("Элемент вышел за границы поля, вы указали неверное число");
            return 0;
        }
    }


    // Метод выводит поле в консоль
    private void showField(){

        for (int i = 0; i < fields.length; i++) {

            for (int j = 0; j < fields[i].length; j++) {

                if (fields[i][j] == 1) {
                    gameField[i][j] = 'x';
                } else if (fields[i][j] == 2){
                    gameField[i][j] = 'o';
                } else {
                    gameField[i][j] = '*';
                }

                System.out.print(gameField[i][j]);
            }

            System.out.println();
        }
    }
}
