
import React, { useState } from "react";
import CustomButton from "../reuseables/CustomButton";
import style from "../styles/login.module.css";
import { Link } from "react-router-dom";


const Login = () =>{
  // const afeezImage = "https://res.cloudinary.com/dvllqofwn/image/upload/v1732263394/cld-sample.jpg";
    
    const userDetails = {
        username: "",
        password:"",
    };

    const [data, setData] = useState(userDetails)

    function handleChange(event){
        console.log(event)
        const {name, value}= event.target
        // setData((prevData)=>({...prevData, [name]:value}));
        setData((prevData)=>{
          return {...prevData,[name]:value}
        })

    }

    return(
        <div className={style.wrapper}>

          <div className={style.shelf}>
            <h1>My Book Shelf</h1>
          </div>
          <div className={style.first}>
          <h2>Welcome Back !</h2>
          <h4>Sign in to continue to yourDigital Library</h4>
          </div>
    
      <form action="">
        <div className={style.second}>
        <div>
          <input 
            type="text"
            name="username"
            placeholder="Enter Username"
            className={style.input}   
            onChange={handleChange}
            // value={data.username}
            required
          />
        </div>
        <div>
          <input
            type="password"
            name="password"
            placeholder="Enter Password"
            className={style.input}
            onChange={handleChange}
            required
          />
        </div>

        </div>
        
        <Link to = {"/homepage"}> <CustomButton style = {style.btn} type = "Login" textContent= "login"/></Link>
       
      </form>
      <div className={style.both}>
        <span className  = {style.mine}> New User?</span>
          <span className={style.your}><Link to={"/signup"}>Register Here</Link></span>
          {/* <img src={afeezImage} alt="afeez" className={style.image}/> */}
      </div>
    </div>
    )
}
export default Login;