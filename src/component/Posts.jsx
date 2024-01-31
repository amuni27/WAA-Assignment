import React, {useEffect, useState} from 'react';
import Post from './Post';
import axios from 'axios';
import PostDetails from "./PostDetail";

const Posts = () => {
    const [postsData, setPostData] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [selectedPostId, setSelectedPostId] = useState(null);


    const handlePostClick = (postId) => {
        // Set the selected post ID when a post is clicked
        setSelectedPostId(postId);
    };
    useEffect(() => {
        axios.get("http://localhost:8000/api/v1/post")
            .then(response => {
                console.log(response.data);
                setPostData(response.data);
                setLoading(false);
            })
            .catch(err => {
                console.log(err);
                setError(err);
                setLoading(false);
            });
    }, []);


    if (loading) {
        return <p>Loading...</p>;
    }

    if (error != null) {
        return <p>Error: {error.message}</p>;
    }

    return (
        <div>
            <h2>All Posts</h2>
            {postsData && postsData.map((post) => (
                <Post  id={post.id} title={post.title} author={post.author} onClick={() => handlePostClick(post.id)}/>
            ))}
            {selectedPostId && <PostDetails postId={selectedPostId} />}
        </div>
    );
};

export default Posts;
