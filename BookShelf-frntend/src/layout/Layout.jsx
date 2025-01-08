import React from "react";
import { Outlet } from "react-router-dom";
import Header from "../components/Header";
import SideBar from "../components/SiderBar";
// import Footer from "../components/Footer";
import Top from "../components/Top";

const Layout = ()=>{
  return(
    <>
        <Top/>
        
        <div>
        {/* <Header/> */}
        {/* <SideBar/> */}
        <Outlet/>
        </div>
      
        {/* <Footer/> */}
        
    </>
  )
           
           

 
}
export default Layout;