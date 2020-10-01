package first;

public class Logic {
    private int sizeField;
    private int n;
    private int m;
    private int[] allMoves;  // Заполняется всеми ответами, для учитывания ошибок и прочего
    private int[][] example; // Заполняется от 1 до n * m, для определения индекса


    public Logic(int n, int m) {
        this.n = n;
        this.m = m;
        sizeField = n * m;
        example = new int[n][m];
        fillExample(example);
        allMoves = new int[sizeField];
    }

    // Получаем индексы в двумерном массиве, по номеру элемента
    // Возвращаем одномерный массив, с индексом для двумерного внутри
    public int[] getIndex(int inputNumber, int count){
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

    // Проверяем введенный пользователем индекс на возможность добавления! Возвращаем:
    // -1 совпадения введенного пользователем элемента
    //  1 отсутствие совпадений, можно добавлять
    //  0 отсутствует в пределах границ
    public int checkIndex(int inputNumber){

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

    // Данный метод определяет можем ли мы играть, или продолжать игру, вызывает при каждом введении числа. Возвращаем:
    // -2 Непредвиденная ошибка
    // -1 Никто не выйграл, поле закончилось (некуда заполнять)
    //  0 Никто не выйграл, поле не закончилось (есть куда заполнять)
    //  1 Выйграл первый игрок X
    //  2 Выйграл второй игрок O
    public int canWePlay(int count, int[][] fields){

        int winner = whoIsWinner(fields, count);

        if (winner == 0 && count < n * m){
            return 0;

        }
        if (winner == 0 && count == n * m){
            return -1;

        }
        if(winner == 1){
            return 1;

        }
        if(winner == 2){
            return 2;
        }

        return -2;
    }

    // Алгорит вычисления победителя
    private int whoIsWinner(int[][] fields, int count){
        // Переменные счетчик для хранения комбинаций по строкам
        int countXLines;
        int countOLines;

        // Переменные счетчик для хранения комбинаций по столбцам
        int countXColumn;
        int countOColumn;

        // Переменные счетчик для хранения комбинаций по диагонали положительной
        int countXDiagonalX = 0;
        int countODiagonalX = 0;

        // Переменные счетчик для хранения комбинаций по диагонили отрицательной
        int countXDiagonalY = 0;
        int countODiagonalY = 0;

        int flag = n - 1;
        int minS = n < m ? n : m;



        if ( count > minS + 1) {

            // Цикл проверки для строк и столбцов
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

                // Считаем отрицательную диагональ для O
                if (fields[flag][i] == 2) {
                    countODiagonalY++;
                }

                flag--;


                if (countXLines == minS || countXColumn == minS || countXDiagonalX == minS || countXDiagonalY == minS) {
                    return 1;  // Выйграл первый X
                }

                if (countOLines == minS || countOColumn == minS || countODiagonalX == minS || countODiagonalY == minS) {
                    return 2;  // Выйграл второй O
                }
            }

        }

        return 0; // Продолжаем играть
    }



    // Метод заполняет двухмерный массив example по порядку
    private void fillExample(int[][] example){

        int flag = 1;

        for (int i = 0; i < example.length; i++) {

            for (int j = 0; j < example[i].length; j++) {

                example[i][j] = flag;
                flag++;

            }
        }
    }

}
