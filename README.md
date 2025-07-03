# URL Shortener

A simple and efficient URL shortener application that allows users to convert long URLs into short, easy-to-share links.

## Features

- Shorten any valid URL to a concise, shareable link
- Redirects short URLs to the original long URL
- Basic analytics: track the number of times a URL has been accessed
- Optional user registration and authentication
- RESTful API for programmatic URL shortening
- Customizable short links (optional)



## Getting Started


### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/url-shortener.git
   cd url-shortener
   ```

2. Install dependencies:
   ```bash
   npm install
   # or
   yarn install
   ```

3. Create a `.env` file and set your environment variables:
   ```
   PORT=3000
   MONGODB_URI=mongodb://localhost:27017/url-shortener
   BASE_URL=http://localhost:3000
   ```

4. Start the server:
   ```bash
   npm start
   # or
   yarn start
   ```

### Usage

#### Web Interface

1. Enter your long URL in the input box.
2. Click "Shorten" to receive a short link.
3. Share the short link with others.

#### API

- **Shorten a URL**
  ```
  POST /api/shorten
  {
      "originalUrl": "https://www.example.com/very/long/url"
  }
  ```
  **Response:**
  ```json
  {
      "shortUrl": "http://localhost:3000/abcd12"
  }
  ```

- **Redirect**
  ```
  GET /abcd12
  ```
  Redirects to the original URL.

### Configuration

- You can change the default port and database connection string in the `.env` file.

## Contributing

Contributions are welcome! Please open an issue or submit a pull request.

## License

[MIT](LICENSE)

## Acknowledgements
- [Nanoid](https://github.com/ai/nanoid) (for unique ID generation)
