package first;
import java.util.Scanner;

public class Field {
    private Logic logic;
    private int[][] fields;  // Игровое поле
    private int[][] example; // Заполняется от 1 до n * m, для определения индекса
    private int[] allMoves;  // Заполняется всеми ответами, для учитывания ошибок и прочего
    private int count;



    public Field(int n, int m) {
        logic = new Logic(n , m);
        fields = new int[n][m];
        example = new int[n][m];
        fillExample(example);
        count = 0;
        allMoves = new int[n * m];
    }

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


    private void addNewElemX(int inputNumber){

        int checkNumber = logic.checkIndex(inputNumber, allMoves);

        if (checkNumber == 1) {

            int[] array = logic.getIndex(inputNumber, allMoves, example, count);

            fields[array[0]][array[1]] = 1;

        } else if (checkNumber == -1){

            System.out.println("Ошибка, данное поле уже занято");

        } else {

            System.out.println("Элемент вышел за границы поля, вы указали неверное число");

        }
    }

    private void addNewElemO(int inputNumber){

        int checkNumber = logic.checkIndex(inputNumber, allMoves);

        if (checkNumber == 1) {

            int[] array = logic.getIndex(inputNumber, allMoves, example, count);

            fields[array[0]][array[1]] = 2;

        } else if (checkNumber == -1){

            System.out.println("Ошибка, данное поле уже занято");

        } else {

            System.out.println("Элемент вышел за границы поля, вы указали неверное число");

        }
    }


    private void fillExample(int[][] example){

        int flag = 1;

        for (int i = 0; i < example.length; i++) {

            for (int j = 0; j < example[i].length; j++) {

                example[i][j] = flag;
                flag++;

            }
        }
    }


    private void showField(){

        for (int i = 0; i < fields.length; i++) {

            for (int j = 0; j < fields[i].length; j++) {

                System.out.print(fields[i][j] + " ");
            }

            System.out.println();
        }
    }
}
