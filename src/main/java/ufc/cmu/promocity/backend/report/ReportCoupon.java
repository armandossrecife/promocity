package ufc.cmu.promocity.backend.report;

import java.util.Date;

import ufc.cmu.promocity.backend.model.Coupon;
import ufc.cmu.promocity.backend.model.Promotion;
import ufc.cmu.promocity.backend.model.Store;

public class ReportCoupon {
	private Store store;
	private Promotion promotion;
	private Coupon coupon;
	private long idPromotion;
	private String descriptionPromotion;
	private Date fromDate;
	private Date toDate;
	private long idStore;
	private String name;
	private double latitude;
	private double longitude;
	private long idCoupon;
	private String descriptionCoupon;
	private String qrCode;
	private double discount;
	private boolean activated; // o cupom foi ativado por 3 amigos
	private boolean consumed; //quando o limite de vezes é alcançado, o cupom não pode mais ser consumido
	private boolean awarded; //o cupom se torna premiado quando 3 amigos ativam ao mesmo tempo dentro da vizinhança	
	private int limitedToUse; 
	
	public ReportCoupon() {
		
	}
	
	public ReportCoupon(Store store, Promotion promotion, Coupon coupon) {
		this.promotion = promotion;
		this.store = store;
		this.coupon = coupon;
		this.idPromotion = promotion.getId();
		this.descriptionPromotion = promotion.getDescription();
		this.fromDate = promotion.getFromDate();
		this.toDate = promotion.getToDate();
		this.idStore = store.getId();
		this.name = store.getName();
		this.latitude = store.getLatitude();
		this.longitude = store.getLongitude();
		this.idCoupon = coupon.getId();
		this.descriptionCoupon = coupon.getDescription();
		this.qrCode = coupon.getQrCode();
		this.discount = coupon.getDiscount();
		this.activated = coupon.isActivated();
		this.consumed = coupon.isConsumed();
		this.awarded = coupon.isAwarded();	
		this.limitedToUse = coupon.getLimitedToUse();
	}

	public long getIdPromotion() {
		return idPromotion;
	}

	public void setIdPromotion(long idPromotion) {
		this.idPromotion = idPromotion;
	}

	public String getDescriptionPromotion() {
		return descriptionPromotion;
	}

	public void setDescriptionPromotion(String descriptionPromotion) {
		this.descriptionPromotion = descriptionPromotion;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public long getIdStore() {
		return idStore;
	}

	public void setIdStore(long idStore) {
		this.idStore = idStore;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public long getIdCoupon() {
		return idCoupon;
	}

	public void setIdCoupon(long idCoupon) {
		this.idCoupon = idCoupon;
	}

	public String getDescriptionCoupon() {
		return descriptionCoupon;
	}

	public void setDescriptionCoupon(String descriptionCoupon) {
		this.descriptionCoupon = descriptionCoupon;
	}

	public String getQrCode() {
		return qrCode;
	}

	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public boolean isActivated() {
		return activated;
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
	}

	public boolean isConsumed() {
		return consumed;
	}

	public void setConsumed(boolean consumed) {
		this.consumed = consumed;
	}

	public boolean isAwarded() {
		return awarded;
	}

	public void setAwarded(boolean awarded) {
		this.awarded = awarded;
	}

	public int getLimitedToUse() {
		return limitedToUse;
	}

	public void setLimitedToUse(int limitedToUse) {
		this.limitedToUse = limitedToUse;
	}
}
