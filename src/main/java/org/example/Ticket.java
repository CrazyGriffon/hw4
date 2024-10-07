package org.example;

import java.time.OffsetDateTime;

/**
 * Можно изменять по своему усмотрению, не нарушая правила приоритезации очереди
 */
public class Ticket implements Comparable<Ticket> {

    private static int idSeq;

    /**
     * Автогенерация id
     */
    int id = ++idSeq;

    /**
     * Приоритеты типов:
     * 1) pension
     * 2) любые другие
     */
    TicketType type;

    /**
     * Приоритет для ранней регистрации
     */
    //добавляем id ко времени, чтобы визуально можно было проверить упорядоченность тикетов
    OffsetDateTime registerTime = OffsetDateTime.now().plusMinutes(id);

    public Ticket(TicketType type) {
        this.type = type;
    }

    @Override
    public int compareTo(Ticket o) {
        if (this == o) return 0;

        boolean a = TicketType.PENSION.equals(this.type);
        boolean b = TicketType.PENSION.equals(o.type);

        if (a && !b) {
            return 1;
        }
        if (!a && b) {
            return -1;
        }
        return -registerTime.compareTo(o.registerTime);
    }
}
