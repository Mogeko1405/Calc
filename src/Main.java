import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите операцию");
            String input = scanner.nextLine();                        // ввод данных
            input = calc(input);                                      // возвращение данных с метода calc
            System.out.println("Ответ: " + input);
            System.out.println();
    }

    public static String calc(String input) {
        Converter converter = new Converter();
        String[] symbols = {"+", "-", "*", "/"};                          // знаки действия
        String[] regexSymbols = {"\\+", "-", "\\*", "/"};                 // регулярные выражения

        int symbolIndex = -1;

        for (int i = 0; i < symbols.length; i++) {                        // определение знака действия
            if (input.contains(symbols[i])){
                symbolIndex = i;
                break;
            }
        }
        if(symbolIndex==-1){
            throw new RuntimeException();
        }

        String[] data = input.split(regexSymbols[symbolIndex]);           // разделение строки по знаку (с помощью рег. выражения)
        if(converter.isRoman(data[0]) == converter.isRoman(data[1])){     // условие для рассчета одновременно только арабских или римских чисел
            int a,b,c = 0;

            boolean isRoman = converter.isRoman(data[0]);                 // проверка что это именно римские числа
            if(isRoman){
                a = converter.romanToInt(data[0]);                        // конвертация римских в арабские числа и их дальнейшее присваивание для рассчёта
                b = converter.romanToInt(data[1]);
            }

            else {
                a = Integer.parseInt(data[0]);                            // присваивание арабских значений для рассчёта
                b = Integer.parseInt(data[1]);

                if ( a<1 || a>10 || b<1 || b>10 ){                        // ограничение ввода для арабских
                    throw new RuntimeException();
            }

            }
            switch (symbols[symbolIndex]){                                // подсчёт
                case "+":
                    c = a + b;
                    break;
                case "-":
                    c = a - b;
                    break;
                case "*":
                    c = a * b;
                    break;
                case "/":
                    c = a / b;
                    break;
            }

                                                                         // возврат данных
           if(isRoman){
               if ( a<1 || a>10 || b<1 || b>10 ) {                       // ограничение ввода для римских
                   throw new RuntimeException();
               }
               else {
                   input = converter.intToRoman(c);
                   return input;
               }
            }
           else {
               input = c + "";
               return input;
           }
        }
        else {
            throw new RuntimeException();
        }
    }
}

