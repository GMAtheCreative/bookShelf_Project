import React from 'react'
import  style from "../styles/sidebar.module.css"
import { IoIosHome } from "react-icons/io";
import { FaSearch } from "react-icons/fa";
import { MdAssignmentAdd } from "react-icons/md";
import { Link } from 'react-router-dom';
import { MdOutlineFavorite } from "react-icons/md";
import { GiBookshelf } from "react-icons/gi";
import { ImBooks } from "react-icons/im";
import { IoIosCloudUpload } from "react-icons/io";

export default function SiderBar() {
  return (
    <div className={style.wrap}>
      <div className={style.mid}>
      <h1><b>My <span className={style.hightlight}>Book</span><br />  Shelf</b></h1>
      <ul>
      <Link to = {"/homepage"}className={style.book}><li><GiBookshelf />All Books</li></Link>
      <Link to ={"/upload"}className={style.book}><li><IoIosCloudUpload />Upload a Book </li></Link>
      <Link to ={"/addbook"}className={style.book}><li><MdAssignmentAdd />Add a Book </li></Link>
      
     
      </ul>

      
      </div>
      <div className={style.last}>
        <h6>About</h6>
        <h6>Support</h6>
        <h6>Terms & Condition</h6>
      </div>
      

      
    </div>
    
    
  )
}



