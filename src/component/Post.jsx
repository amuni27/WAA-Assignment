import React, {useState} from 'react';

function Post({id, title, author,onClick}) {

    return (
        <div className="colors" onClick={onClick}>
            <h3 style={{color: "blue"}}>id: {id}</h3>
            <h3>title: {title}</h3>
            <h3>Author: {author}</h3>
        </div>
    );
}

export default Post;