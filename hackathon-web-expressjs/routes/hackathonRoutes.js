const express = require('express');
const router = express.Router();
const hackathonController = require('../controllers/hackathonController');

// Get all hackathons
router.get('/', hackathonController.getAllHackathons);

// Get hackathon by ID
router.get('/:id', hackathonController.getHackathonById);

// Create new hackathon
router.post('/', hackathonController.createHackathon);

// Update hackathon
router.put('/:id', hackathonController.updateHackathon);

// Delete hackathon
router.delete('/:id', hackathonController.deleteHackathon);

module.exports = router;