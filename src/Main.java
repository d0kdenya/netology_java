//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Calculator calc =  Calculator.instance.get();

        int a = calc.plus.apply(1, 2); // 3
        int b = calc.minus.apply(1, 1); // 0



        /* Ошибка возникает при попытке деления на ноль
         * А именно: 3 / 0
         * int c = calc.devide.apply(a, b);
         *
         * Проблема решается путём обработки исключения
         */

        // Решение:
        try {
            int c = calc.devide.apply(a, b);
            calc.println.accept(c);
        } catch (ArithmeticException e) {
            System.out.println("Ошибка: Деление на ноль недопустимо!");
        }
    }
}