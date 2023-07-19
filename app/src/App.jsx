import { useEffect, useState } from 'react';
import reactLogo from './assets/react.svg';
import viteLogo from '/vite.svg';
import './App.css';
import axios from 'axios';
import Ticket from './Ticket';
import Category from './Category';

function App() {
  const [count, setCount] = useState(0);
  const [tickets, setTickets] = useState({});
  const [loaded, setLoaded] = useState(false);

  const CATEGORIES = ['OPEN', 'PENDING', 'HOLD', 'SOLVED', 'CLOSED']

  useEffect(() => {
    axios
      .get(`http://localhost:8000/api/v1/tickets`)
      .then((res) => filterTickets(res.data))
      .catch((err) => console.log(err))
      .finally(() => setLoaded(true));
  }, []);

  function filterTickets(tickets) {
    const obj = {}
    tickets.forEach(ticket => {
      if (obj[ticket.status]) {
        obj[ticket.status].push(ticket)
      } else {
        obj[ticket.status] = [ticket]
      }
    })
    setTickets(obj)
  }

  return (
    <>
      {loaded && (
        <div className='categories'>
          {CATEGORIES.map(category => <Category key={category} title={category} tickets={tickets[category]} />)}
        </div>
      )}
    </>
  );
}

export default App;
