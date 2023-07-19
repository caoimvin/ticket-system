package com.caoimvin.security.ticket;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @GetMapping
    public ResponseEntity<List<Ticket>> getAllTickets() {
        return ResponseEntity.ok(ticketService.getAllTickets());
    }

    @GetMapping("/{id}")
    public Optional<Ticket> findById(@PathVariable("id") Long id) {
        return ticketService.findById(id);
    }

    @PostMapping
    public ResponseEntity<Ticket> add(@RequestBody Ticket request) {
        return new ResponseEntity<>(ticketService.add(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ticket> update(@PathVariable("id") Long id, @RequestBody Ticket ticket) {
        return new ResponseEntity<>(ticketService.update(id, ticket), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        ticketService.delete(id);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<Ticket>> getAllTicketsByUserId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(ticketService.getAllTicketsByUserId(id));
    }
}
