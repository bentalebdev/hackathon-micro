const express = require('express');
const router = express.Router();
const evaluationCriterionController = require('../controllers/evaluationCriterionController');

// Get all criteria for a specific hackathon
router.get('/hackathons/:hackathonId/criteria', evaluationCriterionController.getCriteriaByHackathon);

// Create a new criterion for a hackathon
router.post('/hackathons/:hackathonId/criteria', evaluationCriterionController.createCriterion);

// Update a criterion
router.put('/criteria/:id', evaluationCriterionController.updateCriterion);

// Delete a criterion
router.delete('/criteria/:id', evaluationCriterionController.deleteCriterion);

module.exports = router;