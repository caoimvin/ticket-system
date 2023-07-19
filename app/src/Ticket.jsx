function Ticket({ id, title, description, status, comments, createdAt }) {
  return (
    <div className='ticket'>
      <div className='ticketHeader'>
        <div className='status'>{status}</div>
        <div className='date'>{createdAt}</div>
      </div>
      <div className='ticketBody'>
        <div className='title'>{title}</div>
        <div className='description'>{description}</div>
      </div>
      <div className='ticketFooter'>
        <div className='number'>{id}</div>
        <div className='comments'>{comments.length}</div>
      </div>
    </div>
  );
}

export default Ticket;
