const { DataTypes } = require('sequelize');
const { sequelize } = require('../config/db');

const EvaluationCriterion = sequelize.define('evaluation_criterion', {
    id: {
        type: DataTypes.INTEGER,
        autoIncrement: true,
        primaryKey: true
    },
    name: {
        type: DataTypes.STRING,
        allowNull: false
    },
    description: {
        type: DataTypes.TEXT,
        allowNull: false
    },
    weight: {
        type: DataTypes.FLOAT,
        allowNull: false,
        validate: {
            min: 0,
            max: 100
        }
    },
    hackathonId: {
        type: DataTypes.INTEGER,
        allowNull: false,
        field: 'hackathon_id'
    }
}, {
    timestamps: true,
    tableName: 'evaluation_criteria'
});

module.exports = EvaluationCriterion;