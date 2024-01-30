import React from 'react';

function Post({ key,title, author }) {
    return (
        <div className="colors">
            <h3>id: {key}</h3>
            <h3>title: {title}</h3>
            <h3>Author: {author}</h3>
        </div>
    );
}

export default Post;