package com.greedy.rotutee.point.entity;

import javax.persistence.*;

/**
 * packageName : com.greedy.rotutee.point.entity
 * fileName : Product
 * author : 7sang
 * date : 2022-05-02
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-05-02 7sang 최초 생성
 */

@Entity(name = "Point_PointProduct")
@Table(name = "TBL_POINT_PRODUCT")
@SequenceGenerator(
        name = "POINT_PRODUCT_SEQ_GENERATOR",
        sequenceName = "PRODUCT_NO",
        initialValue = 1,
        allocationSize = 1
)
public class PointProduct {

    @Id
    @Column(name = "PRODUCT_NO")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "POINT_PRODUCT_SEQ_GENERATOR"
    )
    private int productNo;

    @Column(name = "PRODUCT_NAME")
    private String productName;

    @Column(name = "PRODUCT_PRICE")
    private int productPrice;

    @Column(name = "REMAINING_NUMBER")
    private int remainingNumber;

    @Column(name = "TOTAL_SALES_COUNT")
    private int totalSalesCount;

    @Column(name = "SALES_STATUS")
    private String productStatus;

    @Column(name = "PRODUCT_CONTENT")
    private String productContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COUPON_NO")
    private Coupon coupon;

    public PointProduct() {}

    public PointProduct(int productNo, String productName, int productPrice, int remainingNumber, int totalSalesCount, String productStatus, String productContent, Coupon coupon) {
        this.productNo = productNo;
        this.productName = productName;
        this.productPrice = productPrice;
        this.remainingNumber = remainingNumber;
        this.totalSalesCount = totalSalesCount;
        this.productStatus = productStatus;
        this.productContent = productContent;
        this.coupon = coupon;
    }

    public int getTotalSalesCount() {
        return totalSalesCount;
    }

    public void setTotalSalesCount(int totalSalesCount) {
        this.totalSalesCount = totalSalesCount;
    }

    public int getProductNo() {
        return productNo;
    }

    public void setProductNo(int productNo) {
        this.productNo = productNo;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public int getRemainingNumber() {
        return remainingNumber;
    }

    public void setRemainingNumber(int remainingNumber) {
        this.remainingNumber = remainingNumber;
    }

    public String getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
    }

    public String getProductContent() {
        return productContent;
    }

    public void setProductContent(String productContent) {
        this.productContent = productContent;
    }

    public Coupon getCoupon() {
        return coupon;
    }

    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
    }

    @Override
    public String toString() {
        return "PointProduct{" +
                "productNo=" + productNo +
                ", productName='" + productName + '\'' +
                ", productPrice='" + productPrice + '\'' +
                ", remainingNumber=" + remainingNumber +
                ", productStatus='" + productStatus + '\'' +
                ", productContent='" + productContent + '\'' +
                ", coupon=" + coupon +
                '}';
    }
}
