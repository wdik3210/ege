import java.io.*;
import java.util.*;

public class ArchiveSolver {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\Админ\\Desktop\\26.txt";

        try {
            // Читаем файл
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String firstLine = reader.readLine();
            String[] firstLineParts = firstLine.trim().split("\\s+");

            int S = Integer.parseInt(firstLineParts[0]); // свободное место
            int N = Integer.parseInt(firstLineParts[1]); // количество файлов

            int[] a = new int[N];

            // Читаем все числа
            for (int i = 0; i < N; i++) {
                a[i] = Integer.parseInt(reader.readLine().trim());
            }
            reader.close();

            // Сортируем массив по возрастанию (пузырьковая сортировка, как в Паскале)
            for (int i = 0; i < N - 1; i++) {
                for (int j = i + 1; j < N; j++) {
                    if (a[i] > a[j]) {
                        int temp = a[i];
                        a[i] = a[j];
                        a[j] = temp;
                    }
                }
            }

            // Находим максимальное количество файлов
            int sum = 0;
            int maxi = 0; // индекс последнего добавленного файла (в 0-based)

            for (int i = 0; i < N; i++) {
                if (sum + a[i] <= S) {
                    sum += a[i];
                    maxi = i;
                } else {
                    break;
                }
            }

            // Количество файлов
            int count = maxi + 1;

            // Последний добавленный файл
            int lastFile = a[maxi];

            // Пытаемся заменить последний файл на максимально возможный
            for (int i = maxi + 1; i < N; i++) {
                if ((sum - lastFile) + a[i] <= S) {
                    sum = sum - lastFile + a[i];
                    lastFile = a[i];
                }
            }

            // Выводим результат
            System.out.println(count + " " + lastFile);

        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден по пути: " + filePath);
            System.out.println("Проверьте правильность пути к файлу.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла.");
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Ошибка в формате данных файла.");
            e.printStackTrace();
        }
    }
}