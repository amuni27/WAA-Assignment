import React, { useState } from 'react';
import Posts from './Posts';

const Dashboard = () => {
    const [firstPostTitle, setFirstPostTitle] = useState('Happiness');

    const handleTitleUpdate = (newTitle) => {
        setFirstPostTitle(newTitle);
    };

    return (
        <div>
            <h1>Dashboard</h1>
            <Posts firstPostTitle={firstPostTitle} />
            <input
                type="text"
                value={firstPostTitle}
                onChange={(e) => handleTitleUpdate(e.target.value)}
            />
            <button onClick={() => handleTitleUpdate('Updated Title')}>Update Title</button>

        </div>
    );
};

export default Dashboard;