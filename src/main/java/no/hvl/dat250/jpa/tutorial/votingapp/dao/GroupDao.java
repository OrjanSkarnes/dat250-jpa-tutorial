package no.hvl.dat250.jpa.tutorial.votingapp.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;
import no.hvl.dat250.jpa.tutorial.votingapp.Group;
import no.hvl.dat250.jpa.tutorial.votingapp.Poll;
import no.hvl.dat250.jpa.tutorial.votingapp.User;

import java.util.List;

@AllArgsConstructor
public class GroupDao {

    @PersistenceContext(unitName = "votingapp")
    private EntityManager em;

    public Group createGroup(Group group) {
        em.getTransaction().begin();
        em.persist(group);
        em.getTransaction().commit();
        return group;
    }

    public Group findGroupById(Long id) {
        return em.find(Group.class, id);
    }

    public void deleteGroup(Group group) {
        em.getTransaction().begin();
        em.remove(group);
        em.getTransaction().commit();
    }

    public Group updateGroup(Group group) {
        em.getTransaction().begin();
        em.merge(group);
        em.getTransaction().commit();
        return group;
    }

    public List<Group> getAllGroups() {
        TypedQuery<Group> query = em.createQuery("SELECT g FROM Group g", Group.class);
        return query.getResultList();
    }

    public List<Group> getGroupsByUser(User user) {
        TypedQuery<Group> query = em.createQuery("SELECT g FROM Group g WHERE :user MEMBER OF g.members", Group.class);
        query.setParameter("user", user);
        return query.getResultList();
    }

    public List<Group> getGroupsByPoll(Poll poll) {
        TypedQuery<Group> query = em.createQuery("SELECT g FROM Group g WHERE :poll MEMBER OF g.polls", Group.class);
        query.setParameter("poll", poll);
        return query.getResultList();
    }
}
