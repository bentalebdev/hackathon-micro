package com.example.registrationservice.controllers;

import lombok.RequiredArgsConstructor;
import com.example.registrationservice.model.Group;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.registrationservice.services.GroupService;

import java.util.List;

@RestController
@RequestMapping("/api/groups")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @GetMapping("/")
    public List<Group> getAllGroups() {
        return groupService.getAllGroups();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Group> getGroupById(@PathVariable Long id) {
        Group group = groupService.getGroupById(id);
        if (group == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(group);
    }

    @PostMapping("/")
    public ResponseEntity<Group> createGroup(@RequestBody Group group) {
        Group createdGroup = groupService.createGroup(group);
        return ResponseEntity.ok(createdGroup);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Group> updateGroup(@PathVariable Long id, @RequestBody Group group) {
        Group updatedGroup = groupService.updateGroup(id, group);
        if (updatedGroup == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updatedGroup);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGroup(@PathVariable Long id) {
        boolean deleted = groupService.deleteGroup(id);
        if (!deleted) return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }
}