/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import java.util.*;
import java.sql.*;

/**
 *
 * @author faycal
 */
public class MagasinHelper {

    Session session = null;

    public List<String> getColonnes() {
        return colonnes;
    }
    ArrayList<String> colonnes = new ArrayList();

    public MagasinHelper() {
        this.session = HibernateUtil.getSessionFactory().openSession();
    }

    /**
     * *
     *
     * @return -1 if error, the max_id from CUSTOMER table otherwise
     */
    public int getClientMaxId() {
        Transaction tx = null;
        int resultat = -1;
        try {
            if (!session.isOpen()) {
                session = HibernateUtil.getSessionFactory().openSession();
            }
            session.flush();

            tx = session.beginTransaction();
            Query q = session.createQuery("SELECT MAX(customerId) FROM Customer");
            resultat = (int) q.list().iterator().next();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session.isOpen()) {
                session.close();
            }
        }
        return resultat;
    }

    public List getClients() {
        List<Customer> resultat = null;
        Transaction tx = null;
        try {
            if (!session.isOpen()) {
                session = HibernateUtil.getSessionFactory().openSession();
            }
            session.flush();

            tx = session.beginTransaction();
            Query q = session.createQuery("select a.customerId, a.name, a.addressline1,a.addressline2,a.zip,b.rate from Customer a, DiscountCode b where a.discountCode=b.discountCode");
            //Query q=session.createQuery("from Customer");
            resultat = q.list();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session.isOpen()) {
                session.close();
            }
        }
        return resultat;
    }

    public List getClients(String name) {
        List resultat = null;
        Transaction tx = null;
        try {
            if (!session.isOpen()) {
                session = HibernateUtil.getSessionFactory().openSession();
            }
            session.flush();

            tx = session.beginTransaction();
            Query q = session.createQuery("select a.customerId, a.name, a.addressline1,a.addressline2,a.zip,b.rate from Customer a, DiscountCode b where a.discountCode=b.discountCode and a.name like :_name");
            //Query q=session.createQuery("from Customer");
            q.setString("_name", name);
            resultat = q.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session.isOpen()) {
                session.close();
            }
        }

        return resultat;
    }

    public List getDiscountCode() {
        List resultat = null;
        Transaction tx = null;
        try {
            if (!session.isOpen()) {
                session = HibernateUtil.getSessionFactory().openSession();
            }
            session.flush();

            tx = session.beginTransaction();
            Query q = session.createQuery("select a.discountCode from DiscountCode a");
            resultat = q.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session.isOpen()) {
                session.close();
            }
        }
        return resultat;

    }

    public List getMicroMarket() {
        List<MicroMarket> resultat = null;
        Transaction tx = null;
        try {
            //if(!session.isOpen())
            session = HibernateUtil.getSessionFactory().openSession();
            session.flush();

            tx = session.beginTransaction();
            Query q = session.createQuery("from MicroMarket");
            resultat = q.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session.isOpen()) {
                session.close();
            }

        }
        return resultat;
    }

    public List getZipCode() {
        List resultat = null;
        Transaction tx = null;
        try {
            //if(!session.isOpen())
            session = HibernateUtil.getSessionFactory().openSession();
            session.flush();

            tx = session.beginTransaction();
            Query q = session.createQuery("select a.zipCode from MicroMarket a");
            resultat = q.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session.isOpen()) {
                session.close();
            }

        }
        return resultat;
    }

    // Fonction modifié pour insérer directement un object Customer créé.
    public void insertCustomer(Customer c) {

        Transaction tx = null;
        try {
            if (!session.isOpen()) {
                session = HibernateUtil.getSessionFactory().openSession();
            }
            session.flush();
            tx = session.beginTransaction();

            session.save(c);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            throw e;
        } finally {
            if (session.isOpen()) {
                session.close();
            }
        }

    }
// Ajout de paramètres. Fix email missing. (causing null values to be updated)

    public void updateCustomer(Customer c) {
        Transaction tx = null;
        try {
            if (!session.isOpen()) {
                session = HibernateUtil.getSessionFactory().openSession();
            }
            session.flush();

            tx = session.beginTransaction();
            // Customer a =new Customer(_customerId,_name, _adress, _phone,_discountCode,_zip, _email);
            session.update(c);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            throw e;
        } finally {
            if (session.isOpen()) {
                session.close();
            }
        }
    }

    public Customer getClient(int id) {

        Customer client = null;
        Transaction tx = null;
        try {
            if (!session.isOpen()) {
                session = HibernateUtil.getSessionFactory().openSession();
            }
            session.flush();
            tx = session.beginTransaction();
            Query q = session.createQuery(" from Customer a  where a.customerId =:_id");
            q.setInteger("_id", id);
            client = (Customer) q.list().iterator().next();
        } catch (Exception e) {
            System.out.println("erreur" + e);
            e.printStackTrace();
        } finally {
            if (session.isOpen()) {
                session.close();
            }
        }

        return client;
    }

    public Customer getClient(String name) {

        Customer client = null;
        Transaction tx = null;
        try {
            if (!session.isOpen()) {
                session = HibernateUtil.getSessionFactory().openSession();
            }
            session.flush();
            tx = session.beginTransaction();
            Query q = session.createQuery(" from Customer a  where a.customerId =:_name");
            q.setString("_name", name);
            client = (Customer) q.list().iterator().next();
        } catch (Exception e) {
            System.out.println("erreur" + e);
            e.printStackTrace();
        } finally {
            if (session.isOpen()) {
                session.close();
            }
        }

        return client;
    }

    public void deleteCustomer(int _id) {

        Transaction tx = null;
        try {
            if (!session.isOpen()) {
                session = HibernateUtil.getSessionFactory().openSession();
            }
            session.flush();
            tx = session.beginTransaction();

            // Supprime les occurences du Client dans la table PurchaseOrder pour autorisé la suppression en cascade.
            Query q2 = session.createQuery(" from PurchaseOrder po where po.customerId IN ( SELECT customerId FROM Customer Where customerId = :_id)");
            q2.setInteger("_id", _id);
            if (!q2.list().isEmpty()) {
                session.delete((PurchaseOrder) q2.list().iterator().next());
            }
            // Supprime les clients
            Query q = session.createQuery(" from Customer a where a.customerId =:_id");
            q.setInteger("_id", _id);
            session.delete((Customer) q.list().iterator().next());
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            throw e;
        } finally {
            if (session.isOpen()) {
                session.close();
            }
        }

    }

    public List<PurchaseOrder> getAchats(int id) {
        List resultat = null;
        Transaction tx = null;
        try {
            if (!session.isOpen()) {
                session = HibernateUtil.getSessionFactory().openSession();
            }
            session.flush();

            tx = session.beginTransaction();
            Query q = session.createQuery(" from PurchaseOrder  a where a.customerId=:_id");
            q.setInteger("_id", id);
            resultat = (List<PurchaseOrder>) q.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session.isOpen()) {
                session.close();
            }
        }

        return resultat;
    }

    /**
     * *
     * Récupère la liste des produits
     *
     * @return
     */
    public List getProduits() {
        List<Product> resultat = null;
        Transaction tx = null;
        try {
            if (!session.isOpen()) {
                session = HibernateUtil.getSessionFactory().openSession();
            }
            session.flush();

            tx = session.beginTransaction();
            Query q = session.createQuery("select productId, manufacturerId, productCode, cast(purchaseCost as float), quantityOnHand, cast(markup as float), available, description from Product");
            resultat = q.list();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session.isOpen()) {
                session.close();
            }
        }
        return resultat;
    }

    /**
     * *
     * Récupère un produit d'après son _id
     *
     * @param _id
     * @return
     */
    public Object getProduit(int id) {
        Object[] productRaw = new Object[8];
        Product product = new Product();
        Transaction tx = null;
        try {
            if (!session.isOpen()) {
                session = HibernateUtil.getSessionFactory().openSession();
            }
            session.flush();

            tx = session.beginTransaction();
            Query q = session.createQuery("select productId, manufacturerId, productCode, cast(purchaseCost as float), quantityOnHand, cast(markup as float), available, description from Product WHERE productId =:_id");
            //Query q=session.createQuery("from Customer");
            q.setInteger("_id", id);
            productRaw = (Object[]) q.list().iterator().next();
            product.setProductId((int) productRaw[0]);
            product.setManufacturerId((int) productRaw[1]);
            product.setProductCode((String) productRaw[2]);
            product.setPurchaseCost((float) productRaw[3]);
            product.setQuantityOnHand((Integer) productRaw[4]);
            product.setMarkup((float) productRaw[5]);
            product.setAvailable((String) productRaw[6]);
            product.setDescription((String) productRaw[7]);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session.isOpen()) {
                session.close();
            }
        }
        return product;
    }

    public void updateProduit(Product p) {
        Transaction tx = null;
        try {
            if (!session.isOpen()) {
                session = HibernateUtil.getSessionFactory().openSession();
            }
            session.flush();

            tx = session.beginTransaction();
            session.update(p);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            throw e;
        } finally {
            if (session.isOpen()) {
                session.close();
            }
        }
    }

    public void deleteProduit(Product p) {

        Transaction tx = null;
        try {
            if (!session.isOpen()) {
                session = HibernateUtil.getSessionFactory().openSession();
            }
            session.flush();
            tx = session.beginTransaction();

            session.delete(p);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            throw e;
        } finally {
            if (session.isOpen()) {
                session.close();
            }
        }

    }
    
    public void insertProduit(Product p) {

        Transaction tx = null;
        try {
            if (!session.isOpen()) {
                session = HibernateUtil.getSessionFactory().openSession();
            }
            session.flush();
            tx = session.beginTransaction();

            session.save(p);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            throw e;
        } finally {
            if (session.isOpen()) {
                session.close();
            }
        }
    }
    
      public List getManufacturer() {
        List resultat = null;
        Transaction tx = null;
        try {
            if (!session.isOpen()) {
                session = HibernateUtil.getSessionFactory().openSession();
            }
            session.flush();

            tx = session.beginTransaction();
            Query q = session.createQuery("select m.manufacturerId from Manufacturer m");
            resultat =  q.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session.isOpen()) {
                session.close();
            }
        }

        return resultat;
    }
      
       public List getProductCode() {
        List resultat = null;
        Transaction tx = null;
        try {
            if (!session.isOpen()) {
                session = HibernateUtil.getSessionFactory().openSession();
            }
            session.flush();

            tx = session.beginTransaction();
            Query q = session.createQuery("select pc.prodCode from ProductCode pc");
            
            resultat =  q.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session.isOpen()) {
                session.close();
            }
        }
        return resultat;
    }
    
    
}
