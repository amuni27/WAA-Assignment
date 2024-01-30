import React from 'react';
import Post from './Post';

const Posts = ({ firstPostTitle }) => {
    const postsData = [
        { id: 111, title: firstPostTitle, author: 'John' },
        { id: 112, title: 'MIU', author: 'Dean' },
        { id: 113, title: ' Enjoy Life', author: 'Jasmine ' },
    ];

    return (
        <div>
            <h2>All Posts</h2>
            {postsData.map((post) => (
                <Post key={post.id} title={post.title} author={post.author} />
            ))}
        </div>
    );
};

export default Posts;