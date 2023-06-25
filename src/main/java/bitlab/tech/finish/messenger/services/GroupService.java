package bitlab.tech.finish.messenger.services;


import bitlab.tech.finish.messenger.models.group_p.Group;
import bitlab.tech.finish.messenger.repositories.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    public List<Group> searchGroups(String name){
        return groupRepository.searchAllByNameContainingIgnoreCase(name);
    }

    public Group getGroupBySlug(String slug){
        return groupRepository.findBySlug(slug);
    }
    public Group save(Group group){
        return groupRepository.save(group);
    }

}
