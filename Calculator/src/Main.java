import NewExceptions.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));// Создаем считыватель с консоли
        System.out.println("Добро пожаловать в консольную программу Калькулятор");
        System.out.println("Автор данной программы FreeHardSkill (E-Mail: freehardskill777@gmail.com)");
        System.out.println("Чтобы продолжить введите арифметическое действие");
        System.out.println("Пример: 1+1 или II / II");
        System.out.println("Значения могут быть от 1 до 10 или от I до X");
        System.out.println("Для выхода из программы введите Quit");
        String string; // Создаем переменную для записи введенных данных
        while(true) // Повторяем набор пока не будет введено слово
        {
            string = reader.readLine(); // записываем введеные данные в переменную
            if(string.contains("Quit") || string.contains("quit")) break;
            gCount(string);
            System.out.println("Введите новое значение либо Quit для выхода");
        }

    }

    private static void gCount(String string) {

        try
        {
            String[] intArray = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}; // Массив для проверки арабских чисел
            String[] stringArray = {"I" , "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"}; // Массив для проверки римских чисел
            Boolean varType1 = null, varType2 = null; // Тип чисел, false = Арабские, true = Римские
            int var1=0, var2=0, value=0; // переменные для присвоения значений из массива
            if (string.equals("")) throw new EmptyException("Значение не может быть пустым!");
            string.replaceAll(" ", ""); // Удаляем пробелы внутри значения
            string.trim(); // Удаляем пробелы по краям значения
            if (!string.contains("+") && !string.contains("-") && !string.contains("*") && !string.contains("/")) throw new UndefinedSimbolException("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
            String[] values = string.split("[+-/*]"); // Разделяем строку на значения
            if(values.length < 1) throw new ValueLimitException("Строка не является математической операцией!");
            else if(values.length > 2) throw new ValueLimitException("Можно расчитать только 2 значения!");

            for(int i=0;i<10;i++) // делаем перебор всех доступных значений
            {
                if (values[0].equals(intArray[i])) {
                    varType1 = false;
                    var1 = i+1; // Присваеваем значение
                }
                else if (values[0].equals(stringArray[i])) {
                    varType1 = true;
                    var1 = i + 1;
                }
                if(values[1].equals(intArray[i])) {
                    varType2 = false;
                    var2 = i+1; // Присваеваем значение
                }
                else if(values[1].equals(stringArray[i])) {
                    varType2 = true;
                    var2 = i+1;
                }
            }

            if(var1 == 0 || var2 == 0) throw new ValueNullException("Можно вводить только числа от 1 до 10 либо от I До X");
            if(varType1 != varType2) throw new ValueTypeException("Используются одновременно разные системы счисления!");
            if((string.contains("-") || string.contains("/")) && varType1 && var1 < var2) throw new ValueNullException("В римской системе нет отрицательных чисел");

            //Производим арифметические действия
            if(string.contains("+")) value = var1+var2;
            else if(string.contains("-")) value = var1-var2;
            else if(string.contains("*")) value = var1*var2;
            else if(string.contains("/")) value = var1/var2;

            if(varType1) // Если используется римская система счисление выводим ответ римскими цифрами
            {
                String stringValue = "";
                if(value >=10)
                {
                    int hundreds = (value - (value % 10)) / 10;
                    if(hundreds == 1  || value == 10) stringValue = "X";
                    else if(hundreds == 2 || value == 20) stringValue = "XX";
                    else if(hundreds == 3 || value == 30) stringValue = "XXX";
                    else if(hundreds == 4 || value == 40) stringValue = "XL";
                    else if(hundreds == 5 || value == 50) stringValue = "L";
                    else if(hundreds == 6 || value == 60) stringValue = "LX";
                    else if(hundreds == 7 || value == 70) stringValue = "LXX";
                    else if(hundreds == 8 || value == 80) stringValue = "LXXX";
                    else if(hundreds == 9 || value == 90) stringValue = "XC";
                    else if(value == 100) stringValue = "C";
                }
                int ten = value % 10;
                if(ten == 1 || value == 1) stringValue = stringValue + "I";
                else if(ten == 2 || value == 2) stringValue = stringValue + "II";
                else if(ten == 3 || value == 3) stringValue = stringValue + "III";
                else if(ten == 4 || value == 4) stringValue = stringValue + "IV";
                else if(ten == 5 || value == 5) stringValue = stringValue + "V";
                else if(ten == 6 || value == 6) stringValue = stringValue + "VI";
                else if(ten == 7 || value == 7) stringValue = stringValue + "VII";
                else if(ten == 8 || value == 8) stringValue = stringValue + "VIII";
                else if(ten == 9 || value == 9) stringValue = stringValue + "IX";
                System.out.println("Ответ: " + stringValue);
            }
            else System.out.println("Ответ: " + value);
        }
        catch(EmptyException | ValueTypeException | ValueLimitException | ValueNullException | ArrayIndexOutOfBoundsException | UndefinedSimbolException e)
        {
            System.out.println(e.getMessage()); // Выдаем сообщение в случае ошибки
        }
    }
}
