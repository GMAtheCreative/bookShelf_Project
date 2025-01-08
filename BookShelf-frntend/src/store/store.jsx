import {configureStore} from "@reduxjs/toolkit";
import { setupListeners } from "@reduxjs/toolkit/query";
import { bookApi } from "../service/bookApi";

export const store = configureStore({
    reducer:{
        [bookApi.reducerPath]:bookApi.reducer,
    },
        
    middleware:(getDefaultMiddleware)=>{
        return getDefaultMiddleware().concat(bookApi.middleware)
    }
        
});

setupListeners(store.dispatch);

