/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.List;
import org.drools.devguide.BaseTest;
import org.drools.devguide.eshop.model.Client;
import org.drools.devguide.eshop.model.Order;
import org.drools.devguide.eshop.model.OrderItem;
import org.drools.devguide.eshop.model.Product;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.api.runtime.KieSession;

/**
 *
 * @author salaboy
 */
public class SimpleOrderDiscountTest extends BaseTest{

    public SimpleOrderDiscountTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void simpleApplyDiscount() {
        KieSession kSession = createDefaultSession();
        Order o = new Order();
        
        Product pA = new Product("A", 700.0,800.0);
        Product pB = new Product("B", 800.0,850.0);
        List<OrderItem> items = new ArrayList<OrderItem>();
        OrderItem item1 = new OrderItem();
        item1.setProduct(pA);
        item1.setQuantity(2);
        OrderItem item2 = new OrderItem();
        item1.setProduct(pB);
        item1.setQuantity(1);
        items.add(item2);
        o.setItems(items);
        Client c = new Client();
        o.setClient(c);
        
        kSession.insert(pA);
        kSession.insert(pB);
        kSession.insert(item1);
        kSession.insert(o);
        
        int fired = kSession.fireAllRules();
        assertEquals(3, fired);
        Assert.assertNotNull(o.getDiscount());
        Assert.assertEquals((Double)10.0, o.getDiscount().getPercentage());
        
        
    }
}