import './App.css';
import Articles from "../src/components/Articles";
import Header from './components/Header';
import { useEffect, useState } from 'react';

function App() {
  const [user, setUser] = useState(null);

  useEffect(() => {
    fetch("/api/profile")
      .then(response => {
        if (response.ok) {
          return response.json();
        }
        if (response.status === 404) {
          return {
            username: "Anonymous",
            hobbies: []
          }
        }
        return null;
      })
      .then((profile) => {
        setUser(profile);
      });
  }, []);

  const requestParams = window.location.search;

  const content = user ? <Articles /> : <h2 className="text-xl">Please log in to see your articles</h2>;

  return (
    <div className="App">
      <Header user={user} />

      <main className="mt-64 flex items-center flex-col gap-4">
        {requestParams.includes("?logout") &&
          <aside className="text-lg px-20 py-6 mb-8 bg-green-100">You have been successfully logged out</aside>
        }
        {content}
      </main>
    </div>
  );
}

export default App;
