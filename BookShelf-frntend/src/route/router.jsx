
import Login from "../auth/login";
import SignUp from "../auth/SignUp";
import Layout from "../layout/Layout";
import AddBook from "../page/AddBook";
import Upload from "../page/Upload";
import ReadBook from "../components/ReadBook";
import AllBooks from "../components/Allbooks";
// import Books from "../components/Books"
// import HomePage from "../page/HomePage";
// import Books from "../components/Books";


const ROUTES = [
    {
        path: "/",
        element:<Layout/>,
        children:[
            {
                path:"/read",
                element:<ReadBook/>
            },
            {
                path: "/",
                element: <Login/>
            },
            {
                path:"/homepage",
                element:<AllBooks/>
            },
            {
                path: "/signup",
                element:<SignUp/>
            },
            {
                path: "/login",
                element:<Login/>
            },
            {
                path: "/addbook",
                element:<AddBook/>
            },
            {
                path:"/upload",
                element:<Upload/>
            },        
            // {
            //     path:"/books",
            //     element:<Books/>
            // },
            {
                path:"/allbooks",
                element:<AllBooks/>
            },
        ]

        
    },
 

]
export default ROUTES;