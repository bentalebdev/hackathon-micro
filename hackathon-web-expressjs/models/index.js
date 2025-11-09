const Hackathon = require('./Hackathon');
const EvaluationCriterion = require('./EvaluationCriterion');

// Define associations
Hackathon.hasMany(EvaluationCriterion, {
    foreignKey: 'hackathonId',
    as: 'criteria',
    onDelete: 'CASCADE'
});

EvaluationCriterion.belongsTo(Hackathon, {
    foreignKey: 'hackathonId',
    as: 'hackathon'
});

module.exports = {
    Hackathon,
    EvaluationCriterion
};