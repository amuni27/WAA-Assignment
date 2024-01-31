import React, {useState, useEffect} from 'react';
import axios from "axios";

const PostDetails = ({postId}) => {
    const [post, setPost] = useState(null);

    useEffect(() => {
        axios.get(`http://localhost:8000/api/v1/post/${postId}`)
            .then(response => setPost(response.data))
            .catch(error => console.error('Error fetching post details:', error));
    }, [postId]);

    const handleDelete = () => {
        axios.delete(`http://localhost:8000/api/v1/post/${postId}`)
            .then((response) => {
                alert(response.data)
            })
            .catch(error => console.error('Error deleting post:', error));
    };

    return (
        <div>
            <h2 >Post Details</h2>
            {post && (
                <div style={{backgroundColor: "gray" ,width:"500px", height:"200px", alignContent: "center"}}>
                    <h3>Id: {post.id}</h3>
                    <h3>Title: {post.title}</h3>
                    <p>Content: {post.content}</p>
                    <p>Author: {post.author}</p>
                    <button onClick={handleDelete}>Delete</button>
                </div>
            )}
        </div>
    );
};

export default PostDetails;