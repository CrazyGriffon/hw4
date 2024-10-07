package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.*;


public class HomeWork {

    /**
     * <h1>Задание 1.</h1>
     * Требуется реализовать интерфейс TicketManager в соответствии с JavaDoc описанием.
     * Реализации очередей из стандартной библиотеки не используем.
     */
    public TicketManager managerFabric() {
        return new TicketManagerImpl();
    }


    /**
     * Задача со звездочкой (опционально)
     * <br/>
     * На вход строки:
     * номер_впереди : номер_сзади
     * Если впереди или сзади никого нет - то 0, для первого и последнего в очереди.
     * На выход нужно восстановить порядок номеров в очереди.
     * <p>
     * В тестах генератор тестовых данных (очереди и пары).
     *
     * @see <a href="https://codeforces.com/problemset/problem/490/B?locale=ru">https://codeforces.com/problemset/problem/490/B?locale=ru</a>
     */
    public List<Integer> check(List<String> records) {
        //для числа a на позиции i в очереди храним числа на позициях i - 2, i + 2
        Map<Integer, Pair<Integer, Integer>> map = new HashMap<>();

        for (String s : records) {
            String[] parts = s.split(":");
            int a = Integer.parseInt(parts[0]);
            int b = Integer.parseInt(parts[1]);

            Pair<Integer, Integer> entryA = map.getOrDefault(a, new Pair<>(null, null));
            entryA.setSecond(b);
            map.put(a, entryA);

            Pair<Integer, Integer> entryB = map.getOrDefault(b, new Pair<>(null, null));
            entryB.setFirst(b);
            map.put(b, entryB);
        }

        int n = records.size();
        Integer[] result = new Integer[n];

        //число, у которого first=null, является первым в очереди
        Integer curr = map.entrySet()
                .stream()
                .filter(x -> x.getValue().first == null)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElseThrow();

        //заполняем нечетные позиции
        for (int i = 0; i < n; i += 2) {
            result[i] = curr;
            curr = map.get(curr).second;
        }

        //число, у которого предыдущее(first) = 0, является вторым в очереди
        //заполняем четные позиции
        curr = map.get(0).second;
        for (int i = 1; i < n; i += 2) {
            result[i] = curr;
            curr = map.get(curr).second;
        }

        return Arrays.asList(result);
    }

    @AllArgsConstructor
    @Setter
    @Getter
    @ToString
    public static class Pair<T1, T2> {
        private T1 first;
        private T2 second;
    }
}
