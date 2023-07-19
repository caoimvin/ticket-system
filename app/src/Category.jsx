import { useEffect, useState } from 'react';
import Ticket from './Ticket';

function Category({ title, tickets }) {
    const [ticketCount, setTicketCount] = useState(0)
  useEffect(() => {
    if (tickets) setTicketCount(tickets.length)
  }, [tickets]);

  return (
    <div className='category'>
      <div className='categoryTitle'>
        {title}
        <span>{` (${ticketCount})`}</span>
      </div>
      <div className='categoryBody'>
        {tickets &&
          tickets.map((ticket) => (
            <Ticket
              key={ticket.id}
              id={ticket.id}
              title={ticket.title}
              description={ticket.description}
              status={ticket.status}
              comments={ticket.comments}
              createdAt={ticket.createdAt}
            />
          ))}
      </div>
    </div>
  );
}

export default Category;
