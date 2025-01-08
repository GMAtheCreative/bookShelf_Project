import { createApi, fetchBaseQuery } from "@reduxjs/toolkit/query";

const BASEURL = "http://your-api-url.com/api"; 


export const bookApi = createApi({
  reducerPath: "bookApi",
  baseQuery: fetchBaseQuery({ baseUrl: BASEURL }),
  endpoints: (builder) => ({
    getAllBooks: builder.query({
        query: (id) => `books/${id}`,
    }),
    getBookById: builder.query({
      query: (id) => `books/${id}`,
    }),
    deleteBook: builder.mutation({
      query: (id) => ({
        url: `books/${id}`,
        method: "DELETE",
      }),
    }),
    addBook: builder.mutation({
      query: (newBook) => ({
        url: "books",
        method: "POST",
        body: newBook,
        headers: {
          "Content-Type": "application/json",
        },
      }),
    }),
  }),
});

export const {
  useGetAllBooksQuery,
  useGetBookByIdQuery,
  useDeleteBookMutation,
  useAddBookMutation,
} = bookApi;