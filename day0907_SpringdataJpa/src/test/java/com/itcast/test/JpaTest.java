package com.itcast.test;

import com.itcast.dao.CustomerDao;
import com.itcast.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class JpaTest {
    @Autowired
    private CustomerDao customerDao;

    @Test
    public void testFindOne() {
        Customer customer = customerDao.findOne(2l);
        System.out.println(customer);
    }

    @Test
    @Transactional
    public void testGetOne() {
        Customer customer = customerDao.getOne(4l);
        System.out.println(customer);
    }

    @Test
    public void testFindAll() {
        List<Customer> customers = customerDao.findAll();
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }

    @Test
    public void testSave() {
        Customer customer = new Customer();
        customer.setCustName("黑马程序员");
        customer.setCustIndustry("教育");
        customerDao.save(customer);
    }

    @Test
    public void testUpdate() {
        Customer customer = new Customer();
        customer.setCustId(4l);
        customer.setCustName("黑马程序员2");
        customer.setCustIndustry("教育");
        customerDao.save(customer);
    }

    @Test
    public void testExist() {
        boolean flag = customerDao.exists(4l);
        System.out.println(flag);
    }


    @Test
    public void testFindJpql() {
        Customer customer = customerDao.findJpql("传智播客2");
        System.out.println(customer);
    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void testUpdateJpql() {
        customerDao.updateJpql(4l, "传智播客4");
    }

    @Test
    public void testName() {
        Customer customer = customerDao.findByCustName("传智播客2");
        System.out.println(customer);
    }

    @Test
    public void testNameLike() {
        List<Customer> list = customerDao.findByCustNameLike("%播客%");
        for (Customer customer : list) {
            System.out.println(customer);
        }
    }


    @Test
    public void testSpec01() {
        Specification<Customer> spec = new Specification<Customer>() {
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                Path<Object> custName = root.get("custName");
                Path<Object> custAddress = root.get("custAddress");
                Predicate p1 = cb.equal(custName, "传智播客");
                Predicate p2 = cb.equal(custAddress, "北京");
                Predicate and = cb.and(p1, p2);
                return and;
            }
        };

        Customer customer = customerDao.findOne(spec);
        System.out.println(customer);
    }

    @Test
    public void testSpec02() {
        Pageable pageable = new PageRequest(0, 2);
        Page<Customer> page = customerDao.findAll(null, pageable);
        long totalElements = page.getTotalElements();
        int totalPages = page.getTotalPages();
        List<Customer> list = page.getContent();

        System.out.println(totalElements);
        System.out.println(totalPages);
        System.out.println(list);
    }
}
