/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.*;
import service.User;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import javax.servlet.http.*;
import javax.servlet.*;

/**
 *
 * @author REA & REBRAY
 */
public class BddController extends MultiActionController {

    private String login;
    private String pwd;
    private User utilisateur;
    private HttpSession session;

    public BddController() {
    }

    public ModelAndView accueil(HttpServletRequest request,
            HttpServletResponse response) {
        login = request.getUserPrincipal().getName();
        // If the logged account is Admin = 1 otherwise he is a mere User = 0
        boolean isAdmin = request.isUserInRole("admin");

        //pwd=request.getParameter("password");
        utilisateur = new User(login);
        utilisateur.setIsAdmin(isAdmin); // Administrator authorizations are managed here
        session = request.getSession();
        session.setAttribute("user", utilisateur);
        System.out.println("nbuser=" + User.getCompteur());
        return new ModelAndView("accueil").addObject("user", utilisateur);
    }

    public ModelAndView gestionClient(HttpServletRequest request,
            HttpServletResponse response) {

        /*login = request.getUserPrincipal().getName();

        //pwd=request.getParameter("password");
        utilisateur = new User(login);

        session = request.getSession();
        session.setAttribute("user", utilisateur);
        System.out.println("nbuser=" + User.getCompteur());*/
        return new ModelAndView("menu").addObject("user", utilisateur);
    }

    public ModelAndView gestionStock(HttpServletRequest request,
            HttpServletResponse response) {
        return new ModelAndView("menuStock").addObject("user", utilisateur);
    }

    public ModelAndView logout(HttpServletRequest request,
            HttpServletResponse response) {

        request.getSession().invalidate();

        return new ModelAndView("deconnexion");
    }

    public ModelAndView list(HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        return new ModelAndView("resultat").addObject("liste", new MagasinHelper().getClients());
    }

    public ModelAndView listProduct(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ModelAndView mv = new ModelAndView("resultatStock");
        mv.addObject("liste", new MagasinHelper().getProduits());

        return mv;
    }

    public ModelAndView add(HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        ModelAndView mv = new ModelAndView("form_inscription");

        mv.addObject("user", session.getAttribute("user"));
        mv.addObject("discount", new MagasinHelper().getDiscountCode());
        mv.addObject("code", new MagasinHelper().getZipCode());
        return mv;
    }

    public ModelAndView addStock(HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        ModelAndView mv = new ModelAndView("form_product");
        mv.addObject("user", session.getAttribute("user"));
        mv.addObject("manufacturer", new MagasinHelper().getManufacturer());
        mv.addObject("productCode", new MagasinHelper().getProductCode());

        return mv;
    }

    public ModelAndView detail(HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        ModelAndView mv = new ModelAndView("detail");

        mv.addObject("user", session.getAttribute("user"));
        mv.addObject("code", new MagasinHelper().getDiscountCode());
        mv.addObject("zip", new MagasinHelper().getZipCode()); // Permet de récupérer la liste des CODE POSTAUX
        mv.addObject("client", new MagasinHelper().getClient(Integer.parseInt(request.getParameter("num"))));
        return mv;
    }

    public ModelAndView detailStock(HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        ModelAndView mv = new ModelAndView("detailStock");

        mv.addObject("user", session.getAttribute("user"));
        mv.addObject("product", new MagasinHelper().getProduit(Integer.parseInt(request.getParameter("num"))));
        return mv;
    }

    public ModelAndView find(HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        ModelAndView mv;
        MagasinHelper requeteur = new MagasinHelper();
        if (requeteur.getClients(request.getParameter("nom")).isEmpty() || request.getParameter("nom").equals("%")) {
            mv = new ModelAndView("error");
            mv.addObject("erreur", "0 enregistrement");

        } else {
            mv = new ModelAndView("resultat");
            mv.addObject("liste", requeteur.getClients(request.getParameter("nom")));

        }

        mv.addObject("user", session.getAttribute("user"));
        return mv;
    }

    public ModelAndView formfind(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        return new ModelAndView("recherche");
    }

    public ModelAndView achats(HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        ModelAndView mv = new ModelAndView("achats");

        mv.addObject("user", session.getAttribute("user"));
        System.out.println("num " + Integer.parseInt(request.getParameter("numero")));

        mv.addObject("achats", new MagasinHelper().getAchats(Integer.parseInt(request.getParameter("numero"))));
        return mv;
    }

    public ModelAndView save(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        try {
            String nom = request.getParameter("nom");
            String adresse1 = request.getParameter("adresse1");
            String telephone = request.getParameter("telephone");
            String email = request.getParameter("email");
            String code_remise = request.getParameter("code_remise");
            String cp = request.getParameter("cp");

            Customer customer = new Customer();
            // On modifie l'objet puis on l'update en BDD
            customer.setName(nom);
            customer.setAddressline1(adresse1);
            //   customer.setAddressline2(adresse2);
            customer.setPhone(telephone);
            customer.setEmail(email);
            customer.setDiscountCode(code_remise.charAt(0));
            customer.setZip(cp);
            // Récupération de l'ID max pour la prise en compte de l'auto-increment (géré par le code ici et non par la BDD)
            int max_id = new MagasinHelper().getClientMaxId();
            if (max_id != -1) {
                customer.setCustomerId(max_id + 1);

                new MagasinHelper().insertCustomer(customer);
            }
            return new ModelAndView("confirm").addObject("confirm", "enregistrement effectué");
        } catch (Exception e) {
            return new ModelAndView("error").addObject("erreur", "Vous devez respecter le format des valeurs du formulaire");
        }

    }

    public ModelAndView update(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        try {
            int id = Integer.parseInt(request.getParameter("numero"));
            String nom = request.getParameter("nom");
            String adresse1 = request.getParameter("adresse1");
            //String adresse2 = request.getParameter("adresse2");
            String telephone = request.getParameter("telephone");
            String email = request.getParameter("email");
            String code_remise = request.getParameter("code_remise");
            String cp = request.getParameter("cp");
            // On charge les données du client qui va être modifié
            Customer customer = new MagasinHelper().getClient(request.getParameter("numero"));
            // On modifie l'objet puis on l'update en BDD
            customer.setName(nom);
            customer.setAddressline1(adresse1);
            //   customer.setAddressline2(adresse2);
            customer.setPhone(telephone);
            customer.setEmail(email);
            customer.setDiscountCode(code_remise.charAt(0));
            customer.setZip(cp);

            new MagasinHelper().updateCustomer(customer);
            return new ModelAndView("confirm").addObject("confirm", "produit effectué");
        } catch (Exception e) {
            return new ModelAndView("error").addObject("erreur", "Vous devez respecter le format des valeurs du formulaire");
        }
    }

    public ModelAndView updateStock(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        try {
            int productId = Integer.parseInt(request.getParameter("productId"));
            int manufacturerId = Integer.parseInt(request.getParameter("manufacturerId"));
            String productCode = request.getParameter("productCode");
            float purchaseCost = Float.parseFloat(request.getParameter("purchaseCost"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            float markup = Float.parseFloat(request.getParameter("markup"));
            String available = request.getParameter("available");
            String description = request.getParameter("description");
            // Gestion du produit sous forme d'objet suite à des exceptions levées sur le cast en object Product.
            Product product = (Product) new MagasinHelper().getProduit(Integer.parseInt(request.getParameter("productId")));
            // On modifie l'objet puis on l'update en BDD
            product.setProductId(productId);
            product.setManufacturerId(manufacturerId);
            product.setProductCode(productCode);
            product.setPurchaseCost(purchaseCost);
            product.setQuantityOnHand(quantity);
            product.setMarkup(markup);
            product.setAvailable(available);
            product.setDescription(description);

            new MagasinHelper().updateProduit(product);
            return new ModelAndView("confirmStock");
        } catch (Exception e) {
            return new ModelAndView("error").addObject("erreur", "Vous devez respecter le format des valeurs du formulaire");
        }
    }

    /**
     * *
     * Delete a customer
     *
     * @param request
     * @param response
     * @return Information page about result of our request
     * @throws Exception
     */
    public ModelAndView delete(HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        new MagasinHelper().deleteCustomer(Integer.parseInt(request.getParameter("numero"))); // Obligatoirement SET.
        return new ModelAndView("confirm").addObject("confirm", "Suppression effectuée");
    }

    /**
     * *
     * Delete a product based on the form datas
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ModelAndView deleteStock(HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        Product product = (Product) new MagasinHelper().getProduit(Integer.parseInt(request.getParameter("productId")));

        new MagasinHelper().deleteProduit(product);
        return new ModelAndView("confirmStock").addObject("confirm", "Suppression effectuée");
    }

    /**
     * Create a Product based on form datas
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ModelAndView insertStock(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        try {

            int productId = Integer.parseInt(request.getParameter("productId"));
            int manufacturerId = Integer.parseInt(request.getParameter("manufacturerId"));
            String productCode = request.getParameter("productCode");
            float purchaseCost = Float.parseFloat(request.getParameter("purchaseCost"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            float markup = Float.parseFloat(request.getParameter("markup"));
            String available = request.getParameter("available");
            String description = request.getParameter("description");

            if (quantity > 0) {
                available = "TRUE";
            } else {
                available = "FALSE";
            }

            Product product = new Product();
            product.setProductId(productId);
            product.setManufacturerId(manufacturerId);
            product.setProductCode(productCode);
            product.setPurchaseCost(purchaseCost);
            product.setQuantityOnHand(quantity);
            product.setMarkup(markup);
            product.setAvailable(available);
            product.setDescription(description);
            new MagasinHelper().insertProduit(product);
            return new ModelAndView("confirmStock").addObject("confirm", "Produit ajouté");
        } catch (Exception e) {
            return new ModelAndView("error").addObject("erreur", "Vous devez remplir tous les champs");
        }
    }

}
