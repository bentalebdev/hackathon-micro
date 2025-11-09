const { Sequelize } = require('sequelize');
require('dotenv').config();

// Create Sequelize instance with environment variables
const sequelize = new Sequelize(
    process.env.DB_NAME || 'hackathon_db',
    process.env.DB_USER || 'postgres',
    process.env.DB_PASSWORD || 'root',
    {
        host: process.env.DB_HOST || 'localhost',
        port: process.env.DB_PORT || 5432,
        dialect: 'postgres',
        logging: process.env.NODE_ENV === 'development' ? console.log : false,
        pool: {
            max: 5,
            min: 0,
            acquire: 30000,
            idle: 10000
        }
    }
);

// Connect to database
const connectDB = async () => {
    try {
        await sequelize.authenticate();
        console.log('✅ Database connection established successfully');

        // Sync models with database (creates tables if they don't exist)
        await sequelize.sync({ alter: process.env.NODE_ENV === 'development' });
        console.log('✅ Database models synchronized');

    } catch (error) {
        console.error('❌ Unable to connect to database:', error.message);
        process.exit(1);
    }
};

module.exports = { sequelize, connectDB };