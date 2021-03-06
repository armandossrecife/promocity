package ufc.cmu.promocity.backend.controller;

import ufc.cmu.promocity.backend.context.PromotionArea;
import ufc.cmu.promocity.backend.context.UserLocationMonitoring;
import ufc.cmu.promocity.backend.model.Coupon;
import ufc.cmu.promocity.backend.model.Promotion;
import ufc.cmu.promocity.backend.model.Store;
import ufc.cmu.promocity.backend.report.ReportPromotion;
import ufc.cmu.promocity.backend.model.Promotion;
import ufc.cmu.promocity.backend.service.CouponsService;
import ufc.cmu.promocity.backend.service.PromotionsService;
import ufc.cmu.promocity.backend.service.StoreService;
import ufc.cmu.promocity.backend.utils.geographic.GPSPoint;

import java.net.URI;
import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Promotion Controller
 * @author armandosoaressousa
 *
 */
@Component
@Path("/promotions")
public class PromotionController {
	private PromotionsService promotionService;
	private CouponsService couponService;
	private StoreService storeService;
		
	@Autowired
	public void setPromotionService(PromotionsService promotionServices){
		this.promotionService = promotionServices;
	}
	
	@Autowired
	public void setCouponsService(CouponsService couponService) {
		this.couponService = couponService;
	}
	
	@Autowired
	public void StoreController(StoreService storeService) {
		this.storeService = storeService;
	}
	
	/**
	 * Contrutor of PromotionController
	 * @param promotionService
	 */
	public PromotionController() {
	}

	 /**
     * Retorna em um JSON todas promocoes cadastrados
     * @return código http
     */
    @GET
    @Produces("application/json")
    public List<Promotion> getAllPromotion() {
       	List<Promotion> listPromotions = new LinkedList<Promotion>();
    	listPromotions = promotionService.getListAll();
    	return listPromotions;
    }
    
    /**
     * Dado um id retorna o JSON dos dados da promocao
     * @param id
     * @return código http
     */
    @GET
    @Produces("application/json")
    @Path("/{id}")
    public Promotion getPromotion(@PathParam("id") String id) {
    	return promotionService.get(Long.parseLong(id));
    }
    
    /**
     * Dado um id da promoção e da loja retorna o JSON dos dados da promocao
     * @param idPromotion
     * @param idStore
     * @return código http
     */
    @GET
    @Produces("application/json")
    @Path("/basic/{idPromotion}/store/{idStore}")
    public ReportPromotion getBasicPromotion(@PathParam("idPromotion") String id, @PathParam("idStore") String idStore) {
    	ReportPromotion basicPromotion=null;
    	Promotion myPromotion = promotionService.get(Long.parseLong(id));
    	Store myStore = storeService.get(Long.parseLong(idStore));
    	
    	boolean myStoreExist = false;
    	boolean myPromotionExist = false;
    	
    	if (myStore != null) {
    		myStoreExist = true;
    		for (Promotion promotion : myStore.getPromotionList()) {
    			if (promotion.getId() == myPromotion.getId()) {
    				myPromotionExist = true;
    				break;
    			}
    		}
    	}
    	
    	if (myStoreExist && myPromotionExist) {
    		basicPromotion = new ReportPromotion(myPromotion, myStore);
    	}
    	 
    	return basicPromotion;
    }
    
    
    /**
     * Dados os dados de um promocao adiciona um promocao no repositorio
     * @param promocao
     * @return código http
     */
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    public Response addPromotion(Promotion promotion) {
        promotionService.save(promotion);
        URI uri = URI.create("/" + String.valueOf(promotion.getId()));
		return Response.created(uri).build();
    }
    
    /**
     * Dado um id e os dados do user faz sua atualizacao
     * @param id
     * @param user
     * @return código http
     */
    @PUT
    @Consumes("application/json")
    @Path("/{id}")
    public Response updatePromotion(@PathParam("id") String id, Promotion promotion) {
       promotionService.save(promotion);
       return Response.noContent().build();
    }
    
    /**
     * Dado um id de um usuario faz sua remocao do repositorio
     * @param id
     * @return código http
     */
    @DELETE
    @Path("/{id}")
    public Response deletePromotion(@PathParam("id") String id) {
        promotionService.delete(Long.parseLong(id));
        return Response.ok().build();
    }
    
    /*
    Lista todos os cupons da promoção 1
    http://localhost:8082/promocity/promotions/1/coupons
	*/
    /**
     * Retorna em um JSON todos os cupons de uma promocao
     * @return código http
     */
    @GET
    @Produces("application/json")
    @Path("/{id}/coupons")
    public List<Coupon> getAllCouponsFromPromotion(@PathParam("id") String id) {
       	List<Coupon> listCouponsFromPromotion = new LinkedList<Coupon>();       	   	
       	Promotion promotion = promotionService.get(Long.parseLong(id));
       	
       	for(Coupon element : promotion.getCoupons()) {
    			listCouponsFromPromotion.add(element);    		
    	}
    	
    	return listCouponsFromPromotion;
    }
    
    /*
    Dados do cupom 1, da promoção 1 da loja 1
    http://localhost:8082/promocity/promotions/1/coupons/1
    */
    @GET
    @Produces("application/json")
    @Path("/{id}/coupons/{id}")
    public Coupon getCouponFromPromotion(@PathParam("id") String idParam, @PathParam("id") String idCoupon) {       	
    	return this.couponService.get(Long.valueOf(idCoupon));
    }

}