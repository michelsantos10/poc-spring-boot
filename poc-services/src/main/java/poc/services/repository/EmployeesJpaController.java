///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package poc.services.repository;
//
//import java.io.Serializable;
//import javax.persistence.Query;
//import javax.persistence.EntityNotFoundException;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Root;
//import poc.services.entities.Salaries;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import poc.services.entities.DeptEmp;
//import poc.services.entities.DeptManager;
//import poc.services.entities.Employees;
//import poc.services.entities.Titles;
//import poc.services.repository.exceptions.IllegalOrphanException;
//import poc.services.repository.exceptions.NonexistentEntityException;
//import poc.services.repository.exceptions.PreexistingEntityException;
//
///**
// *
// * @author miche
// */
//public class EmployeesJpaController implements Serializable {
//
//    public EmployeesJpaController(EntityManagerFactory emf) {
//        this.emf = emf;
//    }
//    private EntityManagerFactory emf = null;
//
//    public EntityManager getEntityManager() {
//        return emf.createEntityManager();
//    }
//
//    public void create(Employees employees) throws PreexistingEntityException, Exception {
//        if (employees.getSalariesCollection() == null) {
//            employees.setSalariesCollection(new ArrayList<Salaries>());
//        }
//        if (employees.getDeptEmpCollection() == null) {
//            employees.setDeptEmpCollection(new ArrayList<DeptEmp>());
//        }
//        if (employees.getDeptManagerCollection() == null) {
//            employees.setDeptManagerCollection(new ArrayList<DeptManager>());
//        }
//        if (employees.getTitlesCollection() == null) {
//            employees.setTitlesCollection(new ArrayList<Titles>());
//        }
//        EntityManager em = null;
//        try {
//            em = getEntityManager();
//            em.getTransaction().begin();
//            Collection<Salaries> attachedSalariesCollection = new ArrayList<Salaries>();
//            for (Salaries salariesCollectionSalariesToAttach : employees.getSalariesCollection()) {
//                salariesCollectionSalariesToAttach = em.getReference(salariesCollectionSalariesToAttach.getClass(), salariesCollectionSalariesToAttach.getSalariesPK());
//                attachedSalariesCollection.add(salariesCollectionSalariesToAttach);
//            }
//            employees.setSalariesCollection(attachedSalariesCollection);
//            Collection<DeptEmp> attachedDeptEmpCollection = new ArrayList<DeptEmp>();
//            for (DeptEmp deptEmpCollectionDeptEmpToAttach : employees.getDeptEmpCollection()) {
//                deptEmpCollectionDeptEmpToAttach = em.getReference(deptEmpCollectionDeptEmpToAttach.getClass(), deptEmpCollectionDeptEmpToAttach.getDeptEmpPK());
//                attachedDeptEmpCollection.add(deptEmpCollectionDeptEmpToAttach);
//            }
//            employees.setDeptEmpCollection(attachedDeptEmpCollection);
//            Collection<DeptManager> attachedDeptManagerCollection = new ArrayList<DeptManager>();
//            for (DeptManager deptManagerCollectionDeptManagerToAttach : employees.getDeptManagerCollection()) {
//                deptManagerCollectionDeptManagerToAttach = em.getReference(deptManagerCollectionDeptManagerToAttach.getClass(), deptManagerCollectionDeptManagerToAttach.getDeptManagerPK());
//                attachedDeptManagerCollection.add(deptManagerCollectionDeptManagerToAttach);
//            }
//            employees.setDeptManagerCollection(attachedDeptManagerCollection);
//            Collection<Titles> attachedTitlesCollection = new ArrayList<Titles>();
//            for (Titles titlesCollectionTitlesToAttach : employees.getTitlesCollection()) {
//                titlesCollectionTitlesToAttach = em.getReference(titlesCollectionTitlesToAttach.getClass(), titlesCollectionTitlesToAttach.getTitlesPK());
//                attachedTitlesCollection.add(titlesCollectionTitlesToAttach);
//            }
//            employees.setTitlesCollection(attachedTitlesCollection);
//            em.persist(employees);
//            for (Salaries salariesCollectionSalaries : employees.getSalariesCollection()) {
//                Employees oldEmployeesOfSalariesCollectionSalaries = salariesCollectionSalaries.getEmployees();
//                salariesCollectionSalaries.setEmployees(employees);
//                salariesCollectionSalaries = em.merge(salariesCollectionSalaries);
//                if (oldEmployeesOfSalariesCollectionSalaries != null) {
//                    oldEmployeesOfSalariesCollectionSalaries.getSalariesCollection().remove(salariesCollectionSalaries);
//                    oldEmployeesOfSalariesCollectionSalaries = em.merge(oldEmployeesOfSalariesCollectionSalaries);
//                }
//            }
//            for (DeptEmp deptEmpCollectionDeptEmp : employees.getDeptEmpCollection()) {
//                Employees oldEmployeesOfDeptEmpCollectionDeptEmp = deptEmpCollectionDeptEmp.getEmployees();
//                deptEmpCollectionDeptEmp.setEmployees(employees);
//                deptEmpCollectionDeptEmp = em.merge(deptEmpCollectionDeptEmp);
//                if (oldEmployeesOfDeptEmpCollectionDeptEmp != null) {
//                    oldEmployeesOfDeptEmpCollectionDeptEmp.getDeptEmpCollection().remove(deptEmpCollectionDeptEmp);
//                    oldEmployeesOfDeptEmpCollectionDeptEmp = em.merge(oldEmployeesOfDeptEmpCollectionDeptEmp);
//                }
//            }
//            for (DeptManager deptManagerCollectionDeptManager : employees.getDeptManagerCollection()) {
//                Employees oldEmployeesOfDeptManagerCollectionDeptManager = deptManagerCollectionDeptManager.getEmployees();
//                deptManagerCollectionDeptManager.setEmployees(employees);
//                deptManagerCollectionDeptManager = em.merge(deptManagerCollectionDeptManager);
//                if (oldEmployeesOfDeptManagerCollectionDeptManager != null) {
//                    oldEmployeesOfDeptManagerCollectionDeptManager.getDeptManagerCollection().remove(deptManagerCollectionDeptManager);
//                    oldEmployeesOfDeptManagerCollectionDeptManager = em.merge(oldEmployeesOfDeptManagerCollectionDeptManager);
//                }
//            }
//            for (Titles titlesCollectionTitles : employees.getTitlesCollection()) {
//                Employees oldEmployeesOfTitlesCollectionTitles = titlesCollectionTitles.getEmployees();
//                titlesCollectionTitles.setEmployees(employees);
//                titlesCollectionTitles = em.merge(titlesCollectionTitles);
//                if (oldEmployeesOfTitlesCollectionTitles != null) {
//                    oldEmployeesOfTitlesCollectionTitles.getTitlesCollection().remove(titlesCollectionTitles);
//                    oldEmployeesOfTitlesCollectionTitles = em.merge(oldEmployeesOfTitlesCollectionTitles);
//                }
//            }
//            em.getTransaction().commit();
//        } catch (Exception ex) {
//            if (findEmployees(employees.getEmpNo()) != null) {
//                throw new PreexistingEntityException("Employees " + employees + " already exists.", ex);
//            }
//            throw ex;
//        } finally {
//            if (em != null) {
//                em.close();
//            }
//        }
//    }
//
//    public void edit(Employees employees) throws IllegalOrphanException, NonexistentEntityException, Exception {
//        EntityManager em = null;
//        try {
//            em = getEntityManager();
//            em.getTransaction().begin();
//            Employees persistentEmployees = em.find(Employees.class, employees.getEmpNo());
//            Collection<Salaries> salariesCollectionOld = persistentEmployees.getSalariesCollection();
//            Collection<Salaries> salariesCollectionNew = employees.getSalariesCollection();
//            Collection<DeptEmp> deptEmpCollectionOld = persistentEmployees.getDeptEmpCollection();
//            Collection<DeptEmp> deptEmpCollectionNew = employees.getDeptEmpCollection();
//            Collection<DeptManager> deptManagerCollectionOld = persistentEmployees.getDeptManagerCollection();
//            Collection<DeptManager> deptManagerCollectionNew = employees.getDeptManagerCollection();
//            Collection<Titles> titlesCollectionOld = persistentEmployees.getTitlesCollection();
//            Collection<Titles> titlesCollectionNew = employees.getTitlesCollection();
//            List<String> illegalOrphanMessages = null;
//            for (Salaries salariesCollectionOldSalaries : salariesCollectionOld) {
//                if (!salariesCollectionNew.contains(salariesCollectionOldSalaries)) {
//                    if (illegalOrphanMessages == null) {
//                        illegalOrphanMessages = new ArrayList<String>();
//                    }
//                    illegalOrphanMessages.add("You must retain Salaries " + salariesCollectionOldSalaries + " since its employees field is not nullable.");
//                }
//            }
//            for (DeptEmp deptEmpCollectionOldDeptEmp : deptEmpCollectionOld) {
//                if (!deptEmpCollectionNew.contains(deptEmpCollectionOldDeptEmp)) {
//                    if (illegalOrphanMessages == null) {
//                        illegalOrphanMessages = new ArrayList<String>();
//                    }
//                    illegalOrphanMessages.add("You must retain DeptEmp " + deptEmpCollectionOldDeptEmp + " since its employees field is not nullable.");
//                }
//            }
//            for (DeptManager deptManagerCollectionOldDeptManager : deptManagerCollectionOld) {
//                if (!deptManagerCollectionNew.contains(deptManagerCollectionOldDeptManager)) {
//                    if (illegalOrphanMessages == null) {
//                        illegalOrphanMessages = new ArrayList<String>();
//                    }
//                    illegalOrphanMessages.add("You must retain DeptManager " + deptManagerCollectionOldDeptManager + " since its employees field is not nullable.");
//                }
//            }
//            for (Titles titlesCollectionOldTitles : titlesCollectionOld) {
//                if (!titlesCollectionNew.contains(titlesCollectionOldTitles)) {
//                    if (illegalOrphanMessages == null) {
//                        illegalOrphanMessages = new ArrayList<String>();
//                    }
//                    illegalOrphanMessages.add("You must retain Titles " + titlesCollectionOldTitles + " since its employees field is not nullable.");
//                }
//            }
//            if (illegalOrphanMessages != null) {
//                throw new IllegalOrphanException(illegalOrphanMessages);
//            }
//            Collection<Salaries> attachedSalariesCollectionNew = new ArrayList<Salaries>();
//            for (Salaries salariesCollectionNewSalariesToAttach : salariesCollectionNew) {
//                salariesCollectionNewSalariesToAttach = em.getReference(salariesCollectionNewSalariesToAttach.getClass(), salariesCollectionNewSalariesToAttach.getSalariesPK());
//                attachedSalariesCollectionNew.add(salariesCollectionNewSalariesToAttach);
//            }
//            salariesCollectionNew = attachedSalariesCollectionNew;
//            employees.setSalariesCollection(salariesCollectionNew);
//            Collection<DeptEmp> attachedDeptEmpCollectionNew = new ArrayList<DeptEmp>();
//            for (DeptEmp deptEmpCollectionNewDeptEmpToAttach : deptEmpCollectionNew) {
//                deptEmpCollectionNewDeptEmpToAttach = em.getReference(deptEmpCollectionNewDeptEmpToAttach.getClass(), deptEmpCollectionNewDeptEmpToAttach.getDeptEmpPK());
//                attachedDeptEmpCollectionNew.add(deptEmpCollectionNewDeptEmpToAttach);
//            }
//            deptEmpCollectionNew = attachedDeptEmpCollectionNew;
//            employees.setDeptEmpCollection(deptEmpCollectionNew);
//            Collection<DeptManager> attachedDeptManagerCollectionNew = new ArrayList<DeptManager>();
//            for (DeptManager deptManagerCollectionNewDeptManagerToAttach : deptManagerCollectionNew) {
//                deptManagerCollectionNewDeptManagerToAttach = em.getReference(deptManagerCollectionNewDeptManagerToAttach.getClass(), deptManagerCollectionNewDeptManagerToAttach.getDeptManagerPK());
//                attachedDeptManagerCollectionNew.add(deptManagerCollectionNewDeptManagerToAttach);
//            }
//            deptManagerCollectionNew = attachedDeptManagerCollectionNew;
//            employees.setDeptManagerCollection(deptManagerCollectionNew);
//            Collection<Titles> attachedTitlesCollectionNew = new ArrayList<Titles>();
//            for (Titles titlesCollectionNewTitlesToAttach : titlesCollectionNew) {
//                titlesCollectionNewTitlesToAttach = em.getReference(titlesCollectionNewTitlesToAttach.getClass(), titlesCollectionNewTitlesToAttach.getTitlesPK());
//                attachedTitlesCollectionNew.add(titlesCollectionNewTitlesToAttach);
//            }
//            titlesCollectionNew = attachedTitlesCollectionNew;
//            employees.setTitlesCollection(titlesCollectionNew);
//            employees = em.merge(employees);
//            for (Salaries salariesCollectionNewSalaries : salariesCollectionNew) {
//                if (!salariesCollectionOld.contains(salariesCollectionNewSalaries)) {
//                    Employees oldEmployeesOfSalariesCollectionNewSalaries = salariesCollectionNewSalaries.getEmployees();
//                    salariesCollectionNewSalaries.setEmployees(employees);
//                    salariesCollectionNewSalaries = em.merge(salariesCollectionNewSalaries);
//                    if (oldEmployeesOfSalariesCollectionNewSalaries != null && !oldEmployeesOfSalariesCollectionNewSalaries.equals(employees)) {
//                        oldEmployeesOfSalariesCollectionNewSalaries.getSalariesCollection().remove(salariesCollectionNewSalaries);
//                        oldEmployeesOfSalariesCollectionNewSalaries = em.merge(oldEmployeesOfSalariesCollectionNewSalaries);
//                    }
//                }
//            }
//            for (DeptEmp deptEmpCollectionNewDeptEmp : deptEmpCollectionNew) {
//                if (!deptEmpCollectionOld.contains(deptEmpCollectionNewDeptEmp)) {
//                    Employees oldEmployeesOfDeptEmpCollectionNewDeptEmp = deptEmpCollectionNewDeptEmp.getEmployees();
//                    deptEmpCollectionNewDeptEmp.setEmployees(employees);
//                    deptEmpCollectionNewDeptEmp = em.merge(deptEmpCollectionNewDeptEmp);
//                    if (oldEmployeesOfDeptEmpCollectionNewDeptEmp != null && !oldEmployeesOfDeptEmpCollectionNewDeptEmp.equals(employees)) {
//                        oldEmployeesOfDeptEmpCollectionNewDeptEmp.getDeptEmpCollection().remove(deptEmpCollectionNewDeptEmp);
//                        oldEmployeesOfDeptEmpCollectionNewDeptEmp = em.merge(oldEmployeesOfDeptEmpCollectionNewDeptEmp);
//                    }
//                }
//            }
//            for (DeptManager deptManagerCollectionNewDeptManager : deptManagerCollectionNew) {
//                if (!deptManagerCollectionOld.contains(deptManagerCollectionNewDeptManager)) {
//                    Employees oldEmployeesOfDeptManagerCollectionNewDeptManager = deptManagerCollectionNewDeptManager.getEmployees();
//                    deptManagerCollectionNewDeptManager.setEmployees(employees);
//                    deptManagerCollectionNewDeptManager = em.merge(deptManagerCollectionNewDeptManager);
//                    if (oldEmployeesOfDeptManagerCollectionNewDeptManager != null && !oldEmployeesOfDeptManagerCollectionNewDeptManager.equals(employees)) {
//                        oldEmployeesOfDeptManagerCollectionNewDeptManager.getDeptManagerCollection().remove(deptManagerCollectionNewDeptManager);
//                        oldEmployeesOfDeptManagerCollectionNewDeptManager = em.merge(oldEmployeesOfDeptManagerCollectionNewDeptManager);
//                    }
//                }
//            }
//            for (Titles titlesCollectionNewTitles : titlesCollectionNew) {
//                if (!titlesCollectionOld.contains(titlesCollectionNewTitles)) {
//                    Employees oldEmployeesOfTitlesCollectionNewTitles = titlesCollectionNewTitles.getEmployees();
//                    titlesCollectionNewTitles.setEmployees(employees);
//                    titlesCollectionNewTitles = em.merge(titlesCollectionNewTitles);
//                    if (oldEmployeesOfTitlesCollectionNewTitles != null && !oldEmployeesOfTitlesCollectionNewTitles.equals(employees)) {
//                        oldEmployeesOfTitlesCollectionNewTitles.getTitlesCollection().remove(titlesCollectionNewTitles);
//                        oldEmployeesOfTitlesCollectionNewTitles = em.merge(oldEmployeesOfTitlesCollectionNewTitles);
//                    }
//                }
//            }
//            em.getTransaction().commit();
//        } catch (Exception ex) {
//            String msg = ex.getLocalizedMessage();
//            if (msg == null || msg.length() == 0) {
//                Integer id = employees.getEmpNo();
//                if (findEmployees(id) == null) {
//                    throw new NonexistentEntityException("The employees with id " + id + " no longer exists.");
//                }
//            }
//            throw ex;
//        } finally {
//            if (em != null) {
//                em.close();
//            }
//        }
//    }
//
//    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
//        EntityManager em = null;
//        try {
//            em = getEntityManager();
//            em.getTransaction().begin();
//            Employees employees;
//            try {
//                employees = em.getReference(Employees.class, id);
//                employees.getEmpNo();
//            } catch (EntityNotFoundException enfe) {
//                throw new NonexistentEntityException("The employees with id " + id + " no longer exists.", enfe);
//            }
//            List<String> illegalOrphanMessages = null;
//            Collection<Salaries> salariesCollectionOrphanCheck = employees.getSalariesCollection();
//            for (Salaries salariesCollectionOrphanCheckSalaries : salariesCollectionOrphanCheck) {
//                if (illegalOrphanMessages == null) {
//                    illegalOrphanMessages = new ArrayList<String>();
//                }
//                illegalOrphanMessages.add("This Employees (" + employees + ") cannot be destroyed since the Salaries " + salariesCollectionOrphanCheckSalaries + " in its salariesCollection field has a non-nullable employees field.");
//            }
//            Collection<DeptEmp> deptEmpCollectionOrphanCheck = employees.getDeptEmpCollection();
//            for (DeptEmp deptEmpCollectionOrphanCheckDeptEmp : deptEmpCollectionOrphanCheck) {
//                if (illegalOrphanMessages == null) {
//                    illegalOrphanMessages = new ArrayList<String>();
//                }
//                illegalOrphanMessages.add("This Employees (" + employees + ") cannot be destroyed since the DeptEmp " + deptEmpCollectionOrphanCheckDeptEmp + " in its deptEmpCollection field has a non-nullable employees field.");
//            }
//            Collection<DeptManager> deptManagerCollectionOrphanCheck = employees.getDeptManagerCollection();
//            for (DeptManager deptManagerCollectionOrphanCheckDeptManager : deptManagerCollectionOrphanCheck) {
//                if (illegalOrphanMessages == null) {
//                    illegalOrphanMessages = new ArrayList<String>();
//                }
//                illegalOrphanMessages.add("This Employees (" + employees + ") cannot be destroyed since the DeptManager " + deptManagerCollectionOrphanCheckDeptManager + " in its deptManagerCollection field has a non-nullable employees field.");
//            }
//            Collection<Titles> titlesCollectionOrphanCheck = employees.getTitlesCollection();
//            for (Titles titlesCollectionOrphanCheckTitles : titlesCollectionOrphanCheck) {
//                if (illegalOrphanMessages == null) {
//                    illegalOrphanMessages = new ArrayList<String>();
//                }
//                illegalOrphanMessages.add("This Employees (" + employees + ") cannot be destroyed since the Titles " + titlesCollectionOrphanCheckTitles + " in its titlesCollection field has a non-nullable employees field.");
//            }
//            if (illegalOrphanMessages != null) {
//                throw new IllegalOrphanException(illegalOrphanMessages);
//            }
//            em.remove(employees);
//            em.getTransaction().commit();
//        } finally {
//            if (em != null) {
//                em.close();
//            }
//        }
//    }
//
//    public List<Employees> findEmployeesEntities() {
//        return findEmployeesEntities(true, -1, -1);
//    }
//
//    public List<Employees> findEmployeesEntities(int maxResults, int firstResult) {
//        return findEmployeesEntities(false, maxResults, firstResult);
//    }
//
//    private List<Employees> findEmployeesEntities(boolean all, int maxResults, int firstResult) {
//        EntityManager em = getEntityManager();
//        try {
//            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
//            cq.select(cq.from(Employees.class));
//            Query q = em.createQuery(cq);
//            if (!all) {
//                q.setMaxResults(maxResults);
//                q.setFirstResult(firstResult);
//            }
//            return q.getResultList();
//        } finally {
//            em.close();
//        }
//    }
//
//    public Employees findEmployees(Integer id) {
//        EntityManager em = getEntityManager();
//        try {
//            return em.find(Employees.class, id);
//        } finally {
//            em.close();
//        }
//    }
//
//    public int getEmployeesCount() {
//        EntityManager em = getEntityManager();
//        try {
//            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
//            Root<Employees> rt = cq.from(Employees.class);
//            cq.select(em.getCriteriaBuilder().count(rt));
//            Query q = em.createQuery(cq);
//            return ((Long) q.getSingleResult()).intValue();
//        } finally {
//            em.close();
//        }
//    }
//    
//}
