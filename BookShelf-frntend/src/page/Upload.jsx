import React from 'react'
import { useState } from 'react'
import { useNavigate } from 'react-router-dom';
import style from "../styles/upload.module.css"

export default function Upload() {
    const navigate = useNavigate();
  const details ={
    title:"",
    author:"",
  }
  const [data, setData]= useState(details)

  function handleInputChange (event){
    const{name,value}=event.target
    setData((prevData)=>{
      return {...prevData,[name]:value}

    })

  };
  const handleSubmit=(event)=>{
    event.preventDefault();
    
    console.log("Book data submitted:");
    alert("Book added successfully!");
    navigate("/home")
  }

  return (
    <div>
        <div className={style.top}>
        <h1><b>My <span className={style.hightlight}>Book</span><br />  Shelf</b></h1>
            </div> 
       
          <div className={style.wrap}>
          <div className={style.all}>
        <h1>Upload Book</h1>
        <form  onSubmit={handleSubmit}>
            <div>
            <input type="text" name="title" placeholder='Book Title' value={data.title}onChange={handleInputChange}className={style.put} required/>

            </div>
            <div>
                <input type="text" name="author" placeholder="Author"value={data.author}onChange={handleInputChange}className={style.put} required />
            </div>
            <button type='submit' className={style.btn}>Uplaod Book</button>
          
            
        </form>
        </div>
        </div>
    </div>
  )
}
