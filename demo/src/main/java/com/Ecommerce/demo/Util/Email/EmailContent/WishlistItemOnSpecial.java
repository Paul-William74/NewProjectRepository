package com.Ecommerce.demo.Util.Email.EmailContent;

public class WishlistItemOnSpecial {

    public static String buildWishlistSaleEmail(
            String firstName,
            String productName,
            String productImageUrl,
            String material,
            String size,
            String sizeId,
            String originalPrice,
            String salePrice,
            String discountPercent,
            String stockQuantity,
            String productUrl) {

        StringBuilder emailBuilder = new StringBuilder();

        emailBuilder.append("<!DOCTYPE html>")
                .append("<html lang=\"en\">")
                .append("<head>")
                .append("<meta charset=\"UTF-8\">")
                .append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">")
                .append("<title>Your Wishlist Item Is On Sale! | VEMOS</title>")
                .append("<style>")
                .append("body {font-family: 'Georgia', 'Times New Roman', serif;line-height: 1.6;margin: 0;padding: 0;background-color: #f9f7f4;color: #333333;}")
                .append(".container {max-width: 600px;margin: 20px auto;background: #ffffff;border-radius: 8px;overflow: hidden;box-shadow: 0 0 25px rgba(0,0,0,0.08);border: 1px solid #e8e5de;}")
                .append(".header {background-color: #776733;color: white;padding: 35px 20px;text-align: center;position: relative;border-bottom: 4px solid #d4c9a8;}")
                .append(".header h1 {margin: 0;font-size: 26px;font-weight: 500;letter-spacing: 0.5px;}")
                .append(".sale-badge {position: absolute;top: 20px;right: 20px;background-color: #8B2D3B;color: white;padding: 8px 15px;border-radius: 20px;font-size: 14px;font-weight: bold;transform: rotate(15deg);box-shadow: 0 3px 10px rgba(139, 45, 59, 0.2);}")
                .append(".product-card {margin: 25px 0;border: 1px solid #f0ede6;border-radius: 8px;overflow: hidden;background-color: #fcfbf9;}")
                .append(".product-image-container {height: 220px;background-color: #f8f7f3;display: flex;align-items: center;justify-content: center;border-bottom: 1px solid #f0ede6;}")
                .append(".product-image {max-width: 80%;max-height: 80%;object-fit: contain;}")
                .append(".product-details {padding: 25px;}")
                .append(".product-title {font-size: 20px;font-weight: 500;margin: 0 0 15px 0;color: #222222;font-family: 'Playfair Display', serif;}")
                .append(".product-attribute {margin-bottom: 8px;display: flex;}")
                .append(".attribute-label {font-weight: 600;color: #776733;width: 90px;flex-shrink: 0;}")
                .append(".price-section {margin: 20px 0;padding: 15px;background-color: #faf5f5;border-radius: 6px;border: 1px dashed #d9c2c5;text-align: center;}")
                .append(".original-price {text-decoration: line-through;color: #999999;font-size: 16px;}")
                .append(".sale-price {color: #8B2D3B;font-size: 24px;font-weight: bold;margin: 0 10px;}")
                .append(".discount-badge {background-color: #8B2D3B;color: white;padding: 3px 8px;border-radius: 4px;font-size: 14px;font-weight: bold;}")
                .append(".cta-button {display: block;background-color: #8B2D3B;color: white;text-decoration: none;padding: 16px 0;border-radius: 6px;margin: 25px 0;font-weight: 600;text-align: center;font-size: 18px;transition: background-color 0.3s;}")
                .append(".cta-button:hover {background-color: #6e2430;}")
                .append(".stock-warning {color: #8B2D3B;font-weight: 600;margin: 10px 0;text-align: center;font-style: italic;}")
                .append(".footer {background-color: #000000;color: #ffffff;text-align: center;padding: 25px;font-size: 12px;border-top: 1px solid #333333;}")
                .append("@media only screen and (max-width: 480px) {")
                .append(".container {border-radius: 0;margin: 0;}")
                .append(".product-image-container {height: 180px;}}")
                .append("</style>")
                .append("</head>")
                .append("<body>")
                .append("<div class=\"container\">")
                .append("<div class=\"header\">")
                .append("<div class=\"sale-badge\">SALE</div>")
                .append("<h1>Your Wishlist Item Is On Sale!</h1>")
                .append("</div>")
                .append("<div class=\"content\">")
                .append("<h2>Dear ").append(firstName).append(",</h2>")
                .append("<p>The jewelry piece you saved is now available at a special price—in your selected size and material.</p>")
                .append("<div class=\"product-card\">")
                .append("<div class=\"product-image-container\">")
                .append("<img src=\"").append(productImageUrl).append("\" alt=\"").append(productName).append("\" class=\"product-image\">")
                .append("</div>")
                .append("<div class=\"product-details\">")
                .append("<h3 class=\"product-title\">").append(productName).append("</h3>")
                .append("<div class=\"product-attribute\">")
                .append("<span class=\"attribute-label\">Material:</span>")
                .append("<span>").append(material).append("</span>")
                .append("</div>")
                .append("<div class=\"product-attribute\">")
                .append("<span class=\"attribute-label\">Size:</span>")
                .append("<span><strong>").append(size).append("</strong> (your selection)</span>")
                .append("</div>")
                .append("<div class=\"price-section\">")
                .append("<span class=\"original-price\">$").append(originalPrice).append("</span>")
                .append("<span class=\"sale-price\">$").append(salePrice).append("</span>")
                .append("<span class=\"discount-badge\">Save ").append(discountPercent).append("%</span>")
                .append("</div>")
                .append("<p class=\"stock-warning\">Only ").append(stockQuantity).append(" left in this size!</p>")
                .append("</div>")
                .append("</div>")
                .append("<a href=\"").append(productUrl)
                .append("?size=").append(sizeId)
                .append("\" class=\"cta-button\">CLAIM YOUR SIZE NOW</a>")
                .append("<p>This price is exclusive to wishlist members. Act fast—your selected configuration may sell out.</p>")
                .append("</div>")
                .append("<div class=\"footer\">")
                .append("&copy; 2023 VEMOS Jewelry. All rights reserved.<br>")
                .append("<a href=\"#\" style=\"color: #d4c9a8;\">Unsubscribe</a> | ")
                .append("<a href=\"#\" style=\"color: #d4c9a8;\">Privacy Policy</a>")
                .append("</div>")
                .append("</div>")
                .append("</body>")
                .append("</html>");

        return emailBuilder.toString();
    }
}
