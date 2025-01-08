

export const makeApiCall = async (title, author, genre) => {
    const url = "http://your-api-url.com/api/endpoint"; 
    const payload = {
      title: title,
      author: author,
      genre: genre,
    };
  
    try {
      const response = await fetch(url, {
        method: "POST",
        headers: {
          "Content-Type": "application/json", 
        },
        body: JSON.stringify(payload),
      });
  
      if (!response.ok) {
        throw new Error(`Error: ${response.status}`);
      }
  
      const data = await response.json();
      console.log("Response data:", data);
    } catch (error) {
      console.error("API call failed:", error);
    }
  };