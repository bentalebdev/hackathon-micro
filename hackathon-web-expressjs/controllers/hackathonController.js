const { Hackathon, EvaluationCriterion } = require('../models');

// Get all hackathons
exports.getAllHackathons = async (req, res) => {
    try {
        const hackathons = await Hackathon.findAll({
            include: [{
                model: EvaluationCriterion,
                as: 'criteria'
            }]
        });
        res.status(200).json(hackathons);
    } catch (error) {
        res.status(500).json({ message: 'Error fetching hackathons', error: error.message });
    }
};

// Get hackathon by ID
exports.getHackathonById = async (req, res) => {
    try {
        const { id } = req.params;
        const hackathon = await Hackathon.findByPk(id, {
            include: [{
                model: EvaluationCriterion,
                as: 'criteria'
            }]
        });

        if (!hackathon) {
            return res.status(404).json({ message: 'Hackathon not found' });
        }

        res.status(200).json(hackathon);
    } catch (error) {
        res.status(500).json({ message: 'Error fetching hackathon', error: error.message });
    }
};

// Create new hackathon
exports.createHackathon = async (req, res) => {
    try {
        const newHackathon = await Hackathon.create(req.body);
        res.status(201).json(newHackathon);
    } catch (error) {
        res.status(500).json({ message: 'Error creating hackathon', error: error.message });
    }
};

// Update hackathon
exports.updateHackathon = async (req, res) => {
    try {
        const { id } = req.params;
        const hackathon = await Hackathon.findByPk(id);

        if (!hackathon) {
            return res.status(404).json({ message: 'Hackathon not found' });
        }

        await hackathon.update(req.body);

        res.status(200).json({
            message: 'Hackathon updated successfully',
            hackathon
        });
    } catch (error) {
        res.status(500).json({ message: 'Error updating hackathon', error: error.message });
    }
};

// Delete hackathon
exports.deleteHackathon = async (req, res) => {
    try {
        const { id } = req.params;
        const hackathon = await Hackathon.findByPk(id);

        if (!hackathon) {
            return res.status(404).json({ message: 'Hackathon not found' });
        }

        await hackathon.destroy();

        res.status(200).json({ message: 'Hackathon deleted successfully' });
    } catch (error) {
        res.status(500).json({ message: 'Error deleting hackathon', error: error.message });
    }
};