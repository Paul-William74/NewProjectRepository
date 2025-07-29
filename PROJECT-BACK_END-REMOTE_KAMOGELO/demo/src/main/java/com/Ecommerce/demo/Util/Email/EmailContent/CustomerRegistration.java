package com.Ecommerce.demo.Util.Email.EmailContent;

public class CustomerRegistration {

    public static String getContent(String firstName, String lastName, int points) {

        String emailBuilder = STR."""
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Your Exclusive Welcome</title>
    <style>
        body, html {
            margin: 0;
            padding: 0;
            font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif;
            line-height: 1.5;
            color: #000000;
            background-color: #ffffff;
        }
        .email-container {
            max-width: 600px;
            margin: 0 auto;
            background: #ffffff;
        }
        .header {
            padding: 40px 20px 20px;
            text-align: center;
            border-bottom: 1px solid #f0f0f0;
        }
        .logo {
            max-width: 160px;
            height: auto;
        }
        .content {
            padding: 0 30px 30px;
        }
        h1 {
            font-size: 28px;
            font-weight: 300;
            letter-spacing: -0.5px;
            margin: 0 0 25px 0;
            text-align: center;
        }
        p {
            font-size: 16px;
            margin: 0 0 25px 0;
            color: #333333;
        }
        .points-card {
            background: #fafafa;
            border: 1px solid #eeeeee;
            border-radius: 8px;
            padding: 30px;
            text-align: center;
            margin: 40px 0;
            position: relative;
        }
        .points-card:before {
            content: "";
            position: absolute;
            top: -1px;
            left: 0;
            right: 0;
            height: 4px;
            background: #000000;
            border-radius: 8px 8px 0 0;
        }
        .points-label {
            font-size: 14px;
            text-transform: uppercase;
            letter-spacing: 1px;
            margin-bottom: 10px;
            color: #666666;
        }
        .points-number {
            font-size: 52px;
            font-weight: 700;
            margin: 15px 0;
            color: #000000;
            line-height: 1;
        }
        .points-subtext {
            font-size: 14px;
            color: #666666;
        }
        .button-container {
            text-align: center;
            margin: 35px 0;
        }
        .button {
            display: inline-block;
            padding: 15px 40px;
            background-color: #000000;
            color: #ffffff !important;
            text-decoration: none;
            font-weight: 500;
            border-radius: 30px;
            font-size: 16px;
            transition: all 0.3s ease;
        }
        .footer {
            padding: 30px 20px;
            text-align: center;
            font-size: 12px;
            color: #999999;
            border-top: 1px solid #f0f0f0;
            line-height: 1.6;
        }
        .social-links {
            margin: 20px 0;
        }
        .social-links a {
            margin: 0 10px;
        }
        @media only screen and (max-width: 480px) {
            .header { padding: 30px 15px 15px; }
            .content { padding: 0 20px 20px; }
            h1 { font-size: 24px; margin-bottom: 20px; }
            .points-card { padding: 25px 15px; margin: 30px 0; }
            .points-number { font-size: 42px; }
            .button { padding: 12px 30px; font-size: 15px; }
        }
    </style>
</head>
<body>
    <div class="email-container">
        <div class="header">
            <img src="https://tse1.mm.bing.net/th/id/OIP.SEvaiWifAToaAnPJj3VzNwHaBu?rs=1&pid=ImgDetMain&o=7&rm=3" alt="Company" class="logo">
        </div>
       \s
        <div class="content">
            <h1>Welcome Aboard, \{firstName}</h1>
           \s
            <p>Dear \{firstName} \{lastName},</p>
           \s
            <p>We're delighted to have you join our community. To celebrate your arrival, please accept this gift:</p>
           \s
            <div class="points-card">
                <div class="points-label">Your Welcome Bonus</div>
                <div class="points-number">\{points}</div>
                <div class="points-subtext">Loyalty Points</div>
            </div>
           \s
            <p>Your points are ready to use immediately. Apply them at checkout to save on your first purchase.</p>
           \s
            <div class="button-container">
                <a href="http://localhost:5173/" class="button">Redeem Your Points</a>
            </div>
           \s
            <p>For any questions, simply reply to this email. We're here to help.</p>
           \s
            <p>Welcome to the family,<br><strong>The Team</strong></p>
        </div>
       \s
        <div class="footer">
            <div class="social-links">
                <a href="https://facebook.com/example" style="color: #999999;">Facebook</a>
                <a href="https://instagram.com/example" style="color: #999999;">Instagram</a>
                <a href="https://twitter.com/example" style="color: #999999;">Twitter</a>
            </div>
            <p>&copy; 2023 Company Name. All rights reserved.</p>
            <p>
                <a href="https://example.com/privacy" style="color: #999999; text-decoration: none;">Privacy Policy</a> |\s
                <a href="https://example.com/terms" style="color: #999999; text-decoration: none;">Terms of Service</a>
            </p>
            <p style="font-size:11px; color:#ccc;">If you didn't request this email, please <a href="https://example.com/unsubscribe" style="color:#ccc;">unsubscribe</a>.</p>
        </div>
    </div>
</body>
</html>""";

        return emailBuilder;
    }
}
