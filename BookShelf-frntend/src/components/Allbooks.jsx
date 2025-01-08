import React from 'react';
import BookCard from './BookCard/BookCard';
import Header from './Header';
import SiderBar from './SiderBar';
import style from "../styles/allbook.module.css"
import { useState, useEffect } from 'react';

const AllBooks = () => {
  const books = [
    { id: 1, title: 'The Great Gatsby', author: 'F. Scott Fitzgerald' },
    { id: 2, title: 'To Kill a Mockingbird', author: 'Harper Lee' },
    { id: 3, title: '1984', author: 'George Orwell' },
  ];
  return (
    <>
      <Header />
      <SiderBar />
      
      <div className={style.nam}>
      
        {books.map((book) => (
          <BookCard key={book.id} book={book} />
        ))}
      </div>
    </>
  );
};

export default AllBooks;






















// const AllBooks = () => {
//     const [books, setBooks] = useState([]); 
//     const [loading, setLoading] = useState(true); 
//     const [error, setError] = useState(null); 
  
//     useEffect(() => {
//       const fetchBooks = async () => {
//         try {
//           const response = await fetch('https://api.example.com/books'); 
//           if (!response.ok) {
//             throw new Error('Failed to fetch books');
//           }
//           const data = await response.json();
  
         
//           const updatedBooks = data.map(book => ({
//             ...book,
//             isRead: false,
//             isFavorite: false,
//           }));
  
//           setBooks(updatedBooks); 
//         } catch (err) {
//           setError(err.message); 
//         } finally {
//           setLoading(false); 
//         }
//       };
  
//       fetchBooks();
//     }, []); 
  
    
//     const handleRead = (book) => {
//       if (book.url) {
//         window.open(book.url, "_blank");
//       } else {
//         alert(`Content for "${book.title}" is unavailable.`);
//       }
//     };

//     const handleDelete = (bookId) => {
//       setBooks((prevBooks) => prevBooks.filter((book) => book.id !== bookId));
//     };
  
//     const handleFavorite = (bookId) => {
//       setBooks((prevBooks) =>
//         prevBooks.map((book) =>
//           book.id === bookId ? { ...book, isFavorite: !book.isFavorite } : book
//         )
//       );
//     };
  
//     if (loading) return <p>Loading books...</p>;
//     if (error) return <p>Error: {error}</p>;
  
//     return (
//       <>
//         <Header />
//         <SiderBar />
//         <div className={style.container}>
//           {books.length === 0 ? (
//             <p>No books available.</p>
//           ) : (
//             books.map((book) => (
//               <BookCard
//                 key={book.id}
//                 book={book}
//                 onRead={handleRead}
//                 onDelete={handleDelete}
//                 onFavorite={handleFavorite}
//               />
//             ))
//           )}
//         </div>
//       </>
//     );
//   };
  
//   export default AllBooks;