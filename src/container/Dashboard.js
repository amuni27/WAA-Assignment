import React, {useState} from 'react';
import Posts from '../component/Posts';
import axios from 'axios'
import PostDetails from "../component/PostDetail";


const Dashboard = () => {

    const [title, setTitle] = useState('');
    const [author, setAuthor] = useState('');
    const [content, setContent] = useState('');

    const handleTitleChange = (event) => {
        setTitle(event.target.value);
    };

    const handleAuthorChange = (event) => {
        setAuthor(event.target.value);
    };

    const handleContentChange = (event) => {
        setContent(event.target.value);
    };

    const handleSubmit = () => {
        // Now, you can use 'title', 'author', and 'content' as needed
        console.log('Title:', title);
        console.log('Author:', author);
        console.log('Content:', content);

        axios.post("http://localhost:8000/api/v1/post", {
            title,
            author,
            content,
        }).then(response => alert("submitted"))
            .catch(err => prompt(err.message))
    };


    return (
        <div>
            <h1>Dashboard</h1>
            <Posts/>
            <label htmlFor="titleInput">Title:</label>
            <input
                type="text"
                id="titleInput"
                value={title}
                onChange={handleTitleChange}
            />

            <label htmlFor="authorInput">Author:</label>
            <input
                type="text"
                id="authorInput"
                value={author}
                onChange={handleAuthorChange}
            />

            <label htmlFor="contentInput">Content:</label>
            <textarea
                id="contentInput"
                value={content}
                onChange={handleContentChange}
            />

            <button onClick={handleSubmit}>Submit</button>

        </div>
    );
};

export default Dashboard;