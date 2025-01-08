import React from 'react'
import style from "../styles/addbook.module.css";
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { makeApiCall } from '../service/addBookApi';

export default function AddBook() {

  const navigate = useNavigate();
  const details ={
    title:"",
    author:"",
    description:"",

  };
  const [data, setData]= useState(details)

  function handleChange (event){
    const{name,value}=event.target
    setData((prevData)=>{
      return {...prevData,[name]:value}

    })

  };
    
  const handleSubmit=(event)=>{
    event.preventDefault();
    
    console.log("Book data submitted:");
    alert("Book added successfully!");
    navigate("/homepage")
    if (data.title.trim().length <3){
      alert("book title must be at least 3 characters long.");
      return;
    }
    if (data.author.trim().length <3){
      alert("Author name must be at least 3 characters long.");
      return;
  }
  makeApiCall(data);
}

  
  return (
    <div >
      <div>
          <h1><b>My <span className={style.hightlight}>Book</span><br />  Shelf</b></h1>
      </div>
     
       <div className={style.wrap}>
      <div className={style.all}>
      <h1>Add a Book</h1>

        <form onSubmit={handleSubmit}>
            <div >
                <input type="text"  name="title"placeholder='Book Title'value={data.title} onChange={handleChange}className={style.put} required />
            </div>  
            <div>
                <input type="text" name="author"  placeholder='Author' value={data.author} onChange={handleChange}className={style.put} required  />
                </div> 
                <div>
                    <textarea  name = "description"placeholder='Description ' value={data.description} onChange={handleChange}className={style.put} required />    
                </div>
                <button type="submit"className={style.btn}>Add Book</button>          
        </form >
        
      
        
      </div>
        
    </div>

    </div>
   
  )
}
