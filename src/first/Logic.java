package first;

public class Logic {
    private int sizeField;
    private int n;
    private int m;

    public Logic(int n, int m) {
        this.n = n;
        this.m = m;

        sizeField = n * m;

    }


    public int[] getIndex(int inputNumber, int[] allMoves, int[][] example, int count){
        int[] index = new int[2];
        allMoves[count] = inputNumber;

        for (int i = 0; i < example.length; i++) {
            for (int j = 0; j < example[i].length; j++) {
                if(example[i][j] == inputNumber){
                    index[0] = i;
                    index[1] = j;
                }
            }
        }
        return index;
    }


    public int checkIndex(int inputNumber, int[] allMoves){

        if ( inputNumber > 0 && inputNumber <= sizeField) {
            for (int i = 0; i < allMoves.length; i++) {
                if (allMoves[i] == inputNumber){
                    return -1;
                }
            }
            return 1;
        }

        return 0;
    }

    // Можем играть дальше? Считаем тут)
    public int canWePlay(int count, int[][] fields){

        int winner = whoIsWinner(fields, count);

        if (winner == 0 && count < n * m){
            return 0;  // Можем продолжать

        }
        if (winner == 0 && count == n * m){
            return -1; // Никто не выйграл, поле закончилось

        }
        if(winner == 1){
            return 1; // Победитель X

        }
        if(winner == 2){
            return 2; // Победитель O
        }

        return -2; // Непридвиденная ошибка
    }

    // Алгорит вычисления победителя
    private int whoIsWinner(int[][] fields, int count){
        int countXLines = 0;
        int countOLines = 0;

        int countXColumn = 0;
        int countOColumn = 0;

        int countXDiagonalX = 0;
        int countODiagonalX = 0;

        int flag = n - 1;
        int countXDiagonalY = 0;
        int countODiagonalY = 0;



        int minS = n < m ? n : m;

        if ( count > minS + 1) {

            // Цикл проверки для строк и столбцов!!!
            for (int i = 0; i < n; i++) {
                countXLines = 0;
                countOLines = 0;
                countXColumn = 0;
                countOColumn = 0;

                for (int j = 0; j < m; j++) {

                    // Проверяем X по строкам
                    if (fields[i][j] == 1) {
                        countXLines++;

                    }

                    // Проверяем О по строкам
                    if (fields[i][j] == 2) {
                        countOLines++;

                    }

                    // Проверяем Х по столбцам
                    if (fields[j][i] == 1 ){
                        countXColumn++;

                    }

                    // Проверяем О по столбцам
                    if (fields[j][i] == 2) {
                        countOColumn++;

                    }

                }


                if (countXLines == m || countXColumn == n) {
                    return 1;
                }

                if (countOLines == m || countOColumn == n)  {
                    return 2;
                }
            }

            // Алгоритм для диагонали положительной и отрицательной
            for (int i = 0; i < n ; i++) {

                // Считаем положительную диагональ для X
                if (fields[i][i] == 1){
                    countXDiagonalX++;
                }

                // Считаем положительную даигональ для O
                if (fields[i][i] == 2) {
                    countODiagonalX++;
                }

                // Считаем отрицательную диагональ для X
                if (fields[flag][i] == 1) {
                    countXDiagonalY++;
                }

                if (fields[flag][i] == 2) {
                    countODiagonalY++;
                }

                flag--;

            }


            if (countXDiagonalX == m || countXDiagonalY == n){
                return 1;
            }

            if (countODiagonalX == m || countODiagonalY == n){
                return 2;
            }



        }

        return 0; // Продолжаем играть
    }

}
