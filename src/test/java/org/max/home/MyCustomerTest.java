package org.max.home;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MyCustomerTest extends AbstractTest {
    @Test
    void addCustomerAndSave() {
        //given
        CustomersEntity customersEntity = new CustomersEntity();
        customersEntity.setCustomerId((short) 19);
        customersEntity.setApartment("15");
        customersEntity.setDistrict("Западный");
        customersEntity.setFirstName("Иван");
        customersEntity.setLastName("Иванович");
        customersEntity.setHouse("1");
        customersEntity.setPhoneNumber("+7 987 654 3210");
        customersEntity.setStreet("Тихая");
        //when
        Session session = getSession();
        session.beginTransaction();
        session.persist(customersEntity);
        session.getTransaction().commit();

        final Query query = getSession()
                .createSQLQuery("SELECT * FROM customers WHERE customer_id=" + 19).addEntity(CustomersEntity.class);
        CustomersEntity creditEntity = (CustomersEntity) query.uniqueResult();
        //then
        Assertions.assertNotNull(creditEntity);
        Assertions.assertEquals("15", creditEntity.getApartment());
        Assertions.assertEquals("1", creditEntity.getHouse());
    }

    @ParameterizedTest
    @CsvSource({"Южный", "Северный"})
    void getCustomerById(String district) throws SQLException {
        //given
        String sql = "SELECT * FROM customers WHERE district='" + district + "'";
        Statement stmt = getConnection().createStatement();
        String res = "";
        //when
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            res = rs.getString(5);
        }
        //then
        Assertions.assertEquals(district, res);
    }
}
