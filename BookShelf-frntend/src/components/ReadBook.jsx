import React, { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";

const ReadBook = () => {
  const { id } = useParams();
  const [book, setBook] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchBook = async () => {
      try {
        const response = await fetch(`https://api.example.com/books/${id}`);
        if (!response.ok) {
          throw new Error("Failed to fetch book details");
        }
        const data = await response.json();
        setBook(data);
      } catch (err) {
        setError(err.message);
      } finally {
        setLoading(false);
      }
    };

    fetchBook();
  }, [id]);

  if (loading) return <p>Loading book...</p>;
  if (error) return <p>Error: {error}</p>;

  return (
    <div style={{ padding: "20px" }}>
      <button onClick={() => navigate(-1)}>Back</button>
      <h1>{book.title}</h1>
      <h3>By {book.author}</h3>
      <p>{book.content}</p>
    </div>
  );
};

export default ReadBook;