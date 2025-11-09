package com.example.registrationservice.services;

import lombok.RequiredArgsConstructor;
import com.example.registrationservice.model.Group;
import org.springframework.stereotype.Service;
import com.example.registrationservice.repositories.GroupRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;

    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    public Group getGroupById(Long id) {
        Optional<Group> group = groupRepository.findById(id);
        return group.orElse(null);
    }

    public Group createGroup(Group group) {
        return groupRepository.save(group);
    }

    public Group updateGroup(Long id, Group updatedGroup) {
        Optional<Group> existingGroupOpt = groupRepository.findById(id);
        if (existingGroupOpt.isEmpty()) {
            return null;
        }

        Group existingGroup = existingGroupOpt.get();
        existingGroup.setLabel(updatedGroup.getLabel());
        existingGroup.setDescription(updatedGroup.getDescription());
        existingGroup.setGerant(updatedGroup.getGerant());
        existingGroup.setUsers(updatedGroup.getUsers());

        return groupRepository.save(existingGroup);
    }

    public boolean deleteGroup(Long id) {
        if (!groupRepository.existsById(id)) {
            return false;
        }
        groupRepository.deleteById(id);
        return true;
    }
}