const { EvaluationCriterion } = require('../models');

// Get all criteria for a hackathon
exports.getCriteriaByHackathon = async (req, res) => {
    try {
        const { hackathonId } = req.params;
        const criteria = await EvaluationCriterion.findAll({
            where: { hackathonId }
        });
        res.status(200).json(criteria);
    } catch (error) {
        res.status(500).json({
            message: 'Error fetching criteria',
            error: error.message
        });
    }
};

// Create a new criterion
exports.createCriterion = async (req, res) => {
    try {
        const { hackathonId } = req.params;
        const { name, description, weight } = req.body;

        // Validate weight
        if (weight < 0 || weight > 100) {
            return res.status(400).json({
                message: 'Weight must be between 0 and 100'
            });
        }

        const newCriterion = await EvaluationCriterion.create({
            name,
            description,
            weight,
            hackathonId
        });

        res.status(201).json(newCriterion);
    } catch (error) {
        res.status(500).json({
            message: 'Error creating criterion',
            error: error.message
        });
    }
};

// Update a criterion
exports.updateCriterion = async (req, res) => {
    try {
        const { id } = req.params;
        const criterion = await EvaluationCriterion.findByPk(id);

        if (!criterion) {
            return res.status(404).json({ message: 'Criterion not found' });
        }

        // Validate weight if provided
        if (req.body.weight && (req.body.weight < 0 || req.body.weight > 100)) {
            return res.status(400).json({
                message: 'Weight must be between 0 and 100'
            });
        }

        await criterion.update(req.body);

        res.status(200).json({
            message: 'Criterion updated successfully',
            criterion
        });
    } catch (error) {
        res.status(500).json({
            message: 'Error updating criterion',
            error: error.message
        });
    }
};

// Delete a criterion
exports.deleteCriterion = async (req, res) => {
    try {
        const { id } = req.params;
        const criterion = await EvaluationCriterion.findByPk(id);

        if (!criterion) {
            return res.status(404).json({ message: 'Criterion not found' });
        }

        await criterion.destroy();

        res.status(200).json({ message: 'Criterion deleted successfully' });
    } catch (error) {
        res.status(500).json({
            message: 'Error deleting criterion',
            error: error.message
        });
    }
};