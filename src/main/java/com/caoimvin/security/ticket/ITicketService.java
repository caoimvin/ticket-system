package com.caoimvin.security.ticket;

import java.util.List;
import java.util.Optional;

public interface ITicketService {

    List<Ticket> getAllTickets();

    Ticket add(Ticket request);

    Optional<Ticket> findById(Long id);

    void delete(Long id);

    Ticket update(Long id, Ticket request);
}
