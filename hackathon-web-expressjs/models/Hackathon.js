const { DataTypes } = require('sequelize');
const { sequelize } = require('../config/db');

const Hackathon = sequelize.define('hackathon', {
    id: {
        type: DataTypes.INTEGER,
        autoIncrement: true,
        primaryKey: true
    },
    title: {
        type: DataTypes.STRING,
        allowNull: false
    },
    theme: {
        type: DataTypes.STRING,
        allowNull: false
    },
    description: {
        type: DataTypes.TEXT,
        allowNull: true
    },
    rules: {
        type: DataTypes.TEXT,
        allowNull: true
    },
    startDate: {
        type: DataTypes.DATE,
        allowNull: false
    },
    endDate: {
        type: DataTypes.DATE,
        allowNull: false
    },
    organizerId: {
        type: DataTypes.INTEGER,
        allowNull: false
    },
    location: {
        type: DataTypes.STRING,
        allowNull: true
    },
    mode: {
        type: DataTypes.ENUM('online', 'in-person', 'hybrid'),
        allowNull: false,
        defaultValue: 'online'
    }
}, {
    timestamps: true,
    tableName: 'hackathons'
});

module.exports = Hackathon;