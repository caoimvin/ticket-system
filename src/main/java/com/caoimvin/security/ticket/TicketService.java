package com.caoimvin.security.ticket;

import com.caoimvin.security.exception.NotAuthorizedException;
import com.caoimvin.security.exception.TicketNotFoundException;
import com.caoimvin.security.exception.UserNotFoundException;
import com.caoimvin.security.user.User;
import com.caoimvin.security.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TicketService implements ITicketService {

    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;

    @Override
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public Ticket add(Ticket request) {
        Ticket ticket = new Ticket();
        ticket.setTitle(request.getTitle());
        ticket.setDescription(request.getDescription());
        ticket.setStatus(request.getStatus());
        ticket.setUser(request.getUser());
        return ticketRepository.save(ticket);
    }

    @Override
    public Optional<Ticket> findById(Long id) {
        return Optional.ofNullable(ticketRepository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException("No ticket found with id: " + id)));
    }

    @Override
    public void delete(Long id) {
        Optional<Ticket> ticket = findById(id);
        ticket.ifPresent(theTicket -> ticketRepository.deleteById(theTicket.getId()));
    }

    @Override
    public Ticket update(Long id, Ticket request) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException("No ticket found with id: " + id));
        ticket.setTitle(request.getTitle());
        ticket.setDescription(request.getDescription());
        ticket.setStatus(request.getStatus());
        if (request.getUser() != null) {
            Optional<User> user = userRepository.findById(request.getUser().getId());
            user.ifPresent(ticket::setUser);
        }
        return ticketRepository.save(ticket);
    }

    public List<Ticket> getAllTicketsByUserId(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUser = authentication.getName();
            User user = userRepository.findByEmail(currentUser)
                    .orElseThrow(() -> new UserNotFoundException("No user found with email: " + currentUser));
            if (!Objects.equals(id, user.getId())) throw new NotAuthorizedException("Not authorized for user with id: " + id);
        } else {
            throw new NotAuthorizedException("No authenticated user");
        }
        return ticketRepository.findTicketsByUserId(id);
    }
}
