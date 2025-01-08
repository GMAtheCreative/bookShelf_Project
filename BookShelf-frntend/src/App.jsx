import { createBrowserRouter, RouterProvider } from "react-router-dom";
import ROUTES from "./route/router";
import backgroundImage from "./assets/background2.avif"
import "./App"



const routes = createBrowserRouter([...ROUTES])

function App(){
  const style = {
    backgroundImage: `url(${backgroundImage})`,
    backgroundSize: 'cover', 
    backgroundPosition: 'center',
    backgroundAttachment: 'fixed',
    height: '165vh',
    width:'100%' ,
    margin: 0,
   
  };
  return(
    <div style={style}>
      <RouterProvider router={routes} />
    </div>
  )
}
export default App