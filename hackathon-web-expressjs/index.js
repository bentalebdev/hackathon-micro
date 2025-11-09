const express = require('express');
const cors = require('cors');
require('dotenv').config();

const { connectDB } = require('./config/db');
const hackathonRoutes = require('./routes/hackathonRoutes');
const evaluationCriterionRoutes = require('./routes/evaluationCriterionRoutes');

const app = express();
const PORT = process.env.PORT || 3000;

// Middleware
app.use(cors());
app.use(express.json());
app.use(express.urlencoded({ extended: true }));

// Logging middleware
app.use((req, res, next) => {
    console.log(`${new Date().toISOString()} - ${req.method} ${req.path}`);
    next();
});

// Routes
app.use('/api/hackathons', hackathonRoutes);
app.use('/api', evaluationCriterionRoutes);

// Health check endpoint
app.get('/health', (req, res) => {
    res.status(200).json({
        status: 'healthy',
        service: 'hackathon-api',
        timestamp: new Date().toISOString()
    });
});

// 404 handler
app.use((req, res) => {
    res.status(404).json({ message: 'Route not found' });
});

// Error handler
app.use((err, req, res, next) => {
    console.error(err.stack);
    res.status(500).json({
        message: 'Something went wrong!',
        error: process.env.NODE_ENV === 'development' ? err.message : undefined
    });
});

// Connect to database and start server
connectDB().then(() => {
    app.listen(PORT, () => {
        console.log(`🚀 Hackathon API running on http://localhost:${PORT}`);
        console.log(`📊 Environment: ${process.env.NODE_ENV || 'development'}`);
    });
});

module.exports = app;