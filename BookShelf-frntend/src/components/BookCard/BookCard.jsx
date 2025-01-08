import React from 'react'
import styles from "./index.module.css";
import { Link } from 'react-router-dom';

export default function BookCard({ book, onRead, onDelete, onFavorite,onEdit }) {
  return (
    <div className={styles.card}>
      <div className={styles.title}>
      <h3>{book?.title || 'Untitled'}</h3>
      <p>{book?.author || 'Unknown Author'}</p>

      </div>
     
      <div className={styles.wrapper}>
      <button>Favorite: {book.isFavorite ? "Yes" : "No"}</button>
        <Link to ={"/read"}><button onClick={() => onRead(book)}>Read</button></Link>
        <button onClick={() => onDelete(book.id)}>Delete</button>
        <button onClick={() => onFavorite(book.id)}>
        {book.isFavorite ? "Unfavorite" : "Favorite"} </button>
        </div>
      </div>

  );
}
