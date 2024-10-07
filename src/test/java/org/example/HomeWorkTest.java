package org.example;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class HomeWorkTest {

    HomeWork homeWork = new HomeWork();

    @Test
    void managerFabric() {
        TicketManager ticketManager = homeWork.managerFabric();
        List<Ticket> tickets = Arrays.asList(
                new Ticket(TicketType.OTHER),
                new Ticket(TicketType.PENSION),
                new Ticket(TicketType.INVALID),
                new Ticket(TicketType.PENSION),
                new Ticket(TicketType.PENSION),
                new Ticket(TicketType.STUDENT),
                new Ticket(TicketType.OTHER),
                new Ticket(TicketType.OTHER),
                new Ticket(TicketType.PENSION)
        );
        //будем добавлять одно и то же множество в разном порядке
        Collections.shuffle(tickets);
        for (Ticket ticket : tickets) {
            ticketManager.add(ticket);
        }

        //сортируем билеты библиотечным методом
        tickets.sort(Collections.reverseOrder());

        //сортируем билеты с помощью heapsort
        List<Ticket> heapSortedTickets = new ArrayList<>();
        for (int i = 0; i < tickets.size(); ++i) {
            heapSortedTickets.add(ticketManager.next());
        }

        //проверяем равенство ссылок на каждой позиции
        for (int i = 0; i < tickets.size(); ++i) {
            assertSame(tickets.get(i), heapSortedTickets.get(i));
        }
    }

    @Test
    void nextNullIfQueueIsEmpty() {
        TicketManager ticketManager = homeWork.managerFabric();
        assertNull(ticketManager.next());
    }

    @Test
    void check() {
        for (int i = 2; i < 1000; ++i) {
            //используем generateQueue1, потому что generateQueue иногда генерирует повторы, и check отрабатывает неверно
            List<Integer> expectedQueue = generateQueue1(1, i);
            List<String> pairs = generatePairs(expectedQueue);
            assertEquals(expectedQueue, homeWork.check(pairs));
        }
    }

    private List<String> generatePairs(List<Integer> expectedQueue) {
        List<String> pairs = new ArrayList<>();
        Function<Integer, Integer> map = (x) -> (x < 0 || x >= expectedQueue.size()) ? 0 : expectedQueue.get(x);

        for (int i = 0;
             i < expectedQueue.size(); i++) {
            pairs.add(String.format("%d:%d", map.apply(i - 1), map.apply(i + 1)));
        }
        Collections.shuffle(pairs);
        return pairs;
    }

    private List<Integer> generateQueue(int seed, int length) {
        return new Random(seed)
                .ints(1, length * 100)
                .limit(length)
                .boxed()
                .collect(Collectors.toList());
    }

    private List<Integer> generateQueue1(int seed, int length) {
        List<Integer> s = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            s.add(i + 1);
        }
        Collections.shuffle(s, new Random(seed));
        return s;
    }
}