package com.Ecommerce.demo.Util.Email.EmailContent;


public final class WaitlistConfirmation {

    public static String buildWaitlistEmail(String firstName, String lastName,
                                            String productName, String productImage,
                                            String size, String material,
                                            String collectionUrl) {

        StringBuilder emailBuilder = new StringBuilder();

        emailBuilder.append("<!DOCTYPE html>")
                .append("<html lang=\"en\">")
                .append("<head>")
                .append("<meta charset=\"UTF-8\">")
                .append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">")
                .append("<title>You're on the Waitlist! | VEMOS Jewelry</title>")
                .append("<style>")
                .append("body {font-family: 'Georgia', 'Times New Roman', serif;line-height: 1.6;margin: 0;padding: 0;background-color: #f9f7f4;color: #333333;}")
                .append(".container {max-width: 600px;margin: 20px auto;background: #ffffff;border-radius: 8px;overflow: hidden;box-shadow: 0 0 25px rgba(0,0,0,0.08);border: 1px solid #e8e5de;}")
                .append(".header {background-color: #776733;color: white;padding: 35px 20px;text-align: center;border-bottom: 4px solid #d4c9a8;}")
                .append(".header h1 {margin: 0;font-size: 26px;font-weight: 500;letter-spacing: 0.5px;}")
                .append(".content {padding: 35px;color: #555555;}")
                .append(".product-card {margin: 25px 0;border: 1px solid #f0ede6;border-radius: 8px;overflow: hidden;background-color: #fcfbf9;}")
                .append(".product-image-container {height: 220px;background-color: #f8f7f3;display: flex;align-items: center;justify-content: center;border-bottom: 1px solid #f0ede6;}")
                .append(".product-image {max-width: 80%;max-height: 80%;object-fit: contain;}")
                .append(".product-details {padding: 25px;}")
                .append(".product-title {font-size: 20px;font-weight: 500;margin: 0 0 15px 0;color: #222222;font-family: 'Playfair Display', serif;}")
                .append(".product-attributes {margin: 15px 0;font-size: 15px;}")
                .append(".product-attribute {margin-bottom: 8px;display: flex;}")
                .append(".attribute-label {font-weight: 600;color: #776733;width: 90px;flex-shrink: 0;}")
                .append(".waitlist-status {background-color: #f8f7f3;border: 1px solid #e8e5de;padding: 12px 20px;border-radius: 6px;margin: 20px 0;text-align: center;}")
                .append(".status-label {font-weight: 600;color: #776733;}")
                .append(".footer {background-color: #000000;color: #ffffff;text-align: center;padding: 25px;font-size: 12px;border-top: 1px solid #333333;}")
                .append(".footer a {color: #d4c9a8;text-decoration: none;}")
                .append("@media only screen and (max-width: 480px) {")
                .append(".container {border-radius: 0;margin: 0;}")
                .append(".content {padding: 25px 20px;}")
                .append(".product-image-container {height: 180px;}")
                .append(".product-title {font-size: 18px;}}")
                .append("</style>")
                .append("</head>")
                .append("<body>")
                .append("<div class=\"container\">")
                .append("<div class=\"header\">")
                .append("<h1>You're On The Waitlist!</h1>")
                .append("</div>")
                .append("<div class=\"content\">")
                .append("<h2>Dear ").append(firstName).append(" ").append(lastName).append(",</h2>")
                .append("<p>Thank you for joining the waitlist for this exquisite piece. We'll notify you the moment it becomes available.</p>")
                .append("<div class=\"product-card\">")
                .append("<div class=\"product-image-container\">")
                .append("<img src=\"").append(productImage).append("\" alt=\"").append(productName).append("\" class=\"product-image\">")
                .append("</div>")
                .append("<div class=\"product-details\">")
                .append("<h3 class=\"product-title\">").append(productName).append("</h3>")
                .append("<div class=\"product-attributes\">")
                .append("<div class=\"product-attribute\">")
                .append("<span class=\"attribute-label\">Material:</span>")
                .append("<span>").append(material).append("</span>")
                .append("</div>")
                .append("<div class=\"product-attribute\">")
                .append("<span class=\"attribute-label\">Size:</span>")
                .append("<span>").append(size).append("</span>")
                .append("</div>")
                .append("</div>")
                .append("</div>")
                .append("</div>")
                .append("<p>We appreciate your patience and will contact you as soon as this item is back in stock. In the meantime, feel free to browse our <a href=\"").append(collectionUrl).append("\" style=\"color: #776733; text-decoration: underline;\">current collection</a>.</p>")
                .append("<p>Warm regards,<br>The VEMOS Team</p>")
                .append("</div>")
                .append("<div class=\"footer\">")
                .append("&copy; 2023 VEMOS Jewelry. All rights reserved.<br>")
                .append("123 Jewel Street, Luxe City, LC 10001<br>")
                .append("<a href=\"#\">Unsubscribe</a> | <a href=\"#\">Privacy Policy</a>")
                .append("</div>")
                .append("</div>")
                .append("</body>")
                .append("</html>");

        return emailBuilder.toString();
    }

}

