import React, { useState } from 'react';
import './App.css';

function App() {
  const [originalUrl, setOriginalUrl] = useState('');
  const [shortUrl, setShortUrl] = useState('');
  const [error, setError] = useState('');

  const handleShorten = async () => {
    setError('');
    setShortUrl('');
    if (!originalUrl) {
      setError('Please enter a URL!');
      return;
    }
    try {
      const response = await fetch('/api/shorten', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({  originalUrl })
      });
      if (!response.ok) {
        throw new Error('Failed to shorten URL.');
      }
      const data = await response.json();
      setShortUrl(data.shortUrl);
    } catch (err) {
      setError(err.message);
    }
  };

  return (
      <div className="container">
        <h1> Shorten Your Link! </h1>
        <input
            type="text"
            placeholder="Paste your long URL here..."
            value={originalUrl}
            onChange={e => setOriginalUrl(e.target.value)}
        />
        <button onClick={handleShorten}>Shorten</button>
        {shortUrl && (
            <div className="result">
              <p>Your short URL:</p>
              <a href={shortUrl} target="_blank" rel="noopener noreferrer">{shortUrl}</a>
            </div>
        )}
        {error && <div className="error">{error}</div>}
        <footer>
          <p>Built with ❤️ by Oseaghe</p>
        </footer>
      </div>
  );
}

export default App;