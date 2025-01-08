import React from 'react'
import { SiBookstack } from "react-icons/si";
import style from "../styles/header.module.css";
import { Link } from 'react-router-dom';
import 'animate.css';
import { IoLibrary } from "react-icons/io5";


export default function Header() {
  const books=[
    { id: 1, title: "Book Title 1", author: "Author 1" },
    { id: 2, title: "Book Title 2", author: "Author 2" },
  
]
const [searchTerm, setSearchTerm] = React.useState("");

const handleSearch = (event) => {
  setSearchTerm(event.target.value);
}

const filteredBooks = books.filter((book) =>
  book.title.toLowerCase().includes(searchTerm.toLowerCase())
);
  
  return (
    <div>
         <div>
          <SiBookstack color='black' size="3rem"/> <IoLibrary color='black'  />
         </div>
       <div className={style.nav}>
       <div className={style.navBar}>
       <Link to = {"/login"} className={style.book}><h2>Login</h2></Link>
       <Link to = {"/signup"} className={style.book}><h2>SignUp</h2></Link>
          {/* <li>Favorite</li> */}
        </div>
        <form id = {style.search} >
                <input placeholder='Search For a book....' name="search" type="search" className={style.search} valie={searchTerm} onChange={handleSearch}/>
                </form> 
            

      </div>
      <div>
      
      </div>
      

    </div>
   
       
        
       
   
  )
}
