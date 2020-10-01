package first;
import java.util.Scanner;

public class Field {
    private Logic logic;
    private int[][] fields;  // Игровое поле
    private int count;       // Счетчик



    public Field(int n, int m) {
        logic = new Logic(n , m);
        fields = new int[n][m];
        count = 0;
    }


    // Запуск игры
    public void startGame(){

        Scanner scn = new Scanner(System.in);
        int canPlay = logic.canWePlay(count, fields);

        while (canPlay == 0) {

            addNewElemX(scn.nextInt());
            count++;
            showField();
            canPlay = logic.canWePlay(count, fields);

            if (canPlay == 0) {
                addNewElemO(scn.nextInt());
                count++;
            } else {
                break;
            }

            canPlay = logic.canWePlay(count, fields);
            showField();
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
    private void addNewElemX(int inputNumber){

        int checkNumber = logic.checkIndex(inputNumber);

        if (checkNumber == 1) {

            int[] array = logic.getIndex(inputNumber, count);

            fields[array[0]][array[1]] = 1;

        } else if (checkNumber == -1){

            System.out.println("Ошибка, данное поле уже занято");

        } else {

            System.out.println("Элемент вышел за границы поля, вы указали неверное число");

        }
    }

    // Метод добавляет элемент для второго игрока в поле. Возвращает:
    // Пока работает только вариант с успешным добавлением
    //
    //
    private void addNewElemO(int inputNumber){

        int checkNumber = logic.checkIndex(inputNumber);

        if (checkNumber == 1) {

            int[] array = logic.getIndex(inputNumber, count);

            fields[array[0]][array[1]] = 2;

        } else if (checkNumber == -1){

            System.out.println("Ошибка, данное поле уже занято");

        } else {

            System.out.println("Элемент вышел за границы поля, вы указали неверное число");

        }
    }


    // Метод выводит поле в консоль
    private void showField(){

        for (int i = 0; i < fields.length; i++) {

            for (int j = 0; j < fields[i].length; j++) {

                System.out.print(fields[i][j] + " ");
            }

            System.out.println();
        }
    }
}
